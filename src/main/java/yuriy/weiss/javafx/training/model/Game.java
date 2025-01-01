package yuriy.weiss.javafx.training.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final List<Board> boardStates = new ArrayList<>();
    private final List<Move> moves = new ArrayList<>();

    public void addBoardState( Board board ) {
        boardStates.add( board );
    }

    public List<Board> getBoardStates() {
        return boardStates;
    }

    public void addMove( Move move ) {
        moves.add( move );
    }

    public List<Move> getMoves() {
        return moves;
    }
}
