package model.match;

import model.util.Pair;

/**
 * Interface that handle the playground of battleship.
 */
public interface PlaygroundBattle {

    boolean positionShip(ShipAngelo ship, Pair<Integer, Integer> box);

    boolean isPositionable(ShipAngelo ship, Pair<Integer, Integer> box);

    boolean removeShip(Pair<Integer, Integer> box);

    boolean shot(Pair<Integer, Integer> box);
}
