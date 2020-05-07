package controller.players;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class FileSystemManager implements FileManager {

    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String SYS_PATH = System.getProperty("user.home");
    private static final String DIR_NAME = "battleship";
    private static final String DIR_PATH = SYS_PATH + SEPARATOR + DIR_NAME;

    private File fileDir;

    public FileSystemManager() throws IOException {
        this.installDir();
    }

    private void installDir() throws IOException {
        this.fileDir = new File(DIR_PATH);

        if (!this.fileDir.exists()) {
            this.fileDir.mkdir();
        }
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
