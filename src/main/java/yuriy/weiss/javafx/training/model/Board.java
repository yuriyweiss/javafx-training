package yuriy.weiss.javafx.training.model;

import yuriy.weiss.javafx.training.model.cell.Cell;

import java.util.List;

public class Board {

    private final List<Team> teams;
    private final Cell[][] cells;
    private Team activeTeam;

    public Board( List<Team> teams, Cell[][] cells ) {
        this.teams = teams;
        this.cells = cells;
        activeTeam = teams.get(0);
    }

    public int getSize() {
        return cells.length;
    }

    public Cell[][] getCells() {
        return cells;
    }
}
