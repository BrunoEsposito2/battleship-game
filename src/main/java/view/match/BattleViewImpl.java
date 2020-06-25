package view.match;

import java.util.List;
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

    private PlayerNumber playerNumber;

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
            }
        }
        gridPane.setStyle("-fx-background-color: #000000");
    }

    private void initTextField() {
        this.pointsPLOne.setText("" + 0);
        this.pointsPLOne.setEditable(false);

        this.pointsPLTwo.setText("" + 0);
        this.pointsPLTwo.setEditable(false);

        this.shotAvailablePLOne.setText("" + MatchControllerImpl.getShipNumberOfGame());
        this.shotAvailablePLOne.setEditable(false);

        this.shotAvailablePLTwo.setText("" + MatchControllerImpl.getShipNumberOfGame());
        this.shotAvailablePLTwo.setEditable(false);
    }

    @FXML
    public void initialize() {
        this.initGridPane(this.playerOneGrid);
        this.initGridPane(this.playerTwoGrid);
        this.initTextField();
        this.nameOne.setText(Battleships.getController().getPlayerInfo(PlayerNumber.PLAYER_ONE).get().getUsername());
        this.nameOne.setText(Battleships.getController().getPlayerInfo(PlayerNumber.PLAYER_TWO).get().getUsername());
        this.currentPlayerGridPane = this.playerOneGrid;
        this.currentVillainGridPane = this.playerTwoGrid;
        //this.playerNumber = PlayerNumber.PLAYER_ONE;
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
        final String winner = Battleships.getController()
                .getPlayerInfo(Battleships.getController().getCurrentPlayer().get())
                .get().getUsername();
        final String description = "Il giocatore " + winner + " vince la partita!";
        final Optional<String> returnDialog = Battleships.getController().launchDialog(DialogType.INFORMATION, "Fine partita",
                "Vittoria!", description);
        System.out.println(returnDialog.orElse("Non Presente"));
    }

    @Override
    public void drawHit(final Pair<Integer, Integer> pair) {
        Platform.runLater(() -> {
            this.getNodeByRowColumnIndex(pair.getX(), pair.getY(), this.currentVillainGridPane)
                    .setStyle("-fx-background-color: #ff6600");
        });
    }

    // @Override
    // public void drawShip(nave da disegnare e posizioni) {
    //
    // }

    @Override
    public void drawShip(final List<Pair<Integer, Integer>> cells) {
        for (final Pair<Integer, Integer> cell : cells) {
            Platform.runLater(() -> {
                this.getNodeByRowColumnIndex(cell.getX(), cell.getY(), this.currentVillainGridPane)
                        .setStyle("-fx-background-color: #00995c");
            });
        }
    }

    @Override
    public void drawSunkShip(final ShipType shipType, final List<Pair<Integer, Integer>> cells) {
        for (final Pair<Integer, Integer> cell : cells) {
            Platform.runLater(() -> {
                this.getNodeByRowColumnIndex(cell.getX(), cell.getY(), this.currentVillainGridPane)
                        .setStyle("-fx-background-color: #660033");
            });
        }
    }

    @Override
    public void drawMissed(final Pair<Integer, Integer> pair) {
        Platform.runLater(() -> {
            this.getNodeByRowColumnIndex(pair.getX(), pair.getY(), this.currentVillainGridPane)
                    .setStyle("-fx-background-color: #329ef9");
        });
    }

    @Override
    public void changePlayer() {
        if (Battleships.getController().getCurrentPlayer().get() == PlayerNumber.PLAYER_TWO) {
            this.currentPlayerGridPane = playerOneGrid;
            this.currentPointsPL = this.pointsPLOne;
            this.currentShotAvailable = this.shotAvailablePLTwo;
            this.currentVillainGridPane = playerTwoGrid;
            //this.playerNumber = PlayerNumber.PLAYER_ONE;
            //aggiungere nextPlayer
        } else {
            this.currentPlayerGridPane = playerTwoGrid;
            this.currentPointsPL = this.pointsPLTwo;
            this.currentShotAvailable = this.shotAvailablePLOne;
            this.currentVillainGridPane = playerOneGrid;
            //aggiungere nextPlayer
            //this.playerNumber = PlayerNumber.PLAYER_TWO;
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

    private Node getNodeByRowColumnIndex(final int row, final int column, final GridPane gridPane) {
        Node result = null;
        final ObservableList<Node> childrens = gridPane.getChildren();

        for (final Node node : childrens) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
                result = node;
                return result;
            }
        }
        return result;
    }
}
