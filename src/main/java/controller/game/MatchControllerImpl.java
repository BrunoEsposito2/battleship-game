package controller.game;

import java.util.List;
import java.util.Optional;
import java.util.Map.Entry;

import model.enums.PlayerNumber;
import model.enums.ShipType;
import model.match.CellAlreadyShottedException;
import model.match.CellsFilledException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.match.Ship;
import model.util.Pair;
import view.match.BattleView;

public class MatchControllerImpl implements MatchController {

    /*
     * For now i put grid size here
     */
    private static final int LINE = 10;
    private static final int COLUMN = 10;
    private static final int SHIPS_NUMBER = 5;

    private BattleView battleView;
    private PlayerNumber currentPlayer;
    private final PlaygroundBattle playgroundPlayerOne;
    private final PlaygroundBattle playgroundPlayerTwo;

    private PlaygroundBattle currentPlaygroundBattle;

    public MatchControllerImpl() {
        this.playgroundPlayerOne = new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN);
        this.playgroundPlayerTwo = new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN);
        this.currentPlaygroundBattle = this.playgroundPlayerOne;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void positionShip(final ShipType shipType, final Pair<Integer, Integer> firstCell) {
        try {
            this.currentPlaygroundBattle.positionShip(new Ship(shipType), firstCell);
        } catch (CellsFilledException e) {
            this.battleView.showCellAlreadyUsedAlert(e.getCellsUsed());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeShip(final Pair<Integer, Integer> cell) {
        this.currentPlaygroundBattle.removeShipWithCell(cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shot(final int line, final int col) {
        try {

            final Optional<Entry<List<Pair<Integer, Integer>>, Ship>> v = this.currentPlaygroundBattle.shipHitted(new Pair<>(line, col)); 

            // If optional is present a ship is hitted.
            if (v.isPresent()) {
                //If ship is sunk, player could be winner.
                if (this.currentPlaygroundBattle.shipSunk(v.get().getKey()).get()) {
                    this.battleView.drawSunkShip(v.get().getValue().getShipType(), v.get().getKey());
                    this.checkWin();
                } else {
                    this.battleView.drawHit(new Pair<>(line, col));
                }
            } else {
                this.battleView.drawMissed(new Pair<>(line, col));
            }
        } catch (CellAlreadyShottedException e) {
            /*
             * MEMO -> Scrivo qualcosa da qualche parte (log, std.out, std.err) 
             * dell'eccezione?
             */
            this.battleView.showCellAlreadyShottedAlert(new Pair<>(line, col));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        this.currentPlayer = PlayerNumber.PLAYER_ONE;
        this.currentPlaygroundBattle = this.playgroundPlayerTwo;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final BattleView battleView) {
        this.battleView = battleView;
    }

    public static int getShipNumberOfGame() {
        return MatchControllerImpl.SHIPS_NUMBER;
    }

    private void checkWin() {
        /*
         * The current playground is of the opponent. 
         */
        if (this.currentPlaygroundBattle.getNumberOfAliveShip() == 0) {
            this.battleView.showWinDialog(this.currentPlayer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePlayer() {
        this.currentPlaygroundBattle = getNext();
    }

    /**
     * Method to get playground to use in next turn.
     * 
     * @return playgroundBattle - the playground for next turn. 
     */
    private PlaygroundBattle getNext() {
        if (this.currentPlayer == PlayerNumber.PLAYER_ONE) {
            return this.playgroundPlayerOne;
        } else {
            return this.playgroundPlayerTwo;
        }
    }
}
