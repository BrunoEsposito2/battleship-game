package model.match;

import model.ShipType;

/**
 * Represents a generic ship of the game
 * 
 */
public class Ship implements GameObject {

    private int size;
    private int damage;    // how many damages has received
    private boolean destroyed;
    private ShipType shipType;

    public Ship(int size, ShipType shiptype) {
        this.size = size;
        this.damage = 0;
        this.destroyed = false;
        this.shipType = shiptype;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

    @Override
    public boolean isDestroyed() {
        return this.destroyed;
    }

    public ShipType getShipType() {
        return this.shipType;
    }

    @Override
    public boolean hit() {
        this.damage++;
        
        if (this.damage >= this.size) {
            this.destroyed = true;
            return this.destroyed;
        }
        
        return this.destroyed;
    }

}
