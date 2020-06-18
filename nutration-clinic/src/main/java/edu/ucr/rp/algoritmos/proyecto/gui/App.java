package edu.ucr.rp.algoritmos.proyecto.gui;

import edu.ucr.rp.algoritmos.proyecto.gui.model.SceneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.MainScene;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        // Start with the main scene
        scenes.put(SceneName.MAIN_S, new MainScene(stage).getScene());
        stage.setScene(scenes.get(SceneName.MAIN_S));

        stage.setTitle("Inventory System");
        //stage.getIcons().add(new Image("logo.png"));
        stage.getIcons().add(new Image("naturaleza.png"));
        stage.show();
    }

    /**
     * Returns a Map of the scenes by {@link SceneName}
     */
    public static Map<SceneName, Scene> getScenes() {
        return scenes;
    }

    public void display(String... args) {
        launch(args);
    }
}
