package view.match;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import application.Battleships;
import controller.game.MatchController;
import controller.game.MatchControllerImpl;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import model.enums.PlayerNumber;
import model.enums.ShipType;
import model.util.Pair;
import view.dialog.DialogType;

import static java.util.stream.Collectors.joining;

import java.util.HashMap;

public class BattleViewImpl implements BattleView {

    @FXML
    private GridPane playerOneGrid, playerTwoGrid;

    @FXML
    private TextField pointsPLOne, pointsPLTwo, shotAvailablePLOne, shotAvailablePLTwo;

    @FXML
    private Label nameOne, nameTwo;

    private GridPane currentPlayerGridPane;
    private GridPane currentVillainGridPane;
    private TextField currentPointsPL, currentShotAvailable;
    private Map<PlayerNumber, Map<Pair<Integer, Integer>, Pane>> panes; 

    private MatchController controller;

    private void initGridPane(final GridPane gridPane) {
        for (int col = 0; col < gridPane.getColumnCount(); col++) {
            for (int row = 0; row < gridPane.getRowCount(); row++) {
                Pane pane = new Pane();
                final int finalRow = row, finalCol = col;
                pane.setStyle("-fx-background-color: #FFFFFF");
                // pane.setOnMouseEntered(e -> {
                // System.out.printf("Mouse enetered cell");
                // pane.setStyle("-fx-background-color: #ff3333");
                // });
                pane.setOnMouseClicked(e -> {
                    // Image img = new Image(getClass().getResourceAsStream("Cruiser.png"));
                    // ImageView imageView = new ImageView(img);
                    // imageView.fitWidthProperty().bind(pane.widthProperty());
                    // imageView.fitHeightProperty().bind(pane.heightProperty());
                    // playerOneGrid.add(imageView, jey, ii, 1, 5);
                    // pane.setStyle("-fx-background-image: url(\"prova1.png\")");
                    // pane.setStyle("-fx-background-color: #f0f0f0");
                    if (pane.getParent().equals(this.currentVillainGridPane)) {
                        new Thread(() -> {
                            this.controller.shot(finalRow, finalCol);
                        }).start();
                    }
                });
                gridPane.add(pane, col, row);

                if (gridPane == this.playerOneGrid) {
                    this.panes.get(PlayerNumber.PLAYER_ONE).put(new Pair<Integer, Integer>(row, col), pane);
                } else {
                    this.panes.get(PlayerNumber.PLAYER_TWO).put(new Pair<Integer, Integer>(row, col), pane);
                }
            }
        }
        gridPane.setStyle("-fx-background-color: #000000");
    }

    private void initTextField() {
        this.pointsPLOne.setText("0");
        this.pointsPLOne.setEditable(false);

        this.pointsPLTwo.setText("0");
        this.pointsPLTwo.setEditable(false);

        
        this.shotAvailablePLOne.setText(Integer.toString(Battleships.getController().getMatchInfo().get().getShipsNumber()));
        this.shotAvailablePLOne.setEditable(false);

        this.shotAvailablePLTwo.setText(Integer.toString(Battleships.getController().getMatchInfo().get().getShipsNumber()));
        this.shotAvailablePLTwo.setEditable(false);
    }

    private void initMapPanes() {
        this.panes = new HashMap<>();
        this.panes.put(PlayerNumber.PLAYER_ONE, new HashMap<>());
        this.panes.put(PlayerNumber.PLAYER_TWO, new HashMap<>());
    }

