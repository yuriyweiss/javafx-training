package yuriy.weiss.javafx.training.model.cell;

import lombok.Data;
import yuriy.weiss.javafx.training.model.Coin;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Position;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

@Data
public class Cell {

    private final Position position;
    private final int rotation;
    private boolean closed = true;
    private final CellType cellType;

    private final Set<Pirate> pirates = new HashSet<>();
    private final Queue<Coin> coins = new LinkedList<>();
}
