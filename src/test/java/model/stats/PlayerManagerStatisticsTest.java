package model.stats;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import model.players.HumanPlayer;
import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;

public class PlayerManagerStatisticsTest {

    private static final Double WIN_SCORE = 20.0;
    private static final Double LOSS_SCORE = 15.0;

    private Optional<List<Player>> playerList = Optional.ofNullable(new LinkedList<>());

    private PlayerManager managerPlayer;

    private Statistics stats;

    @Test
    void basicPlayerManagerWinStatsTest() {
        Player player1 = new HumanPlayer("Test1", "PassTest1");
        Player player2 = new HumanPlayer("Test2", "PassTest2");

        playerList.get().add(player1);
        playerList.get().add(player2);

        managerPlayer = new PlayerOperation(playerList);

        managerPlayer.updateWinStats(player1.getUsername(), WIN_SCORE);
        Map<String, Double> res = player1.getStatistics();

        stats = new WinnerStatsCalculator(player2, WIN_SCORE);
        stats.basicStats();
        Map<String, Double> expected = player2.getStatistics();

        assertEquals(expected, res);
    }

    @Test
    void basicPlayerManagerLossStatsTest() {
        Player player1 = new HumanPlayer("Test1", "PassTest1");
        Player player2 = new HumanPlayer("Test2", "PassTest2");

        playerList.get().add(player1);
        playerList.get().add(player2);

        managerPlayer = new PlayerOperation(playerList);

        managerPlayer.updateLosStats(player1.getUsername(), LOSS_SCORE);
        Map<String, Double> res = player1.getStatistics();

        stats = new LoserStatsCalculator(player2, LOSS_SCORE);
        stats.basicStats();
        Map<String, Double> expected = player2.getStatistics();

        assertEquals(expected, res);
    }

}
