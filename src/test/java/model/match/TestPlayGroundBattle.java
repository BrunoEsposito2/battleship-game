package model.match;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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

    private static Ship FIRST_SHIP = new Ship(1, ShipType.BATTLESHIP);
    private static Ship SECOND_SHIP = new Ship(2, ShipType.CRUISER);
    private static Ship THIRD_SHIP = new Ship(3, ShipType.DESTROYER);
    private static Ship FOURTH_SHIP = new Ship(4, ShipType.SUBMARINE);


    private static PlaygroundBattle PLAYGROUND_BATTLE = null;

    @BeforeEach
    public void initPlaygroundBattle() {
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

    @Test
    public void testInsertMultipleShip() {
        Pair<Integer, Integer> firstShipPosition = new Pair<Integer, Integer>(1, 1);

        Pair<Integer, Integer> secondShipPositionNotCorrect = new Pair<Integer, Integer>(2, 0);
        Orientation secondShipOrientationNotCorrect = Orientation.HORIZONTAL;

        Pair<Integer, Integer> secondShipPositionCorrect = new Pair<Integer, Integer>(0, 0);

        Pair<Integer, Integer> thirdShipPosition = new Pair<Integer, Integer>(0, 0);
        Pair<Integer, Integer> fourthShipPosition = new Pair<Integer, Integer>(0, 0);

        assertTrue(PLAYGROUND_BATTLE.positionShip(THIRD_SHIP,
                new Pair<Integer, Integer>(1, 1), Orientation.VERTICAL));

        assertFalse(PLAYGROUND_BATTLE.positionShip(THIRD_SHIP,
                secondShipPositionNotCorrect, secondShipOrientationNotCorrect));

    }

}
