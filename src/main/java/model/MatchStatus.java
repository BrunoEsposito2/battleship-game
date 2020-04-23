package model;

/**
 *  This interface gives information about the status of the match currently in progress.
 */
public interface MatchStatus {
    /**
     * Checks is the match is over according to the WinCondition passed as param.
     * @param wc - the WinCondition used to check if the match is over
     * @return true - if the match is over 
     */
    boolean isMatchOver(WinCondition wc);
}
