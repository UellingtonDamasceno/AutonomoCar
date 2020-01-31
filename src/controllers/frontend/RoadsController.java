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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import util.Settings.Scenes;
import util.Settings.SpritesCity;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class RoadsController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label lblCordinates;

    private double heigth;
    private double width;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        
        gridPane.setPrefHeight(FacadeBackend.getInstance().getCityHeight());
        gridPane.setPrefWidth(FacadeBackend.getInstance().getCityWidth());
        
        int dx = FacadeBackend.getInstance().getCityDimensionX();
        int dy = FacadeBackend.getInstance().getCityDimensionY();

        this.addNColumns(dx);
        this.addNRows(dy);

        this.render();
    }

    @FXML
    private void updateCoordinate(MouseEvent event) {
        lblCordinates.setText("X = " + event.getX() + " :: " + "Y = " + event.getY());
    }

    private void render() {
        int rows = this.gridPane.getRowConstraints().size();
        int columns = this.gridPane.getColumnConstraints().size();

        heigth = this.gridPane.getPrefHeight() / rows;
        width = this.gridPane.getPrefWidth() / columns;

        System.out.println(heigth);
        System.out.println(width);
        Image image;
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                String sprite = FacadeBackend.getInstance().getCity().getRoad(i, j).getSprite();
                image = new Image(sprite);
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(heigth);
                imageView.setFitWidth(width);
                gridPane.add(imageView, i, j);
            }
        }
    }

    private void addNColumns(int columns) {
        for (int i = 0; i < columns; i++) {
            this.gridPane.getColumnConstraints().add(new ColumnConstraints());
        }
    }

    private void addNRows(int rows) {
        for (int i = 0; i < rows; i++) {
            this.gridPane.getRowConstraints().add(new RowConstraints());
        }
    }

    private void spawn(String sprite, int x, int y) {
        Image image = new Image(sprite);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(this.heigth);
        imageView.setFitWidth(this.width);
        this.gridPane.add(imageView, x, y);
    }

    @FXML
    private void pushCar(MouseEvent event) {
        String selectedCar = FacadeBackend.getInstance().getSelectedCar();
        this.spawn(selectedCar, (int)(event.getX()/this.width), (int)(event.getY()/this.heigth));
    }

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.SELECT_CITY);
        } catch (Exception ex) {
            Logger.getLogger(RoadsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
