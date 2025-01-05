package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.controller.DragSource;
import yuriy.weiss.javafx.training.controller.FocusedType;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.util.ColorNames;
import yuriy.weiss.javafx.training.util.JsonUtils;

import java.util.Objects;

import static javafx.scene.input.DataFormat.PLAIN_TEXT;

@RequiredArgsConstructor
public class PirateView extends AbstractElementView {

    private final BoardPaneController controller;
    private final Pane cellPane;
    private final Pirate pirate;
    private final int position;

    private ImageView imageView;

    public void createView() {
        final ImageView pirateImageView = buildImageView();
        this.imageView = pirateImageView;
        cellPane.getChildren().add( pirateImageView );
    }

    @Override
    public void updateView() {
        cellPane.getChildren().removeIf( e -> Objects.equals( e.getUserData(), pirate ) );
        createView();
    }

    private ImageView buildImageView() {
        final ImageView pirateImageView = new ImageView( getImageName() );
        pirateImageView.setUserData( pirate );
        pirateImageView.setLayoutX( position * 16 + 1.0 );
        pirateImageView.setLayoutY( 33 );
        pirateImageView.setOnMouseClicked( e -> {
            if ( !isFocused() ) {
                setFocused( true );
                controller.fireFocusChanged( this, FocusedType.PIRATE, pirate );
            }
        } );
        pirateImageView.setOnMouseDragged( e -> {
            if ( isFocused() ) {
                e.setDragDetect( true );
            }
        } );
        pirateImageView.setOnDragDetected( e -> {
            Dragboard dragboard = pirateImageView.startDragAndDrop( TransferMode.MOVE );
            ClipboardContent content = new ClipboardContent();
            content.put( PLAIN_TEXT, DragSource.PIRATE.getPrefix() + JsonUtils.objectToJsonString( pirate ) );
            dragboard.setContent( content );
            dragboard.setDragView( new Image( "pirate_drag.png" ) );
            e.consume();
        } );
        return pirateImageView;
    }

    private String getImageName() {
        String teamColor = ColorNames.getColorName( pirate.getTeam().getColor() );
        String pirateImageName = "pirate_" + teamColor;
        if ( isFocused() ) {
            pirateImageName += "_focused";
        }
        return pirateImageName + ".png";
    }
}
