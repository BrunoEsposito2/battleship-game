package model;

import java.util.List;
import java.util.Optional;
<<<<<<< HEAD

import model.intelligence.BasicArtificialIntelligence;
import model.intelligence.BasicIntelligenceComputation;
import model.players.ArtificialPlayer;
import model.enums.GameMode;
import model.enums.PlayerNumber;
import model.match.PlaygroundBattle;
=======
import model.intelligence.BasicArtificialIntelligence;
import model.intelligence.BasicIntelligenceComputation;
import model.players.ArtificialPlayer;
import model.enums.PlayerNumber;
import model.gamemode.GameMode;
import model.gamemode.WinCondition;
import model.gamemode.WinConditionImpl;
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
import model.match.players.CurrentPlayer;
import model.match.players.CurrentPlayerImpl;
import model.match.players.PlayerInfo;
import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;
import model.util.Pair;

/**
 * Implementation of Model interface.
 */
public final class ModelImpl implements Model {

<<<<<<< HEAD
    /**
     * 
     */
    public static final int MAX_ROWS = 10;

    /**
     * 
     */
    public static final int MAX_COLS = 10;

=======
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
    private static final String BASIC_AI_NAME = "BasicAI";
    private static final String BASIC_AI_PASS = "basic";

    private final ArtificialPlayer playerAI;
    private final WinCondition winCondition = new WinConditionImpl();
    private final CurrentPlayer currentPlayer = new CurrentPlayerImpl();
    private Optional<PlayerInfo> player1 = Optional.empty(); 
    private Optional<PlayerInfo> player2 = Optional.empty(); 

<<<<<<< HEAD
    private final CurrentPlayer currentPlayer = new CurrentPlayer();
    private Optional<PlayerInfo> player1 = Optional.empty(); 
    private Optional<PlayerInfo> player2 = Optional.empty(); 
    private Optional<GameMode> gameMode = Optional.empty();

    public ModelImpl() {
        this.playerAI = new ArtificialPlayer(BASIC_AI_NAME, BASIC_AI_PASS, 
                new BasicArtificialIntelligence(new BasicIntelligenceComputation(MAX_ROWS, MAX_COLS)));
=======
    /**
     * concrete implementation of Model interface. 
     */
    public ModelImpl() {
        this.playerAI = new ArtificialPlayer(BASIC_AI_NAME, BASIC_AI_PASS);
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
    }

    @Override
    public PlayerManager setPlayerManager(final Optional<List<Player>> players) {
        return new PlayerOperation(players);
    }

    @Override
<<<<<<< HEAD
    public PlaygroundBattle startBasicAI() {
        return this.playerAI.getArtificialIntelligence().initShipsOnGrid();
    }

    @Override
    public Pair<Integer, Integer> getNextHitPointAI() {
        return this.playerAI.getArtificialIntelligence().setNextHitPoint();
=======
    public void startBasicAI() {
        this.playerAI.setArtificialIntelligence(new BasicArtificialIntelligence(new BasicIntelligenceComputation()));
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
    }

    /**
     * @return the current player
     */
    @Override
    public Optional<PlayerNumber> getCurrentPlayer() {
        return currentPlayer.getCurrentPlayer();
    }

    /**
     * @param playerNumber - the new current player
     */
    @Override
    public void setCurrentPlayer(final PlayerNumber playerNumber) {
        currentPlayer.setCurrentPlayer(playerNumber);
    }

    @Override
    public void setGameMode(final GameMode gameMode) {
        winCondition.setGameMode(gameMode);
    }

    @Override
    public Optional<PlayerInfo> getPlayerInfo(final PlayerNumber number) {
        return number.equals(PlayerNumber.PLAYER_ONE) ? player1 : player2;
    }

    @Override
    public void setPlayerInfo(final PlayerNumber number, final PlayerInfo info) {
        if (number.equals(PlayerNumber.PLAYER_ONE)) {
            player1 = Optional.ofNullable(info);
        } else {
            player2 = Optional.ofNullable(info);
        }
    }

    @Override
<<<<<<< HEAD
    public Boolean isMatchOver(final int playerHits, final int opponentHits, final int opponentRemainingShips) {
        return gameMode.isPresent() ? gameMode.get().isMatchOver(playerHits, opponentHits, opponentRemainingShips) : false;
=======
    public Boolean isMatchOver(final int hits, final int opponentRemainingShips) {
        return winCondition.isMatchOver(hits, opponentRemainingShips);
>>>>>>> 95320364063f8a0752f48aae1265902e42b70738
    }

}
