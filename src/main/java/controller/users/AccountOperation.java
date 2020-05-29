package controller.users;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import model.players.PlayerManager;
import model.players.PlayerOperation;
import model.players.Player;
import model.players.HumanPlayer;

public class AccountOperation implements AccountManager {

    private final FileManager system;
    private final PlayerManager modelMng;

    public AccountOperation() {
        this.system = new FileSystemManager();
        this.modelMng = new PlayerOperation(initAllUsers());
    }

    private Optional<List<Player>> initAllUsers() {
        if (this.system.loadUsers().isPresent()) {
            return this.system.loadUsers();
        } else {
            return Optional.of(new LinkedList<>());
        }
    }

    @Override
    public final void createAccount(final String userName, final String password) {
        try {
            Optional<Player> p = this.modelMng.addPlayer(userName, password);
            if (p.isPresent()) {
                this.system.saveUser(p.get());
            } else {
                throw new Exception("Account already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public void setWinner(final String userName, final Double scoreValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setLoser(final String userName, final Double scoreValue) {
        // TODO Auto-generated method stub

    }

    @Override
    public final void removeAccount(final String userName, final String password) {
        try {
            if (this.modelMng.removePlayer(userName, password)) {
                this.system.removeUser(new HumanPlayer(userName, password));
            } else {
                throw new Exception("Invalid info: Account already not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

