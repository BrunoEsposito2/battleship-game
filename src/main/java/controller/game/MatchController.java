package controller.game;

import model.enums.ShipType;
import model.util.Pair;
import view.match.BattleView;

public interface MatchController {

    /**
     * Method to position a new Ship inside battle playground.
     * 
     * @param shipType  - ShipType of ship to position
     * @param firstCell - Upper and further to left cell used by ship.
     */
    void positionShip(ShipType shipType, Pair<Integer, Integer> firstCell);
    
    /**
     * Method to remove ship that occupied one of cells passed.
     * 
     * @param cell - One of cell occupied by the ship to remove.
     */
    void removeShip(Pair<Integer, Integer> cell);

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

    /**
     * Pass to nextTurn player.
     */
    void changePlayer();

}
