package model.enums;

import model.util.Pair;

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
    VERTICAL(new Pair<>(1,0));

    private Pair<Integer, Integer> direction;

    Orientation(final Pair<Integer, Integer> direction) {
        this.direction = direction;
    }
}
