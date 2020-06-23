package controller.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import application.Battleships;
import model.match.CellsFilledException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.enums.Orientation;
import model.enums.ShipType;
import model.util.Pair;
import view.scene.SceneName;
import model.match.Ship;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public final class ShipDeployment {
    
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
    private Map<ImageView, Pair<Ship, Pair<Integer, Integer>>> ships;
    
    private final PlaygroundBattle playgroundBattle = new PlaygroundBattleImpl(GRIDSIZE, GRIDSIZE);
    private final ManageDeployment manageDeployment = new ManageDeployment(playgroundBattle);

    @FXML
    private GridPane board;

    @FXML
    private ImageView carrier, battleship, cruiser, submarine, destroyer;
    
    @FXML
    private Button buttonBack, buttonStart;

    /**
     * This method is called automatically when loading the fxml layout
     */
    @FXML
    public void initialize() {
        this.ships = manageDeployment.createShips(carrier, battleship, cruiser, submarine, destroyer);
        
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
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void buttonBack() {
        Battleships.getController().changeScene(SceneName.MATCH_SETTINGS);
    }
    
    /**
     *  The handler for the click events generated by the button.
     */
    @FXML
    public void buttonStart() {
        Battleships.getController().changeScene(SceneName.MATCH_BATTLE);
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
            
            this.size = manageDeployment.extractSize(draggingShip);
            this.extractHorizontalOffset();
            this.extractVerticalOffset();
            
            if (db.hasImage()) {
                
                this.extractOrientation();
                
                //*** Horizontal dropping ***
                if (this.orientation.equals(Orientation.HORIZONTAL)
                    && (this.coordX < GRIDSIZE - this.size + 1)
                    && !playgroundBattle.isCellUsed(new Pair<>(this.coordY, this.coordX))) {

                    //check whether this ship is already present
                    if (manageDeployment.checkShip(manageDeployment.extractShip(draggingShip))) {
                        playgroundBattle.removeShip(manageDeployment.extractShip(draggingShip));
                        System.out.println("REMOVE FATTA");
                    }
                    
                    try {
                    playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
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

                  //check whether this ship is already present
                    if (manageDeployment.checkShip(manageDeployment.extractShip(draggingShip))) {
                        playgroundBattle.removeShip(manageDeployment.extractShip(draggingShip));
                        System.out.println("REMOVE FATTA");
                    }
                    
                    try {
                        playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
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
            playgroundBattle.removeShip(manageDeployment.extractShip(draggingShip));
            draggingShip.setRotate(rot - 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.VERTICAL);
            
            try {
                playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
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
            playgroundBattle.removeShip(manageDeployment.extractShip(draggingShip));
            draggingShip.setRotate(rot + 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.HORIZONTAL);
            
            try {
                playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
                                              new Pair<>(this.coordY, this.coordX));
                board.add(draggingShip, this.coordX + 1, this.coordY, this.size, 1);
                draggingShip.setTranslateX(this.horizOffset);
            } catch (CellsFilledException exception) {
                System.out.println("NON VA BENE");
            }
            
        }
    }

}
