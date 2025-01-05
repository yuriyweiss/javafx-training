package yuriy.weiss.javafx.training.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.scene.paint.Color;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class Team {

    @Getter
    private Color color;
    @Getter
    @Setter
    @JsonIgnore
    private Ship ship;
    @JsonIgnore
    private final Set<Pirate> pirates = new HashSet<>();
    @Getter
    @JsonIgnore
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

    public Pirate getPirate( int pirateId ) {
        return pirates.stream()
                .filter( e -> pirateId == e.getId() )
                .findFirst()
                .orElseThrow();
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
