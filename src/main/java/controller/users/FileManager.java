package controller.players;

import java.util.List;
import java.util.Optional;

public interface FileManager {

    void savePlayer(Player player);

    Optional<List<Player>> loadPlayers();

    void removePlayer(Player player);

}
