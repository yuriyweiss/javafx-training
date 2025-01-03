package yuriy.weiss.javafx.training.model;

import lombok.Getter;

import java.util.*;

public class Ship {

    @Getter
    private final Team team;
    @Getter
    private final Position position;
    private final List<Pirate> piratesOnBoard = new ArrayList<>();

    public Ship( Team team, Position position ) {
        this.team = team;
        this.position = position;
    }

    public void putPirate( Pirate pirate ) {
        piratesOnBoard.add( pirate );
    }

    public void removePirate( Pirate pirate ) {
        piratesOnBoard.remove( pirate );
    }

    public List<Pirate> getPiratesOnBoard() {
        return Collections.unmodifiableList( piratesOnBoard );
    }

    public void setPosition( int x, int y ) {
        this.position.setX( x );
        this.position.setY( y );
    }
}
