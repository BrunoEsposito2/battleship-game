package model.players;

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
