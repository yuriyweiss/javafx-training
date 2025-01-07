package yuriy.weiss.javafx.training.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import yuriy.weiss.javafx.training.model.Board;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.util.JsonUtils;

import static yuriy.weiss.javafx.training.model.cell.CellType.WATER;

@Slf4j
public class DragAcceptControllerShip {

    public boolean shipCanAccept( Ship ship, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.PIRATE ) {
            return shipCanAcceptPirate( ship, jsonString );
        }
        return false;
    }

    private boolean shipCanAcceptPirate( Ship ship, String jsonString ) {
        Pirate pirate = JsonUtils.jsonStringToObject( jsonString, Pirate.class );
        pirate.attachRealTeam( Game.getInstance().getCurrentBoard().getTeams() );
        int pirateX = pirate.getPosition().getX();
        int pirateY = pirate.getPosition().getY();
        boolean result = false;
        if ( pirate.getTeam().equals( ship.getTeam() ) ) {
            if ( isPirateInWater( pirateX, pirateY ) ) {
                result = shipCanAcceptPirateFromAround( ship, pirateX, pirateY );
            } else {
                result = shipCanAcceptPirateFromGround( ship, pirateX, pirateY );
            }
        }
        return result;
    }

    private boolean isPirateInWater( int pirateX, int pirateY ) {
        Board currentBoard = Game.getInstance().getCurrentBoard();
        return currentBoard.getCells()[pirateY][pirateX].getCellType() == WATER;
    }

    private static boolean shipCanAcceptPirateFromAround( Ship ship, int pirateX, int pirateY ) {
        int shipX = ship.getPosition().getX();
        int shipY = ship.getPosition().getY();
        return ( shipX == pirateX && Math.abs( shipY - pirateY ) == 1 )
                || ( shipY == pirateY && Math.abs( shipX - pirateX ) == 1 )
                || ( Math.abs( shipX - pirateX ) == 1 && Math.abs( shipY - pirateY ) == 1 );
    }

    private boolean shipCanAcceptPirateFromGround( Ship ship, int pirateX, int pirateY ) {
        int shipX = ship.getPosition().getX();
        int shipY = ship.getPosition().getY();
        return ( shipX == pirateX && Math.abs( shipY - pirateY ) == 1 )
                || ( shipY == pirateY && Math.abs( shipX - pirateX ) == 1 );
    }
}
