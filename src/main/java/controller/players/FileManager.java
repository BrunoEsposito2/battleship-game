package controller.players;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FileManager {

    void savePlayer(HumanPlayer player);

    Optional<List<Player>> loadPlayers();

    void removePlayer(HumanPlayer player);

    void saveStats(Map<String, List<Double>> scores);
}
