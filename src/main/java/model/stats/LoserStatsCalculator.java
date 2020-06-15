package model.stats;

import model.enums.StatsInfo;
import model.players.ArtificialPlayer;
import model.players.HumanPlayer;
import model.players.Player;

public class LoserStatsCalculator implements Statistics {

    private static final double DEFAULT_VAL = 0.00;

    private Player loser;
    private double matchScore;

    public LoserStatsCalculator(final HumanPlayer loser, final double score) {
        this.loser = loser;
        this.matchScore = score;
    }

    public LoserStatsCalculator(final ArtificialPlayer loser, final double score) {
        this.loser = loser;
        this.matchScore = score;
    }

    @Override
    public final void basicStats() {
        final double tot = getInfoValue(StatsInfo.TOTALS.getName()) + 1;
        final double loss = getInfoValue(StatsInfo.LOSS.getName()) + 1;

        bestScoreCheck();

        this.loser.updateStats(StatsInfo.TOTALS.getName(), tot);
        this.loser.updateStats(StatsInfo.LOSS.getName(), loss);
        this.loser.updateStats(StatsInfo.LOSS_RATE.getName(), (loss / tot) * 100);
    }

    private void bestScoreCheck() {
        double record = getInfoValue(StatsInfo.RECORD.getName());

        if (record < this.matchScore) {
            this.loser.updateStats(StatsInfo.RECORD.getName(), this.matchScore);
        }
    }

    private Double getInfoValue(final String name) {
        return this.loser.getStatistics().getOrDefault(name, DEFAULT_VAL);
    }

}
