package model.match;

import java.util.List;

import model.enums.Orientation;
import model.util.Pair;

/**
 * Interface that handle the playground of battleship.
 */
public interface PlaygroundBattle {

    void positionShip(Ship ship, Pair<Integer, Integer> firstCell, Orientation orientation) throws CellsFilledException;

    List<Pair<Integer, Integer>> getCellsOverlappedList(Ship ship, Pair<Integer, Integer> firstCell, Orientation orientation);

    boolean removeShip(Pair<Integer, Integer> box);

    void removeAllShips();

    void resetPlayground();

    boolean cellAlreadyShotted(Pair<Integer, Integer> cell);

    boolean shot(Pair<Integer, Integer> cell) throws CellAlreadyShottedException;

    boolean areThereAliveShip();

    List<List<Boolean>> getPlaygroundBattle();
}
