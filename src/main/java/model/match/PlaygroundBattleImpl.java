package model.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import model.enums.Orientation;
import model.util.Pair;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class PlaygroundBattleImpl implements PlaygroundBattle {

    private List<List<Boolean>> playground;
    private Map<Ship, List<Pair<Integer, Integer>>> shipList = new HashMap<Ship, List<Pair<Integer, Integer>>>();

    private int lines;
    private int columns;

    /**
     * Constructor of battle's playground with size passed.
     * @param lines - number of box for vertical side.
     * @param columns - number of box for horizontal side.
     */
    public PlaygroundBattleImpl(final int lines, final int columns) {
        this.lines = lines;
        this.columns = columns;
        this.createPlayGround();
    }

    @Override
    public boolean positionShip(final Ship ship, final Pair<Integer, Integer> firstCell, final Orientation orientation) {
        List<Pair<Integer, Integer>> cellsNecessary = orientation.cellsUsedList(firstCell, ship.getSize());
        /*
         * If list is empty there aren't overlap.
         */
        if (this.getCellsOverlappedList(ship, firstCell, orientation).isEmpty()) {
            this.shipList.put(ship, cellsNecessary);
            cellsNecessary.forEach(i -> this.playground.get(i.getX()).set(i.getY(), true));
            return true;
        }
        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> getCellsOverlappedList(final Ship ship, final Pair<Integer, Integer> firstCell, final Orientation orientation) {
        List<Pair<Integer, Integer>> cellsAlreadyUsed = orientation.cellsUsedList(firstCell, ship.getSize()).stream()
                                              .filter(i -> isCellUsed(i))
                                              .collect(toList());
        return cellsAlreadyUsed;
    }

    @Override
    public boolean removeShip(final Pair<Integer, Integer> cell) {
        Set<Entry<Ship, List<Pair<Integer, Integer>>>> setOfShipEntries = this.shipList.entrySet();
        Iterator<Entry<Ship, List<Pair<Integer, Integer>>>> iterator = setOfShipEntries.iterator();

        while (iterator.hasNext()) {
            Entry<Ship, List<Pair<Integer, Integer>>> ship = iterator.next();
            List<Pair<Integer, Integer>> shipCells = ship.getValue();

            if (shipCells.contains(cell)) {
                shipCells.forEach(i -> this.playground.get(i.getX()).set(i.getY(), false));
                iterator.remove();
            }
        }
        return true;
    }

    @Override
    public boolean shot(final Pair<Integer, Integer> cell) {
        this.playground.get(cell.getX()).set(cell.getY(), true);
        for (Entry<Ship, List<Pair<Integer, Integer>>> v : this.shipList.entrySet()) {
            if (v.getValue().contains(cell)) {
                return true; 
            }
        }
        return false;
    }

    @Override
    public boolean cellAlreadyShotted(final Pair<Integer, Integer> cell) {
        return this.playground.get(cell.getX()).get(cell.getY());
    }

    @Override
    public boolean areThereAliveShip() {
        for (Entry<Ship, List<Pair<Integer, Integer>>> v : this.shipList.entrySet()) {
            if (!v.getKey().isDestroyed()) {
                return true; 
            }
        }
        return false;
    }

    @Override
    public ArrayList<List<Boolean>> getPlaygroundBattle() {
        return new ArrayList<List<Boolean>>(this.playground);
    }
    
    private boolean isCellUsed(final Pair<Integer, Integer> cell) {
        return this.playground.get(cell.getX()).get(cell.getY());
    }

    private void createPlayGround() {
        this.playground = new ArrayList<List<Boolean>>(this.lines);
        for (int i = 0; i < this.lines; i++) {
            this.playground.add(new ArrayList<Boolean>(this.columns));
            for (int j = 0; j < this.columns; j++) {
                this.playground.get(i).add(false);
            }
        }
    }

}
