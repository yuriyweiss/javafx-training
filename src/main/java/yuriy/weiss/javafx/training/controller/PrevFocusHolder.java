package yuriy.weiss.javafx.training.controller;

import lombok.Getter;
import yuriy.weiss.javafx.training.model.Coin;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.view.element.AbstractElementView;

@Getter
public class PrevFocusHolder {

    private static PrevFocusHolder instance = null;

    private PrevFocusHolder() {
    }

    public static PrevFocusHolder getInstance() {
        if ( instance == null ) {
            instance = new PrevFocusHolder();
        }
        return instance;
    }

    private FocusedType type = null;

    private AbstractElementView elementView = null;
    private Cell cell = null;
    private Ship ship = null;
    private Pirate pirate = null;
    private Coin coin = null;

    public void setFocused( AbstractElementView elementView, FocusedType focusedType, Object focusedObject ) {
        clearFocus();
        this.type = focusedType;
        this.elementView = elementView;
        switch ( focusedType ) {
            case CELL:
                this.cell = ( Cell ) focusedObject;
                break;
            case SHIP:
                this.ship = ( Ship ) focusedObject;
                break;
            case PIRATE:
                this.pirate = ( Pirate ) focusedObject;
                break;
            case COIN:
                this.coin = ( Coin ) focusedObject;
                break;
        }
    }

    public void clearFocus() {
        type = null;
        elementView = null;
        cell = null;
        ship = null;
        pirate = null;
        coin = null;
    }
}
