package model.match;

import java.util.Arrays;
import model.util.Pair;

public class CellAlreadyShottedException extends CellsAlreadyUsedException {

    /**
     * 
     */
    private static final long serialVersionUID = 6085552272590623167L;

    public CellAlreadyShottedException(final Pair<Integer, Integer> cellUsed) {
        super(Arrays.asList(cellUsed));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Cell already shotted: [" + this.getCellsUsed().get(0).getX() + ", " 
                + this.getCellsUsed().get(0).getX() + "]" + super.toString(); 
    }

}
