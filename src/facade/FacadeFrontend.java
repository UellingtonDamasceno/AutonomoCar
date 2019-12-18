package facade;

import controllers.frontend.ScreensController;
import controllers.frontend.StageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import util.Settings.Scenes;

/**
 *
 * @author Uellington Damasceno
 */
public class FacadeFrontend {

    private static FacadeFrontend facade;

    private ScreensController screensController;
    private StageController stageController;

    private FacadeFrontend() {
        this.screensController = new ScreensController();
    }

    public static synchronized FacadeFrontend getInstance() {
        return (facade == null) ? facade = new FacadeFrontend() : facade;
    }

    public void initialize(Stage stage, Scenes scene) throws Exception {
        Parent loadedScreen = this.screensController.loadScreen(scene);

        this.stageController = new StageController(stage, scene.getTitle());
        this.stageController.changeMainStage(loadedScreen);
    }

    public void changeScreean(Scenes scene) throws Exception {
        Parent loadedScreen = this.screensController.loadScreen(scene);
        this.stageController.changeMainStage(loadedScreen);
    }

    public void showContentAuxStage(Scenes scenes, String name) throws Exception {
        Parent content = this.screensController.loadScreen(scenes);
        this.stageController.changeStageContent(name, content);
    }
}
