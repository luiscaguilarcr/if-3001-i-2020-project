/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


/**
 * @author Noel
 */
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
    private static UserService userService;

    public GridPane changePassword() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        serviceInstance();
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
        cancelButton.setOnAction(e -> {
            refreshItems();
            MainManagePane.clearPane();
        });

        modifyButton.setOnAction(e -> {
            Utility utility = new Utility();
            String encrypted = utility.encrypt(newPassword1TextField.getText());

            if (validateAdd()) {
                if (valiteFill()) {

                    if (utility.encrypt(currentPasswordTextField.getText()).equals(LogIn.getUser().getPassword())) {

                        if (changePassword(encrypted, LogIn.getUser())) {
                            refreshItems();
                            PaneUtil.showAlert(Alert.AlertType.INFORMATION, "Password changed", "The new password has been changed");
                        } else {
                            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", " Added a password diferent of the original");
                        }
                    } else {
                        PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The new password cannot be the same as the old one");
                    }
                } else {
                    PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "New password are diferent");

                }
            }
        });
    }

    private void refreshItems() {
        currentPasswordTextField.clear();
        newPassword1TextField.clear();
        newPassword2TextField.clear();
    }

    public boolean changePassword(String password, User user) {
        if (user.getPassword() == null ? password != null : !user.getPassword().equals(password)) {
            user.setPassword(password);
            if (userService.edit(user, user)) {
                return true;
            }
        }
        return false;
    }

    private boolean valiteFill() {
        if (newPassword1TextField.getText().equals(newPassword2TextField.getText())) {
            return true;
        } else {
            return false;
        }

    }

    private boolean validateAdd() {
        if (currentPasswordTextField.getText().isEmpty()) {
            currentPasswordTextField.setPromptText("Obligatory field");
            currentPasswordTextField.setStyle("-fx-background-color: #FDC7C7");
            return false;
        }
        if (newPassword1TextField.getText().isEmpty()) {
            newPassword1TextField.setPromptText("Obligatory field");
            newPassword1TextField.setStyle("-fx-background-color: #FDC7C7");
            return false;
        }
        if (newPassword2TextField.getText().isEmpty()) {
            newPassword2TextField.setPromptText("Obligatory field");
            newPassword2TextField.setStyle("-fx-background-color: #FDC7C7");
            return false;

        }
        return true;
    }

    public static void serviceInstance() {
        userService = UserService.getInstance();
    }

    @Override
    public Pane getPane() {
        return changePassword();
    }
}
