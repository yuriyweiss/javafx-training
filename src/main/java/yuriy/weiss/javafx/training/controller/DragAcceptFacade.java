package yuriy.weiss.javafx.training.controller;

import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.cell.Cell;

@Slf4j
public class DragAcceptFacade {

    private final DragAcceptControllerCell dragAcceptControllerCell = new DragAcceptControllerCell();
    private final DragAcceptControllerShip dragAcceptControllerShip = new DragAcceptControllerShip();
    private final DragAcceptControllerPirate dragAcceptControllerPirate = new DragAcceptControllerPirate();

    public boolean cellCanAccept( Cell cell, String sourceString ) {
        return dragAcceptControllerCell.cellCanAccept( cell, sourceString );
    }

    public boolean shipCanAccept( Ship ship, String sourceString ) {
        return dragAcceptControllerShip.shipCanAccept( ship, sourceString );
    }

    public boolean pirateCanAccept( Pirate pirate, String sourceString ) {
        return dragAcceptControllerPirate.pirateCanAccept( pirate, sourceString );
    }
}
