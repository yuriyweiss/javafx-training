package yuriy.weiss.javafx.training.controller;

import org.apache.commons.lang3.tuple.Pair;

public enum DragSource {

    PIRATE( "PIRATE:" ),
    COIN_ON_CELL( "COIN_ON_CELL:" ),
    COIN_ON_PIRATE( "COIN_ON_PIRATE:" ),
    SHIP( "SHIP:" );

    private final String dragStringPrefix;

    DragSource( String dragStringPrefix ) {
        this.dragStringPrefix = dragStringPrefix;
    }

    public String getPrefix() {
        return dragStringPrefix;
    }

    public static Pair<DragSource, String> splitDragInfo( String dragInfo ) {
        for ( DragSource dragSource : values() ) {
            if ( dragInfo.startsWith( dragSource.dragStringPrefix ) ) {
                return Pair.of( dragSource, dragInfo.substring( dragSource.dragStringPrefix.length() ) );
            }
        }
        throw new RuntimeException( "DragSource not found for string: " + dragInfo );
    }
}
