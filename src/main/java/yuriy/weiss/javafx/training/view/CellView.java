package yuriy.weiss.javafx.training.view;

import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.controller.DragAcceptController;
import yuriy.weiss.javafx.training.controller.DragDropController;
import yuriy.weiss.javafx.training.controller.FocusedType;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;

@RequiredArgsConstructor
public class CellView extends BoardElementView {

    private final BoardPaneController controller;
    private final DragAcceptController dragAcceptController;
    private final DragDropController dragDropController;
    private final Pane cellPane;
    private final Cell cell;

    private ImageView imageView;

    public void createView() {
        ImageView cellView = buildCellImageView( cell );
        this.imageView = cellView;
        cellPane.getChildren().add( cellView );
    }

    @Override
    public void updateView() {
        cellPane.getChildren().clear();
        createView();
    }

    private ImageView buildCellImageView( final Cell cell ) {
        String imageFile = getImageFile( cell );
        ImageView cellView = new ImageView( imageFile );
        cellView.setUserData( cell );
        cellView.setLayoutX( 1 );
        cellView.setLayoutY( 1 );
        cellView.setOnMouseClicked( e -> {
            if ( !isFocused() ) {
                this.setFocused( true );
                controller.fireFocusChanged( this, FocusedType.CELL, cell );
            }
        } );
        cellView.setOnDragOver( e -> {
            boolean accept = dragAcceptController.cellCanAccept( cell, e.getDragboard().getString() );
            if ( accept ) {
                e.acceptTransferModes( TransferMode.MOVE );
            }
            e.consume();
        } );
        cellView.setOnDragDropped( e -> {
            // Move move = dragDropController.prepareMove(cell, e.getDragboard().getString());
            dragDropController.dropToCell( cell, e.getDragboard().getString() );

        } );
        return cellView;
    }

    private String getImageFile( Cell cell ) {
        String imageName = CellType.WATER.getImageName();
        if ( cell.getCellType() != CellType.WATER ) {
            if ( cell.isClosed() ) {
                imageName = "closed_ground";
            } else {
                imageName = cell.getCellType().getImageName();
            }
        }
        if ( isFocused() ) {
            imageName += "_focused";
        }
        return imageName + ".png";
    }
}
