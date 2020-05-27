package view.match;

import model.enums.Player;
import model.util.Pair;

public interface BattleView {

    void showCellAlreadyShottedAlert(Pair<Integer, Integer> cell);

    void showWinDialog(Player winnerPlayer);
}
