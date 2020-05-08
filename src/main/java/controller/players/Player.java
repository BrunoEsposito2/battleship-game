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
