package edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane;

import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.SignUpForm;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class LogInManagePane implements PaneViewer {

    // Contains all the Panes.
    private static Map<PaneName, Pane> panes = new HashMap<>();
    private Stage stage;
    private static Pane basePane;
    private static HBox hbox;

    public LogInManagePane(Stage stage) {
        this.stage = stage;
        this.basePane = buildBasePane();
        initializePanes(stage, basePane);
        setupBasePane(basePane);
    }

    /**
     * Create and store all the panes.
     * Set up the main pane.
     */
    private void initializePanes(Stage stage, Pane basePane) {
        panes.put(PaneName.BASE, basePane);
        panes.put(PaneName.LOG_IN, new LogIn(stage).getPane()); 
        panes.put(PaneName.SIGN_UP_FORM, new SignUpForm().getPane());
    }

    @Override
    public Pane getPane() {
        return panes.get(PaneName.BASE);
    }

    /**
     * Just inizialize the pane without children.
     * Build the base pane.
     *
     * @return the base pane.
     */
    private Pane buildBasePane() {
        GridPane gridPane = new GridPane();
        return gridPane;
    }

    /**
     * Configure all the panes for the basePane.
     *
     * @param pane
     */
    private void setupBasePane(Pane pane) {
        ((GridPane) pane).add(panes.get(PaneName.LOG_IN), 0, 0);
        hbox = new HBox();
        ((GridPane) pane).add(hbox, 0, 1);
    }

    /**
     * Change the current pane
     *
     * @param pane
     */
    public static void setCenterPane(Pane pane) {
        hbox.getChildren().clear();
     hbox.getChildren().add(pane);   
    }

    /**
     * Change the current pane
     *
     */
    public static void clearPane() {
        hbox.getChildren().clear();
    }

    /**
     * Returns a Map of the scenes
     *
     * @return
     */
    public static Map<PaneName, Pane> getPanes() {
        return panes;
    }
}
