package model.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import model.enums.Orientation;
import model.util.Pair;

import static java.util.stream.Collectors.toList;

/**
 *
 */
public class PlaygroundBattleImpl implements PlaygroundBattle {

    private List<List<Boolean>> playground;
    private final Map<List<Pair<Integer, Integer>>, Ship> shipList;

    private final int lines;
    private final int columns;

    /**
     * Constructor of battle's playground with size passed.
     * @param lines - number of box for vertical side.
     * @param columns - number of box for horizontal side.
     */
    public PlaygroundBattleImpl(final int lines, final int columns) {
        this.lines = lines;
        this.columns = columns;
        this.shipList = new HashMap<>();
        this.createPlayGround();
    }


    @Override
    public void positionShip(final Ship ship, final Pair<Integer, Integer> firstCell) throws CellsFilledException {
        final List<Pair<Integer, Integer>> cellsNecessary = ship.getOrientation().cellsUsedList(firstCell, ship.getSize());
        final List<Pair<Integer, Integer>> cellsOverlapped = this.getCellsOverlappedList(ship, firstCell, ship.getOrientation());

        if (!cellsOverlapped.isEmpty()) {
            throw new CellsFilledException(cellsOverlapped);
        }

        this.shipList.put(cellsNecessary, ship);
        cellsNecessary.forEach(i -> this.playground.get(i.getX()).set(i.getY(), true));

    }

    @Override
    public List<Pair<Integer, Integer>> getCellsOverlappedList(final Ship ship, final Pair<Integer, Integer> firstCell, final Orientation orientation) {
        return orientation.cellsUsedList(firstCell, ship.getSize()).stream()
                                                                   .filter(i -> isCellUsed(i))
                                                                   .collect(toList());
    }

    @Override
    public boolean removeShipWithCell(final Pair<Integer, Integer> cell) {
        final Set<Entry<List<Pair<Integer, Integer>>, Ship>> setOfShipEntries = this.shipList.entrySet();
        final Iterator<Entry<List<Pair<Integer, Integer>>, Ship>> iterator = setOfShipEntries.iterator();

        while (iterator.hasNext()) {
            final Entry<List<Pair<Integer, Integer>>, Ship> ship = iterator.next();
            final List<Pair<Integer, Integer>> shipCells = ship.getKey();

            if (shipCells.contains(cell)) {
                shipCells.forEach(i -> this.playground.get(i.getX()).set(i.getY(), false));
                iterator.remove();
            }
        }
        return true;
    }
    
    @Override
    public void removeShipWithShip(final Ship shipPassata) {
        final Set<Entry<List<Pair<Integer, Integer>>, Ship>> setOfShipEntries = this.shipList.entrySet();
        final Iterator<Entry<List<Pair<Integer, Integer>>, Ship>> iterator = setOfShipEntries.iterator();
 
        while (iterator.hasNext()) {
            final Entry<List<Pair<Integer, Integer>>, Ship> entry = iterator.next();
            Ship ship = entry.getValue();
            final List<Pair<Integer, Integer>> shipCells = entry.getKey();
 
            if (ship.equals(shipPassata)) {
                shipCells.forEach(i -> this.playground.get(i.getX()).set(i.getY(), false));
                iterator.remove();
            }
        }
    }

    @Override
    public void resetPlayground() {
        this.createPlayGround();
    }

    @Override
    public void removeAllShips() {
        for (Entry<List<Pair<Integer, Integer>>, Ship> list : this.shipList.entrySet()) {
            this.removeShipWithCell(list.getKey().get(0));
        }
    }


//    @Override
//    public boolean isShipPresent(final Pair<Integer, Integer> cell) throws CellAlreadyShottedException {
//        if (this.isCellUsed(cell)) {
//            throw new CellAlreadyShottedException(cell);
//        }
//        this.playground.get(cell.getX()).set(cell.getY(), true);
//        for (final Entry<List<Pair<Integer, Integer>>, Ship> v : this.shipList.entrySet()) {
//            if (v.getKey().contains(cell)) {
//                return true; 
//            }
//        }
//        return false;
//    }

    @Override
    public Optional<Entry<List<Pair<Integer, Integer>>, Ship>> shipHitted(final Pair<Integer, Integer> cell) throws CellAlreadyShottedException {

        if (this.isCellUsed(cell)) {
            throw new CellAlreadyShottedException(cell);
        }

        this.playground.get(cell.getX()).set(cell.getY(), true);
        for (final Entry<List<Pair<Integer, Integer>>, Ship> v : this.shipList.entrySet()) {
            if (v.getKey().contains(cell)) {
                v.getValue().hit();
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }


    @Override
    public Optional<Boolean> shipSunk(final List<Pair<Integer, Integer>> cells) {
        return this.shipList.containsKey(cells) ? Optional.of(this.shipList.get(cells).isDestroyed()) : Optional.empty();
    }
//    public Ship getShip(Pair<Integer, Integer> cell) {
//        for(var v : this.shipList.entrySet()) {
//            if (v.getKey().contains(cell)) {
//                return v.getValue();
//            }
//        }
//        throw new IOException()
//    }

    @Override
    public boolean cellAlreadyShotted(final Pair<Integer, Integer> cell) {
        return this.playground.get(cell.getX()).get(cell.getY());
    }

    @Override
    public int getNumberOfAliveShip() {
        int aliveShips = 0;
        for (final Entry<List<Pair<Integer, Integer>>, Ship> v : this.shipList.entrySet()) {
            if (!v.getValue().isDestroyed()) {
                aliveShips++; 
            }
        }
        return aliveShips;
    }

    @Override
    public ArrayList<List<Boolean>> getPlaygroundBattle() {
        return new ArrayList<List<Boolean>>(this.playground);
    }

    @Override
    public Map<List<Pair<Integer, Integer>>, Ship> getShips(){
        return new HashMap<List<Pair<Integer, Integer>>, Ship>(this.shipList);
    }

//    @Override
//    public boolean isCellUsed(final Pair<Integer, Integer> cell) {
//        return this.playground.get(cell.getX()).get(cell.getY());
//    }

    @Override
    public boolean isCellUsed(final Pair<Integer, Integer> cell) {
        final Set<Entry<List<Pair<Integer, Integer>>, Ship>> setOfShipEntries = this.shipList.entrySet();
        final Iterator<Entry<List<Pair<Integer, Integer>>, Ship>> iterator = setOfShipEntries.iterator();
 
        while (iterator.hasNext()) {
            final Entry<List<Pair<Integer, Integer>>, Ship> ship = iterator.next();
            final List<Pair<Integer, Integer>> shipCells = ship.getKey();
 
            if (shipCells.contains(cell)) {
                return true;
            }
        }
        return false;
    }

    private void createPlayGround() {
        this.playground = new ArrayList<>(this.lines);
        for (int i = 0; i < this.lines; i++) {
            this.playground.add(new ArrayList<>(this.columns));
            for (int j = 0; j < this.columns; j++) {
                this.playground.get(i).add(false);
            }
        }
    }

}
