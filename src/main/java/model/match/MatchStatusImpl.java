package model.match;

import model.enums.GameMode;

/**
 * Implementation of MatchStatus interface.
 */
public final class MatchStatusImpl implements MatchStatus {

    @Override
    public boolean isMatchOver(final GameMode gm) {
        return gm.checkWinCondition();
    }

}
