package controller.game;

import model.enums.Player;
import model.match.CellAlreadyShottedException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.util.Pair;
import view.match.BattleView;

public class MatchControllerImpl implements MatchController {

    /*
     * For now i put grid size here
     */
    private static final int LINE = 8;
    private static final int COLUMN = 8;
    
    private final BattleView battleView;
    private Player currentPlayer;
    private PlaygroundBattle playgroundPlayerOne;
    private PlaygroundBattle playgroundPlayerTwo;

    private PlaygroundBattle currentPlayerPlaygroundBattle;
    
    public MatchControllerImpl() {
        this.playgroundPlayerOne = new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN);
        this.playgroundPlayerTwo = new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN);
        this.currentPlayerPlaygroundBattle = this.playgroundPlayerOne;
    }

    @Override
    public void shot(final int line, final int col) {
        try {
            
            if (this.currentPlayerPlaygroundBattle.shot(new Pair<Integer, Integer>(line, col))) {
                this.checkWin();
            } else {
                //Advise view to draw no-hitted cell
            }
            
            changePlayer();
        } catch (CellAlreadyShottedException e) {
            /*
             * MEMO -> Scrivo qualcosa da qualche parte (log, std.out, std.err) 
             * dell'eccezione?
             */
            this.battleView.showCellAlreadyShottedAlert(new Pair<>(line, col));
        }
    }
    
    private void checkWin() {
        /*
         * The current playground is of the opponent. 
         */
        if (this.currentPlayerPlaygroundBattle.areThereAliveShip()) {
            this.battleView.showWinDialog(this.currentPlayer);
        }
    }

    private void changePlayer() {
        this.currentPlayerPlaygroundBattle = next();
    }

    private PlaygroundBattle next() {
        if (this.currentPlayerPlaygroundBattle == this.playgroundPlayerOne) {
            return this.playgroundPlayerTwo;
        } else {
            return this.playgroundPlayerOne;
        }
    }
}
