package model.intelligence;

import java.util.List;

import model.match.PlaygroundBattle;
import model.util.Pair;

public interface IntelligenceComputation {

    PlaygroundBattle initShips();

    Pair<Integer, Integer> setNextToHit(List<Pair<Integer, Integer>> attackGrid);

}
