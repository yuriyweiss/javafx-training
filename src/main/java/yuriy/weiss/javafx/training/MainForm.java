package yuriy.weiss.javafx.training;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
        Rectangle box1 = new Rectangle( 100, 50, Color.BLUE );
        Rectangle box2 = new Rectangle( 100, 50, Color.rgb( 0, 0, 0, 0.25 ) );
        transform( box1, box2 );
        ImageView iv1 = new ImageView( "horse.png" );
        Pane parentPane = new Pane();
        parentPane.getChildren().addAll( box1, box2, iv1 );
        return parentPane;
    }

    private void transform( Rectangle box1, Rectangle box2 ) {
        box1.setTranslateX( 100 );
        box1.setTranslateY( 200 );
        box1.setRotate( 30 );
        box2.setTranslateX( 100 );
        box2.setTranslateY( 200 );
    }
}
