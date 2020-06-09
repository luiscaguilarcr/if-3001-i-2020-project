package edu.ucr.rp.algoritmos.proyecto.gui.scenes;

import edu.ucr.rp.algoritmos.proyecto.gui.model.SceneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.panes.AdminManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.panes.LogInManagePane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminScene implements SceneViewer {
    private Stage stage;
    private AdminManagePane managePane;

    /**
     * Receive the main stage reference.
     *
     * @param stage App stage.
     */
    public AdminScene(Stage stage) {
        this.stage = stage;
        managePane = new AdminManagePane(stage);
    }

    @Override
    public Scene getScene() {
        return buildScene();
    }

    private Scene buildScene() {
        Scene scene = new Scene(managePane.getPane(), 900, 700);
        // more code...
        scene.getStylesheets().add("Style.css");
        return scene;
    }
}
