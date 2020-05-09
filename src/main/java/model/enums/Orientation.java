package model.enums;

import model.util.Pair;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * 
 * Enumeration type to indicate the orientation of a ship inside a playground.
 * It could be vertical or horizontal.
 *
 */
public enum Orientation {

    /**
     * Orientation is horizontal.
     */
    HORIZONTAL(new Pair<>(0, 1)),

    /**
     * Orientation in vertical.
     */
    VERTICAL(new Pair<>(1, 0));

    private Pair<Integer, Integer> direction;

    Orientation(final Pair<Integer, Integer> direction) {
        this.direction = direction;
    }

    public List<Pair<Integer, Integer>> occupied(final Pair<Integer, Integer> initialBox, final int size) {
        return Stream.iterate(initialBox, 
                        i -> new Pair<Integer, Integer>(i.getX() + this.direction.getX(), i.getY() + this.direction.getY()))
                .limit(size)
                .collect(toList());
    }
}
