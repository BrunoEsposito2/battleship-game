package model.players;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import model.enums.StatsInfo;

public class HumanPlayer implements Player, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8116603641426068234L;

    private String userName;
    private String password;
    private transient boolean online;
    private Map<String, Double> stats;

    public HumanPlayer(final String name, final String password) {
        this.userName = name;
        this.password = password;
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
        return this.userName;
    }

    @Override
    public final String getPassword() {
        return this.password;
    }

    @Override
    public final void updateStats(final String desc, final Double updatedValue) {
        this.stats.computeIfPresent(desc, (x, y) -> Double.valueOf(updatedValue));
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
        return "Username: " + this.userName;
    }

}
