package yuriy.weiss.javafx.training.model;

import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Team {

    @Getter
    private final Color color;
    @Getter
    @Setter
    private Ship ship;
    private final Set<Pirate> pirates = new HashSet<>();
    @Getter
    private final Set<Coin> coins = new HashSet<>();

    public Team( Color color, Position shipPosition ) {
        this.color = color;
        this.ship = new Ship( this, shipPosition );
    }

    public Set<Pirate> getPirates() {
        return Collections.unmodifiableSet( pirates );
    }

    public void addPirate( Pirate pirate ) {
        if ( pirate.getTeam().equals( this ) ) {
            pirates.add( pirate );
        }
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;

        Team team = ( Team ) o;

        return color.equals( team.color );
    }

    @Override
    public int hashCode() {
        return color.hashCode();
    }

    @Override
    public String toString() {
        return "Team{" +
                "color=" + color +
                '}';
    }
}
