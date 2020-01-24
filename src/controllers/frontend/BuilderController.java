package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import util.Settings.ActionBuilder;
import util.Settings.Scenes;
import util.Settings.SpritesCity;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class BuilderController implements Initializable {

    @FXML
    private Label lblCoordinates;
    @FXML
    private GridPane gridPane;
    @FXML
    private VBox vboxRoadsTypes;
    @FXML
    private VBox vboxButtons;
    @FXML
    private Button cancel;
    @FXML
    private Button btnSave;
    @FXML
    private BorderPane borderPane;

    private SpritesCity selectedSprite;
    private ActionBuilder action;

    private ImageView[][] images;
    private String[][] imagesPath;

    private int dRow, dy;
    private int dCol, dx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridPane.getRowConstraints().clear();
        gridPane.getColumnConstraints().clear();

        this.dx = FacadeBackend.getInstance().getCityDimensionX();
        this.dy = FacadeBackend.getInstance().getCityDimensionY();

        gridPane.setPrefHeight(FacadeBackend.getInstance().getCityHeight());
        gridPane.setPrefWidth(FacadeBackend.getInstance().getCityWidth());

        dRow = (int) (gridPane.getPrefHeight() / dy);
        dCol = (int) (gridPane.getPrefWidth() / dx);

        this.selectedSprite = SpritesCity.values()[0];
        this.action = ActionBuilder.CLICK;

        this.addNColumns(dx);
        this.addNRows(dy);

        this.initialize(selectedSprite.get(), dx, dy);
        this.loadRoadTypes();

    }

    @FXML
    private void setDefaultCoordinates(MouseEvent event) {
        lblCoordinates.setText("X = 0 :: Y = 0");
    }

    @FXML
    private void clicked(MouseEvent event) {

        int y = (int) (event.getY() / dRow);
        int x = (int) (event.getX() / dCol);

        System.out.println("PosX:" + x);
        System.out.println("PosY:" + y);

        switch (this.action) {
            case CLICK:
                System.out.println("Esta clicando");
                this.putRoad(selectedSprite.get(), x, y);
                break;
            case ROTATE_LEFT:
                this.images[x][y].setRotate(this.images[x][y].getRotate() - 90);
                System.out.println("Esta girando: " + action);

                break;
            case ROTATE_RIGHT:
                this.images[x][y].setRotate(this.images[x][y].getRotate() + 90);
                System.out.println("Esta girando: " + action);
                break;
            default:
                event.consume();
                break;

        }
    }

    @FXML
    private void updateCoordinates(MouseEvent event) {
        lblCoordinates.setText("X = " + event.getX() + " :: " + "Y = " + event.getY());
    }

    @FXML
    private void cancelEditing(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.BUILDER_SETTINGS);
        } catch (Exception ex) {
            Logger.getLogger(BuilderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void save(ActionEvent event) {

    }

    private void initialize(String initialSprite, int dx, int dy) {
        this.images = new ImageView[dx][dy];
        this.imagesPath = new String[dx][dy];

        ImageView imageView;
        Image image = new Image(initialSprite);

        for (int i = 0; i < dx; i++) {
            for (int j = 0; j < dy; j++) {
                imageView = new ImageView(image);

                imageView.setFitHeight(dRow);
                imageView.setFitWidth(dCol);

                this.images[i][j] = imageView;
                this.imagesPath[i][j] = initialSprite;
                this.gridPane.add(imageView, i, j);
            }
        }
    }

    private void loadRoadTypes() {
        ImageView imageView;
        Image image;
        Label options;
        for (SpritesCity sprite : SpritesCity.values()) {
            image = new Image(sprite.get());
            imageView = new ImageView(image);
            options = new Label(sprite.name(), imageView);

            options.setOnMouseClicked((MouseEvent event) -> {
                if (selectedSprite != null && selectedSprite != sprite) {
                    BuilderController.this.selectedSprite = sprite;
                    System.out.println("Imagem selecionada: " + sprite.get());
                    action = ActionBuilder.CLICK;
                }
            });

            this.vboxRoadsTypes.getChildren().add(options);
        }
    }

    private void addNColumns(int columns) {
        ColumnConstraints col;
        for (int i = 0; i < columns; i++) {
            col = new ColumnConstraints();
            col.setMinWidth(dCol);
            this.gridPane.getColumnConstraints().add(col);
        }
    }

    private void addNRows(int rows) {
        RowConstraints row;
        for (int i = 0; i < rows; i++) {
            row = new RowConstraints();
            row.setMinHeight(dRow);
            this.gridPane.getRowConstraints().add(row);
        }
    }

    @FXML
    private void rotateLess(ActionEvent event) {
        this.action = ActionBuilder.ROTATE_LEFT;
    }

    @FXML
    private void rotateMore(ActionEvent event) {
        this.action = ActionBuilder.ROTATE_RIGHT;
    }

    private void putRoad(String sprite, int x, int y) {
        String imagePath = this.imagesPath[x][y];
        ImageView imageView = this.images[x][y];
        Image image = new Image(sprite);

        if (!imagePath.equals(sprite)) {
            FacadeBackend.getInstance().putRoad(sprite, x, y);
            imageView.setImage(image);
            imageView.setRotate(0);
            imagePath = sprite;
        }

    }

}
