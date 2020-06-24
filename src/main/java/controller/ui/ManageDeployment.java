package controller.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.util.Pair;
import model.match.PlaygroundBattle;
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
    private PlaygroundBattle playgroundBattle;
    
    protected ManageDeployment(PlaygroundBattle playgroundBattle) {
        this.ships = new HashMap<>();
        this.playgroundBattle = playgroundBattle;
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
    
    /**
     * Method to extract the size from the selected ship
     * 
     * @param draggingShip - the ImageView of the ship currently selected
     * @return ship's size
     */
    int extractSize(ImageView draggingShip) {
        List<Ship> resultUserList = this.ships.entrySet().stream()
                .filter(x -> x.getKey().equals(draggingShip))
                .map(x -> x.getValue().getX())
                .collect(Collectors.toList());
        if (resultUserList.size() != 1) {
            throw new IllegalStateException();
        }
        
        return resultUserList.get(0).getSize();
    }
    
    /**
     * Method to extract the current ship
     * 
     * @param draggingShip - the ImageView of the selected ship
     * @return Ship object of the selected ship
     */
    Ship extractShip(ImageView draggingShip) {
        List<Ship> resultUserList = this.ships.entrySet().stream()
                .filter(x -> x.getKey().equals(draggingShip))
                .map(x -> x.getValue().getX())
                .collect(Collectors.toList());
        if (resultUserList.size() != 1) {
            throw new IllegalStateException();
        }
        
        Ship ship = resultUserList.get(0);
        return ship;
    }
    
    /**
     * Method to check if a particular ship has been dropped in the grid
     * 
     * @param ship - the current ship
     * @return true if the ship is present
     */
    boolean checkShip(Ship ship) {
        Map<List<Pair<Integer, Integer>>, Ship> map = playgroundBattle.getShips();
        return map.containsValue(ship);
    }
    
    /**
     * Method to extract the horizontal offset from the selected ship
     * 
     * @param draggingShip - the ImageView of the selected ship
     * @return horizontal offset
     */
    Optional<Integer> extractHorizontalOffset(ImageView draggingShip) {
        for (Entry<ImageView, Pair<Ship, Pair<Integer, Integer>>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                return Optional.of(entry.getValue().getY().getX());
            }
        }
        return Optional.empty();
    }
    
    /**
     * Method to extract the vertical offset from the selected ship
     * 
     * @param draggingShip - the ImageView of the selected ship
     * @return vertical offset
     */
    Optional<Integer> extractVerticalOffset(ImageView draggingShip) {
        for (Entry<ImageView, Pair<Ship, Pair<Integer, Integer>>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                return Optional.of(entry.getValue().getY().getY());
            }
        }
        return Optional.empty();
    }

}
