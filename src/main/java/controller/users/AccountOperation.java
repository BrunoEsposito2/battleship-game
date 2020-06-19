package controller.users;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.players.PlayerManager;
import model.players.Player;
import model.Model;
import model.players.HumanPlayer;

public class AccountOperation implements AccountManager {

    private final FileManager system;
    private final PlayerManager modelMng;

    public AccountOperation(final Model model) {
        this.system = new FileSystemManager();
        this.modelMng = model.setPlayerManager(initAllUsers());
    }

    private Optional<List<Player>> initAllUsers() {
        if (this.system.loadUsers().isPresent()) {
            return this.system.loadUsers();
        } else {
            return Optional.of(new LinkedList<>());
        }
    }

    @Override
    public final void createAccount(final String userName, final String password) throws Exception {
        try {
            Optional<Player> p = this.modelMng.addPlayer(userName, password);
            if (p.isPresent()) {
                this.system.saveUser(p.get());
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Account already exists");
        }
    }

    @Override
    public final boolean logInAccount(final String userName, final String password) {
        try {
            if (this.modelMng.setLogIn(userName, password)) {
                return true;
            } else {
                throw new Exception("Invalid Info: Account doesn't exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public final void logOutAccount(final String userName) {
        try {
            if (!this.modelMng.setLogOut(userName)) {
                throw new Exception("Log out Failed");
            } 
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.modelMng.getPlayers().get().forEach(x -> this.system.saveUser(x));
        }
    }

    @Override
    public final Optional<List<String>> getAllUsername() {
        return Optional.of(Collections.unmodifiableList(this.modelMng.getPlayers().get()
                .stream()
                .map(x -> x.getUsername())
                .collect(Collectors.toList())));
    }

    @Override
    public final void setWinner(final String userName, final Double scoreValue) throws Exception {
        try {
            if (!this.modelMng.updateWinStats(userName, scoreValue)) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Invalid Winner Infos");
        } finally {
            this.modelMng.getPlayers().get().forEach(x -> this.system.saveUser(x));
        }
    }

    @Override
    public final void setLoser(final String userName, final Double scoreValue) throws Exception {
        try {
            if (!this.modelMng.updateLosStats(userName, scoreValue)) {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Invalid Loser Infos");
        } finally {
            this.modelMng.getPlayers().get().forEach(x -> this.system.saveUser(x));
        }
    }

    @Override
    public final void removeAccount(final String userName, final String password) throws Exception {
        try {
            if (this.modelMng.removePlayer(userName, password)) {
                this.system.removeUser(new HumanPlayer(userName, password));
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Invalid info: Account already not exists");
        }
    }

}

