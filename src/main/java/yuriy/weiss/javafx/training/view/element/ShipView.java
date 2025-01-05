package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.controller.FocusedType;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.util.ColorNames;

@RequiredArgsConstructor
public class ShipView extends AbstractElementView implements GridCellView {

    private final BoardPaneController controller;
    private final Pane cellPane;
    private final Ship ship;

    private ImageView imageView;

    public void createView() {
        ImageView shipImageView = buildImageView();
        this.imageView = shipImageView;
        cellPane.getChildren().add( shipImageView );
        for ( int i = 0; i < ship.getPiratesOnBoard().size(); i++ ) {
            Pirate pirate = ship.getPiratesOnBoard().get( i );
            new PirateView( controller, cellPane, pirate, i ).createView();
        }
    }

    @Override
    public void updateView() {
        cellPane.getChildren().clear();
        createView();
    }

    private ImageView buildImageView() {
        ImageView shipView = new ImageView( getImageName() );
        shipView.setUserData( ship );
        shipView.setLayoutX( 1 );
        shipView.setLayoutY( 1 );
        shipView.setOnMouseClicked( e -> {
            if ( !isFocused() ) {
                setFocused( true );
                controller.fireFocusChanged( this, FocusedType.SHIP, ship );
            }
        } );
        return shipView;
    }

    private String getImageName() {
        String teamColor = ColorNames.getColorName( ship.getTeam().getColor() );
        String shipImageName = "ship_" + teamColor;
        if ( isFocused() ) {
            shipImageName += "_focused";
        }
        return shipImageName + ".png";
    }
}
