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
import javafx.scene.control.TextField;
import util.Settings;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class BuilderSettingsController implements Initializable {

    @FXML
    private TextField txtWidth;
    @FXML
    private TextField txtX;
    @FXML
    private TextField txtY;
    @FXML
    private TextField txtPropotion;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtHeight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void previous(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Settings.Scenes.MAIN);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void next(ActionEvent event) {
        String name = this.txtName.getText();
        Double height = Double.valueOf(this.txtHeight.getText());
        Double width = Double.valueOf(this.txtWidth.getText());
        int x = Integer.valueOf(this.txtX.getText());
        int y = Integer.valueOf(this.txtY.getText());
//        int prop = Integer.valueOf(this.txtPropotion.getText());
        
        FacadeBackend.getInstance().createCity(name, height, width, x, y, 0);
        try {
            FacadeFrontend.getInstance().changeScreean(Settings.Scenes.BUILDER);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
