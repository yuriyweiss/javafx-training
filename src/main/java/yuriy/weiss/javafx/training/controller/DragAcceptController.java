package yuriy.weiss.javafx.training.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import yuriy.weiss.javafx.training.model.Board;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.util.JsonUtils;

import static yuriy.weiss.javafx.training.model.cell.CellType.WATER;

@Slf4j
public class DragAcceptController {

    public boolean cellCanAccept( Cell cell, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.PIRATE ) {
            return cellCanAcceptPirate( cell, jsonString );
        } else if ( dragSource == DragSource.SHIP ) {
            // TODO check if ship can move by water
            return false;
        } else if ( dragSource == DragSource.COIN ) {
            // TODO check if pirate can drop coins to this cell
            return false;
        }
        return false;
    }

    private boolean cellCanAcceptPirate( Cell cell, String jsonString ) {
        log.trace( "got json: {}", jsonString );
        Pirate pirate = JsonUtils.jsonStringToObject( jsonString, Pirate.class );
        log.trace( "Pirate deserialized: {}", pirate );
        pirate.attachRealTeam( Game.getInstance().getCurrentBoard().getTeams() );
        int pirateX = pirate.getPosition().getX();
        int pirateY = pirate.getPosition().getY();
        // from ship no diagonal move
        if ( pirate.isOnShip() ) {
            return cellCanAcceptPirateFromShip( cell, pirateX, pirateY );
        } else if ( isPirateInWater( pirateX, pirateY ) ) {
            return cellCanAcceptPirateFromWater( cell, pirateX, pirateY );
        } else {
            return cellCanAcceptPirateFromGround( cell, pirateX, pirateY );
        }
    }

    private boolean cellCanAcceptPirateFromShip( Cell cell, int pirateX, int pirateY ) {
        int cellX = cell.getPosition().getX();
        int cellY = cell.getPosition().getY();
        return ( cellX == pirateX && Math.abs( cellY - pirateY ) == 1 )
                || ( cellY == pirateY && Math.abs( cellX - pirateX ) == 1 );
    }

    private boolean isPirateInWater( int pirateX, int pirateY ) {
        Board currentBoard = Game.getInstance().getCurrentBoard();
        return currentBoard.getCells()[pirateY][pirateX].getCellType() == WATER;
    }

    private boolean cellCanAcceptPirateFromWater( Cell cell, int pirateX, int pirateY ) {
        if ( cell.getCellType() != WATER ) {
            return false;
        }
        return cellCanAcceptPirateFromAround( cell, pirateX, pirateY );
    }

    private boolean cellCanAcceptPirateFromGround( Cell cell, int pirateX, int pirateY ) {
        if ( cell.getCellType() == WATER ) {
            return false;
        }
        return cellCanAcceptPirateFromAround( cell, pirateX, pirateY );
    }

    private static boolean cellCanAcceptPirateFromAround( Cell cell, int pirateX, int pirateY ) {
        int cellX = cell.getPosition().getX();
        int cellY = cell.getPosition().getY();
        return ( cellX == pirateX && Math.abs( cellY - pirateY ) == 1 )
                || ( cellY == pirateY && Math.abs( cellX - pirateX ) == 1 )
                || ( Math.abs( cellX - pirateX ) == 1 && Math.abs( cellY - pirateY ) == 1 );
    }
}