    @FXML
    public void initialize() {
        this.initMapPanes();
        this.initGridPane(this.playerOneGrid);
        this.initGridPane(this.playerTwoGrid);
        this.initTextField();
        this.nameOne.setText(Battleships.getController().getMatchInfo()
                .get()
                .getPlayerInfo(PlayerNumber.PLAYER_ONE)
                .getUsername());
        this.nameTwo.setText(Battleships.getController().getMatchInfo()
                .get()
                .getPlayerInfo(PlayerNumber.PLAYER_TWO)
                .getUsername());
        this.currentPlayerGridPane = this.playerOneGrid;
        this.currentVillainGridPane = this.playerTwoGrid;
        this.currentShotAvailable = this.shotAvailablePLTwo;
        this.currentPointsPL = this.pointsPLOne;
        this.controller = Battleships.getController().getMatchController();
        this.controller.setView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showCellAlreadyShottedAlert(final Pair<Integer, Integer> cell) {
        final String description = "Cell [line, column]: [" + cell.getX() + "," + cell.getY() + "] is already shotted.\n"
                + "Select another cell, please.";
        Battleships.getController().launchDialog(DialogType.WARNING, "Choiche not valid", "Cell choiced is already shotted!",
                description);
    }

    @Override
    public void showCellAlreadyUsedAlert(final List<Pair<Integer, Integer>> cell) {
        final String description = "Cell [line, column]: "
                + cell.stream().map(e -> "[" + e.getX() + "," + e.getY() + "]").collect(joining(",")) + " already used." + "\n"
                + "Select a different place, please.";
        Battleships.getController().launchDialog(DialogType.WARNING, "Choiche not valid", "Position choiced is already used!",
                description);
    }

    @Override
    public void showWinDialog() {
        final String winner = Battleships.getController().getMatchInfo()
                .get()
                .getPlayerInfo(Battleships.getController().getCurrentPlayer().get())
                .getUsername();

        final String description = "Il giocatore " + winner + " vince la partita!";
        final Optional<String> returnDialog = Battleships.getController().launchDialog(DialogType.INFORMATION, "Fine partita",
                "Vittoria!", description);
        System.out.println(returnDialog.orElse("Non Presente"));
    }

    @Override
    public void drawHit(final Pair<Integer, Integer> cell, final PlayerNumber playerNumber) {
        Platform.runLater(() -> {
            this.getNodeByRowColumnIndex(cell, playerNumber)
                    .setStyle("-fx-background-color: #ff6600");
        });
    }


    @Override
    public void drawShip(final List<Pair<Integer, Integer>> cells, final PlayerNumber playerNumber) {
        for (final Pair<Integer, Integer> cell : cells) {
            Platform.runLater(() -> {
                this.getNodeByRowColumnIndex(cell, playerNumber)
                        .setStyle("-fx-background-color: #00995c");
            });
        }
    }

    @Override
    public void drawSunkShip(final ShipType shipType, final List<Pair<Integer, Integer>> cells, final PlayerNumber playerNumber) {
        for (final Pair<Integer, Integer> cell : cells) {
            Platform.runLater(() -> {
                this.getNodeByRowColumnIndex(cell, playerNumber)
                        .setStyle("-fx-background-color: #660033");
            });
        }
    }

    @Override
    public void drawMissed(final Pair<Integer, Integer> cell, final PlayerNumber playerNumber) {
        Platform.runLater(() -> {
            this.getNodeByRowColumnIndex(cell, playerNumber)
                    .setStyle("-fx-background-color: #329ef9");
        });
    }

    @Override
    public void changePlayer() {
        if (Battleships.getController().getCurrentPlayer().get().equals(PlayerNumber.PLAYER_TWO)) {
            this.currentPlayerGridPane = playerOneGrid;
            this.currentPointsPL = this.pointsPLOne;
            this.currentShotAvailable = this.shotAvailablePLTwo;
            this.currentVillainGridPane = playerTwoGrid;
            // this.playerNumber = PlayerNumber.PLAYER_ONE;
            // aggiungere nextPlayer
        } else {
            this.currentPlayerGridPane = playerTwoGrid;
            this.currentPointsPL = this.pointsPLTwo;
            this.currentShotAvailable = this.shotAvailablePLOne;
            this.currentVillainGridPane = playerOneGrid;
            // aggiungere nextPlayer
            // this.playerNumber = PlayerNumber.PLAYER_TWO;
        }
    }

    @Override
    public void setPoints(final int point) {
        this.currentPointsPL.setText("" + point);
    }

    @Override
    public void setShotAvailable(final int shotAvailable) {
        this.currentShotAvailable.setText("" + shotAvailable);
    }

    private Pane getNodeByRowColumnIndex(final Pair<Integer, Integer> cell, final PlayerNumber playerNumber) {
        return this.panes.get(playerNumber).get(cell);
    }
}
