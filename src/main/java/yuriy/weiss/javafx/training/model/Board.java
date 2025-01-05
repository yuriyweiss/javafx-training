package yuriy.weiss.javafx.training.model;

import lombok.Getter;
import yuriy.weiss.javafx.training.model.cell.Cell;

import java.util.ArrayList;
import java.util.List;

public class Board {

    @Getter
    private final List<Team> teams;
    @Getter
    private final Cell[][] cells;

    public Board( List<Team> teams, Cell[][] cells ) {
        this.teams = teams;
        this.cells = cells;
    }

    public Board( Board source ) {
        this.teams = new ArrayList<>();
        source.teams.forEach( sourceTeam -> this.teams.add( new Team( sourceTeam ) ) );
        int boardSize = source.cells.length;
        this.cells = new Cell[boardSize][boardSize];
        for ( int i = 0; i < boardSize; i++ ) {
            for ( int j = 0; j < boardSize; j++ ) {
                this.cells[i][j] = new Cell(teams, source.cells[i][j]);
            }
        }
    }

    public int getSize() {
        return cells.length;
    }
}
