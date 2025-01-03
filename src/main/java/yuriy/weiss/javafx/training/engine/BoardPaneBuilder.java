package yuriy.weiss.javafx.training.engine;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.model.Board;
import yuriy.weiss.javafx.training.model.Position;
import yuriy.weiss.javafx.training.model.Ship;
import yuriy.weiss.javafx.training.model.Team;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;
import yuriy.weiss.javafx.training.util.ColorNames;

import java.util.Optional;

@Slf4j
public class BoardPaneBuilder {

    private static final Border SELECTED_BORDER =
            new Border( new BorderStroke( Paint.valueOf( "red" ), BorderStrokeStyle.SOLID, null, null ) );
    private static final Border GRAY_BORDER =
            new Border( new BorderStroke( Paint.valueOf( "lightgray" ), BorderStrokeStyle.SOLID, null, null ) );

    public Pane buildPane( Board board ) {
        GridPane gridPane = new GridPane();
        int boardSize = board.getSize();
        for ( int i = 0; i < boardSize; i++ ) {
            for ( int j = 0; j < boardSize; j++ ) {
                final Pane cellPane = new Pane();
                cellPane.setBorder( GRAY_BORDER );
                Ship ship = getShipAtPosition( j, i, board );
                Node cellNode = ( ship == null )
                        ? createCellView( cellPane, j, i, board )
                        : createShipView( cellPane, ship );
                cellPane.getChildren().add( cellNode );
                gridPane.add( cellPane, j, i );
            }
        }
        return gridPane;
    }

    private Ship getShipAtPosition( int x, int y, Board board ) {
        Optional<Team> team = board.getTeams().stream()
                .filter( e -> e.getShip().getPosition().equals( new Position( x, y ) ) )
                .findFirst();
        return team.map( Team::getShip ).orElse( null );
    }

    private Node createShipView( Pane cellPane, Ship ship ) {
        String shipColor = ColorNames.getColorName( ship.getTeam().getColor() );
        String imageFile = "ship_" + shipColor + ".png";
        final ImageView shipView = new ImageView( imageFile );
        shipView.setUserData( ship );
        shipView.setLayoutX( 1 );
        shipView.setLayoutY( 1 );
        shipView.setOnMouseExited( e -> cellPane.setBorder( GRAY_BORDER ) );
        shipView.setOnMouseEntered( e -> cellPane.setBorder( SELECTED_BORDER ) );
        return shipView;
    }

    private Node createCellView( Pane cellPane, int x, int y, Board board ) {
        Cell currentCell = board.getCells()[y][x];
        String imageFile = getImageFile( currentCell );
        final ImageView cellView = new ImageView( imageFile );
        cellView.setUserData( board.getCells()[y][x] );
        cellView.setLayoutX( 1 );
        cellView.setLayoutY( 1 );
        cellView.setOnMouseExited( e -> cellPane.setBorder( GRAY_BORDER ) );
        cellView.setOnMouseEntered( e -> cellPane.setBorder( SELECTED_BORDER ) );
        cellView.setOnMouseClicked( e -> onMouseClicked( cellView ) );
        return cellView;
    }

    private static void onMouseClicked( ImageView cellView ) {
        Cell clickedCell = ( Cell ) cellView.getUserData();
        if ( clickedCell.isClosed() ) {
            cellView.setImage( new Image( clickedCell.getCellType().getImageFile() ) );
        } else {
            cellView.setImage( new Image( "closed_ground.png" ) );
        }
        clickedCell.setClosed( !clickedCell.isClosed() );
    }

    private static String getImageFile( Cell currentCell ) {
        String imageFile = CellType.WATER.getImageFile();
        if ( currentCell.getCellType() != CellType.WATER ) {
            if ( currentCell.isClosed() ) {
                imageFile = "closed_ground.png";
            } else {
                imageFile = currentCell.getCellType().getImageFile();
            }
        }
        return imageFile;
    }
}
