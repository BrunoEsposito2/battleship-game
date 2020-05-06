package model.match;

import java.util.ArrayList;
import java.util.List;

import model.util.Pair;

/**
 *
 */
public class PlayGroundBattleImpl implements PlaygroundBattle {

    private List<List<Boolean>> playground;
    private int columns;
    private int lines;

    /**
     * Constructor of battle's playground with size passed.
     * @param size - number of box for side.
     */
    public PlayGroundBattleImpl(final int columns, final int lines) {
        this.columns = columns;
        this.lines = lines;
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

    private void createPlayGround() {
        this.playground = new ArrayList<List<Boolean>>(this.columns);
        for (int i = 0; i < this.columns ; i++) {
            this.playground.add(new ArrayList<Boolean>(this.lines));
            for (int j = 0; j < this.lines; j++) {
                this.playground.get(j).add(false);
            }
        }
    }
    
    
}
