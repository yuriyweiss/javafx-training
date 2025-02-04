package yuriy.weiss.javafx.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.paint.Color;
import lombok.Data;
import lombok.NoArgsConstructor;
import yuriy.weiss.javafx.training.util.ColorNames;
import yuriy.weiss.javafx.training.util.TeamUtils;

import java.util.List;

@Data
@NoArgsConstructor
public class Pirate {

    private int id;
    private Team team;
    private Position position;
    private StateInCell stateInCell;
    private boolean alive = true;
    private Coin coin = null;

    public Pirate( int id, Team team, Position position, StateInCell stateInCell ) {
        this.id = id;
        this.team = team;
        this.position = position;
        this.stateInCell = stateInCell;
    }

    public Pirate( Team team, Pirate source ) {
        this.id = source.getId();
        this.team = team;
        this.position = new Position( source.getPosition() );
        this.coin = source.getCoin() == null ? null : new Coin( source.getCoin().getId() );
        this.alive = source.isAlive();
    }

    @JsonIgnore
    public Color getColor() {
        return team.getColor();
    }

    public void updatePosition( int x, int y ) {
        this.position.setX( x );
        this.position.setY( y );
    }

    public void updatePosition( Position position ) {
        this.position.setX( position.getX() );
        this.position.setY( position.getY() );
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
                ", team.color=" + ColorNames.getColorName( team.getColor() ) +
                ", position=" + position +
                ", stateInCell=" + stateInCell +
                ", coin=" + coin +
                '}';
    }

    @JsonIgnore
    public boolean isOnShip() {
        return team.getShip().getPiratesOnBoard().contains( this );
    }
}
