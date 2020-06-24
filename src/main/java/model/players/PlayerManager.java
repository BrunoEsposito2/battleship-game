package model.players;

import java.util.List;
import java.util.Optional;

public interface PlayerManager {

    Optional<Player> addPlayer(String userName, String password);

    void addArtificialPlayer(ArtificialPlayer playerAI);

    boolean removePlayer(String userName, String password);

    boolean setLogIn(String userName, String password);

    boolean setLogOut(String userName);

    boolean updateWinStats(String userName, Double score);

    boolean updateLosStats(String userName, Double score);

    Optional<List<Player>> getPlayers();

    boolean artificialExists();
}
