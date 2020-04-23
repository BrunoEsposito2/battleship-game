package model.player;

/**
 * Implementation of Player interface.
 * Models an AI-controlled player
 */
public final class PlayerAI implements Player {

    private final Boolean isHuman;
    private String name;

    /**
     * This class' constructor.
     * @param name - the player's name
     */
    public PlayerAI(final String name) {
        this.name = name;
        isHuman = false;
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
