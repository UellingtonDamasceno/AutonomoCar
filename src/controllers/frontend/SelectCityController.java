package controllers.frontend;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import model.exceptions.FileNameIsEmptyException;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class SelectCityController implements Initializable {

    @FXML
    private ComboBox<String> cbSelectCity;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.initialize();
    }
    
    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.MAIN);
        } catch (Exception ex) {
            Logger.getLogger(SelectCityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void createCity(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.BUILDER_SETTINGS);
        } catch (Exception ex) {
            Logger.getLogger(SelectCityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void next(ActionEvent event) {
        try {
            FacadeBackend.getInstance().loadCity(cbSelectCity.getSelectionModel().getSelectedItem());
            FacadeFrontend.getInstance().changeScreean(Scenes.SELECT_CAR);
        } catch (Exception ex) {
            Logger.getLogger(SelectCityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initialize(){
        try {
            String[] cities = FacadeBackend.getInstance().getAllCities();
            ObservableList<String> cbItens = FXCollections.observableArrayList(cities);
            this.cbSelectCity.setItems(cbItens);
            this.cbSelectCity.getSelectionModel().select(0);
        } catch (FileNameIsEmptyException ex) {
            Logger.getLogger(SelectCityController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
