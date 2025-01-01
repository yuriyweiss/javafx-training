package yuriy.weiss.javafx.training.model;

import javafx.scene.paint.Color;
import lombok.Data;

@Data
public class Pirate {

    private final int id;
    private final Team team;
    private final Position position;
    private final StateInCell stateInCell;
    private boolean alive = true;
    private Coin coin = null;

    public Color getColor() {
        return team.getColor();
    }

    public void setPosition( int x, int y ) {
        this.position.setX( x );
        this.position.setY( y );
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Pirate pirate = ( Pirate ) o;

        if ( id != pirate.id ) return false;
        return team.equals( pirate.team );
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + team.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pirate{" +
                "id=" + id +
                ", team.color=" + team.getColor() +
                ", position=" + position +
                ", stateInCell=" + stateInCell +
                ", coin=" + coin +
                '}';
    }
}
