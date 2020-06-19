package model.players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;

public class ArtificialPlayer extends AbstractPlayer {

    /**
     * 
     */
    private static final long serialVersionUID = -6800182305987141934L;

    private static final String DEFAULT_NAME = "IA";
    private static final String DEFAULT_PASSWORD = "battleship";

    private Map<String, Double> stats;

    public ArtificialPlayer() {
        super(DEFAULT_NAME, DEFAULT_PASSWORD);
        initStats();
    }

    private void initStats() {
        this.stats = new HashMap<>();
        Arrays.asList(StatsInfo.values()).forEach(x -> {
            this.stats.put(x.getName(), 0.00);
        });
    }

    @Override
    public final Map<String, Double> getStatistics() {
        return Collections.unmodifiableMap(this.stats);
    }

    @Override
    public final void updateStats(final String desc, final Double updatedValue) {
        this.stats.computeIfPresent(desc, (x, y) -> Double.valueOf(updatedValue));
    }

}
