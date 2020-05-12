package controller.players;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class HumanStats {

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
