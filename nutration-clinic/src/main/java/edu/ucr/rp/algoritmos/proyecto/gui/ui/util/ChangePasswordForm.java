package edu.ucr.rp.algoritmos.proyecto.gui.ui.util;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class ChangePasswordForm implements PaneViewer {
    private static GridPane pane;
    private static Button cancelButton;
    private static Button modifyButton;
    private static TextField currentPasswordTextField;
    private static TextField newPassword1TextField;
    private static TextField newPassword2TextField;
    private static Label currentPasswordLabel;
    private static Label newPassword1Label;
    private static Label newPassword2Label;

    public GridPane addDatesForm() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }

    private void setupControls() {
        currentPasswordLabel = PaneUtil.buildLabel(pane, "Current password", 0, 2);
        currentPasswordTextField = PaneUtil.buildTextField(pane, 2);
        newPassword1Label = PaneUtil.buildLabel(pane, "New password", 0, 3);
        newPassword1TextField = PaneUtil.buildTextField(pane, 3);
        newPassword2Label = PaneUtil.buildLabel(pane, "Confirm password", 0, 4);
        newPassword2TextField = PaneUtil.buildTextField(pane, 4);
        modifyButton = PaneUtil.buildButtonImage(new Image(""), pane, 0, 6);
        cancelButton = PaneUtil.buildButtonImage(new Image(""), pane, 1, 6);
    }

    private void addHandlers() {
        cancelButton.setOnAction(event -> MainManagePane.clearPane());

        modifyButton.setOnAction(e -> {
        });

    }

    @Override
    public Pane getPane() {
        return null;
    }
}
