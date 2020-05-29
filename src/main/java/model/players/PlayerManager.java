package model.players;

import java.util.List;
import java.util.Optional;

public interface PlayerManager {

    Optional<Player> addPlayer(String userName, String password);

    boolean removePlayer(String userName, String password);

    boolean setLogIn(String userName, String password);

    boolean setLogOut(String userName);

    Optional<List<Player>> getPlayers();
}