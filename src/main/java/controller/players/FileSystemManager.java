<<<<<<< HEAD
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
import java.util.Map;
import java.util.Optional;

public class FileSystemManager implements FileManager {

    public FileSystemManager() {
        InstallManager.setupApplication();
    }

    private void writePlayerOnFile(final Player player) {
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

    private void loadPlayerFromFile(final List<Player> players, final String usernameFile) {
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
    public final void savePlayer(final HumanPlayer player) {

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
            String[] names = dir.list();
            for (String user : names) {
                loadPlayerFromFile(players, user);
            }
        }

        return Optional.of(players);
    }

    @Override
    public void removePlayer(final HumanPlayer player) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveStats(final Map<String, List<Double>> scores) {
        // TODO Auto-generated method stub

    }

}
=======
package controller.players;

import java.util.List;
import java.util.Map;

public class FileSystemManager implements FileManager {

    public FileSystemManager() {
        InstallManager.install();
    }

    @Override
    public void savePlayer(final String userName, final String password) {
        // TODO Auto-generated method stub

    }

    @Override
    public final Map<String, String> loadPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Map<String, List<Double>> loadStats(final String userName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void removePlayer(final String userName) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveStats(final Map<String, List<Double>> scores) {
        // TODO Auto-generated method stub

    }

}
>>>>>>> 53a18f5fdf9361d738b862b8ebc8a40127a3706c
