/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

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
        ObservableList<ColumnConstraints> columns = this.gridPane.getColumnConstraints();
        ObservableList<RowConstraints> row = this.gridPane.getRowConstraints();

        Image image = new Image("/images/sprite-road.jpg");
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < 10; j++) {
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(61);
                imageView.setFitWidth(77);
                gridPane.add(imageView, i, j);
            }
        }
    }

    @FXML
    private void updateCoordinate(MouseEvent event) {
        lblCordinates.setText("X = " + event.getX() + " :: " + "Y = " + event.getY());
    }

    private void render() {

    }
}
