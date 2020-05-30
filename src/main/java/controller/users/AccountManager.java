package controller.users;

import java.util.List;
import java.util.Optional;

public interface AccountManager {

    void createAccount(String userName, String password) throws Exception;

    boolean logInAccount(String userName, String password);

    void logOutAccount(String userName);

    Optional<List<String>> getAllUsername();

    void setWinner(String userName, Double scoreValue);

    void setLoser(String userName, Double scoreValue);

    void removeAccount(String userName, String password) throws Exception;

}
