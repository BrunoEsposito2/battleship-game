package controller.game;

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

}
