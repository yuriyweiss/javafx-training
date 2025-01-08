package yuriy.weiss.javafx.training.util;

import javafx.scene.paint.Color;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.Team;

import java.util.List;

public class TeamUtils {

    private TeamUtils() {
    }

    public static Pirate findPirate( List<Team> teams, Pirate sourcePirate ) {
        for ( Team team : teams ) {
            Pirate pirate = team.getPirates().stream()
                    .filter( e -> e.equals( sourcePirate ) )
                    .findFirst()
                    .orElse( null );
            if ( pirate != null ) {
                return pirate;
            }
        }
        throw new RuntimeException( "Pirate not found in new teams: " + sourcePirate );
    }

    public static void attachRealTeam( Pirate pirate, List<Team> teams ) {
        Color teamColor = pirate.getColor();
        pirate.setTeam( getRealTeam( teams, teamColor ) );
    }

    public static void attachRealTeam( Ship ship, List<Team> teams ) {
        Color teamColor = ship.getColor();
        ship.setTeam( getRealTeam( teams, teamColor ) );
    }

    public static Team getRealTeam( List<Team> teams, final Color teamColor ) {
        return teams.stream()
                .filter( e -> e.getColor().equals( teamColor ) )
                .findFirst()
                .orElseThrow();
    }
}
