package controller.ui;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import application.Battleships;
import model.match.CellsFilledException;
import model.match.PlaygroundBattle;
import model.match.PlaygroundBattleImpl;
import model.enums.Orientation;
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
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public final class ShipDeployment {

    private static final int GRIDSIZE = Battleships.getController().getMatchInfo().get().getFieldSize().getKey();
    private static final int NUMSHIPS = Battleships.getController().getMatchInfo().get().getShipsNumber();

    private int coordX;
    private int coordY;
    private int size;
    private Optional<Integer> horizOffset;
    private Optional<Integer> vertOffset;
    private Optional<Orientation> orientation;
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
        for (int i = 0; i < GRIDSIZE; i++) {
            for (int j = 0; j < GRIDSIZE; j++) {
                final Pane pane = new Pane();
                pane.setStyle("-fx-background-color: white;");
                board.add(pane, i, j);
            }
        }

        for (final Entry<ImageView, Pair<Ship, Pair<Integer,Integer>>> entry : this.ships.entrySet()) {
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
        if (playgroundBattle.getNumberOfAliveShip() == NUMSHIPS) {
            Battleships.getController().getMatchController().setPlayground(playgroundBattle);
            Battleships.getController().getMatchController().nextToPosition();
        }
    }

    /**
     * Set the source for the drag and drop function
     * 
     * @param ship, the ship currently dragged
     */
    private void dragImage(final ImageView ship) {
        ship.setOnDragDetected(e -> {
            final Dragboard db = ship.startDragAndDrop(TransferMode.MOVE);
            final ClipboardContent cc = new ClipboardContent();
            cc.putImage(ship.getImage());

            db.setContent(cc);
            draggingShip = ship;

            e.consume();
        });

        ship.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                System.out.println("Double clicked");
                this.applyRotation();
            }
        });
    }

    /**
     * Set the target for the drag and drop function
     * 
     * @param gridPane, where the ship is dropped
     */
    private void dropImage(final GridPane gridPane) {
        gridPane.setOnDragOver(e -> {

            final Dragboard db = e.getDragboard();
            System.out.println("onDragOver");

            if (db.hasImage()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }

            this.extractCoordinates(e);
            System.out.printf("Dragging on cell [Row %d, Col %d]%n", this.coordX, this.coordY); 

            e.consume();
        });

        gridPane.setOnDragDropped(e -> {

            final Dragboard db = e.getDragboard();
            System.out.println("onDragDropped");

            this.size = manageDeployment.extractSize(draggingShip);
            this.horizOffset = manageDeployment.extractHorizontalOffset(draggingShip);
            this.vertOffset = manageDeployment.extractVerticalOffset(draggingShip);

            if (db.hasImage()) {

                this.orientation = manageDeployment.extractOrientation(draggingShip);

                //*** Horizontal dropping ***
                if (this.orientation.get().equals(Orientation.HORIZONTAL)
                    && this.coordY < GRIDSIZE - this.size + 1
                    && !playgroundBattle.isCellUsedByShip(new Pair<>(this.coordX, this.coordY))) {

                    //check whether this ship is already present
                    if (manageDeployment.checkShip(manageDeployment.extractShip(draggingShip))) {
                        playgroundBattle.removeShipWithShip(manageDeployment.extractShip(draggingShip));
                    }

                    try {
                        playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
                                                      new Pair<>(this.coordX, this.coordY));
                        ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                        board.add(draggingShip, this.coordY + 1, this.coordX, this.size, 1);
                        draggingShip.setTranslateX(this.horizOffset.get());
                    } catch(CellsFilledException exception) {
                    }

                    e.setDropCompleted(true);

                //*** Vertical dropping ***    
                } else if (this.orientation.get().equals(Orientation.VERTICAL)
                           && this.coordX < GRIDSIZE - this.size + 1
                           && !playgroundBattle.isCellUsedByShip(new Pair<>(this.coordX, this.coordY))) {

                  //check whether this ship is already present
                    if (manageDeployment.checkShip(manageDeployment.extractShip(draggingShip))) {
                        playgroundBattle.removeShipWithShip(manageDeployment.extractShip(draggingShip));
                    }

                    try {
                        playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
                                                      new Pair<>(this.coordX, this.coordY));
                        ((Pane) draggingShip.getParent()).getChildren().remove(draggingShip);
                        board.add(draggingShip, this.coordY, this.coordX, 1, this.size);
                        draggingShip.setTranslateX(this.vertOffset.get());
                    } catch (CellsFilledException exception) {
                    }

                    e.setDropCompleted(true);
                }


            }

            e.consume();
        });

    }

    /**
     * Method to extract the coordinates where the ship is dropping
     * 
     * @param e, DragEvent
     */
    private void extractCoordinates(final DragEvent e) {
        final Node clickedNode = e.getPickResult().getIntersectedNode();
        final Integer colIndex = GridPane.getColumnIndex(clickedNode);
        final Integer rowIndex = GridPane.getRowIndex(clickedNode);
        this.coordY = colIndex == null ? 0 : colIndex;
        this.coordX = rowIndex == null ? 0 : rowIndex;
    }

    /**
     * This method applies a rotation of 90° at the selected ship
     */
    private void applyRotation() {
        this.orientation = manageDeployment.extractOrientation(draggingShip);

        final double rot = draggingShip.getRotate();

        //*** Vertical rotation ***
        if (this.orientation.get().equals(Orientation.HORIZONTAL)
            && this.coordX < GRIDSIZE - this.size + 1
            && manageDeployment.checkVertRotation(this.size, this.coordX, this.coordY)) {

            board.getChildren().remove(draggingShip);
            playgroundBattle.removeShipWithShip(manageDeployment.extractShip(draggingShip));
            draggingShip.setRotate(rot - 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.VERTICAL);

            try {
                playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
                                              new Pair<>(this.coordX, this.coordY));
                board.add(draggingShip, this.coordY, this.coordX, 1, this.size);
                draggingShip.setTranslateX(this.vertOffset.get());
            } catch (CellsFilledException exception) {
            }

        //*** Horizontal rotation ***    
        } else if (this.orientation.get().equals(Orientation.VERTICAL)
                   && this.coordY < GRIDSIZE - this.size + 1
                   && manageDeployment.checkHorizRotation(this.size, this.coordX, this.coordY)) {

            board.getChildren().remove(draggingShip);
            playgroundBattle.removeShipWithShip(manageDeployment.extractShip(draggingShip));
            draggingShip.setRotate(rot + 90);
            this.ships.get(draggingShip).getX().setOrientation(Orientation.HORIZONTAL);

            try {
                playgroundBattle.positionShip(manageDeployment.extractShip(draggingShip), 
                                              new Pair<>(this.coordX, this.coordY));
                board.add(draggingShip, this.coordY + 1, this.coordX, this.size, 1);
                draggingShip.setTranslateX(this.horizOffset.get());
            } catch (CellsFilledException exception) {
            }

        }
    }

}
