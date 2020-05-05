package model.enums;

/**
 * This enum models the different types of ship of the game
 * 
 */
public enum ShipType {

    // fileName should be the png file, without the extension (Carrier -> Carrier.png)
    CARRIER("Carrier"),
    BATTLESHIP("Battleship"),
    CRUISER("Cruiser"),
    SUBMARINE("Submarine"),
    DESTROYER("Destroyer");

    private final String fileName;

    ShipType(final String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return The file's name
     */
    public String getFileName() {
        return this.fileName;
    }

}
