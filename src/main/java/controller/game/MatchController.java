package controller.game;

import view.match.BattleView;

public interface MatchController {

    /**
     * Method for shot to enemy playground.
     * 
     * @param line - vertical position of shot.
     * @param col - horizontal position of shot.
     */
    void shot(int line, int col);

    /**
     * Method for start the game after ship placement.
     */
    void startGame();

    /**
     * Set the game view linked to this controller.
     * 
     * @param battleView - Game view
     */
    void setView(BattleView battleView);

}
