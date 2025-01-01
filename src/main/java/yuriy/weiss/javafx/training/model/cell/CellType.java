package yuriy.weiss.javafx.training.model.cell;

public enum CellType {

    WATER( "water.png", 0, 0 ),
    GROUND( "ground.png", 0, 0 ),
    CROCODILE( "crocodile.png", 0, 0 ),
    TREASURE_1( "treasure1.png", 1, 0 ),
    TREASURE_2( "treasure2.png", 2, 0 );

    private final String imageFile;
    private final int treasureSize;
    private final int labyrinthSize;

    CellType( String imageFile, int treasureSize, int labyrinthSize ) {
        this.imageFile = imageFile;
        this.treasureSize = treasureSize;
        this.labyrinthSize = labyrinthSize;
    }

    public String getImageFile() {
        return imageFile;
    }

    public int getTreasureSize() {
        return treasureSize;
    }

    public int getLabyrinthSize() {
        return labyrinthSize;
    }
}
