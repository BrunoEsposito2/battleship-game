package model.intelligence;

import java.util.Optional;
import java.util.Random;

import model.util.Pair;

public class BasicIntelligenceComputation implements IntelligenceComputation {

    private static final Integer MAX_ROWS = 8;  //da Angelo tramite playgroundbattle
    private static final Integer MAX_COLS = 8;  //IDEM

    public BasicIntelligenceComputation() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void initShips() {
    }

    private Pair<Integer, Integer> shipRandomPosition() {
        Optional<Random> rand = Optional.empty();
        return new Pair<Integer, Integer>(rand.get().nextInt(MAX_ROWS), rand.get().nextInt(MAX_COLS));
    }

    @Override
    public void setNextToHit() {
    }

}
