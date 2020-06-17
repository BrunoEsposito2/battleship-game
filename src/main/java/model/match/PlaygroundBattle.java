package model.match;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import model.enums.Orientation;
import model.util.Pair;

/**
 * Interface that handle the playground of battleship.
 */
public interface PlaygroundBattle {

    void positionShip(Ship ship, Pair<Integer, Integer> firstCell) throws CellsFilledException;

    List<Pair<Integer, Integer>> getCellsOverlappedList(Ship ship, Pair<Integer, Integer> firstCell, Orientation orientation);

    boolean removeShip(Pair<Integer, Integer> box);

    void removeAllShips();

    void resetPlayground();

    boolean cellAlreadyShotted(Pair<Integer, Integer> cell);

    Optional<Entry<List<Pair<Integer, Integer>>, Ship>> shipHitted(Pair<Integer, Integer> cell) throws CellAlreadyShottedException;

    Optional<Boolean> isShipSunk(List<Pair<Integer, Integer>> cells);

    boolean areThereAliveShip();

    boolean isCellUsed(Pair<Integer, Integer> cell);

    List<List<Boolean>> getPlaygroundBattle();
}
