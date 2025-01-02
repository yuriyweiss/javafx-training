package yuriy.weiss.javafx.training;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import yuriy.weiss.javafx.training.engine.BoardPaneBuilder;
import yuriy.weiss.javafx.training.engine.GameCreator;
import yuriy.weiss.javafx.training.model.Game;

@Slf4j
public class MainForm extends Application {

    public static void main( String[] args ) {
        launch();
    }

    @Override
    public void start( Stage stage ) {
        Scene scene = new Scene( createContent(), 1000, 750, Color.GRAY );
        stage.setScene( scene );
        stage.setTitle( "Main window" );
        stage.setMaximized( true );
        stage.show();
    }

    private Parent createContent() {
        Game game = new GameCreator().createGame( 2, 7 );
        log.info( "game created" );
        log.info( "board states size: {}", game.getBoardStates().size() );

        return new BoardPaneBuilder().buildPane( game.getBoardStates().get( 0 ) );
    }
}
