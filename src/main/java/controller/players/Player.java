<<<<<<< HEAD
package controller.players;

import java.util.Map;

public interface Player {

    void setStatistics(Map<String, Double> values);

    String getUsername();

    String getPassword();

    Map<String, Double> getStatistics();

    void setLogin(boolean value);

    boolean isPlaying();

    String toString();

}
=======
package controller.players;

import java.util.List;
import java.util.Map;

public interface Player {

    void setStatistics(Map<String, List<Double>> values);

    String getUsername();

    String getPassword();

    Map<String, List<Double>> getStatistics();

    void setLogin(boolean value);

    boolean isPlaying();

}
>>>>>>> 53a18f5fdf9361d738b862b8ebc8a40127a3706c
