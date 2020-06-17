package model.players;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;

public class ArtificialPlayer implements Player, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6800182305987141934L;

    private static final String DEFAULT_NAME = "IA";
    private static final String DEFAULT_PASSWORD = "battleship";

    private transient boolean online;
    private Map<String, Double> stats;

    public ArtificialPlayer() {
        this.online = false;
        initStats();
    }

    private void initStats() {
        this.stats = new HashMap<>();
        Arrays.asList(StatsInfo.values()).forEach(x -> {
            this.stats.put(x.getName(), 0.00);
        });
    }

    @Override
    public final String getUsername() {
        return ArtificialPlayer.DEFAULT_NAME;
    }

    @Override
    public final String getPassword() {
        return ArtificialPlayer.DEFAULT_PASSWORD;
    }

    @Override
    public final Map<String, Double> getStatistics() {
        return Collections.unmodifiableMap(this.stats);
    }

    @Override
    public final void setLogin(final boolean value) {
        this.online = value;
    }

    @Override
    public final boolean isPlaying() {
        return this.online;
    }

    @Override
    public final String toString() {
        return "IA username: ";
    }

    @Override
    public final void updateStats(final String desc, final Double updatedValue) {
        this.stats.computeIfPresent(desc, (x, y) -> Double.valueOf(updatedValue));
    }

}
