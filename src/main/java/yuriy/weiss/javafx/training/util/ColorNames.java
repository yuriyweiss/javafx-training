package yuriy.weiss.javafx.training.util;

import javafx.scene.paint.Color;

public class ColorNames {

    public static final String RED = "red";
    public static final String BLUE = "blue";
    public static final String GREEN = "green";
    public static final String YELLOW = "yellow";

    private ColorNames() {
    }

    public static String getColorName( Color color ) {
        if ( color == Color.RED ) {
            return RED;
        } else if ( color == Color.BLUE ) {
            return BLUE;
        } else if ( color == Color.GREEN ) {
            return GREEN;
        } else if ( color == Color.YELLOW ) {
            return YELLOW;
        }
        throw new RuntimeException( "Not team color: " + color.toString() );
    }

    public static Color getColorByName( String colorName ) {
        return switch ( colorName ) {
            case RED -> Color.RED;
            case BLUE -> Color.BLUE;
            case GREEN -> Color.GREEN;
            case YELLOW -> Color.YELLOW;
            default -> throw new RuntimeException( "Unknown color name: " + colorName );
        };
    }
}
