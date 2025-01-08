package yuriy.weiss.javafx.training.view;

import javafx.scene.layout.Pane;
import lombok.RequiredArgsConstructor;
import yuriy.weiss.javafx.training.controller.DragAcceptFacade;
import yuriy.weiss.javafx.training.controller.DragDropFacade;
import yuriy.weiss.javafx.training.model.*;
import yuriy.weiss.javafx.training.view.element.CellView;
import yuriy.weiss.javafx.training.view.element.ShipView;

import java.util.Optional;

@RequiredArgsConstructor
public class CellPaneBuilder {

    private final DragAcceptFacade dragAcceptController;
    private final DragDropFacade dragDropFacade;

    public Pane createCellPane( int x, int y ) {
        Board board = Game.getInstance().getCurrentBoard();
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
        CellView cellView =
                new CellView( dragAcceptController,
                        dragDropFacade,
                        cellPane,
                        board.getCells()[y][x] );
        cellView.createView();
        BoardPane.getInstance().getGridViews()[y][x] = cellView;
    }

    private void createShipView( Pane cellPane, Ship ship ) {
        ShipView shipView =
                new ShipView( dragAcceptController,
                        dragDropFacade,
                        cellPane,
                        ship );
        shipView.createView();
        int shipX = ship.getPosition().getX();
        int shipY = ship.getPosition().getY();
        BoardPane.getInstance().getGridViews()[shipY][shipX] = shipView;
    }
}
