package yuriy.weiss.javafx.training.controller;

import org.apache.commons.lang3.tuple.Pair;
import yuriy.weiss.javafx.training.model.Coin;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.util.JsonUtils;

public class DragDropControllerPirate {

    public void dropToPirate( Pirate pirate, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.COIN_ON_CELL ) {
            Pair<Coin, Cell> coinAndCell = JsonUtils.jsonStringToObject( jsonString, Pair.class );
            Coin stubCoin = coinAndCell.getLeft();
            Cell stubCell = coinAndCell.getRight();
            // TODO remove coin from cell
            // TODO put coin to pirate
            // TODO update cell
        }
    }
}
