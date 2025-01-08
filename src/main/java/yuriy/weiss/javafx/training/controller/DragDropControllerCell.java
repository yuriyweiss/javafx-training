package yuriy.weiss.javafx.training.controller;

import org.apache.commons.lang3.tuple.Pair;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.Team;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.util.JsonUtils;
import yuriy.weiss.javafx.training.util.TeamUtils;
import yuriy.weiss.javafx.training.view.BoardPane;
import yuriy.weiss.javafx.training.view.element.CellView;
import yuriy.weiss.javafx.training.view.element.ElementView;
import yuriy.weiss.javafx.training.view.element.GridCellView;
import yuriy.weiss.javafx.training.view.element.ShipView;

public class DragDropControllerCell {

    public void dropToCell( Cell cell, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.PIRATE ) {
            Pirate stub = JsonUtils.jsonStringToObject( jsonString, Pirate.class );
            Team team = TeamUtils.getRealTeam( Game.getInstance().getCurrentBoard().getTeams(), stub.getColor() );
            Pirate pirate = team.getPirate( stub.getId() );
            removePirateFromSourceView( pirate );
            if ( cell.isClosed() ) {
                cell.setClosed( false );
            }
            addPirateToTargetCellView( cell, pirate );
            pirate.updatePosition( cell.getPosition() );
        } else if ( dragSource == DragSource.SHIP ) {
            // TODO move ship to new cell
            // ship can be moved only near its team side of board
        } else if ( dragSource == DragSource.COIN ) {
            // TODO place coin to cell coins stack
        }
    }

    private void removePirateFromSourceView( Pirate pirate ) {
        int sourceX = pirate.getPosition().getX();
        int sourceY = pirate.getPosition().getY();
        GridCellView prevCellView = BoardPane.getInstance().getGridView( sourceX, sourceY );
        removePirateFromSource( pirate, prevCellView );
        ( ( ElementView ) prevCellView ).updateView();
    }

    private void removePirateFromSource( Pirate pirate, GridCellView sourceCellView ) {
        if ( sourceCellView instanceof ShipView shipView ) {
            Ship ship = shipView.getShip();
            ship.removePirate( pirate );
        } else if ( sourceCellView instanceof CellView cellView ) {
            Cell cell = cellView.getCell();
            cell.getPirates().remove( pirate );
        }
    }

    private static void addPirateToTargetCellView( Cell cell, Pirate pirate ) {
        cell.getPirates().add( pirate );
        int targetX = cell.getPosition().getX();
        int targetY = cell.getPosition().getY();
        CellView targetCellView = ( CellView ) BoardPane.getInstance().getGridView( targetX, targetY );
        targetCellView.updateView();
    }
}
