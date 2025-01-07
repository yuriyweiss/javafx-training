package yuriy.weiss.javafx.training.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.controller.DragAcceptController;
import yuriy.weiss.javafx.training.controller.DragDropController;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.view.element.GridCellView;

@Slf4j
public class BoardPane {

    private static BoardPane instance = null;

    private BoardPane() {
    }

    public static BoardPane getInstance() {
        if ( instance == null ) {
            instance = new BoardPane();
        }
        return instance;
    }

    private final DragAcceptController dragAcceptController = new DragAcceptController();
    private final DragDropController dragDropController = new DragDropController();
    private final CellPaneBuilder cellPaneBuilder =
            new CellPaneBuilder( dragAcceptController, dragDropController );

    private GridPane gridPane = null;
    @Getter
    private GridCellView[][] gridViews = null;

    public Pane rebuildPane() {
        int boardSize = Game.getInstance().getCurrentBoard().getSize();
        // clear grid pane
        if ( gridPane == null ) {
            gridPane = new GridPane();
        } else {
            gridPane.getChildren().clear();
        }
        gridViews = new GridCellView[boardSize][boardSize];
        // create new cells based on currentBoard state
        for ( int i = 0; i < boardSize; i++ ) {
            for ( int j = 0; j < boardSize; j++ ) {
                final Pane cellPane = cellPaneBuilder.createCellPane( j, i );
                gridPane.add( cellPane, j, i );
            }
        }
        return gridPane;
    }

    public GridCellView getGridView( int x, int y ) {
        return gridViews[y][x];
    }
}
