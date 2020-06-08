package controller.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import model.enums.ShipType;
import model.util.Pair;
import model.match.Ship;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ShipDeployment {
    
  //Offset which is put every time a ship is dropped on the grid
    private static final int CARRIER_OFFSET = -40;
    private static final int BATTLESHIP_OFFSET = 32;
    private static final int CRUISER_OFFSET = 12;
    private static final int SUBMARINE_OFFSET = 5;
    private static final int DESTROYER_OFFSET = -10;
    
    private int coordX;
    private int coordY;
    private int size;
    private int offset;
    private ImageView draggingShip;
    private Map<ImageView, Pair<Ship, Integer>> ships = new HashMap<>();
    
    @FXML
    private GridPane board;

    @FXML
    private ImageView carrier, battleship, cruiser, submarine, destroyer;

    /**
     * This method is called automatically when loading the fxml layout
     */
    @FXML
    private void initialize() {
        ships.put(carrier, new Pair<>(new Ship(ShipType.CARRIER), CARRIER_OFFSET));
        ships.put(battleship, new Pair<>(new Ship(ShipType.BATTLESHIP), BATTLESHIP_OFFSET));
        ships.put(cruiser, new Pair<>(new Ship(ShipType.CRUISER), CRUISER_OFFSET));
        ships.put(submarine, new Pair<>(new Ship(ShipType.SUBMARINE), SUBMARINE_OFFSET));
        ships.put(destroyer, new Pair<>(new Ship(ShipType.DESTROYER), DESTROYER_OFFSET));
        
        board.setStyle("-fx-background-color: black;");
        
        // inizializzo le celle della griglia
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Pane pane = new Pane();
                pane.setStyle("-fx-background-color: white;");
                board.add(pane, i, j);
            }
        }
        
        for (Entry<ImageView, Pair<Ship, Integer>> entry : ships.entrySet()) {
            dragImage(entry.getKey());
        }
        
        dropImage(board);
    }
    
    @FXML
    private void onMouseClicked(final MouseEvent e) {
        Node clickedNode = e.getPickResult().getIntersectedNode();
        Integer colIndex = GridPane.getColumnIndex(clickedNode);
        Integer rowIndex = GridPane.getRowIndex(clickedNode);
        int x = colIndex == null ? 0 : colIndex;
        int y = rowIndex == null ? 0 : rowIndex;
        System.out.printf("Mouse clicked cell [%d, %d]%n", x, y);
    }
    
    private void dragImage(ImageView ship) {
        ship.setOnDragDetected(e -> {
            Dragboard db = ship.startDragAndDrop(TransferMode.MOVE);

            ClipboardContent cc = new ClipboardContent();
            cc.putImage(ship.getImage());

            db.setContent(cc);
            draggingShip = ship;
            
            e.consume();
        });
    }
    
    private void dropImage(GridPane gridPane) {
        gridPane.setOnDragOver(e -> {
            
            Dragboard db = e.getDragboard();
            System.out.println("onDragOver");
            
            if (db.hasImage()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            
            /*Node clickedNode = e.getPickResult().getIntersectedNode();
            Integer colIndex = GridPane.getColumnIndex(clickedNode);
            Integer rowIndex = GridPane.getRowIndex(clickedNode);
            coordX = colIndex == null ? 0 : colIndex;
            coordY = rowIndex == null ? 0 : rowIndex;
            System.out.printf("Dragging on cell [%d, %d]%n", coordX, coordY);*/
            
            e.consume();
        });
        
        gridPane.setOnDragDropped(e -> {
            
            Dragboard db = e.getDragboard();
            System.out.println("onDragDropped");
            
            if (db.hasImage() && (coordX < 8)) {
                this.extractSize();
                this.extractOffset();
                
                ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                board.add(draggingShip, coordX + 1, coordY, size, 1);
                draggingShip.setTranslateX(offset);
                e.setDropCompleted(true);
            }
            
            e.consume();
        });

    }

}
