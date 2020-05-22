package model.match;

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

    private static final int COLUMNS = 10;
    private static final int LINES = 8;
    private static final int FIRST = 0;
    private static final int CELLS_USED = 2;

    //private static final Ship SHIP_SIZE_ONE = new Ship(ShipType.BATTLESHIP);
    private static final Ship SHIP_SIZE_TWO = new Ship(ShipType.CRUISER);
    private static final Ship SHIP_SIZE_THREE = new Ship(ShipType.DESTROYER);
    private static final Ship SHIP_SIZE_FOUR = new Ship(ShipType.SUBMARINE);


    @Test
    public void testCreationPlayground() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final List<List<Boolean>> playground = playgroundBattle.getPlaygroundBattle();

        playground.stream()
                  .forEach(
                          x -> x.stream()
                                .forEach(
                                        y -> assertFalse(y)));
        System.out.println(playground);
        assertEquals(LINES, playground.size());
        assertEquals(COLUMNS, playground.get(FIRST).size());
    }

    @Test
    public void testInsertShip() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final Pair<Integer, Integer> firstShipPosition = new Pair<>(0, 0);

        final List<Pair<Integer, Integer>> cells = 
                Orientation.HORIZONTAL.cellsUsedList(new Pair<Integer, Integer>(0, 0), CELLS_USED);

        assertTrue(playgroundBattle.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.HORIZONTAL));

        /*
         * To verify that list of overlapped cells is not empty.
         */
        assertFalse(playgroundBattle.getCellsOverlappedList(SHIP_SIZE_THREE, firstShipPosition, Orientation.HORIZONTAL).isEmpty());

        /*
         * To verify that list of overlapped cells is equal to cells already used.
         */
        assertEquals(cells, playgroundBattle.getCellsOverlappedList(SHIP_SIZE_THREE, firstShipPosition, Orientation.HORIZONTAL));

        /*
         * Try to position ship to overlap the previous.
         */
        assertFalse(playgroundBattle.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.HORIZONTAL));
    }

    @Test
    public void testInsertMultipleShip() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final Pair<Integer, Integer> firstShipPosition = new Pair<>(1, 1);

        final Pair<Integer, Integer> secondShipPositionNotCorrect = new Pair<>(2, 0);
        final Pair<Integer, Integer> secondShipPositionCorrect = new Pair<>(0, 0);

        final Pair<Integer, Integer> thirdShipPosition = new Pair<>(1, 5);

        final Pair<Integer, Integer> fourthShipPosition = new Pair<>(5, 4);

        final Pair<Integer, Integer> fifthShipPositionIncorrect1 = new Pair<>(5, 6);
        final Pair<Integer, Integer> fifthShipPositionIncorrect2 = new Pair<>(2, 5);


        /*
         * First ship correct.
         */
        assertTrue(playgroundBattle.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.VERTICAL));

        /*
         *  Second ship incorrect
         */
        assertFalse(playgroundBattle.positionShip(SHIP_SIZE_THREE,
                secondShipPositionNotCorrect, Orientation.HORIZONTAL));

        /*
         * Second ship correct.
         */
        assertTrue(playgroundBattle.positionShip(SHIP_SIZE_THREE,
                secondShipPositionCorrect, Orientation.HORIZONTAL));

        /*
         * Third ship correct.
         */
        assertTrue(playgroundBattle.positionShip(SHIP_SIZE_TWO,
                thirdShipPosition, Orientation.VERTICAL));

        /*
         * Fourth ship correct.
         */
        assertTrue(playgroundBattle.positionShip(SHIP_SIZE_FOUR, fourthShipPosition, Orientation.HORIZONTAL));

        /*
         * Fifth ship incorrect.
         */
//      assertFalse(playgroundBattle.positionShip(SHIP_SIZE_ONE,
//              fifthShipPositionIncorrect1, Orientation.VERTICAL));

//      assertEquals(Arrays.asList(fifthShipPositionIncorrect1),
//              playgroundBattle.getCellsOverlappedList(SHIP_SIZE_ONE, fifthShipPositionIncorrect1, Orientation.VERTICAL));

        /*
         * Fifth ship incorrect
         */
//        assertFalse(playgroundBattle.positionShip(SHIP_SIZE_ONE,
//              fifthShipPositionIncorrect2, Orientation.VERTICAL));

//        assertEquals(Arrays.asList(fifthShipPositionIncorrect2),
//                playgroundBattle.getCellsOverlappedList(SHIP_SIZE_ONE, fifthShipPositionIncorrect2, Orientation.VERTICAL));


    }

    @Test
    public void testRemoveShip() {
        final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(LINES, COLUMNS);
        final Pair<Integer, Integer> firstShipPosition = new Pair<>(1, 1);

        playgroundBattle.positionShip(SHIP_SIZE_THREE, firstShipPosition, Orientation.VERTICAL);
        playgroundBattle.getPlaygroundBattle().forEach(i -> System.out.println(i));

        System.out.println("");

//        playgroundBattle.removeShip(firstShipPosition);
        playgroundBattle.removeAllShips();
        playgroundBattle.getPlaygroundBattle().forEach(i -> System.out.println(i));

        Orientation.VERTICAL.cellsUsedList(firstShipPosition, SHIP_SIZE_THREE.getSize())
                            .forEach(i -> assertFalse(playgroundBattle.getPlaygroundBattle()
                                    .get(i.getX()).get(i.getY())));

        /*
         * First ship correct.
         */
        assertTrue(playgroundBattle.positionShip(SHIP_SIZE_THREE,
                firstShipPosition, Orientation.VERTICAL));
    }

}
