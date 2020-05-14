package controller.players;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.stats = new HashMap<>();
        HumanStats.initHumanStats(this.stats);
    }

    @Override
    public final void setStatistics(final Map<String, Double> values) {
        this.stats = values;
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

    private static final class HumanStats {

        private static final String TOT = "Totals";

        private static final String WIN = "Wins";

        private static final String LOSS = "Loss";

        private static final String REC = "Record";

        private static final String W_PERC = "Win Rate";

        private static final String L_PERC = "Loss Rate";

        private static final List<String> INFO_STATS = Arrays.asList(TOT, WIN, LOSS, REC, W_PERC, L_PERC);

        private HumanStats() {
        }

        public static void initHumanStats(final Map<String, Double> stats) {
            INFO_STATS.stream().forEach(x -> stats.put(x, 0.0));
        }

    }

}
