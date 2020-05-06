package model.match;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPlayGroundBattle {

    @Test
    public void testCreationPlayground() {
        int columns = 10;
        int lines = 8;
        int first = 0;

        PlaygroundBattle playgroundBattle = new PlayGroundBattleImpl(lines, columns);

        var playground = playgroundBattle.getPlaygroundBattle();

        playground.stream()
                  .forEach(
                          x -> x.stream()
                                .forEach(
                                        y -> assertEquals(y, false)));

        assertEquals(playground.size(), lines);
        assertEquals(playground.get(first).size(), columns);
    }

}
