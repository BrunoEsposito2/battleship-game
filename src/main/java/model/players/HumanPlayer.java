package model.players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;

public class HumanPlayer extends AbstractPlayer {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private Map<String, Double> stats;

    public HumanPlayer(final String name, final String password) {
        super(name, password);
        initStats();
    }

    private void initStats() {
        this.stats = new HashMap<>();
        Arrays.asList(StatsInfo.values()).forEach(x -> {
            this.stats.put(x.getName(), 0.00);
        });
    }

    @Override
    public final void updateStats(final String desc, final Double updatedValue) {
        this.stats.computeIfPresent(desc, (x, y) -> Double.valueOf(updatedValue));
    }

    @Override
    public final Map<String, Double> getStatistics() {
        return Collections.unmodifiableMap(this.stats);
    }

}
