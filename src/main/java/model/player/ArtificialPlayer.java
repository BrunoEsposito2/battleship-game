package model.player;

/**
 * Represents the player of type "artificial".
 * 
 */
public class ArtificialPlayer extends PlayerPrototype {

    public ArtificialPlayer(final String username, final String password) {
        super(username, password);
    }

    @Override
    public final String toString() {
        return String.valueOf("IA -> username: " + this.getUsername() + "\n"
                         + "\tpassword: " + this.getPassword());
    }

}
