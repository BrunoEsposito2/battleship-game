package controllers;

import java.util.Arrays;
import java.util.List;

import model.Player;
import model.WinCondition;

public class MatchManagerImpl implements MatchManager {

    // VERY UNFINISHED AND TEMPORARY CLASS !!
    // will handle the gameloop
    
    private int turnsElapsed;
    private final List<Player> playerList;
    private final Player p1, p2;
    private final WinCondition wc;
    //private final PlayerTurn playerTurn;
    
    public MatchManagerImpl(Player p1, Player p2, WinCondition wc) {
        //load human player from saved profiles and create ai player
        this.p1 = p1;
        this.p2 = p2;
        this.wc = wc; 
        playerList = Arrays.asList(p1,p2);
    }
    
    @Override
    public void start() {
        turnsElapsed = 0;
        gameLoop();
    }
    
    private void gameLoop() {
        while(true) {
            for(int i=0; i<playerList.size(); i++) {
                // load grid for player(i)
                turnsElapsed++;
                playerList.get(i).startTurn();
                // if(isMatchOver(winCondition)) break;
            }
        }
    }
}