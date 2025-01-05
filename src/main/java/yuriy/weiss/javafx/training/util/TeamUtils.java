package yuriy.weiss.javafx.training.util;

import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Team;

import java.util.List;

public class TeamUtils {

    private TeamUtils() {
    }

    public static Pirate findPirate( List<Team> teams, Pirate sourcePirate ) {
        for ( Team team : teams ) {
            Pirate pirate = team.getPirates().stream().filter( e -> e.equals( sourcePirate ) ).findFirst().orElse(
                    null );
            if ( pirate != null ) {
                return pirate;
            }
        }
        throw new RuntimeException( "Pirate not found in new teams: " + sourcePirate );
    }
}
