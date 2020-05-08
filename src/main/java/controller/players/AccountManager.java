package controller.players;

import java.util.List;

public interface AccountManager {

    void createAccount(String userName, String password);

    void initAll();

    boolean logInAccount(String userName, String password);

    void logOutAccount(String userName);

    List<String> getAllUsername();

    void setWinner(String userName, Double scoreValue);

    void setLoser(String userName, Double scoreValue);

    void removeAccount(String userName);

}
