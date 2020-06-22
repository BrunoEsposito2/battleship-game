package controller.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import model.match.CellsFilledException;
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
    
    //Offset which is put every time a ship is dropped on the grid in HORIZONTAL way
    private static final int CARRIER_HORIZ_OFFSET = -40;
    private static final int BATTLESHIP_HORIZ_OFFSET = 32;
    private static final int CRUISER_HORIZ_OFFSET = 12;
    private static final int SUBMARINE_HORIZ_OFFSET = 5;
    private static final int DESTROYER_HORIZ_OFFSET = -10;
    
    //Offset which is put every time a ship is dropped on the grid in VERTICAL way
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
        
        for (Entry<ImageView, Pair<Ship, Pair<Integer,Integer>>> entry : this.ships.entrySet()) {
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
            this.extractHorizontalOffset();
            this.extractVerticalOffset();
            
            if (db.hasImage()) {
                
                this.extractOrientation();
                
                //*** Horizontal dropping ***
                if (this.orientation.equals(Orientation.HORIZONTAL)
                    && (this.coordX < GRIDSIZE - this.size + 1)
                    && !playgroundBattle.isCellUsed(new Pair<>(this.coordY, this.coordX))) {

                    if (this.checkShip(this.extractShip())) {
                        playgroundBattle.removeShip(this.extractShip());
                        System.out.println("REMOVE FATTA");
                    }
                    
                    try {
                    playgroundBattle.positionShip(this.extractShip(), 
                                                  new Pair<>(this.coordY, this.coordX));
                    ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                    board.add(draggingShip, this.coordX + 1, this.coordY, this.size, 1);
                    draggingShip.setTranslateX(this.horizOffset);
                    } catch(CellsFilledException exception) {
                        System.out.println("NON VA BENE");
                    }
                    
                    e.setDropCompleted(true);
                    
                //*** Vertical dropping ***    
                } else if (this.orientation.equals(Orientation.VERTICAL)
                           && (this.coordY < GRIDSIZE - this.size + 1)
                           && !playgroundBattle.isCellUsed(new Pair<>(this.coordY, this.coordX))) {

                    if (this.checkShip(this.extractShip())) {
                        playgroundBattle.removeShip(this.extractShip());
                        System.out.println("REMOVE FATTA");
                    }
                    
                    try {
                        playgroundBattle.positionShip(this.extractShip(), 
                                                      new Pair<>(this.coordY, this.coordX));
                        ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                        board.add(draggingShip, this.coordX, this.coordY, 1, this.size);
                        draggingShip.setTranslateX(this.vertOffset);
                    } catch (CellsFilledException exception) {
                        System.out.println("NON VA BENE");
                    }

                    e.setDropCompleted(true);
                }
                
                
            }
            
            e.consume();
        });

    }

    /**
     * Populates the map of ships
     */
    private void createShips() {
        this.ships.put(carrier, new Pair<>(new Ship(ShipType.CARRIER), 
                       new Pair<>(CARRIER_HORIZ_OFFSET, CARRIER_VERT_OFFSET)));
        this.ships.put(battleship, new Pair<>(new Ship(ShipType.BATTLESHIP), 
                       new Pair<>(BATTLESHIP_HORIZ_OFFSET, BATTLESHIP_VERT_OFFSET)));
        this.ships.put(cruiser, new Pair<>(new Ship(ShipType.CRUISER),
                       new Pair<>(CRUISER_HORIZ_OFFSET, CRUISER_VERT_OFFSET)));
        this.ships.put(submarine, new Pair<>(new Ship(ShipType.SUBMARINE),
                       new Pair<>(SUBMARINE_HORIZ_OFFSET, SUBMARINE_VERT_OFFSET)));
        this.ships.put(destroyer, new Pair<>(new Ship(ShipType.DESTROYER),
                       new Pair<>(DESTROYER_HORIZ_OFFSET, DESTROYER_VERT_OFFSET)));
    }

    /**
     * Method to extract the size from the selected ship
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
     * Method to extract the current ship
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
     * Method to extract the horizontal offset from the selected ship
     */
    private void extractHorizontalOffset() {
        for (Entry<ImageView, Pair<Ship, Pair<Integer, Integer>>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                this.horizOffset = entry.getValue().getY().getX();
            }
        }
    }

    /**
     * Method to extract the vertical offset from the selected ship
     */
    private void extractVerticalOffset() {
        for (Entry<ImageView, Pair<Ship, Pair<Integer, Integer>>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                this.vertOffset = entry.getValue().getY().getY();
            }
        }
    }

    /**
     * Method to extract the coordinates where the ship is dropping
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
     * Method to extract the orientation of the selected ship
     */
    private void extractOrientation() {
        for (Entry<ImageView, Pair<Ship, Pair<Integer,Integer>>> entry : this.ships.entrySet()) {
            if (entry.getKey().equals(draggingShip)) {
                this.orientation = entry.getValue().getX().getOrientation();
            }
        }
    }

    /**
     * 
     * @return true if vertical rotation is possible (with no collisions)
     */
    private boolean checkVertRotation() {
        for (int i = 1; i < this.size; i++) {
            if (playgroundBattle.isCellUsed(new Pair<>(this.coordY + i, this.coordX))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @return true if horizontal rotation is possible (with no collisions)
     */
    private boolean checkHorizRotation() {
        for (int i = 1; i < this.size; i++) {
            if (playgroundBattle.isCellUsed(new Pair<>(this.coordY, this.coordX + i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method applies a rotation of 90° at the selected ship
     */
    private void applyRotation() {
        this.extractOrientation();
        
        double rot = draggingShip.getRotate();
        
        //*** Vertical rotation ***
        if (this.orientation.equals(Orientation.HORIZONTAL)
            && (this.coordY < GRIDSIZE - this.size + 1)
            && this.checkVertRotation()) {
            
            board.getChildren().remove(draggingShip);
            playgroundBattle.removeShip(this.extractShip());
            draggingShip.setRotate(rot - 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.VERTICAL);
            
            try {
                playgroundBattle.positionShip(this.extractShip(), 
                                              new Pair<>(this.coordY, this.coordX));
                board.add(draggingShip, this.coordX, this.coordY, 1, this.size);
                draggingShip.setTranslateX(this.vertOffset);
            } catch (CellsFilledException exception) {
                System.out.println("NON VA BENE");
            }
            
        //*** Horizontal rotation ***    
        } else if (this.orientation.equals(Orientation.VERTICAL)
                   && (this.coordX < GRIDSIZE - this.size + 1)
                   && this.checkHorizRotation()) {
            
            board.getChildren().remove(draggingShip);
            playgroundBattle.removeShip(this.extractShip());
            draggingShip.setRotate(rot + 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.HORIZONTAL);
            
            try {
                playgroundBattle.positionShip(this.extractShip(), 
                                              new Pair<>(this.coordY, this.coordX));
                board.add(draggingShip, this.coordX + 1, this.coordY, this.size, 1);
                draggingShip.setTranslateX(this.horizOffset);
            } catch (CellsFilledException exception) {
                System.out.println("NON VA BENE");
            }
            
        }
    }

}
