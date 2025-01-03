package yuriy.weiss.javafx.training.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.controller.FocusedType;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;

@RequiredArgsConstructor
public class CellView extends BoardElementView {

    private final BoardPaneController controller;
    private final Pane cellPane;
    private final Cell cell;

    public void createView() {
        ImageView imageView = buildCellImageView( cell );
        cellPane.getChildren().add( imageView );
    }

    @Override
    public void updateView() {
        cellPane.getChildren().clear();
        createView();
    }

    private ImageView buildCellImageView( Cell cell ) {
        String imageFile = getImageFile( cell );
        ImageView imageView = new ImageView( imageFile );
        imageView.setUserData( cell );
        imageView.setLayoutX( 1 );
        imageView.setLayoutY( 1 );
        imageView.setOnMouseClicked( e -> {
            if ( !isFocused() ) {
                this.setFocused( true );
                controller.fireFocusChanged( this, FocusedType.CELL, cell );
            }
        } );
        return imageView;
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
