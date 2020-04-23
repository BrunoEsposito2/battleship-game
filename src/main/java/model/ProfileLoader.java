package model;

import java.util.Set;

/**
 * Temporary prototype class used to load saved profiles.
 */
public class ProfileLoader {

    /**
     * Loads saved profiles.
     * @return a Set containing the player profiles
     */
    public Set<Player> load() {
        // TEMPORARY!
        // will load saved player profiles
        return Set.of(new PlayerHuman("PLACEHOLDER_1"), new PlayerHuman("PLACEHOLDER_2"), new PlayerHuman("PLACEHOLDER_3"));
    }
}
