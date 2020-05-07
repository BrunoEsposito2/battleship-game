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
