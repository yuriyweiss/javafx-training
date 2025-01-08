package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.ImageView;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.DragAcceptFacade;
import yuriy.weiss.javafx.training.controller.DragDropFacade;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;

@RequiredArgsConstructor
public class CellView implements ElementView, GridCellView {

    private final DragAcceptFacade dragAcceptFacade;
    private final DragDropFacade dragDropFacade;
    private final Pane cellPane;
    @Getter
    private final Cell cell;

    private ImageView imageView;

    @Override
    public Pane getCellPane() {
        return cellPane;
    }

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
        // all types drop
        cellImageView.setOnDragOver( e -> {
            boolean accept = dragAcceptFacade.cellCanAccept( cell, e.getDragboard().getString() );
            if ( accept ) {
                e.acceptTransferModes( TransferMode.MOVE );
            }
            e.consume();
        } );
        cellImageView.setOnDragDropped( e -> {
            // TODO build steps chain, maybe user should select actions on next cells before finishing move
            dragDropFacade.dropToCell( cell, e.getDragboard().getString() );
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
