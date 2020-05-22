package model.match;

import java.util.List;
import java.util.stream.Collectors;

import model.util.Pair;

public class CellsFilledException extends CellsAlreadyUsedException {

    /**
     * 
     */
    private static final long serialVersionUID = -6205542022676663926L;

    public CellsFilledException(final List<Pair<Integer, Integer>> cellsUsed) {
        super(cellsUsed);
    }

    @Override
    public String toString() {
        String s = this.getCellsUsed().stream()
                .map(cell -> "[" + cell.getX() + ", " + cell.getY() + "]")
                .collect(Collectors.joining("; ", "{ ", "}"));

//        this.getCellsUsed().forEach(cell -> {
//            s = s + "[" + cell.getX() + ", " + cell.getY() + "]";
//        });
        return "Cells already filled: " + s + "\n" + super.toString();
    }

}
