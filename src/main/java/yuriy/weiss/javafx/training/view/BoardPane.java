package yuriy.weiss.javafx.training.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.controller.BoardPaneController;
import yuriy.weiss.javafx.training.model.Board;

@Slf4j
public class BoardPane {

    private final BoardPaneController boardPaneController = new BoardPaneController();
    private final CellPaneBuilder cellPaneBuilder = new CellPaneBuilder( boardPaneController );

    public Pane buildPane( Board board ) {
        GridPane gridPane = new GridPane();
        int boardSize = board.getSize();
        for ( int i = 0; i < boardSize; i++ ) {
            for ( int j = 0; j < boardSize; j++ ) {
                final Pane cellPane = cellPaneBuilder.createCellPane( j, i, board );
                gridPane.add( cellPane, j, i );
            }
        }
        return gridPane;
    }
}
