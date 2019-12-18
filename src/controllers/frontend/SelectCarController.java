/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import util.Settings.SpritesCars;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class SelectCarController implements Initializable {

    @FXML
    private GridPane gridPane;
    @FXML
    private Label lblCarName;
    @FXML
    private ImageView imgRealCar;
    @FXML
    private Button btnNext;

    private SpritesCars selectedCar;
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadImages();
    }

    @FXML
    private void next(ActionEvent event) {
        try {
            FacadeBackend.getInstance().selectCar(selectedCar);
            FacadeFrontend.getInstance().changeScreean(Scenes.ROADS);
        } catch (Exception ex) {
            Logger.getLogger(SelectCarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadImages() {
        Image image;
        ImageView imageView;
        int i = 0, j = 0;
        for (SpritesCars car : SpritesCars.values()) {
            System.out.println(car.getSmall() + "[i = " + i + ", j = " + j + "]");
            
            image = new Image(car.getSmall());
            imageView = new ImageView(image);
            
            imageView.setFitWidth(95);
            imageView.setFitHeight(185);
            
            imageView.setOnMouseClicked((MouseEvent event) -> {
                Platform.runLater(() -> {
                    lblCarName.setText(car.toString());
                    imgRealCar.setImage(new Image(car.getBig()));
                    btnNext.setDisable(false);
                    selectedCar = car;
                    System.out.println("Selected car: "+ car.name());
                });
            });

            gridPane.add(imageView, j, i);
            
            if (j == 1) {
                i++;
                j = -1;
            }
            j++;
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

}
