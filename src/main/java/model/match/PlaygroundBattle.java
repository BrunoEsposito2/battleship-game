package model.match;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import model.enums.Orientation;
import model.util.Pair;

/**
 * Interface that handle the playground of battleship.
 */
public interface PlaygroundBattle {

    /**
     * Position the ship in this playground starting from passed cell.
     * If a ship will position over another one, it will be throw exception.
     * In this situation, position not be successful.
     * 
     * @param ship - Ship to position in playground
     * @param firstCell - The first cell (higher and more left used by ship)
     * 
     * @throws CellsFilledException - When ship to position is over existing ships.
     */
    void positionShip(Ship ship, Pair<Integer, Integer> firstCell) throws CellsFilledException;

    /**
     * 
     * 
     * @param ship
     * @param firstCell
     * @param orientation
     * @return list
     */
    List<Pair<Integer, Integer>> getCellsOverlappedList(Ship ship, Pair<Integer, Integer> firstCell, Orientation orientation);

    boolean removeShipWithCell(Pair<Integer, Integer> box);

    void removeShipWithShip(Ship shipPassata);

    void removeAllShips();

    void resetPlayground();

    boolean cellAlreadyShotted(Pair<Integer, Integer> cell);

    Optional<Entry<List<Pair<Integer, Integer>>, Ship>> shipHitted(Pair<Integer, Integer> cell) throws CellAlreadyShotException;

    Optional<Boolean> shipSunk(List<Pair<Integer, Integer>> cells);

    int getNumberOfAliveShip();

    boolean isCellUsedByShip(Pair<Integer, Integer> cell);

    List<List<Boolean>> getPlaygroundBattle();

    Map<List<Pair<Integer, Integer>>, Ship> getShips();

    int getDamage();

    boolean isCellUsed(Pair<Integer, Integer> cell);
}
