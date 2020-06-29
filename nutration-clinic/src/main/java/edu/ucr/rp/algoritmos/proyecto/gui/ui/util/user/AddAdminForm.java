/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.DateService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Noel y y Luis Carlos
 */
public class AddAdminForm implements PaneViewer {
    private static UserService userService;
    public static DateService dateService;
    private static Button addButton;
    private static Button exitButton;
    private static Label nameLabel;
    private static Label emailLabel;
    private static Label passwordLabel;
    private static Label addressLabel;
    private static Label phoneNumberLabel;
    private static Label iDLabel;
    private static Label rolLabel;
    private static TextField nameTextField;
    private static TextField passwordTextField;
    private static TextField emailTextField;
    private static TextField addressTextField;
    private static TextField phoneNumberTextField;
    private static TextField iDTextField;
    private static TextField rolTextField;
    private static GridPane pane;

    public GridPane AddAdmin() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        return pane;
    }

    private void setupControls() {
        nameLabel = PaneUtil.buildLabel(pane, "Name", 1, 1);
        nameTextField = PaneUtil.buildTextInput(pane, 1, 2);
        passwordLabel = PaneUtil.buildLabel(pane, "Password", 1, 3);
        passwordTextField = PaneUtil.buildTextInput(pane, 1, 4);
        emailLabel = PaneUtil.buildLabel(pane, "Email", 1, 5);
        emailTextField = PaneUtil.buildTextInput(pane, 1, 6);
        addressLabel = PaneUtil.buildLabel(pane, "Address", 1, 7);
        addressTextField = PaneUtil.buildTextInput(pane, 1, 8);
        phoneNumberLabel = PaneUtil.buildLabel(pane, "Phone Number", 1, 9);
        phoneNumberTextField = PaneUtil.buildTextInput(pane, 1, 10);
        iDLabel = PaneUtil.buildLabel(pane, "Id", 1, 11);
        iDTextField = PaneUtil.buildTextInput(pane, 1, 12);
        rolLabel = PaneUtil.buildLabel(pane, "Rol", 1, 13);
        rolTextField = PaneUtil.buildTextField(pane, 14);
        rolTextField.setText("2");
        rolTextField.setDisable(true);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 15);
        addButton = PaneUtil.buildButton("Add", pane, 4, 15);
    }

    public static void serviceInstance() {
        userService = UserService.getInstance();
    }

    private void addHandlers() {
        exitButton.setOnAction(e -> {
            MainManagePane.clearPane();
            refreshItems();
        });

        addButton.setOnAction(e -> {
            addUser();
        });

        nameTextField.setOnAction(event ->
                nameTextField.setStyle("-fx-background-color: #FFFFFF")
        );
        emailTextField.setOnAction(event ->
                emailTextField.setStyle("-fx-background-color: #FFFFFF")
        );
        addressTextField.setOnAction(event ->
                addressTextField.setStyle("-fx-background-color: #FFFFFF")
        );
        phoneNumberTextField.setOnAction(event ->
                phoneNumberTextField.setStyle("-fx-background-color: #FDC7C7")
        );
        passwordTextField.setOnAction(event ->
                passwordTextField.setStyle("-fx-background-color: #FDC7C7")
        );
        iDTextField.setOnAction(event ->
                iDTextField.setStyle("-fx-background-color: #FDC7C7")
        );
    }

    private void addUser() {
        
        
        if (validateAdd()) {
            
            Utility utility = new Utility();
            User user = new User();
            user.setName(nameTextField.getText());
            user.setAddress(addressTextField.getText());
            user.setRol(Integer.parseInt(rolTextField.getText()));
            user.setEmail(emailTextField.getText());
            user.setPassword(utility.encrypt(passwordTextField.getText()));
            user.setPhoneNumber(Integer.parseInt(phoneNumberTextField.getText()));
            user.setID(Integer.parseInt(iDTextField.getText()));

            if (userService.add(user)) {
                PaneUtil.showAlert(Alert.AlertType.INFORMATION, "User added", "The user was added correctly");
                MainManagePane.clearPane();
                refreshItems();
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The user was not added");
            }
        }

    }

    private void refreshItems() {
        nameTextField.clear();
        passwordTextField.clear();
        emailTextField.clear();
        addressTextField.clear();
        phoneNumberTextField.clear();
        iDTextField.clear();
    }

    private boolean validateAdd() {
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setPromptText("Obligatory field");
            nameTextField.setStyle("-fx-background-color: #FDC7C7");
        }
        if (passwordTextField.getText().isEmpty()) {
            passwordTextField.setPromptText("Obligatory field");
            passwordTextField.setStyle("-fx-background-color: #FDC7C7");
        }
        if (emailTextField.getText().isEmpty()) {
            emailTextField.setPromptText("Obligatory field");
            emailTextField.setStyle("-fx-background-color: #FDC7C7");
        }

        if (addressTextField.getText().isEmpty()) {
            addressTextField.setPromptText("Obligatory field");
            addressTextField.setStyle("-fx-background-color: #FDC7C7");
        }
        if (phoneNumberTextField.getText().isEmpty()) {
            phoneNumberTextField.setPromptText("Obligatory field");
            phoneNumberTextField.setStyle("-fx-background-color: #FDC7C7");
        }
        if (iDTextField.getText().isEmpty()) {
            iDTextField.setPromptText("Obligatory field");
            iDTextField.setStyle("-fx-background-color: #FDC7C7");
        }

        if (!iDTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !nameTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !phoneNumberTextField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Pane getPane() {
        return AddAdmin();
    }
}
