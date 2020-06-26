package view.match;

import java.util.List;

import model.enums.PlayerNumber;
import model.enums.ShipType;
import model.util.Pair;

public interface BattleView {

    /**
     * Show a dialog where is explained which cells have been overlapped. 
     * 
     * @param cells - list of cells already used
     */
    void showCellAlreadyUsedAlert(List<Pair<Integer, Integer>> cells);
    
    /**
     * Show a dialog where is explained which cell has already been shot. 
     * @param cell - cell already shot
     */
    void showCellAlreadyShottedAlert(Pair<Integer, Integer> cell);

    /**
     * 
     * @param pair - 
     * @param playerNumber
     */
    void drawHit(Pair<Integer, Integer> pair, PlayerNumber playerNumber);

    void drawSunkShip(ShipType shipType, List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    void drawShip(List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    void drawMissed(Pair<Integer, Integer> cell, PlayerNumber playerNumber);

    void changePlayer();

    void setPoints(int point);

    void setShotAvailable(int shotAvailable);

    void showWinDialog();

    void hideShip(List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    void showChangePlayerDialog();

}
