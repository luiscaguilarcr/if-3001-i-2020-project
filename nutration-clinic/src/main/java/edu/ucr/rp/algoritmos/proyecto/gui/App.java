package edu.ucr.rp.algoritmos.proyecto.gui;

import edu.ucr.rp.algoritmos.proyecto.gui.model.SceneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.LogInScene;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.SuperAdminScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * Application with the Main Stage.
 */
public class App extends Application {
    // Contains all the Scenes.
    private static Map<SceneName, Scene> scenes = new HashMap<>();

    @Override
    public void start(Stage stage) {
        // Create and store all the scenes.
        scenes.put(SceneName.LOGIN, new LogInScene(stage).getScene()); //pasar stage como parámetro
        //scenes.put(SceneName.SUPER_ADMIN, new SuperAdminScene(stage).getScene());

        // Start with the main scene
        stage.setScene(scenes.get(SceneName.LOGIN));
        //stage.setScene(scenes.get(SceneName.SUPER_ADMIN));
        stage.setTitle("Inventory System");
        stage.show();
    }
    /** Returns a Map of the scenes by {@link SceneName} */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }

    public void display(String... args) {
        launch(args);
    }
}
