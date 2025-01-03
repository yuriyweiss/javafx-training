package yuriy.weiss.javafx.training.view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.controller.FocusedType;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.util.ColorNames;

import java.util.Objects;

@RequiredArgsConstructor
public class PirateView extends BoardElementView {

    private final BoardPaneController controller;
    private final Pane cellPane;
    private final Pirate pirate;
    private final int position;

    public void createView() {
        final ImageView pirateView = buildImageView();
        cellPane.getChildren().add( pirateView );
    }

    private ImageView buildImageView() {
        final ImageView pirateView = new ImageView( getImageName() );
        pirateView.setUserData( pirate );
        pirateView.setLayoutX( position * 16 + 1.0 );
        pirateView.setLayoutY( 33 );
        pirateView.setOnMouseClicked( e -> {
            if (!isFocused()) {
                setFocused( true );
                controller.fireFocusChanged( this, FocusedType.PIRATE, pirate );
            }
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
        cellPane.getChildren().removeIf( e -> Objects.equals( e.getUserData(), pirate) );
        createView();
    }
}
