package view.match;

import controller.game.MatchController;
import model.util.Pair;
import view.dialog.DialogLauncher;
import view.dialog.DialogType;

public class BattleViewImpl implements BattleView {
    
    private final MatchController controller;
    

    public BattleViewImpl() {
        // TODO Auto-generated constructor stub
    }

    public void showCellAlreadyShottedAlert(final Pair<Integer, Integer> cell) {
        final String description = "Cell [line, column]: [" + cell.getX() + "," + cell.getY() + "] is already shotted.\n"
                                    + "Select another cell, please."; 
        DialogLauncher.launch(DialogType.WARNING, "Choiche not valid", "Cell choiced is already shotted!", description);
    }
    
}
