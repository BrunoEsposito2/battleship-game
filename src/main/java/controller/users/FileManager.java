package controller.users;

import java.util.List;
import java.util.Optional;

import model.players.Player;

public interface FileManager {

    void saveUser(Player player);

    Optional<List<Player>> loadUsers();

    void removeUser(Player player);

}
