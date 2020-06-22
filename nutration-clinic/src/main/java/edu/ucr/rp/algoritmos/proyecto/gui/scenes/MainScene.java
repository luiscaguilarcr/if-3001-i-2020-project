package edu.ucr.rp.algoritmos.proyecto.gui.scenes;


import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.SceneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.LogInManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene implements SceneViewer {
    private Stage stage;
    private MainManagePane mainManagePane;
    private LogInManagePane logInManagePane;

    /**
     * Receive the main stage reference.
     *
     * @param stage App stage.
     */
    public MainScene(Stage stage) {
        this.stage = stage;
        mainManagePane = new MainManagePane(stage);
        logInManagePane = new LogInManagePane(stage);
    }

    @Override
    public Scene getScene() {
        return buildScene(LogIn.validateUser());
    }

    public Scene buildScene(boolean validate) {
        Scene scene;
        if (validate) {
            scene = new Scene(mainManagePane.getPane(), 1364, 745);
        } else {
            scene = new Scene(logInManagePane.getPane(), 1364, 745);
        }
        // more code...
        scene.getStylesheets().add("Style.css");
        return scene;
    }
}
