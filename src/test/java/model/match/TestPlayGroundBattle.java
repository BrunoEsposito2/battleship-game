package model.match;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.enums.Orientation;
import model.enums.ShipType;
import model.util.Pair;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

public class TestPlayGroundBattle {

    private static int COLUMNS = 10;
    private static int LINES = 8;
    private static int FIRST = 0;
    private static int CELLS_USED = 3;

    private static Ship SHIP_SIZE_ONE = new Ship(1, ShipType.BATTLESHIP);
    private static Ship SHIP_SIZE_TWO = new Ship(2, ShipType.CRUISER);
    private static Ship SHIP_SIZE_THREE = new Ship(3, ShipType.DESTROYER);
    private static Ship SHIP_SIZE_FOUR = new Ship(4, ShipType.SUBMARINE);


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
        Pair<Integer, Integer> firstShipPosition = new Pair<Integer, Integer>(0, 0);

        List<Pair<Integer, Integer>> cells = 
                Orientation.HORIZONTAL.cellsUsedList(new Pair<Integer, Integer>(0, 0), CELLS_USED);

        assertTrue(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.HORIZONTAL));

        /*
         * To verify that list of overlapped cells is not empty.
         */
        assertFalse(PLAYGROUND_BATTLE.getCellsOverlappedList(SHIP_SIZE_THREE, firstShipPosition, Orientation.HORIZONTAL).isEmpty());

        /*
         * To verify that list of overlapped cells is equal to cells already used.
         */
        assertEquals(cells, PLAYGROUND_BATTLE.getCellsOverlappedList(SHIP_SIZE_THREE, firstShipPosition, Orientation.HORIZONTAL));

        /*
         * Try to position ship to overlap the previous.
         */
        assertFalse(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.HORIZONTAL));
    }

    @Test
    public void testInsertMultipleShip() {
        Pair<Integer, Integer> firstShipPosition = new Pair<Integer, Integer>(1, 1);

        Pair<Integer, Integer> secondShipPositionNotCorrect = new Pair<Integer, Integer>(2, 0);
        Pair<Integer, Integer> secondShipPositionCorrect = new Pair<Integer, Integer>(0, 0);

        Pair<Integer, Integer> thirdShipPosition = new Pair<Integer, Integer>(1, 5);

        Pair<Integer, Integer> fourthShipPosition = new Pair<Integer, Integer>(5, 4);

        Pair<Integer, Integer> fifthShipPositionIncorrect1 = new Pair<Integer, Integer>(5, 6);
        Pair<Integer, Integer> fifthShipPositionIncorrect2 = new Pair<Integer, Integer>(2, 5);


        /*
         * First ship correct.
         */
        assertTrue(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.VERTICAL));

        /*
         *  Second ship incorrect
         */
        assertFalse(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE,
                secondShipPositionNotCorrect, Orientation.HORIZONTAL));

        /*
         * Second ship correct.
         */
        assertTrue(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE,
                secondShipPositionCorrect, Orientation.HORIZONTAL));

        /*
         * Third ship correct.
         */
        assertTrue(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_TWO,
                thirdShipPosition, Orientation.VERTICAL));

        /*
         * Fourth ship correct.
         */
        assertTrue(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_FOUR, fourthShipPosition, Orientation.HORIZONTAL));

        /*
         * Fifth ship incorrect.
         */
        assertFalse(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_ONE,
                fifthShipPositionIncorrect1, Orientation.VERTICAL));

        assertEquals(Arrays.asList(fifthShipPositionIncorrect1),
                PLAYGROUND_BATTLE.getCellsOverlappedList(SHIP_SIZE_ONE, fifthShipPositionIncorrect1, Orientation.VERTICAL));

        /*
         * Fifth ship incorrect
         */
        assertFalse(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_ONE,
                fifthShipPositionIncorrect2, Orientation.VERTICAL));

        assertEquals(Arrays.asList(fifthShipPositionIncorrect2),
                PLAYGROUND_BATTLE.getCellsOverlappedList(SHIP_SIZE_ONE, fifthShipPositionIncorrect2, Orientation.VERTICAL));


    }

    @Test
    public void testRemoveShip() {
        Pair<Integer, Integer> firstShipPosition = new Pair<Integer, Integer>(1, 1);

        PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE, firstShipPosition, Orientation.VERTICAL);
        PLAYGROUND_BATTLE.getPlaygroundBattle().forEach(i -> System.out.println(i));

        System.out.println("");

        PLAYGROUND_BATTLE.removeShip(firstShipPosition);
        PLAYGROUND_BATTLE.getPlaygroundBattle().forEach(i -> System.out.println(i));

        Orientation.VERTICAL.cellsUsedList(firstShipPosition, SHIP_SIZE_THREE.getSize())
                            .forEach(i -> assertFalse(PLAYGROUND_BATTLE.getPlaygroundBattle()
                                    .get(i.getX()).get(i.getY())));

        /*
         * First ship correct.
         */
        assertTrue(PLAYGROUND_BATTLE.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.VERTICAL));
    }

}
