package model.match;

import java.util.List;

import model.enums.Orientation;
import model.util.Pair;

/**
 * Interface that handle the playground of battleship.
 */
public interface PlaygroundBattle {

    boolean positionShip(Ship ship, Pair<Integer, Integer> firstCell, Orientation orientation);

    List<Pair<Integer, Integer>> getCellsOverlappedList(List<Pair<Integer, Integer>> cellsNecessary);

    boolean removeShip(Pair<Integer, Integer> box);

    boolean cellAlreadyShotted(Pair<Integer, Integer> cell);

    boolean shot(Pair<Integer, Integer> box);

    boolean areThereAliveShip();

    List<List<Boolean>> getPlaygroundBattle();
}
