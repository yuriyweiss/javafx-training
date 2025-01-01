package yuriy.weiss.javafx.training.model;

import lombok.Data;

@Data
public class StateInCell {

    private int positionInLabirinth;
    private boolean inBarrel;
    private boolean inTrap;

    public StateInCell( int positionInLabirinth, boolean inBarrel, boolean inTrap ) {
        this.positionInLabirinth = positionInLabirinth;
        this.inBarrel = inBarrel;
        this.inTrap = inTrap;
    }
}
