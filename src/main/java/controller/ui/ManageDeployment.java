package controller.ui;

import java.util.HashMap;
import java.util.Map;

import model.util.Pair;
import model.match.Ship;
import model.enums.ShipType;
import javafx.scene.image.ImageView;

final class ManageDeployment {
    
  //Offset which is put every time a ship is dropped on the grid in HORIZONTAL
    private static final int CARRIER_HORIZ_OFFSET = -40;
    private static final int BATTLESHIP_HORIZ_OFFSET = 32;
    private static final int CRUISER_HORIZ_OFFSET = 12;
    private static final int SUBMARINE_HORIZ_OFFSET = 5;
    private static final int DESTROYER_HORIZ_OFFSET = -10;
    
    //Offset which is put every time a ship is dropped on the grid in VERTICAL
    private static final int CARRIER_VERT_OFFSET = -88;
    private static final int BATTLESHIP_VERT_OFFSET = 9;
    private static final int CRUISER_VERT_OFFSET = 10;
    private static final int SUBMARINE_VERT_OFFSET = 6;
    private static final int DESTROYER_VERT_OFFSET = 10;
    
    private Map<ImageView, Pair<Ship, Pair<Integer, Integer>>> ships;
    
    public ManageDeployment() {
        this.ships = new HashMap<>();
    }
    
    /**
     * Populates the map of ships
     */
    Map<ImageView, Pair<Ship, Pair<Integer, Integer>>> createShips(final ImageView carrier, final ImageView battleship, final ImageView cruiser, final ImageView submarine, final ImageView destroyer) {
        this.ships.put(carrier, new Pair<>(new Ship(ShipType.CARRIER), 
                       new Pair<>(CARRIER_HORIZ_OFFSET, CARRIER_VERT_OFFSET)));
        this.ships.put(battleship, new Pair<>(new Ship(ShipType.BATTLESHIP), 
                       new Pair<>(BATTLESHIP_HORIZ_OFFSET, BATTLESHIP_VERT_OFFSET)));
        this.ships.put(cruiser, new Pair<>(new Ship(ShipType.CRUISER),
                       new Pair<>(CRUISER_HORIZ_OFFSET, CRUISER_VERT_OFFSET)));
        this.ships.put(submarine, new Pair<>(new Ship(ShipType.SUBMARINE),
                       new Pair<>(SUBMARINE_HORIZ_OFFSET, SUBMARINE_VERT_OFFSET)));
        this.ships.put(destroyer, new Pair<>(new Ship(ShipType.DESTROYER),
                       new Pair<>(DESTROYER_HORIZ_OFFSET, DESTROYER_VERT_OFFSET)));
        
        return this.ships;
    }

}
