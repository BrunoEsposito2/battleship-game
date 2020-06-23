package model.intelligence;

public abstract class ArtificialIntelligence {

    private IntelligenceComputation intelligence;

    public ArtificialIntelligence(final IntelligenceComputation intelligence) {
        this.intelligence = intelligence;
    }

    protected final IntelligenceComputation getIntelligenceComputation() {
        return this.intelligence;
    }

    public abstract void initShipsOnGrid();

    public abstract void setNextHitPoint();

}
