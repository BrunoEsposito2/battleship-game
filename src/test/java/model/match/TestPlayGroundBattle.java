package model.match;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.enums.Orientation;
import model.enums.ShipType;
import model.util.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class TestPlayGroundBattle {

    private static int COLUMNS = 10;
    private static int LINES = 8;
    private static int FIRST = 0;
    private static int CELLS_USED = 3;


    private static PlaygroundBattle PLAYGROUND_BATTLE = null;

    @BeforeAll
    public static void initPlaygroundBattle() {
        PLAYGROUND_BATTLE = new PlaygroundBattleImpl(LINES, COLUMNS);
    }

    @Test
    public void testCreationPlayground() {

        List<List<Boolean>> playground = PLAYGROUND_BATTLE.getPlaygroundBattle();

        playground.stream()
                  .forEach(
                          x -> x.stream()
                                .forEach(
                                        y -> assertEquals(y, false)));
        System.out.println(playground);
        assertEquals(LINES, playground.size());
        assertEquals(COLUMNS, playground.get(FIRST).size());
    }

    @Test
    public void testInsertShip() {
        List<Pair<Integer, Integer>> cells = 
                Orientation.HORIZONTAL.cellsUsedList(new Pair<Integer, Integer>(0, 0), CELLS_USED);

        assertTrue(PLAYGROUND_BATTLE.positionShip(new Ship(3, ShipType.BATTLESHIP),
                new Pair<Integer, Integer>(0, 0), Orientation.HORIZONTAL));

        /*
         * To verify that list of overlapped cells is not empty.
         */
        assertFalse((PLAYGROUND_BATTLE.getCellsOverlappedList(cells).isEmpty()));
        
        /*
         * To verify that list of overlapped cells is equal to cells already used.
         */
        assertEquals(cells, PLAYGROUND_BATTLE.getCellsOverlappedList(cells));
        /*
         * Try to position ship to overlap the previous.
         */
        assertFalse(PLAYGROUND_BATTLE.positionShip(new Ship(3, ShipType.BATTLESHIP),
                new Pair<Integer, Integer>(0, 0), Orientation.HORIZONTAL));
    }



}
