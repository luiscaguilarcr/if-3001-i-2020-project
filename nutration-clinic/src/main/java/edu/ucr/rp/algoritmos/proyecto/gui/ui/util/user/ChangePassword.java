/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class ChangePassword implements PaneViewer {

    private static GridPane pane;
    private static Button cancelButton;
    private static Button modifyButton;
    private static TextField currentPasswordTextField;
    private static TextField newPassword1TextField;
    private static TextField newPassword2TextField;
    private static Label currentPasswordLabel;
    private static Label newPassword1Label;
    private static Label newPassword2Label;

    public GridPane changePassword() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        //serviceInstance();
        //visible();
        return pane;
    }

    private void setupControls() {
    currentPasswordLabel = PaneUtil.buildLabel(pane, "Current password", 0, 2);
        currentPasswordTextField = PaneUtil.buildTextField(pane, 2);
        newPassword1Label = PaneUtil.buildLabel(pane, "New password", 0, 3);
        newPassword1TextField = PaneUtil.buildTextField(pane, 3);
        newPassword2Label = PaneUtil.buildLabel(pane, "Confirm password", 0, 4);
        newPassword2TextField = PaneUtil.buildTextField(pane, 4);
        modifyButton = PaneUtil.buildButton("Confirm", pane, 1, 5);
        cancelButton = PaneUtil.buildButton("Back", pane, 2, 5);
    }

    private void addHandlers() {
    }

    @Override
    public Pane getPane() {

        return changePassword();
    }
}
