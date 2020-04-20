package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MatchManagerImpl implements MatchManager {

    //TODO Consider making this class a singleton 
    // VERY UNFINISHED CLASS !!
    // will handle the gameloop
    
    private boolean isMatchStarted;
    private  List<Player> players;
    private  WinCondition wc;
    
    public MatchManagerImpl() {
        this.isMatchStarted = false;
    }
    
    @Override
    public void start(Set<Player> players, WinCondition wc) {
        if(isMatchStarted) {
            throw new IllegalStateException("A match is already in progress");
        }
        isMatchStarted = true;
        this.wc = wc; 
        this.players = new ArrayList<>(players);
        gameLoop();
    }
    
    private void gameLoop() {
        while(true) {
            for(int i=0; i<players.size(); i++) {
                // load grid for player(i)
                players.get(i).startTurn();
                // if(isMatchOver(wc)) break;
            }
        }
        //isMatchStarted = false;
    }
}
