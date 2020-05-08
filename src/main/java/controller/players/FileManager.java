package controller.players;

import java.util.List;
import java.util.Map;

public interface FileManager {

    void savePlayer(String userName, String password);

    Map<String, String> loadPlayers();

    Map<String, List<Double>> loadStats(String userName);

    void removePlayer(String userName);

    void saveStats(Map<String, List<Double>> scores);
}
