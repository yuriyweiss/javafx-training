package yuriy.weiss.javafx.training.view.element;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.DragSource;
import yuriy.weiss.javafx.training.model.Coin;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.util.JsonUtils;

import java.util.Objects;

import static javafx.scene.input.DataFormat.PLAIN_TEXT;

@RequiredArgsConstructor
public class CoinOnCellView implements ElementView {

    private final Pane cellPane;
    private final Cell cell;
    private final Coin coin;
    private final int position;

    private ImageView imageView;

    @Override
    public void createView() {
        final ImageView coinImageView = buildImageView();
        this.imageView = coinImageView;
        cellPane.getChildren().add( coinImageView );
    }

    @Override
    public void updateView() {
        cellPane.getChildren().removeIf( e -> Objects.equals( e.getUserData(), coin ) );
        createView();
    }

    private ImageView buildImageView() {
        final ImageView coinImageView = new ImageView( "coin.png" );
        coinImageView.setUserData( coin );
        int freeSize = 64 - 16 - 1;
        int shift = freeSize / cell.getCoins().size();
        coinImageView.setLayoutX( position * shift + 1.0 );
        coinImageView.setLayoutY( 2 );
        coinImageView.setOnMouseDragged( e -> e.setDragDetect( true ) );
        coinImageView.setOnDragDetected( e -> {
            if ( cell.getPirates().isEmpty() ) {
                e.consume();
                return;
            }
            Dragboard dragboard = coinImageView.startDragAndDrop( TransferMode.MOVE );
            ClipboardContent content = new ClipboardContent();
            content.put( PLAIN_TEXT,
                    DragSource.COIN_ON_CELL.getPrefix() +
                            JsonUtils.objectToJsonString( coin ) + JsonUtils.DRAG_INFO_SEPARATOR +
                            JsonUtils.objectToJsonString( cell ) );
            dragboard.setContent( content );
            dragboard.setDragView( new Image( "coin_drag.png" ) );
            e.consume();
        } );
        return coinImageView;
    }
}
