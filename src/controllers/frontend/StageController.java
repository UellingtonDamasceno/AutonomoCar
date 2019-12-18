package controllers.frontend;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Uellington Damasceno
 */
public class StageController {

    private Map<String, Stage> stages;
    private final Pane container;

    public StageController(Stage mainStage, String titleStage) {
        this.stages = new HashMap();
        this.container = new Pane();

        this.stages.put("mainStage", mainStage);

        mainStage.setResizable(false);
        mainStage.setTitle(titleStage);

    }

    public void changeMainStage(Parent content) {
        this.changeStageContent("mainStage", content);
    }

    public void changeStageContent(String stageName, Parent newContent) {
        Stage stage;
        if (this.stages.containsKey(stageName)) {
            stage = stages.get(stageName);
        } else {
            stage = new Stage();
            stages.put(stageName, stage);
        }
        stage.setScene(new Scene(newContent));
        stage.show();
    }

}
