package model;

/**
 * Implementation of MatchStatus interface.
 */
public final class MatchStatusImpl implements MatchStatus {

    @Override
    public boolean isMatchOver(final WinCondition wc) {
        switch (wc) {
        case ALL_ENEMY_SHIPS_SUNK:
            // check conditions
            break;
        case HIGHEST_SCORE_AFTER_N_TURNS:
            // check conditions
            break;
        default:
            break;
        }
        //TODO remove temp return value
        return true;
    }

}
