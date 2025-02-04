package yuriy.weiss.javafx.training.model;

import javafx.scene.paint.Color;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.model.cell.Cell;
import yuriy.weiss.javafx.training.model.cell.CellType;
import yuriy.weiss.javafx.training.util.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class GameCreator {

    private static final Random RANDOM = new Random();

    public static void main( String[] args ) {
        new GameCreator().initGame( 2, 7 );
        log.info( "game created" );
        log.info( "board states size: {}", Game.getInstance().getBoards().size() );
    }

    public void initGame( int teamsCount, int boardSize ) {
        Game game = Game.getInstance();
        Board startBoard = createBoard( teamsCount, boardSize );
        game.getBoards().add( startBoard );
        Board currentBoard = new Board( startBoard );
        game.setCurrentBoard( currentBoard );
        Team firstTeam = currentBoard.getTeams().get( 0 );
        game.setActiveTeam( firstTeam );
        game.setCurrentMove( new Move( new Team( firstTeam ) ) );
    }

    private Board createBoard( int teamsCount, int boardSize ) {
        return new Board( createTeams( teamsCount, boardSize ),
                initCells( boardSize, new Object[][]{
                        { CellType.CROCODILE, 1 },
                        { CellType.TREASURE_1, 3 },
                        { CellType.TREASURE_2, 2 }
                } ) );
    }

    private List<Team> createTeams( int teamsCount, int boardSize ) {
        if ( teamsCount != 2 ) {
            throw new RuntimeException( "more than 2 teams not implemented" );
        }

        List<Team> teams = new ArrayList<>();
        teams.add( createTeam( Color.RED, 0, boardSize / 2 ) );
        teams.add( createTeam( Color.BLUE, boardSize - 1, boardSize / 2 ) );
        return teams;
    }

    private Team createTeam( Color color, int x, int y ) {
        Team team = new Team( color, new Position( x, y ) );
        for ( int i = 0; i < 3; i++ ) {
            Pirate pirate =
                    new Pirate( i, team, new Position( x, y ), new StateInCell( -1, false, false ) );
            team.addPirate( pirate );
            team.getShip().putPirate( pirate );
        }
        return team;
    }

    private Cell[][] initCells( int boardSize, Object[][] specialCellCounts ) {
        Cell[][] boardCells = new Cell[boardSize][boardSize];
        createWaterCells( boardCells, boardSize );
        List<Position> freeGroundPositions = buildFreeGroundPositions( boardCells, boardSize );
        generateSpecialCells( boardCells, freeGroundPositions, specialCellCounts );
        fillRemainingGroundCells( boardCells, freeGroundPositions );
        return boardCells;
    }

    private void createWaterCells( Cell[][] boardCells, int boardSize ) {
        for ( int j = 0; j < boardSize; j++ ) {
            int x = j;
            int y = 0;
            boardCells[y][x] = new Cell( new Position( x, y ), 0, CellType.WATER );
            y = boardSize - 1;
            boardCells[y][x] = new Cell( new Position( x, y ), 0, CellType.WATER );
        }
        for ( int i = 1; i < boardSize - 1; i++ ) {
            int y = i;
            int x = 0;
            boardCells[y][x] = new Cell( new Position( x, y ), 0, CellType.WATER );
            x = boardSize - 1;
            boardCells[y][x] = new Cell( new Position( x, y ), 0, CellType.WATER );
        }
        // cut the angles
        boardCells[1][1] = new Cell( new Position( 1, 1 ), 0, CellType.WATER );
        boardCells[1][boardSize - 2] = new Cell( new Position( boardSize - 2, 1 ), 0, CellType.WATER );
        boardCells[boardSize - 2][1] = new Cell( new Position( 1, boardSize - 2 ), 0, CellType.WATER );
        boardCells[boardSize - 2][boardSize - 2] =
                new Cell( new Position( boardSize - 2, boardSize - 2 ), 0, CellType.WATER );
    }

    private List<Position> buildFreeGroundPositions( Cell[][] boardCells, int boardSize ) {
        List<Position> result = new ArrayList<>();
        for ( int y = 0; y < boardSize; y++ ) {
            for ( int x = 0; x < boardSize; x++ ) {
                if ( boardCells[y][x] == null ) {
                    result.add( new Position( x, y ) );
                }
            }
        }
        return result;
    }

    private void generateSpecialCells( Cell[][] boardCells, List<Position> freeGroundPositions,
            Object[][] specialCellCounts ) {
        for ( Object[] specialCellCount : specialCellCounts ) {
            CellType cellType = ( CellType ) specialCellCount[0];
            Integer cellCount = ( Integer ) specialCellCount[1];
            for ( int j = 0; j < cellCount; j++ ) {
                int freeIndex = RANDOM.nextInt( freeGroundPositions.size() );
                Position cellPosition = freeGroundPositions.remove( freeIndex ); //NOSONAR
                int x = cellPosition.getX();
                int y = cellPosition.getY();
                Cell newCell = new Cell( new Position( x, y ), RANDOM.nextInt( 4 ), cellType );
                addCoisToTreasureCell( cellType, newCell );
                boardCells[y][x] = newCell;
            }
        }
    }

    private void addCoisToTreasureCell( CellType cellType, Cell newCell ) {
        if ( cellType.getTreasureSize() > 0 ) {
            for ( int c = 0; c < cellType.getTreasureSize(); c++ ) {
                Coin newCoin = new Coin( IdGenerator.getNextId() );
                newCell.getCoins().add( newCoin );
            }
        }
    }

    private void fillRemainingGroundCells( Cell[][] boardCells, List<Position> freeGroundPositions ) {
        freeGroundPositions.forEach( position -> {
            int x = position.getX();
            int y = position.getY();
            boardCells[y][x] = new Cell( new Position( x, y ), RANDOM.nextInt( 4 ), CellType.GROUND );
        } );
    }
}
