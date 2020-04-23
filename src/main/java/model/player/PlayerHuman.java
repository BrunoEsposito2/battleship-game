package model.player;

/**
 * Implementation of Player interface.
 * Models a human player.
 */
public final class PlayerHuman implements Player {

    private final Boolean isHuman;
    private String name;

    /**
     * This class' constructor.
     * @param name - the player's name
     */
    public PlayerHuman(final String name) {
        this.name = name;
        isHuman = true;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Boolean isHuman() {
        return isHuman;
    }

    @Override
    public void startTurn() {
       // shoot at a position
    }
}
