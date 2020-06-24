package view.match;

import java.util.List;

import model.enums.PlayerNumber;
import model.enums.ShipType;
import model.util.Pair;

public interface BattleView {

    void showCellAlreadyUsedAlert(List<Pair<Integer, Integer>> cell);

    void showCellAlreadyShottedAlert(Pair<Integer, Integer> cell);

    void showWinDialog(PlayerNumber winnerPlayer);

    void drawHit(Pair<Integer, Integer> pair);

    void drawSunkShip(ShipType shipType, List<Pair<Integer, Integer>> cells);

    void drawMissed(Pair<Integer, Integer> pair);

    void drawShip(List<Pair<Integer, Integer>> cells);

    void changePlayer();
}
