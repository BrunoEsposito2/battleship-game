package model.gamemode;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

class WinConditionTest {

    private WinCondition winCondition = new WinConditionImpl();
    private int hits = 0;
    private int opponentRemainingShips = 0;

    @Test
    void testClassic() {
        
        winCondition.setGameMode(GameMode.CLASSIC);
        
        opponentRemainingShips = 5;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), false);
        
        opponentRemainingShips = 0;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);
        
        opponentRemainingShips = -300;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);

    }

    @Test
    void testFiveHits() {
        winCondition.setGameMode(GameMode.FIVE_HITS);
        
        hits = -5000;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), false);
        
        hits = 5;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);
        
        hits = 5000;
        assertEquals(winCondition.isMatchOver(hits, opponentRemainingShips), true);
    }

}
