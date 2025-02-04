package yuriy.weiss.javafx.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Position {

    private int x;
    private int y;

    public Position( int x, int y ) {
        this.x = x;
        this.y = y;
    }

    public Position( Position source ) {
        this( source.getX(), source.getY() );
    }
}
