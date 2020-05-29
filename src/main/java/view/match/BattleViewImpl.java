package view.match;

import java.util.List;

import controller.game.MatchController;
import model.enums.Player;
import model.enums.ShipType;
import model.util.Pair;
import view.dialog.DialogLauncher;
import view.dialog.DialogType;

public class BattleViewImpl implements BattleView {
    
    private final MatchController controller;
    

    public BattleViewImpl() {
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showCellAlreadyShottedAlert(final Pair<Integer, Integer> cell) {
        final String description = "Cell [line, column]: [" + cell.getX() + "," + cell.getY() + "] is already shotted.\n"
                                    + "Select another cell, please."; 
        DialogLauncher.launch(DialogType.WARNING, "Choiche not valid", "Cell choiced is already shotted!", description);
    }

    @Override
    public void showWinDialog(final Player winnerPlayer) {
        // Dialog or new scene?
    }

    @Override
    public void drawHit(final Pair<Integer, Integer> pair) {
        // Draw cell hitted with ex. flames
    }

    @Override
    public void drawSunkShip(ShipType shipType, List<Pair<Integer, Integer>> cells) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawMissed(Pair<Integer, Integer> pair) {
        // TODO Auto-generated method stub
        
    }
    
}