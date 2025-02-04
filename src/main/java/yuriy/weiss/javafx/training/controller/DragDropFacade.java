package yuriy.weiss.javafx.training.controller;

import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.cell.Cell;

public class DragDropFacade {

    private final DragDropControllerCell dragDropControllerCell = new DragDropControllerCell();
    private final DragDropControllerShip dragDropControllerShip = new DragDropControllerShip();
    private final DragDropControllerPirate dragDropControllerPirate = new DragDropControllerPirate();

    public void dropToCell( Cell cell, String sourceString ) {
        dragDropControllerCell.dropToCell( cell, sourceString );
    }

    public void dropToShip( Ship ship, String sourceString ) {
        dragDropControllerShip.dropToShip( ship, sourceString );
    }

    public void dropToPirate( Pirate pirate, String sourceString ) {
        dragDropControllerPirate.dropToPirate( pirate, sourceString );
    }
}
