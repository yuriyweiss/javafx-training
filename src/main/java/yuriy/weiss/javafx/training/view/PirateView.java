package yuriy.weiss.javafx.training.view;

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
public class PirateView extends BoardElementView {

    private final BoardPaneController controller;
    private final Pane cellPane;
    private final Pirate pirate;
    private final int position;

    private ImageView imageView;

    public void createView() {
        final ImageView pirateView = buildImageView();
        this.imageView = pirateView;
        cellPane.getChildren().add( pirateView );
    }

    private ImageView buildImageView() {
        final ImageView pirateView = new ImageView( getImageName() );
        pirateView.setUserData( pirate );
        pirateView.setLayoutX( position * 16 + 1.0 );
        pirateView.setLayoutY( 33 );
        pirateView.setOnMouseClicked( e -> {
            if ( !isFocused() ) {
                setFocused( true );
                controller.fireFocusChanged( this, FocusedType.PIRATE, pirate );
            }
        } );
        pirateView.setOnMouseDragged( e -> {
            if ( isFocused() ) {
                e.setDragDetect( true );
            }
        } );
        pirateView.setOnDragDetected( e -> {
            Dragboard dragboard = pirateView.startDragAndDrop( TransferMode.MOVE );
            ClipboardContent content = new ClipboardContent();
            content.put( PLAIN_TEXT, DragSource.PIRATE.getPrefix() + JsonUtils.objectToJsonString( pirate ) );
            dragboard.setContent( content );
            dragboard.setDragView( new Image( "pirate_drag.png" ) );
            e.consume();
        } );
        return pirateView;
    }

    private String getImageName() {
        String teamColor = ColorNames.getColorName( pirate.getTeam().getColor() );
        String pirateImageName = "pirate_" + teamColor;
        if ( isFocused() ) {
            pirateImageName += "_focused";
        }
        return pirateImageName + ".png";
    }

    @Override
    public void updateView() {
        cellPane.getChildren().removeIf( e -> Objects.equals( e.getUserData(), pirate ) );
        createView();
    }
}
