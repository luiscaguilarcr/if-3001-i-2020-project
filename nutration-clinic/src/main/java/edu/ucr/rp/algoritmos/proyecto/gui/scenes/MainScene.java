package edu.ucr.rp.algoritmos.proyecto.gui.scenes;


import edu.ucr.rp.algoritmos.proyecto.gui.model.SceneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.managepane.LogInManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.managepane.MainManagePane;
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
            scene = new Scene(mainManagePane.getPane(), 900, 700);
        } else {
            scene = new Scene(logInManagePane.getPane(), 900, 700);
        }
        // more code...
        scene.getStylesheets().add("Style.css");
        return scene;
    }
}
