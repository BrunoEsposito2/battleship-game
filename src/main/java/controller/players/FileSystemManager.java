package controller.players;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class FileSystemManager implements FileManager {

    public FileSystemManager() {
        InstallManager.setupApplication();
    }

    private static void writePlayerOnFile(final Player player) {

        try (
             OutputStream file = new FileOutputStream(InstallManager.FILE_PATH + InstallManager.SEPARATOR + player.getUsername() + ".bin");
             OutputStream bstream = new BufferedOutputStream(file);
             ObjectOutputStream ostream = new ObjectOutputStream(bstream);
        ) {
             ostream.writeObject(player);
        } catch (IOException e) {
                e.printStackTrace();
         }
    }

    private static void loadPlayerFromFile(final List<Player> players, final String usernameFile) {

        try (
             InputStream file = new FileInputStream(InstallManager.FILE_PATH + InstallManager.SEPARATOR + usernameFile);
             InputStream bstream = new BufferedInputStream(file);
             ObjectInputStream istream = new ObjectInputStream(bstream);
        ) {
             players.add((Player) istream.readObject());
        } catch (Exception e) {
                e.printStackTrace();
         }
    }

    @Override
    public final void savePlayer(final Player player) {

        final File playerFile = new File(InstallManager.FILE_PATH + InstallManager.SEPARATOR + player.getUsername() + ".bin");

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writePlayerOnFile(player);
    }

    @Override
    public final Optional<List<Player>> loadPlayers() {

        List<Player> players = new LinkedList<>();

        final File dir = new File(InstallManager.DIR_PATH);

        if (dir.isDirectory()) {
            Optional<String[]> names = Optional.ofNullable(dir.list());
            if (names.isPresent()) {
                for (String user : names.get()) {
                    loadPlayerFromFile(players, user);
                }
            } else {
                return Optional.of(players);
            }
        }
        return Optional.of(players);
    }

    @Override
    public final void removePlayer(final Player player) {

        final File user = new File(InstallManager.FILE_PATH + InstallManager.SEPARATOR + player.getUsername() + ".bin");

        try {
            if (user.exists() && user.isFile()) {
                try {
                    user.delete();
                } catch (Exception e) {
                    System.err.println("Error in 'delete file': file not deleted");
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Error in 'removePlayer': player is not removed");
            e.printStackTrace();
        }
    }
}
