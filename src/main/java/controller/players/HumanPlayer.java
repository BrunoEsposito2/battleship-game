package controller.players;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HumanPlayer implements Player, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8116603641426068234L;

    private String userName;
    private String password;
    private transient boolean online;
    private Map<String, Double> stats;

    public HumanPlayer(final String name, final String password) {
        this.userName = name;
        this.password = password;
        this.online = false;
        this.stats = new HashMap<>();
    }

    @Override
    public final void setStatistics(final Map<String, Double> values) {
        this.stats = values;
    }

    @Override
    public final String getUsername() {
        return this.userName;
    }

    @Override
    public final String getPassword() {
        return this.password;
    }

    @Override
    public final Map<String, Double> getStatistics() {
        return Collections.unmodifiableMap(this.stats);
    }

    @Override
    public final void setLogin(final boolean value) {
        this.online = value;
    }

    @Override
    public final boolean isPlaying() {
        return this.online;
    }

    @Override
    public final String toString() {
        return "Username: " + this.userName;
    }

}
