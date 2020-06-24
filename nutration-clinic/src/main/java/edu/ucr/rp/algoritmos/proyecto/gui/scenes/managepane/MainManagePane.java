package edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;

import edu.ucr.rp.algoritmos.proyecto.gui.ui.Main_MenuBar;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.AddDatesForm;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.ModifyDate;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.ViewDate;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class MainManagePane implements PaneViewer {
    // Contains all the Panes.
    private static Map<PaneName, Pane> panes = new HashMap<>();
    private Stage stage;
    private static Pane basePane;
    private static HBox hbox;

    public MainManagePane(Stage stage) {
        this.stage = stage;
        this.basePane = buildBasePane();
        initializePanes(stage, basePane);
        setupBasePane(basePane);
    }

    /**
     * Create and store all the scenes.
     * Set up the main pane.
     */
    private void initializePanes(Stage stage, Pane basePane) {
        panes.put(PaneName.BASE, basePane);
        panes.put(PaneName.LOG_IN, new Main_MenuBar(stage).getPane());
        //panes.put(PaneName.CHANGE_PASSWORD), new ChangePassword().getPane());
        //panes.put(PaneName.GENERATE_REPORT), new GenerateReport().getPane());
        //panes.put(PaneName.PERSONAL_PROGRESS), new PersonalProgress().getPane());

        //panes.put(PaneName.ADD_USER_FORM), new AddUserForm().getPane());
        //panes.put(PaneName.MODIFY_USER_FORM), new ModifyUserForm().getPane());
        //panes.put(PaneName.VIEW_USER_FORM), new ViewUserForm().getPane());

        panes.put(PaneName.ADD_DATE_FORM, new AddDatesForm().getPane());
       panes.put(PaneName.MODIFY_DATE_FORM, new ModifyDate().getPane());
        // panes.put(PaneName.VIEW_DATES, new ViewDate().getPane());

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
