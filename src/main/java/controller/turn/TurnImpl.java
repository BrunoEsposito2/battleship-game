package controller.turn;

import model.match.PlaygroundBattle;

/**
 * Concrete implementation of Turn.
 */
public final class TurnImpl implements Turn {

    private final PlaygroundBattle playground;

    /**
     * Constructor of this class.
     * @param playground - playground of the match
     */
    public TurnImpl(final PlaygroundBattle playground) {
        this.playground = playground;
    }

    @Override
    public void start(final String username) {
        //TODO load player's grid
        //TODO have player shoot a position
    }

}
