package view;

import facade.FacadeBackend;
import facade.FacadeFrontend;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.stage.Stage;
import util.Settings.Scenes;

/**
 *
 * @author uellington
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FacadeBackend.getInstance().initialize();
            FacadeFrontend.getInstance().initialize(primaryStage, Scenes.MAIN);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
