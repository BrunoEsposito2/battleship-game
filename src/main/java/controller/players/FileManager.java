<<<<<<< HEAD
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
=======
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
>>>>>>> 53a18f5fdf9361d738b862b8ebc8a40127a3706c
