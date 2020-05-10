<<<<<<< HEAD
package controller.players;

import java.util.LinkedList;
import java.util.List;

public class AccountOperation implements AccountManager {

    private List<Player> users;
    private final FileManager system;

    public AccountOperation() {
        this.users = new LinkedList<Player>();
        this.system = new FileSystemManager();
    }

    @Override
    public void createAccount(final String userName, final String password) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public final boolean logInAccount(final String userName, final String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void logOutAccount(final String userName) {
        // TODO Auto-generated method stub

    }

    @Override
    public final List<String> getAllUsername() {
        // TODO Auto-generated method stub
        return null;
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
    public void removeAccount(final String userName) {
        // TODO Auto-generated method stub

    }

}
=======
package controller.players;

import java.util.LinkedList;
import java.util.List;

public class AccountOperation implements AccountManager {

    private List<Player> users;
    private FileManager system;

    public AccountOperation() {
        this.users = new LinkedList<Player>();
        this.system = new FileSystemManager();
    }

    @Override
    public void createAccount(final String userName, final String password) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public final boolean logInAccount(final String userName, final String password) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void logOutAccount(final String userName) {
        // TODO Auto-generated method stub

    }

    @Override
    public final List<String> getAllUsername() {
        // TODO Auto-generated method stub
        return null;
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
    public void removeAccount(final String userName) {
        // TODO Auto-generated method stub

    }

}
>>>>>>> 53a18f5fdf9361d738b862b8ebc8a40127a3706c
