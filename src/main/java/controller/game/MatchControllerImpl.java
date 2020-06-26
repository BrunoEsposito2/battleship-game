package controller.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import application.Battleships;

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
import view.scene.SceneName;

public class MatchControllerImpl implements MatchController {

    /*
     * For now i put grid size here
     */
    private static final int LINE = 10;
    private static final int COLUMN = 10;
    private static final int SHIPS_NUMBER = 5;

    private BattleView battleView;
    private Map<PlayerNumber, PlaygroundBattle> playgrounds;
    private PlayerNumber currentVillain;
    private int shotAvailable;

    

    public MatchControllerImpl() {
        this.playgrounds = new HashMap<>();
        this.playgrounds.put(PlayerNumber.PLAYER_ONE, new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN));
        this.playgrounds.put(PlayerNumber.PLAYER_TWO, new PlaygroundBattleImpl(MatchControllerImpl.LINE, MatchControllerImpl.COLUMN));
        this.currentVillain = PlayerNumber.PLAYER_TWO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void positionShip(final ShipType shipType, final Pair<Integer, Integer> firstCell) {
        try {
            this.playgrounds.get(Battleships.getController().getCurrentPlayer().get()).positionShip(new Ship(shipType), firstCell);
        } catch (CellsFilledException e) {
            this.battleView.showCellAlreadyUsedAlert(e.getCellsUsed());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeShip(final Pair<Integer, Integer> cell) {
        this.playgrounds.get(Battleships.getController().getCurrentPlayer().get()).removeShipWithCell(cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shot(final int line, final int col) {
        try {
            final Optional<Entry<List<Pair<Integer, Integer>>, Ship>> v = this.playgrounds.get(this.currentVillain)
                    .shipHitted(new Pair<>(line, col));

            // If optional is present a ship is hitted.
            if (v.isPresent()) {
                // If ship is sunk, player could be winner.
                if (this.playgrounds.get(this.currentVillain).shipSunk(v.get().getKey()).get()) {
                    this.battleView.drawSunkShip(v.get().getValue().getShipType(), v.get().getKey(), this.currentVillain);
                    this.checkWin();
                } else {
                    this.battleView.drawHit(new Pair<>(line, col), this.currentVillain);
                }
            } else {
                this.battleView.drawMissed(new Pair<>(line, col), this.currentVillain);
            }

            System.out.println(this.playgrounds.get(this.currentVillain).getNumberOfAliveShip());
            this.battleView.setShotAvailable(this.playgrounds.get(this.currentVillain).getNumberOfAliveShip());
            this.battleView.setPoints(this.playgrounds.get(this.currentVillain).getDamage());

            if (this.shotAvailable <= 1) {
                this.changePlayer();
            } else {
                this.shotAvailable--;
            }

        } catch (CellAlreadyShottedException e) {
            this.battleView.showCellAlreadyShottedAlert(new Pair<>(line, col));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startBattle() {
        this.currentVillain = PlayerNumber.PLAYER_TWO;
        this.shotAvailable = this.playgrounds.get(PlayerNumber.PLAYER_ONE).getNumberOfAliveShip();
        this.battleView.drawShip(this.shipCells(PlayerNumber.PLAYER_ONE), PlayerNumber.PLAYER_ONE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final BattleView battleView) {
        this.battleView = battleView;
    }

    @Override
    public void nextToPosition() {

        if (Battleships.getController().getCurrentPlayer().get().equals(PlayerNumber.PLAYER_ONE)) {
            Battleships.getController().changeScene(SceneName.SHIP_DEPLOYMENT);
        } else {
            Battleships.getController().changeScene(SceneName.MATCH_BATTLE);
            this.startBattle();
        }
        Battleships.getController().nextPlayer();
    }

    @Override
    public void setPlayground(final PlaygroundBattle playgroundBattle) {
        this.playgrounds.put(Battleships.getController().getCurrentPlayer().get(), playgroundBattle);
        playgroundBattle.resetPlayground();
    }

    public static int getShipNumberOfGame() {
        return MatchControllerImpl.SHIPS_NUMBER;
    }

    private void checkWin() {
        if (Battleships.getController().isMatchOver(this.playgrounds.get(this.currentVillain).getDamage(),
                this.playgrounds.get(this.currentVillain).getNumberOfAliveShip())) {
            this.battleView.showWinDialog();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changePlayer() {
        // this.battleView.changinPlayer();
        this.shotAvailable = this.playgrounds.get(this.currentVillain).getNumberOfAliveShip();
        this.battleView.hideShip(this.shipCells(Battleships.getController().getCurrentPlayer().get()), Battleships.getController().getCurrentPlayer().get());
        this.battleView.changePlayer();
        this.currentVillain = Battleships.getController().getCurrentPlayer().get();
        Battleships.getController().nextPlayer();
    }

    @Override
    public void showShip() {
        this.battleView.drawShip(this.shipCells(Battleships.getController().getCurrentPlayer().get()), Battleships.getController().getCurrentPlayer().get());
    }
    
    private List<Pair<Integer, Integer>> shipCells(final PlayerNumber playerNumber) {
        List<Pair<Integer, Integer>> listOfCells = new ArrayList<>();

        var keySet = this.playgrounds.get(playerNumber).getShips().keySet();

        for (List<Pair<Integer, Integer>> list : keySet) {
            listOfCells.addAll(list);
        }

        return listOfCells;
    }
}
