package yuriy.weiss.javafx.training.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Game {

    private static Game instance = null;

    private Game() {
    }

    public static Game getInstance() {
        if ( instance == null ) {
            instance = new Game();
        }
        return instance;
    }

    private final List<Board> boards = new ArrayList<>();
    private final List<Move> moves = new ArrayList<>();

    private Board currentBoard;
    private Move currentMove;
    private Team activeTeam;
}
