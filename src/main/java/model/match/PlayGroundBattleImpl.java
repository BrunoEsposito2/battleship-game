package model.match;

import java.util.ArrayList;
import java.util.List;

import model.util.Pair;

/**
 *
 */
public class PlayGroundBattleImpl implements PlaygroundBattle {

    private List<List<Boolean>> playground;
    private int lines;
    private int columns;

    /**
     * Constructor of battle's playground with size passed.
     * @param size - number of box for side.
     */
    public PlayGroundBattleImpl(final int lines, final int columns) {
        this.lines = lines;
        this.columns = columns;
        this.createPlayGround();
    }

    @Override
    public boolean positionShip(final ShipAngelo ship, final Pair<Integer, Integer> box) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isPositionable(final ShipAngelo ship, final Pair<Integer, Integer> box) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeShip(final Pair<Integer, Integer> box) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean shot(final Pair<Integer, Integer> box) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean areThereAliveShip() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ArrayList<List<Boolean>> getPlaygroundBattle() {
        // TODO Auto-generated method stub
        return new ArrayList<List<Boolean>>(this.playground);
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
