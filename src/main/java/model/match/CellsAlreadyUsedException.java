package model.match;

import java.io.IOException;
import java.util.List;

import model.util.Pair;

public class CellsAlreadyUsedException extends IOException {

    /**
     * 
     */
    private static final long serialVersionUID = 7451832879293050065L;

    private final List<Pair<Integer, Integer>> cellsUsed;


    public CellsAlreadyUsedException(final List<Pair<Integer, Integer>> cellsUsed) {
        super();
        this.cellsUsed = cellsUsed;
    }


    protected List<Pair<Integer, Integer>> getCellsUsed() {
        return cellsUsed;
    }
}
