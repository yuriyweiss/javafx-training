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
import yuriy.weiss.javafx.training.view.element.ShipView;

public class DragDropControllerShip {

    public void dropToShip( Ship ship, String sourceString ) {
        Pair<DragSource, String> dragInfo = DragSource.splitDragInfo( sourceString );
        DragSource dragSource = dragInfo.getLeft();
        String jsonString = dragInfo.getRight();
        if ( dragSource == DragSource.PIRATE ) {
            Pirate stub = JsonUtils.jsonStringToObject( jsonString, Pirate.class );
            Team team = TeamUtils.getRealTeam( Game.getInstance().getCurrentBoard().getTeams(), stub.getColor() );
            Pirate pirate = team.getPirate( stub.getId() );
            removePirateFromCellView( pirate );
            addPirateToTargetShipView( ship, pirate );
            pirate.updatePosition( ship.getPosition() );
        }
    }

    private void removePirateFromCellView( Pirate pirate ) {
        int sourceX = pirate.getPosition().getX();
        int sourceY = pirate.getPosition().getY();
        CellView prevCellView = ( CellView ) BoardPane.getInstance().getGridView( sourceX, sourceY );
        removePirateFromCell( pirate, prevCellView );
        prevCellView.updateView();
    }

    private void removePirateFromCell( Pirate pirate, CellView sourceCellView ) {
        Cell cell = sourceCellView.getCell();
        cell.getPirates().remove( pirate );
    }

    private static void addPirateToTargetShipView( Ship ship, Pirate pirate ) {
        ship.putPirate( pirate );
        int targetX = ship.getPosition().getX();
        int targetY = ship.getPosition().getY();
        ShipView targetShipView = ( ShipView ) BoardPane.getInstance().getGridView( targetX, targetY );
        targetShipView.updateView();
    }
}
