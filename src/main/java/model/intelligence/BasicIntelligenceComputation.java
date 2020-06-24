package model.intelligence;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import model.enums.ShipType;
import model.match.CellsFilledException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.match.Ship;
import model.util.Pair;

public class BasicIntelligenceComputation implements IntelligenceComputation {

    private final int maxRows;
    private final int maxCols;

    public BasicIntelligenceComputation(final int maxRows, final int maxCols) {
        this.maxRows = maxRows;
        this.maxCols = maxCols;
    }

    private Pair<Integer, Integer> getRandomPosition() {
        Optional<Random> rand = Optional.empty();
        return new Pair<Integer, Integer>(rand.get().nextInt(this.maxRows), rand.get().nextInt(this.maxCols));
    }

    private boolean checkCollision(final PlaygroundBattle shipsGrid, final ShipType type) {
        try {
            shipsGrid.positionShip(new Ship(ShipType.BATTLESHIP), this.getRandomPosition());
            return true;
        } catch (CellsFilledException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public final PlaygroundBattle initShips() {
        final PlaygroundBattle shipsGrid = new PlaygroundBattleImpl(this.maxRows, this.maxCols);

        for (ShipType ship : ShipType.values()) {
            boolean setShip = this.checkCollision(shipsGrid, ship);
            if (!setShip) {
                boolean flag = true;
                while (flag) {
                    if (this.checkCollision(shipsGrid, ship)) {
                        flag = false;
                    }
                }
            }
        }
        return shipsGrid;
    }

    @Override
    public final Pair<Integer, Integer> setNextToHit(final List<Pair<Integer, Integer>> attackGrid) {
        Pair<Integer, Integer> nextPos = this.getRandomPosition();

        if (attackGrid.contains(nextPos)) {
            boolean flag = true;
            while (flag) {
                nextPos = this.getRandomPosition();
                if (!attackGrid.contains(nextPos)) {
                    flag = false;
                }
            }
        }
        return nextPos;
    }

}
