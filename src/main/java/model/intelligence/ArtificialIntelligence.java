package model.intelligence;

import model.match.PlaygroundBattle;
import model.util.Pair;

public abstract class ArtificialIntelligence {

    private IntelligenceComputation intelligence;

    public ArtificialIntelligence(final IntelligenceComputation intelligence) {
        this.intelligence = intelligence;
    }

    protected final IntelligenceComputation getIntelligenceComputation() {
        return this.intelligence;
    }

    public abstract PlaygroundBattle initShipsOnGrid();

    public abstract Pair<Integer, Integer> setNextHitPoint();

}
