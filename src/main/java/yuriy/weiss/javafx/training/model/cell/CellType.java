package yuriy.weiss.javafx.training.model.cell;

public enum CellType {

    WATER( "water", 0, 0 ),
    GROUND( "ground", 0, 0 ),
    CROCODILE( "crocodile", 0, 0 ),
    TREASURE_1( "treasure1", 1, 0 ),
    TREASURE_2( "treasure2", 2, 0 );

    private final String imageName;
    private final int treasureSize;
    private final int labyrinthSize;

    CellType( String imageName, int treasureSize, int labyrinthSize ) {
        this.imageName = imageName;
        this.treasureSize = treasureSize;
        this.labyrinthSize = labyrinthSize;
    }

    public String getImageName() {
        return imageName;
    }

    public int getTreasureSize() {
        return treasureSize;
    }

    public int getLabyrinthSize() {
        return labyrinthSize;
    }
}
