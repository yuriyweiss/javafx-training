package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.DragAcceptFacade;
import yuriy.weiss.javafx.training.controller.DragDropFacade;
import yuriy.weiss.javafx.training.controller.DragSource;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.util.ColorNames;
import yuriy.weiss.javafx.training.util.JsonUtils;

import java.util.Objects;

import static javafx.scene.input.DataFormat.PLAIN_TEXT;

@RequiredArgsConstructor
public class PirateView implements ElementView {

    private final DragAcceptFacade dragAcceptFacade;
    private final DragDropFacade dragDropFacade;
    private final Pane cellPane;
    private final Pirate pirate;
    private final int position;

    private ImageView imageView;

    public void createView() {
        final ImageView pirateImageView = buildImageView();
        this.imageView = pirateImageView;
        cellPane.getChildren().add( pirateImageView );
        // TODO add coin view
    }

    @Override
    public void updateView() {
        cellPane.getChildren().removeIf( e -> Objects.equals( e.getUserData(), pirate ) );
        // TODO remove coin
        createView();
    }

    private ImageView buildImageView() {
        final ImageView pirateImageView = new ImageView( getImageName() );
        pirateImageView.setUserData( pirate );
        pirateImageView.setLayoutX( position * 16 + 1.0 );
        pirateImageView.setLayoutY( 33 );
        // pirate drag
        pirateImageView.setOnMouseDragged( e -> e.setDragDetect( true ) );
        pirateImageView.setOnDragDetected( e -> {
            if ( !pirate.getTeam().equals( Game.getInstance().getActiveTeam() ) ) {
                e.consume();
                return;
            }
            Dragboard dragboard = pirateImageView.startDragAndDrop( TransferMode.MOVE );
            ClipboardContent content = new ClipboardContent();
            content.put( PLAIN_TEXT, DragSource.PIRATE.getPrefix() + JsonUtils.objectToJsonString( pirate ) );
            dragboard.setContent( content );
            dragboard.setDragView( new Image( "pirate_drag.png" ) );
            e.consume();
        } );
        // coin drop
        pirateImageView.setOnDragOver( e -> {
            boolean accept = dragAcceptFacade.pirateCanAccept( pirate, e.getDragboard().getString() );
            if ( accept ) {
                e.acceptTransferModes( TransferMode.MOVE );
            }
            e.consume();
        } );
        // TODO add drop event
        return pirateImageView;
    }

    private String getImageName() {
        String teamColor = ColorNames.getColorName( pirate.getTeam().getColor() );
        String pirateImageName = "pirate_" + teamColor;
        return pirateImageName + ".png";
    }
}
