/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import facade.FacadeBackend;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.gridPane.getColumnConstraints().clear();
        this.gridPane.getRowConstraints().clear();
        
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

        Image image = new Image(SpritesCity.ROAD.get());
        
        System.out.println("Reder city: " + rows + " x " + columns);
        System.out.println("Rendering grid X: " + columns + ":: Y: " + rows);
        
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(61);
                imageView.setFitWidth(77);
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
}
