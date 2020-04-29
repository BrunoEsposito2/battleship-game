package model.player;

/**
 * Consists in a prototype of any kind of player.
 * 
 */
public abstract class PlayerPrototype implements Cloneable {

    private String username;
    private String password;
    private boolean onGame;

    /**
     * Initialize the player of a certain username and password.
     * 
     * @param username
     *             represents the specific name of the player
     * @param password
     *             represents the specific password of the player
     */
    public PlayerPrototype(final String username, final String password) {
        this.username = username;
        this.password = password;
        this.onGame = false;
    }

    /**
     * @return the specific name of the player
     */
    public final String getUsername() {
        return this.username;
    }

    /**
     * @return the specific password of the player
     */
    public final String getPassword() {
        return this.password;
    }

    /**
     * @return a boolean that specifics if the player is gaming (or not)
     */
    public final boolean isOnGame() {
        return this.onGame;
    }

    /**
     * Allows to set the name of the player.
     * 
     * @param username
     */
    public final void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Allows to set the password of the player.
     * 
     * @param password
     */
    public final void setPassword(final String password) {
        this.password = password;
    }

    /**
     * Allows to set the "status" of the player.
     * 
     * @param value
     */
    public final void setOnGame(final boolean value) {
        this.onGame = value;
    }

    @Override
    public final PlayerPrototype clone() {
        try {
            return (PlayerPrototype) super.clone();
        } catch (CloneNotSupportedException e) {
           System.out.println("Something went wrong in registration \n Please, ESC and retry.");
           return null;
        }
    }

    public abstract String toString();

}
