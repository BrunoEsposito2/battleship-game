package model.players;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;
import model.intelligence.ArtificialIntelligence;

public class ArtificialPlayer extends AbstractPlayer {

    /**
     * 
     */
    private static final long serialVersionUID = -6800182305987141934L;

    private Map<String, Double> stats;
    private transient ArtificialIntelligence intelligence;

    public ArtificialPlayer(final String nameAI, final String passwordAI, final ArtificialIntelligence intelligence) {
        super(nameAI, passwordAI);
        initStats();
        this.intelligence = intelligence;
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

    public final ArtificialIntelligence getArtificialIntelligence() {
        return this.intelligence;
    }

}
