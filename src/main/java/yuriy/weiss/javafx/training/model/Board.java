package yuriy.weiss.javafx.training.model;

import lombok.Getter;
import yuriy.weiss.javafx.training.model.cell.Cell;

import java.util.List;

public class Board {

    @Getter
    private final List<Team> teams;
    @Getter
    private final Cell[][] cells;
    private Team activeTeam;

    public Board( List<Team> teams, Cell[][] cells ) {
        this.teams = teams;
        this.cells = cells;
        activeTeam = teams.get( 0 );
    }

    public int getSize() {
        return cells.length;
    }
}
