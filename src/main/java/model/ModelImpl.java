package model;

import java.util.List;
import java.util.Optional;

import model.intelligence.BasicArtificialIntelligence;
import model.intelligence.BasicIntelligenceComputation;
import model.players.ArtificialPlayer;
import model.players.Player;
import model.players.PlayerManager;
import model.players.PlayerOperation;

public final class ModelImpl implements Model {

    private static final String BASIC_AI_NAME = "BasicAI";
    private static final String BASIC_AI_PASS = "basic";

    private final ArtificialPlayer playerAI;

    public ModelImpl() {
        this.playerAI = new ArtificialPlayer(BASIC_AI_NAME, BASIC_AI_PASS);
    }

    @Override
    public PlayerManager setPlayerManager(final Optional<List<Player>> players) {
        return new PlayerOperation(players);
    }

    @Override
    public void startBasicAI() {
        this.playerAI.setArtificialIntelligence(new BasicArtificialIntelligence(new BasicIntelligenceComputation()));
    }

}
