package yuriy.weiss.javafx.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
public class Ship {

    @Getter
    @Setter
    private Team team;
    @Getter
    private Position position;
    @JsonIgnore
    private final List<Pirate> piratesOnBoard = new ArrayList<>();

    public Ship( Team team, Position position ) {
        this.team = team;
        this.position = position;
    }

    public Ship( Team team, Ship source ) {
        this.team = team;
        this.position = new Position( source.getPosition() );
        Set<Pirate> newPirates = team.getPirates();
        source.piratesOnBoard.forEach( sourcePirate -> {
            Pirate newPirate = newPirates.stream()
                    .filter( e -> e.equals( sourcePirate ) )
                    .findFirst()
                    .orElseThrow();
            this.putPirate( newPirate );
        } );
    }

    @JsonIgnore
    public Color getColor() {
        return team.getColor();
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

    public void updatePosition( int x, int y ) {
        this.position.setX( x );
        this.position.setY( y );
    }

    public void updatePosition( Position position ) {
        this.position.setX( position.getX() );
        this.position.setY( position.getY() );
    }
}
