package model.players;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import model.stats.LoserStatsCalculator;
import model.stats.Statistics;
import model.stats.WinnerStatsCalculator;

public class PlayerOperation implements PlayerManager {

    private final Optional<List<Player>> players;
    private Statistics stats;

    public PlayerOperation(final Optional<List<Player>> initUsers) {
        this.players = initUsers;
        this.stats = null;
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
                        .filter(x -> x.getUsername().equalsIgnoreCase(userName))
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

    @Override
    public final boolean updateWinStats(final String userName, final Double score) {
        if (usernameExists(userName)) {
            this.players.get().forEach(x -> {
                if (x instanceof HumanPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = new WinnerStatsCalculator((HumanPlayer) x, score);
                } else if (x instanceof ArtificialPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = new WinnerStatsCalculator((ArtificialPlayer) x, score);
                }
            });
            this.stats.basicStats();
            return true;
        }
        return false;
    }

    @Override
    public final boolean updateLosStats(final String userName, final Double score) {
        if (usernameExists(userName)) {
            this.players.get().forEach(x -> {
                if (x instanceof HumanPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = new LoserStatsCalculator((HumanPlayer) x, score);
                } else if (x instanceof ArtificialPlayer && x.getUsername().equalsIgnoreCase(userName)) {
                    this.stats = new LoserStatsCalculator((ArtificialPlayer) x, score);
                }
            });
            this.stats.basicStats();
            return true;
        }
        return false;
    }

}
