package model.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
        if (this.cellsAlreadyUsed(cellsNecessary).isEmpty()) {
            this.shipList.put(ship, cellsNecessary);
            return true;
        }
        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> cellsAlreadyUsed(final List<Pair<Integer, Integer>> cellsNecessary) {
        List<Pair<Integer, Integer>> cellsAlreadyUsed = cellsNecessary.stream()
                                              .filter(i -> isCellUsed(i))
                                              .collect(toList());
        return cellsAlreadyUsed;
    }

    @Override
    public boolean removeShip(final Pair<Integer, Integer> box) {
        // TODO Auto-generated method stub
        return false;
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
        // TODO Auto-generated method stub
        return new ArrayList<List<Boolean>>(this.playground);
    }

    private boolean isCellUsed(final Pair<Integer, Integer> cell) {
        return this.playground.get(cell.getY()).get(cell.getX());
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
