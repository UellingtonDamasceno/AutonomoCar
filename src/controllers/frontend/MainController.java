/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import facade.FacadeFrontend;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import middleware.Connection;
import middleware.Router;
import util.Settings;
import util.Settings.Scenes;

/**
 * FXML Controller class
 *
 * @author uellington
 */
public class MainController implements Initializable {

    @FXML
    private TextField lblPort;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.lblPort.setText(Settings.DefaultConnection.DEFAULT_PORT.getStringValue());
    }

    @FXML
    private void setStartScene(ActionEvent event) {
        try {
            Connection.getInstance().initialize(Integer.parseInt(lblPort.getText()));
            Connection.getInstance().addObserver(Router.getInstance());
            FacadeFrontend.getInstance().changeScreean(Scenes.SELECT_CITY);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void setEditorScene(ActionEvent event) {
        try {
            FacadeFrontend.getInstance().changeScreean(Scenes.BUILDER_SETTINGS);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
