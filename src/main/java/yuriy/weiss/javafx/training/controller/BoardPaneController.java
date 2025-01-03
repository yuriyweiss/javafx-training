package yuriy.weiss.javafx.training.controller;

import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.view.BoardElementView;

import static yuriy.weiss.javafx.training.controller.FocusedType.*;

public class BoardPaneController {

    private final PrevFocusHolder prevFocusHolder = new PrevFocusHolder();

    public void fireFocusChanged( BoardElementView elementView, FocusedType focusedType, Object focusedObject ) {
        if ( prevFocusHolder.getType() != null && !containes( prevFocusHolder, focusedType, focusedObject ) ) {
            removeFocus( prevFocusHolder );
        }
        prevFocusHolder.setFocused( elementView, focusedType, focusedObject );
        elementView.updateView();
    }

    private boolean containes( PrevFocusHolder prevFocusHolder, FocusedType focusedType, Object focusedObject ) {
        if ( focusedType == SHIP && prevFocusHolder.getType() == PIRATE ) {
            return ( ( Ship ) focusedObject ).getPiratesOnBoard().contains( prevFocusHolder.getPirate() );
        }
        if ( focusedType == CELL && prevFocusHolder.getType() == PIRATE ) {
            return ( ( Cell ) focusedObject ).getPirates().contains( prevFocusHolder.getPirate() );
        }
        if ( focusedType == CELL && prevFocusHolder.getType() == COIN ) {
            return ( ( Cell ) focusedObject ).getCoins().contains( prevFocusHolder.getCoin() );
        }
        return false;
    }

    private void removeFocus( PrevFocusHolder prevFocusHolder ) {
        prevFocusHolder.getElementView().setFocused( false );
        prevFocusHolder.getElementView().updateView();
    }
}
