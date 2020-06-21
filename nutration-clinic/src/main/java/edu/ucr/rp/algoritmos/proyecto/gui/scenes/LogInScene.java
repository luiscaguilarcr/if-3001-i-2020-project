package edu.ucr.rp.algoritmos.proyecto.gui.scenes;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.SceneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.LogInManagePane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LogInScene implements SceneViewer {
    private Stage stage;
    private LogInManagePane managePane;

    /**
     * Receive the main stage reference.
     *
     * @param stage App stage.
     */
    public LogInScene(Stage stage) {
        this.stage = stage;
        managePane = new LogInManagePane(stage);
    }

    @Override
    public Scene getScene() {
        return buildScene();
    }

    private Scene buildScene() {
        Scene scene = new Scene(managePane.getPane(), 900, 700);
        scene.getStylesheets().add("Style.css");
        return scene;
    }
}
