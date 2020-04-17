package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;



public class ProfileLoader {
    
    public Set<Player> load() {
        // TEMPORARY!
        // will load saved player profiles
        return new HashSet<Player>(Arrays.asList(new PlayerHuman("ProfileName1"),new PlayerHuman("ProfileName2"),new PlayerHuman("ProfileName3")));
    }
}
