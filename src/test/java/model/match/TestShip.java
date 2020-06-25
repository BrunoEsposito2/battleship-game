package model.match;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import model.enums.ShipType;

public class TestShip {
    
    @Test
    public void testCreationShip() {
        
        final GameObject carrier = new Ship(ShipType.CARRIER);
        final GameObject battleship = new Ship(ShipType.BATTLESHIP);
        final GameObject cruiser = new Ship(ShipType.CRUISER);
        
        assertEquals(carrier.getSize(), 5);
        assertEquals(battleship.getSize(), 4);
        assertEquals(cruiser.getSize(), 3);
    }
    
    @Test
    public void testDestruction() {
        
        final GameObject carrier = new Ship(ShipType.CARRIER);
        final GameObject battleship = new Ship(ShipType.BATTLESHIP);
        final GameObject cruiser = new Ship(ShipType.CRUISER);
        
        for (int i = 0; i < 5; i++) {
            carrier.hit();
        }
        assertTrue(carrier.isDestroyed());
        
        for (int i = 0; i < 4; i++) {
            battleship.hit();
        }
        assertTrue(battleship.isDestroyed());
        
        for (int i = 0; i < 3; i++) {
            cruiser.hit();
        }
        assertTrue(cruiser.isDestroyed());
        
    }

}
