package yuriy.weiss.javafx.training.view;

import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.model.Board;
import yuriy.weiss.javafx.training.model.Position;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.Team;

import java.util.Optional;

@RequiredArgsConstructor
public class CellPaneBuilder {

    private final BoardPaneController boardPaneController;

    public Pane createCellPane( int x, int y, Board board ) {
        final Pane cellPane = new Pane();
        Ship ship = getShipAtPosition( x, y, board );
        if ( ship == null ) {
            createCellView( cellPane, x, y, board );
        } else {
            createShipView( cellPane, ship );
        }
        return cellPane;
    }

    private Ship getShipAtPosition( int x, int y, Board board ) {
        Optional<Team> team = board.getTeams().stream()
                .filter( e -> e.getShip().getPosition().equals( new Position( x, y ) ) )
                .findFirst();
        return team.map( Team::getShip ).orElse( null );
    }

    public void createCellView( Pane cellPane, int x, int y, Board board ) {
        new CellView( boardPaneController, cellPane, board.getCells()[y][x] ).createView();
    }

    private void createShipView( Pane cellPane, Ship ship ) {
        new ShipView( boardPaneController, cellPane, ship ).createView();
    }
}
