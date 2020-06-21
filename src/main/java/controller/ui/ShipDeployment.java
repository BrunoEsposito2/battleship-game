package controller.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.enums.Orientation;
import model.enums.ShipType;
import model.util.Pair;
import model.match.Ship;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ShipDeployment {
    
    //Offset which is put every time a ship is dropped on the grid in HORIZONTAL
    private static final int CARRIER_HORIZ_OFFSET = -40;
    private static final int BATTLESHIP_HORIZ_OFFSET = 32;
    private static final int CRUISER_HORIZ_OFFSET = 12;
    private static final int SUBMARINE_HORIZ_OFFSET = 5;
    private static final int DESTROYER_HORIZ_OFFSET = -10;
    
  //Offset which is put every time a ship is dropped on the grid in VERTICAL
    private static final int CARRIER_VERT_OFFSET = -88;
    private static final int BATTLESHIP_VERT_OFFSET = 9;
    private static final int CRUISER_VERT_OFFSET = 10;
    private static final int SUBMARINE_VERT_OFFSET = 6;
    private static final int DESTROYER_VERT_OFFSET = 10;
    
    private static final int GRIDSIZE = 10;

    private int coordX;
    private int coordY;
    private int mouseCoordX;
    private int mouseCoordY;
    private int size;
    private int horizOffset;
    private int vertOffset;
    private Orientation orientation;
    private ImageView draggingShip;
    private Map<ImageView, Pair<Ship, Pair<Integer, Integer>>> ships = new HashMap<>();
    private PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(GRIDSIZE, GRIDSIZE);

    @FXML
    private GridPane board;

    @FXML
    private ImageView carrier, battleship, cruiser, submarine, destroyer;

    /**
     * This method is called automatically when loading the fxml layout
     */
    @FXML
    private void initialize() {
        this.createShips();
        board.setStyle("-fx-background-color: black;");
        
        //initialize the grid's cells
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: white;");
                board.add(pane, i, j);
            }
        }
        
        for (Entry<ImageView, Pair<Ship, Integer>> entry : this.ships.entrySet()) {
            dragImage(entry.getKey());
        }
        
        dropImage(board);
    }
    
    /**
     * Method to extract the coordinates clicked by the mouse
     * 
     * @param e, MouseEvent
     */
    @FXML
    private void onMouseClicked(final MouseEvent e) {
        Node clickedNode = e.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        this.mouseCoordY = colIndex == null ? 0 : colIndex;
        this.mouseCoordX = rowIndex == null ? 0 : rowIndex;
        System.out.printf("Mouse clicked cell [%d, %d]%n", this.mouseCoordX, this.mouseCoordY);
    }

    /**
     * Set the source for the drag and drop function
     * 
     * @param ship, the ship currently dragged
     */
    private void dragImage(ImageView ship) {
        ship.setOnDragDetected(e -> {
            Dragboard db = ship.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent cc = new ClipboardContent();
            cc.putImage(ship.getImage());

            db.setContent(cc);
            draggingShip = ship;
            
            e.consume();
        });
        
        ship.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                System.out.println("Double clicked");
                this.onMouseClicked(e);
                this.applyRotation();
            }
        });
    }

    /**
     * Set the target for the drag and drop function
     * 
     * @param gridPane, where the ship is dropped
     */
    private void dropImage(GridPane gridPane) {
        gridPane.setOnDragOver(e -> {
            
            Dragboard db = e.getDragboard();
            System.out.println("onDragOver");
            
            if (db.hasImage()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            
            this.extractCoordinates(e);
            System.out.printf("Dragging on cell [%d, %d]%n", this.coordX, this.coordY); 
            
            e.consume();
        });
        
        gridPane.setOnDragDropped(e -> {
            
            Dragboard db = e.getDragboard();
            System.out.println("onDragDropped");
            
            this.extractSize();
            this.extractOffset();
            
            if (db.hasImage()) {
                
                this.extractOrientation();
                
                if (this.orientation.equals(Orientation.HORIZONTAL)
                    && (this.coordX < GRIDSIZE - this.size + 1)) {
                    ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                    board.add(draggingShip, this.coordX + 1, this.coordY, this.size, 1);
                    draggingShip.setTranslateX(this.offset);
                } else if (this.orientation.equals(Orientation.VERTICAL)
                           && (this.coordY < GRIDSIZE - this.size + 1)) {
                    ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                    board.add(draggingShip, this.coordX, this.coordY, 1, this.size);
                }
                
                e.setDropCompleted(true);
            }
            
            e.consume();
        });

    }
    
    /**
     * Populates the map of ships
     */
    private void createShips() {
        this.ships.put(carrier, new Pair<>(new Ship(ShipType.CARRIER), CARRIER_OFFSET));
        this.ships.put(battleship, new Pair<>(new Ship(ShipType.BATTLESHIP), BATTLESHIP_OFFSET));
        this.ships.put(cruiser, new Pair<>(new Ship(ShipType.CRUISER), CRUISER_OFFSET));
        this.ships.put(submarine, new Pair<>(new Ship(ShipType.SUBMARINE), SUBMARINE_OFFSET));
        this.ships.put(destroyer, new Pair<>(new Ship(ShipType.DESTROYER), DESTROYER_OFFSET));
    }

    /**
     * Utility method to extract the size from the selected ship
     */
    private void extractSize() {
        List<Ship> resultUserList = this.ships.entrySet().stream()
                .filter(x -> x.getKey().equals(draggingShip))
                .map(x -> x.getValue().getX())
                .collect(Collectors.toList());
        if (resultUserList.size() != 1) {
            throw new IllegalStateException();
        }
        
        this.size = resultUserList.get(0).getSize();
    }
    
    /**
     * Utility method to extract the current ship
     * 
     * @return Ship
     */
    private Ship extractShip() {
        List<Ship> resultUserList = this.ships.entrySet().stream()
                .filter(x -> x.getKey().equals(draggingShip))
                .map(x -> x.getValue().getX())
                .collect(Collectors.toList());
        if (resultUserList.size() != 1) {
            throw new IllegalStateException();
        }
        
        Ship ship = resultUserList.get(0);
        return ship;
    }
    
    /**
     * Method to check if a particular ship has been dropped in the grid
     * 
     * @param ship
     * @return true if the ship is present
     */
    private boolean checkShip(Ship ship) {
        Map<List<Pair<Integer, Integer>>, Ship> map = playgroundBattle.getShips();
        return map.containsValue(ship);
    }

    /**
     * Utility method to extract the offset from the selected ship
     */
    private void extractOffset() {
        for (Entry<ImageView, Pair<Ship, Integer>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                this.offset = entry.getValue().getY();
            }
        }
    }
    
    /**
     * Utility method to extract coordinates where the ship is dropping
     * 
     * @param e, DragEvent
     */
    private void extractCoordinates(DragEvent e) {
        Node clickedNode = e.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        this.coordX = colIndex == null ? 0 : colIndex;
        this.coordY = rowIndex == null ? 0 : rowIndex;
    }
    
    /**
     * Utility method to extract the orientation of the selected ship
     */
    private void extractOrientation() {
        for (Entry<ImageView, Pair<Ship, Integer>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                this.orientation = entry.getValue().getX().getOrientation();
            }
        }
    }
    
    /**
     * This method applies a rotation of 90° at the selected ship
     */
    private void applyRotation() {
        this.extractOrientation();
        
        double rot = draggingShip.getRotate();
        if (this.orientation.equals(Orientation.HORIZONTAL)) {
            board.getChildren().remove(draggingShip);
            draggingShip.setRotate(rot - 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.VERTICAL);
            board.add(draggingShip, this.coordX, this.coordY, 1, this.size);
        } else if (this.orientation.equals(Orientation.VERTICAL)) {
            board.getChildren().remove(draggingShip);
            draggingShip.setRotate(rot + 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.HORIZONTAL);
            board.add(draggingShip, this.coordX + 1, this.coordY, this.size, 1);
        }
    }

}
