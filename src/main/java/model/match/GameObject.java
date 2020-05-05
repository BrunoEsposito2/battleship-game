package model.match;

/**
 * A generic object belonging to the game world
 * 
 */
public interface GameObject {

    /**
     * @return the size of the ship
     */
    public int getSize();

    /**
     * @return the number of damages received
     */
    public int getDamage();

    /**
     * @return true if (damage == size)
     */
    public boolean isDestroyed();

    /**
     * Hits a ship
     * 
     * @return true if ship was destroyed
     */
    public boolean hit();

}
