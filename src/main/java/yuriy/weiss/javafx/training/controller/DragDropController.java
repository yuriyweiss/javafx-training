package yuriy.weiss.javafx.training.controller;

import org.apache.commons.lang3.tuple.Pair;
import yuriy.weiss.javafx.training.model.Board;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Team;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.util.JsonUtils;

public class DragDropController {

    private Board board;

    public void setBoard( Board board ) {
        this.board = board;
    }

    public void dropToCell( Cell cell, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.PIRATE ) {
            Pirate stub = JsonUtils.jsonStringToObject( jsonString, Pirate.class );
            Team team = stub.getRealTeam( board.getTeams() );
            Pirate pirate = team.getPirate( stub.getId() );
            int prevX = pirate.getPosition().getX();
            int prevY = pirate.getPosition().getY();
            // TODO remove pirate from prev cell or ship
            // TODO open cell if closed
            // TODO add pirate to cell
            // TODO update drag source gridCellView
            // TODO update drag target gridCellView
        } else if ( dragSource == DragSource.SHIP ) {
            // TODO move ship to new cell
        } else if ( dragSource == DragSource.COIN ) {
            // TODO place coin to cell coins stack
        }
    }
}
