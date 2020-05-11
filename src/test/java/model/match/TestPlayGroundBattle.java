package model.match;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class TestPlayGroundBattle {

    @Test
    public void testCreationPlayground() {
        int columns = 10;
        int lines = 8;
        int first = 0;

        PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(lines, columns);

        List<List<Boolean>> playground = playgroundBattle.getPlaygroundBattle();

        playground.stream()
                  .forEach(
                          x -> x.stream()
                                .forEach(
                                        y -> assertEquals(y, false)));
        System.out.println(playground);
        assertEquals(lines, playground.size());
        assertEquals(columns, playground.get(first).size());
    }

}
