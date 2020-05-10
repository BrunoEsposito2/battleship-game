<<<<<<< HEAD
package controller.players;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ArtificialPlayer implements Player, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6800182305987141934L;

    public ArtificialPlayer() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setStatistics(final Map<String, Double> values) {
        // TODO Auto-generated method stub

    }

    @Override
    public final String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Map<String, Double> getStatistics() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogin(final boolean value) {
        // TODO Auto-generated method stub

    }

    @Override
    public final boolean isPlaying() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public final String toString() {
        return "IA username: ";
    }

}
=======
package controller.players;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ArtificialPlayer implements Player, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -6800182305987141934L;

    public ArtificialPlayer() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setStatistics(final Map<String, List<Double>> values) {
        // TODO Auto-generated method stub

    }

    @Override
    public final String getUsername() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public final Map<String, List<Double>> getStatistics() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setLogin(final boolean value) {
        // TODO Auto-generated method stub

    }

    @Override
    public final boolean isPlaying() {
        // TODO Auto-generated method stub
        return false;
    }

}
>>>>>>> 53a18f5fdf9361d738b862b8ebc8a40127a3706c
