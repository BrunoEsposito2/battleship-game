package model.players;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PlayerOperation implements PlayerManager {

    private final Optional<List<Player>> players;

    public PlayerOperation(final Optional<List<Player>> initUsers) {
        this.players = initUsers;
    }

    private boolean usernameExists(final String userName) {
        return (this.players.get().stream()
                .map(x -> x.getUsername())
                .filter(x -> x.equalsIgnoreCase(userName))
                .count() == 1) ? true : false;
    }

    private boolean infoAreValid(final String userName, final String password) {
        return (usernameExists(userName)
                && (this.players.get().stream()
                        .map(x -> x.getPassword())
                        .filter(x -> x.equals(password))
                        .count() == 1)) ? true : false;
    }

    @Override
    public final Optional<Player> addPlayer(final String userName, final String password) {
        if (!usernameExists(userName)) {
            Player p = new HumanPlayer(userName, password);
            this.players.get().add(p);
            return Optional.ofNullable(p);
        }
        return Optional.empty();
    }

    @Override
    public final boolean removePlayer(final String userName, final String password) {
        if (infoAreValid(userName, password)) {
            this.players.get().forEach(x -> {
                if (x instanceof HumanPlayer && x.getUsername().equals(userName)) {
                    this.players.get().remove(x);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public final boolean setLogIn(final String userName, final String password) {
        if (infoAreValid(userName, password)) {
            this.players.get().forEach(x -> {
                if (x.getUsername().equalsIgnoreCase(userName)) {
                    x.setLogin(true);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public final boolean setLogOut(final String userName) {
        if (usernameExists(userName)) {
            this.players.get().forEach(x -> {
                if (x.getUsername().equalsIgnoreCase(userName)) {
                    x.setLogin(false);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public final Optional<List<Player>> getPlayers() {
        return Optional.of(Collections.unmodifiableList(this.players.get()));
    }

}
