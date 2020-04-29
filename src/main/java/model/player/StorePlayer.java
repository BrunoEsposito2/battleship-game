package model.player;

import model.enums.PlayerType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Represents the storage of each player profile.
 * 
 */
public class StorePlayer implements Player {

    private Map<PlayerType, PlayerPrototype> profiles = new HashMap<>();

    public StorePlayer() {
    }

    public final Optional<PlayerPrototype> getPlayerInfo(final PlayerType type, final String username) {
        return Optional.of(this.profiles.entrySet().stream()
                    .filter(x -> x.getKey().equals(type))
                    .filter(y -> y.getValue().getUsername().equals(username))
                    .findFirst().get().getValue());
    }

    /**
     * Aggiungere salvataggio su file.
     * Aggiungere controllo di non inserimento copie.
     * 
     * @param type
     * @param username
     * @param password
     */
    public final void createPlayerProfile(final PlayerType type, final String username, final String password) {
        if (type.equals(PlayerType.HUMAN)) {
            this.profiles.put(type, new HumanPlayer(username, password));
        } else if (type.equals(PlayerType.ARTIFICIAL)) {
            this.profiles.put(type, new ArtificialPlayer(username, password));
        }
    }
}
