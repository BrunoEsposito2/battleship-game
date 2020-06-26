package view.match;

import java.util.List;

import model.enums.PlayerNumber;
import model.enums.ShipType;
import model.util.Pair;

public interface BattleView {

    void showCellAlreadyUsedAlert(List<Pair<Integer, Integer>> cell);

    void showCellAlreadyShottedAlert(Pair<Integer, Integer> cell);

    void drawHit(Pair<Integer, Integer> pair, PlayerNumber playerNumber);

    void drawSunkShip(ShipType shipType, List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    void drawShip(List<Pair<Integer, Integer>> cells, PlayerNumber playerNumber);

    void drawMissed(Pair<Integer, Integer> cell, PlayerNumber playerNumber);

    void changePlayer();

    void setPoints(int point);

    void setShotAvailable(int shotAvailable);

    void showWinDialog();

}
