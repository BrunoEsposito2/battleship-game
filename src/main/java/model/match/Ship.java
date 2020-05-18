package model.match;

import model.enums.ShipType;

/**
 * Represents a generic ship of the game
 * 
 */
public class Ship implements GameObject {

    private int size;
    private int damage;    // how many damages has received
    private boolean destroyed;
    private ShipType shipType;

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.size = shipType.getSize();
        this.damage = 0;
        this.destroyed = false;
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
