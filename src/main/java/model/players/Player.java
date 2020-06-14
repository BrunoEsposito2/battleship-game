package model.players;

import java.util.Map;

public interface Player {

    String getUsername();

    String getPassword();

    void updateStats(String desc, Double updatedValue);

    Map<String, Double> getStatistics();

    void setLogin(boolean value);

    boolean isPlaying();

    String toString();

}
