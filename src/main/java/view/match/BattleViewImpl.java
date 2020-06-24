package view.match;

import java.util.List;

import application.Battleships;
import controller.game.MatchController;
import controller.game.MatchControllerImpl;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.enums.PlayerNumber;
import model.enums.ShipType;
import model.util.Pair;
import view.dialog.DialogType;

import static java.util.stream.Collectors.joining;;

public class BattleViewImpl implements BattleView {

    @FXML
    private GridPane playerOneGrid, playerTwoGrid;
    
    private GridPane currentPlayerGridPane;
    private GridPane currentVillainGridPane;
    
    private PlayerNumber playerNumber;
    
    
    private MatchController controller;
    
    public BattleViewImpl() {
        this.controller = new MatchControllerImpl();
        // TODO Auto-generated constructor stub
    }
    
    private void initGridPane(final GridPane gridPane) {
        for (int col = 0; col < gridPane.getColumnCount(); col++) {
            for (int row = 0; row < gridPane.getRowCount(); row++) {
                Pane pane = new Pane();
                final int finalRow = row, finalCol = col;
                pane.setStyle("-fx-background-color: #FFFFFF");
//                pane.setOnMouseEntered(e -> {
//                    System.out.printf("Mouse enetered cell");
//                    pane.setStyle("-fx-background-color: #ff3333");
//                });
                pane.setOnMouseClicked(e -> {
//                    Image img = new Image(getClass().getResourceAsStream("Cruiser.png"));
//                    ImageView imageView = new ImageView(img);
//                    imageView.fitWidthProperty().bind(pane.widthProperty());
//                    imageView.fitHeightProperty().bind(pane.heightProperty());
//                    playerOneGrid.add(imageView, jey, ii, 1, 5);
//                    pane.setStyle("-fx-background-image: url(\"prova1.png\")");
//                    pane.setStyle("-fx-background-color: #f0f0f0");
                    if (pane.getParent().equals(this.currentVillainGridPane)) {
                        this.controller.shot(finalRow, finalCol);
                    }
                });
                gridPane.add(pane, col, row);
            }
        }
        gridPane.setStyle("-fx-background-color: #000000");
    }
    
    @FXML
    public void initialize() {
        this.initGridPane(this.playerOneGrid);
        this.initGridPane(this.playerTwoGrid);
        this.currentPlayerGridPane = this.playerOneGrid;
        this.currentVillainGridPane = this.playerTwoGrid;
        this.playerNumber = PlayerNumber.PLAYER_ONE;
        this.controller.setView(this);
    }

    
    /**
     * {@inheritDoc}
     */
    @Override
    public void showCellAlreadyShottedAlert(final Pair<Integer, Integer> cell) {
        final String description = "Cell [line, column]: [" + cell.getX() + "," + cell.getY() + "] is already shotted.\n"
                                    + "Select another cell, please."; 
        Battleships.getController().launchDialog(DialogType.WARNING, "Choiche not valid", "Cell choiced is already shotted!", description);
    }

    @Override
    public void showCellAlreadyUsedAlert(final List<Pair<Integer, Integer>> cell) {
        String description = "Cell [line, column]: ";
        description += cell.stream().map(e -> "[" + e.getX() + "," + e.getY() + "]").collect(joining(","));
        description += " already used." + "\n" + "Select a different place, please.";
        Battleships.getController().launchDialog(DialogType.WARNING, "Choiche not valid", "Position choiced is already used!", description);
    }

    @Override
    public void showWinDialog(final PlayerNumber winnerPlayer) {
       
    }

    @Override
    public void drawHit(final Pair<Integer, Integer> pair) {
        
    }
    
//    @Override
//    public void drawShip(nave da disegnare e posizioni) {
//        
//    }
    
    @Override
    public void drawShip(List<Pair<Integer, Integer>> cells) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void drawSunkShip(final ShipType shipType, final List<Pair<Integer, Integer>> cells) {
        // TODO Auto-generated method stub
    }

    @Override
    public void drawMissed(final Pair<Integer, Integer> pair) {
        this.getNodeByRowColumnIndex(pair.getX(), pair.getY(), this.currentVillainGridPane).setStyle("-fx-background-color: #329ef9");
    }
    
    public void changePlayer() {
        if (this.playerNumber == PlayerNumber.PLAYER_ONE) {
            this.currentPlayerGridPane = playerOneGrid;
            this.currentVillainGridPane = playerTwoGrid;
        } else {
            this.currentPlayerGridPane = playerTwoGrid;
            this.currentVillainGridPane = playerOneGrid;
        }
    }
    
    public Node getNodeByRowColumnIndex (final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();

        for (Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                return result;
            }
        }
        return result;
    }
}
