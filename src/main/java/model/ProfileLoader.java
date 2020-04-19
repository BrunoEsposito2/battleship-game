package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Temporary prototype class used to load saved profiles
 */
public class ProfileLoader {
    
    /**
     * Loads saved profiles
     * @return a Set containing the player profiles
     */
    public Set<Player> load() {
        // TEMPORARY!
        // will load saved player profiles
        return new HashSet<Player>(Arrays.asList(new PlayerHuman("ProfileName1"),new PlayerHuman("ProfileName2"),new PlayerHuman("ProfileName3")));
    }
}
