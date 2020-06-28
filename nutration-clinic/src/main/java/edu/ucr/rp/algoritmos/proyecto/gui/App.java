package edu.ucr.rp.algoritmos.proyecto.gui;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.SceneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.MainScene;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;
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
        IOUtility ioUtility = new IOUtility();
        ioUtility.verifyAppDir(); //TODO test

        // Start with the main scene
        scenes.put(SceneName.MAIN_S, new MainScene(stage).getScene());
        stage.setScene(scenes.get(SceneName.MAIN_S));
        
        stage.setTitle("Nutration Clinic");
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
