package yuriy.weiss.javafx.training.view.element;

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
import yuriy.weiss.javafx.training.view.element.AbstractElementView;

@RequiredArgsConstructor
public class CellView extends AbstractElementView implements GridCellView {

    private final BoardPaneController controller;
    private final DragAcceptController dragAcceptController;
    private final DragDropController dragDropController;
    private final Pane cellPane;
    private final Cell cell;

    private ImageView imageView;

    public void createView() {
        ImageView cellImageView = buildImageView( cell );
        this.imageView = cellImageView;
        cellPane.getChildren().add( cellImageView );
    }

    @Override
    public void updateView() {
        cellPane.getChildren().clear();
        createView();
    }

    private ImageView buildImageView( final Cell cell ) {
        String imageFile = getImageFile( cell );
        ImageView cellImageView = new ImageView( imageFile );
        cellImageView.setUserData( cell );
        cellImageView.setLayoutX( 1 );
        cellImageView.setLayoutY( 1 );
        /*
        cellImageView.setOnMouseClicked( e -> {
            if ( !isFocused() ) {
                this.setFocused( true );
                controller.fireFocusChanged( this, FocusedType.CELL, cell );
            }
        } );
        */
        cellImageView.setOnDragOver( e -> {
            boolean accept = dragAcceptController.cellCanAccept( cell, e.getDragboard().getString() );
            if ( accept ) {
                e.acceptTransferModes( TransferMode.MOVE );
            }
            e.consume();
        } );
        cellImageView.setOnDragDropped( e -> {
            // Move move = dragDropController.prepareMove(cell, e.getDragboard().getString());
            dragDropController.dropToCell( cell, e.getDragboard().getString() );

        } );
        return cellImageView;
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
