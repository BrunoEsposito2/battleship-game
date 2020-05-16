package controller.players;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AccountOperation implements AccountManager {

    private Optional<List<Player>> users;
    private final FileManager system;

    public AccountOperation() {
        this.system = new FileSystemManager();
        initAllUsers();
    }

    private void initAllUsers() {
        if (this.system.loadPlayers().isPresent()) {
            this.users = this.system.loadPlayers();
        } else {
            this.users = Optional.of(new LinkedList<>());
        }
    }

    private boolean usernameExists(final String userName) {
        return (this.users.get().stream()
                .map(x -> x.getUsername())
                .filter(x -> x.equalsIgnoreCase(userName))
                .count() == 1) ? true : false;
    }

    private boolean infoAreValid(final String userName, final String password) {
        return (usernameExists(userName) 
                && (this.users.get().stream()
                        .map(x -> x.getPassword())
                        .filter(x -> x.equals(password))
                        .count() == 1)) ? true : false;
    }

    @Override
    public final void createAccount(final String userName, final String password) {
        try {
            if (!usernameExists(userName)) {
                Player p = new HumanPlayer(userName, password);
                this.users.get().add(p);
            } else {
                throw new Exception("Account already exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.users.get().forEach(x -> this.system.savePlayer(x));
        }
    }

    @Override
    public final boolean logInAccount(final String userName, final String password) {
        try {
            if (infoAreValid(userName, password)) {
                this.users.get().forEach(x -> {
                    if (x.getUsername().equalsIgnoreCase(userName)) {
                        x.setLogin(true);
                    }
                });
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
            if (usernameExists(userName)) {
                this.users.get().forEach(x -> {
                    if (x.getUsername().equalsIgnoreCase(userName)) {
                        x.setLogin(false);
                    }
                });
            } else {
                throw new Exception("Log out Failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.users.get().forEach(x -> this.system.savePlayer(x));
        }
    }

    @Override
    public final List<String> getAllUsername() {
        return Collections.unmodifiableList(this.users.get().stream()
                .map(x -> x.getUsername())
                .collect(Collectors.toList()));
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
            if (infoAreValid(userName, password)) {
                this.users.get().forEach(x -> {
                    if (x.getUsername().equals(userName)) {
                        this.users.get().remove(x);
                        this.system.removePlayer(x);
                    }
                });
            } else {
                throw new Exception("Invalid info: Account already not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.users.get().forEach(x -> this.system.savePlayer(x));
        }
    }

}

