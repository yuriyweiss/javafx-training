package yuriy.weiss.javafx.training.model.cell;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import yuriy.weiss.javafx.training.model.Coin;
import yuriy.weiss.javafx.training.model.Pirate;
import yuriy.weiss.javafx.training.model.Position;
import yuriy.weiss.javafx.training.model.Team;
import yuriy.weiss.javafx.training.util.TeamUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class Cell {

    private Position position;
    private int rotation;
    private boolean closed = true;
    private CellType cellType;

    @JsonIgnore
    private final List<Pirate> pirates = new ArrayList<>();
    @JsonIgnore
    private final List<Coin> coins = new LinkedList<>();

    public Cell( Position position, int rotation, CellType cellType ) {
        this.position = position;
        this.rotation = rotation;
        this.cellType = cellType;
    }

    public Cell( List<Team> teams, Cell source ) {
        this.position = new Position( source.position );
        this.rotation = source.rotation;
        this.closed = source.closed;
        this.cellType = source.cellType;
        source.pirates.forEach( sourcePirate -> {
            Pirate newPirate = TeamUtils.findPirate( teams, sourcePirate );
            this.pirates.add( newPirate );
        } );
        source.coins.forEach( sourceCoin -> this.coins.add( new Coin( sourceCoin.getId() ) ) );
    }
}
