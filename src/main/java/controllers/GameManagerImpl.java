package controllers;

import model.Player;

public class GameManagerImpl implements GameManager {

    // VERY UNFINISHED AND TEMPORARY CLASS !!
    // will handle the gameloop
    
    private int turnsElapsed;
    //private final Player p1, p2;
    //private final PlayerTurn playerTurn;
    
    public GameManagerImpl(Player p1, Player p2) {
        //load human player from saved profiles and create ai player
    }
    
    @Override
    public void start() {
        turnsElapsed = 0;
        gameLoop();
    }
    
    private void gameLoop() {
        while(true) {
            turnsElapsed++;
            //playerTurn.start(p1);
            // isOver?(enum gameMode) ?break;
            turnsElapsed++;
            //playerTurn.start(p2);
            // isOver?(enum gameMode) ?break;
        }
    }
}
