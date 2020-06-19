package model.players;

import java.io.Serializable;

public abstract class AbstractPlayer implements Player, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private transient boolean online;

    public AbstractPlayer(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
        this.online = false;
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
    public final String toString() {
        return super.toString();
    }

    @Override
    public final void setLogin(final boolean value) {
        this.online = value;
    }

    @Override
    public final boolean isPlaying() {
        return this.online;
    }

}
