package yuriy.weiss.javafx.training.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import yuriy.weiss.javafx.training.model.Coin;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.util.JsonUtils;

@Slf4j
public class DragAcceptControllerPirate {

    public boolean pirateCanAccept( Pirate pirate, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.COIN_ON_CELL ) {
            return pirateCanAcceptCoin( pirate, jsonString );
        } else {
            return false;
        }
    }

    private boolean pirateCanAcceptCoin( Pirate pirate, String jsonString ) {
        log.info( "got json: {}", jsonString );
        String[] jsonStrings = jsonString.split( JsonUtils.DRAG_INFO_SEPARATOR );
        Coin coin = JsonUtils.jsonStringToObject( jsonStrings[0], Coin.class );
        Cell cell = JsonUtils.jsonStringToObject( jsonStrings[1], Cell.class );
        log.info( "coin and cell deserialized: {} {}", coin, cell );
        log.info( "pirate: {}", pirate );
        return !pirate.isOnShip()
                && pirate.getPosition().equals( cell.getPosition() )
                && pirate.getCoin() == null;
    }
}
