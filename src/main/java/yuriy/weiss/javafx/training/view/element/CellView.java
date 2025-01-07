package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.DragAcceptController;
import yuriy.weiss.javafx.training.controller.DragDropController;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;

@RequiredArgsConstructor
public class CellView implements ElementView, GridCellView {

    private final DragAcceptController dragAcceptController;
    private final DragDropController dragDropController;
    private final Pane cellPane;
    @Getter
    private final Cell cell;

    private ImageView imageView;

    public void createView() {
        ImageView cellImageView = buildImageView( cell );
        this.imageView = cellImageView;
        cellPane.getChildren().add( cellImageView );
        for ( int i = 0; i < cell.getPirates().size(); i++ ) {
            Pirate pirate = cell.getPirates().get( i );
            new PirateView( cellPane, pirate, i ).createView();
        }
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
        return imageName + ".png";
    }
}
