package yuriy.weiss.javafx.training.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.controller.DragAcceptFacade;
import yuriy.weiss.javafx.training.controller.DragDropFacade;
import yuriy.weiss.javafx.training.model.Game;
import yuriy.weiss.javafx.training.model.Position;
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

    private final DragAcceptFacade dragAcceptFacade = new DragAcceptFacade();
    private final DragDropFacade dragDropFacade = new DragDropFacade();
    private final CellPaneBuilder cellPaneBuilder =
            new CellPaneBuilder( dragAcceptFacade, dragDropFacade );

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
                Pane cellPane = cellPaneBuilder.createCellPane( j, i );
                gridPane.add( cellPane, j, i );
            }
        }
        return gridPane;
    }

    public void rebuildCellPane( Position position ) {
        rebuildCellPane( position.getX(), position.getY() );
    }

    public void rebuildCellPane( int x, int y ) {
        Pane cellPane = gridViews[y][x].getCellPane();
        gridPane.getChildren().remove( cellPane );
        cellPane = cellPaneBuilder.createCellPane( x, y );
        gridPane.add( cellPane, x, y );
    }

    public GridCellView getGridView( int x, int y ) {
        return gridViews[y][x];
    }
}
