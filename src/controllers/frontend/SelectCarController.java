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
import model.Car;
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
        this.selectedCar = SpritesCars.values()[0];
        this.imgRealCar.setImage(new Image(selectedCar.getBig()));
        this.lblCarName.setText(selectedCar.name());
        btnNext.setDisable(false);
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
                    selectedCar = car;
                    System.out.println("Selected car: " + car.name());
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
    private void setNextScreen(ActionEvent event) {
        try {
            Car car = FacadeBackend.getInstance().createCar(selectedCar);
            FacadeBackend.getInstance().setMainCar(car);
            FacadeFrontend.getInstance().changeScreenAndSetController(Scenes.ROADS);
        } catch (Exception ex) {
            Logger.getLogger(SelectCarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setPreviousScreen(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.SELECT_CITY);
        } catch (Exception ex) {
            Logger.getLogger(SelectCarController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
