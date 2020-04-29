package model.player;

/**
 * Represents the player of type "human".
 * 
 */
public class HumanPlayer extends PlayerPrototype {

    public HumanPlayer(final String username, final String password) {
        super(username, password);
    }

    @Override
    public final String toString() {
        return String.valueOf("Username: " + this.getUsername() + "\n"
                + "Password: " + this.getPassword());
    }
}
