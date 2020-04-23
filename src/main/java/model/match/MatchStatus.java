package model.match;

import model.enums.GameMode;

/**
 *  This interface gives information about the status of the match currently in progress.
 */
public interface MatchStatus {
    /**
     * Checks is the match is over according to the GameMode passed as param.
     * @param wc - the current GameMode
     * @return true - if the match is over 
     */
    boolean isMatchOver(GameMode wc);
}
