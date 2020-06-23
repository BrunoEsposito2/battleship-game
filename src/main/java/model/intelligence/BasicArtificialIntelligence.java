package model.intelligence;

public class BasicArtificialIntelligence extends ArtificialIntelligence {

    public BasicArtificialIntelligence(final IntelligenceComputation intelligence) {
        super(intelligence);
    }

    @Override
    public final void initShipsOnGrid() {
        this.getIntelligenceComputation().initShips();
    }

    @Override
    public final void setNextHitPoint() {
        this.getIntelligenceComputation().setNextToHit();
    }

}
