package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.DragAcceptFacade;
import yuriy.weiss.javafx.training.controller.DragDropFacade;
import yuriy.weiss.javafx.training.controller.DragSource;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.util.ColorNames;
import yuriy.weiss.javafx.training.util.JsonUtils;

import static javafx.scene.input.DataFormat.PLAIN_TEXT;

@RequiredArgsConstructor
public class ShipView implements ElementView, GridCellView {

    private final DragAcceptFacade dragAcceptFacade;
    private final DragDropFacade dragDropFacade;
    private final Pane cellPane;
    @Getter
    private final Ship ship;

    private ImageView imageView;

    @Override
    public Pane getCellPane() {
        return cellPane;
    }

    public void createView() {
        ImageView shipImageView = buildImageView();
        this.imageView = shipImageView;
        cellPane.getChildren().add( shipImageView );
        for ( int i = 0; i < ship.getPiratesOnBoard().size(); i++ ) {
            Pirate pirate = ship.getPiratesOnBoard().get( i );
            new PirateView( cellPane, pirate, i ).createView();
        }
    }

    @Override
    public void updateView() {
        cellPane.getChildren().clear();
        createView();
    }

    private ImageView buildImageView() {
        ImageView shipImageView = new ImageView( getImageName() );
        shipImageView.setUserData( ship );
        shipImageView.setLayoutX( 1 );
        shipImageView.setLayoutY( 1 );

        // ship drag
        shipImageView.setOnMouseDragged( e -> e.setDragDetect( true ) );
        shipImageView.setOnDragDetected( e -> {
            if ( !ship.getTeam().equals( Game.getInstance().getActiveTeam() )
                    || ship.getPiratesOnBoard().isEmpty() ) {
                e.consume();
                return;
            }
            Dragboard dragboard = shipImageView.startDragAndDrop( TransferMode.MOVE );
            ClipboardContent content = new ClipboardContent();
            content.put( PLAIN_TEXT, DragSource.SHIP.getPrefix() + JsonUtils.objectToJsonString( ship ) );
            dragboard.setContent( content );
            dragboard.setDragView( new Image( "ship_drag.png" ) );
            e.consume();
        } );

        // pirate drop
        shipImageView.setOnDragOver( e -> {
            boolean accept = dragAcceptFacade.shipCanAccept( ship, e.getDragboard().getString() );
            if ( accept ) {
                e.acceptTransferModes( TransferMode.MOVE );
            }
            e.consume();
        } );
        shipImageView.setOnDragDropped( e -> {
            // TODO create step and finish move
            dragDropFacade.dropToShip( ship, e.getDragboard().getString() );
        } );
        return shipImageView;
    }

    private String getImageName() {
        String teamColor = ColorNames.getColorName( ship.getTeam().getColor() );
        String shipImageName = "ship_" + teamColor;
        return shipImageName + ".png";
    }
}
