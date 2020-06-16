package model.stats;

import model.enums.StatsInfo;
import model.players.ArtificialPlayer;
import model.players.HumanPlayer;
import model.players.Player;

public class WinnerStatsCalculator implements Statistics {

    private static final double DEFAULT_VAL = 0.00;

    private Player winner;
    private double matchScore;

    public WinnerStatsCalculator(final HumanPlayer winner, final double score) {
        this.winner = winner;
        this.matchScore = score;
    }

    public WinnerStatsCalculator(final ArtificialPlayer winner, final double score) {
        this.winner = winner;
        this.matchScore = score;
    }

    @Override
    public final void basicStats() {
        final double tot = getInfoValue(StatsInfo.TOTALS.getName()) + 1;
        final double wins = getInfoValue(StatsInfo.WINS.getName()) + 1;

        bestScoreCheck();                                                           //update of score "Record".

        this.winner.updateStats(StatsInfo.TOTALS.getName(), tot);                   //update of the "Totals" match played.
        this.winner.updateStats(StatsInfo.WINS.getName(), wins);                    //update of the number of all "Wons". 
        this.winner.updateStats(StatsInfo.WINS_RATE.getName(), (wins / tot) * 100); //calculation of new "Won_Rate".
    }

    private void bestScoreCheck() {
        double record = getInfoValue(StatsInfo.RECORD.getName());

        if (record < this.matchScore) {
            this.winner.updateStats(StatsInfo.RECORD.getName(), this.matchScore);
        }
    }

    private Double getInfoValue(final String name) {
        return this.winner.getStatistics().getOrDefault(name, DEFAULT_VAL);
    }

}
