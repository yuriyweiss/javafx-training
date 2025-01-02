package yuriy.weiss.javafx.training.engine;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.model.Board;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;

@Slf4j
public class BoardPaneBuilder {

    public Pane buildPane( Board board ) {
        GridPane gridPane = new GridPane();
        int boardSize = board.getSize();
        for ( int i = 0; i < boardSize; i++ ) {
            for ( int j = 0; j < boardSize; j++ ) {
                String imageFile =
                        board.getCells()[i][j].getCellType() == CellType.WATER ? CellType.WATER.getImageFile() :
                                "closed_ground.png";
                final ImageView cellView = new ImageView( imageFile );
                cellView.setUserData( board.getCells()[i][j] );
                cellView.setOnMouseClicked( e -> {
                    log.info( "cell position: {}", ( ( Cell ) cellView.getUserData() ).getPosition() );
                    log.info( "cell type: {}", ( ( Cell ) cellView.getUserData() ).getCellType() );
                } );
                gridPane.add( cellView, j, i );
            }
        }
        return gridPane;
    }
}
