package controller.players;

import java.io.File;
import java.io.IOException;

public final class InstallManager {

    /**
     * 
     */
    public static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * 
     */
    public static final String SYS_PATH = System.getProperty("user.home");

    /**
     * 
     */
    public static final String DIR_NAME = "battleship";

    /**
     * 
     */
    public static final String FILE_NAME = "users_list.txt";

    /**
     * 
     */
    public static final String DIR_PATH = SYS_PATH 
            + SEPARATOR 
            + DIR_NAME;
    /**
     * 
     */
    public static final String FILE_PATH = DIR_PATH 
            + SEPARATOR 
            + FILE_NAME;

    private InstallManager() {
    }

    public static void install() {

        final File usersDir = new File(DIR_PATH);
        final File usersFile = new File(FILE_PATH);

        if (!usersDir.exists()) {           //se non esiste la cartella => creo la cartella ed il file
            usersDir.mkdir();
            try {
                usersFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {                            // se invece esiste la cartella
            if (!usersFile.exists()) {      // ma non esiste il file => lo creo
                try {
                    usersFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
