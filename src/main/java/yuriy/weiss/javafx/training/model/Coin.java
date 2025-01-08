package yuriy.weiss.javafx.training.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Coin {

    private int id;

    public Coin( int id ) {
        this.id = id;
    }
}
