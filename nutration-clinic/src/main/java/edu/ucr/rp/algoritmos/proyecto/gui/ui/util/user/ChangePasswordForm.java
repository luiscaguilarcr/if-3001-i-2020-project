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
 * @author Noel y Luis Carlos
 */
public class ChangePasswordForm implements PaneViewer {

    private static GridPane pane;
    private static Button cancelButton;
    private static Button modifyButton;
    private static TextField currentPasswordTextField;
    private static TextField newFirstPasswordTextField;
    private static TextField newSecondPasswordTextField;
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
        newFirstPasswordTextField = PaneUtil.buildTextField(pane, 3);
        newPassword2Label = PaneUtil.buildLabel(pane, "Confirm password", 0, 4);
        newSecondPasswordTextField = PaneUtil.buildTextField(pane, 4);
        modifyButton = PaneUtil.buildButton("Confirm", pane, 1, 5);
        cancelButton = PaneUtil.buildButton("Back", pane, 2, 5);
    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> {
            refreshItems();
            MainManagePane.clearPane();
            refreshItems();
        });

        currentPasswordTextField.setOnAction((e) -> {
            currentPasswordTextField.setStyle("-fx-background-color: #FFFFFF");
        });

        newFirstPasswordTextField.setOnAction((e) -> {
            newFirstPasswordTextField.setStyle("-fx-background-color: #FFFFFF");
        });

        newSecondPasswordTextField.setOnAction((e) -> {
            newSecondPasswordTextField.setStyle("-fx-background-color: #FFFFFF");
        });

        modifyButton.setOnAction(e -> {
            String encrypted = Utility.encrypt(newFirstPasswordTextField.getText());

            if (validateAdd()) { //espacios vacíos
                if (valiteFill()) { // contraseñas iguales
                    if (Utility.encrypt(currentPasswordTextField.getText()).equals(LogIn.getUser().getPassword())) {//contraseña válida
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
                    PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "New passwords are diferent");

                }
            }
        });
    }

    private void refreshItems() {
        currentPasswordTextField.clear();
        newFirstPasswordTextField.clear();
        newSecondPasswordTextField.clear();
    }

    public boolean changePassword(String password, User user) {
        user.setPassword(password);
        return userService.edit(user, user);
    }

    private boolean valiteFill() {
        if (newFirstPasswordTextField.getText().equals(newSecondPasswordTextField.getText())) {
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
        if (newFirstPasswordTextField.getText().isEmpty()) {
            newFirstPasswordTextField.setPromptText("Obligatory field");
            newFirstPasswordTextField.setStyle("-fx-background-color: #FDC7C7");
            return false;
        }
        if (newSecondPasswordTextField.getText().isEmpty()) {
            newSecondPasswordTextField.setPromptText("Obligatory field");
            newSecondPasswordTextField.setStyle("-fx-background-color: #FDC7C7");
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
