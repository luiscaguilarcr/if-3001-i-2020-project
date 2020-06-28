/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;

import static edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn.refresh;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;

import static javafx.collections.FXCollections.observableList;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Noel
 */
public class AddCustomerForm implements PaneViewer {

    private static UserService userService;
    public static Service service;
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

    public GridPane addUserForm() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        serviceInstance();
        visible();
        return pane;
    }

    private void setupControls() {
        List<String> list = new ArrayList<String>();
        list.add("3");

        ObservableList<String> observableList = FXCollections.observableList(list);
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
        rolTextField.setText("3");
        rolTextField.setDisable(true);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 15);
        addButton = PaneUtil.buildButton("Add", pane, 4, 15);
    }

    public static void serviceInstance() {
        userService = UserService.getInstance();
    }

    private void addHandlers() {
        exitButton.setOnAction(e -> {
            LogIn.visible();
            notVisible();
        });

        addButton.setOnAction(e -> {

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
                    LogIn.visible();
                    notVisible();
                } else {
                    PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The user was not added correctly");
                }
            }
        });
    }

    private void notVisible() {
        nameLabel.setVisible(false);
        nameTextField.setVisible(false);
        nameTextField.clear();
        passwordLabel.setVisible(false);
        passwordTextField.setVisible(false);
        passwordTextField.clear();
        emailLabel.setVisible(false);
        emailTextField.setVisible(false);
        emailTextField.clear();
        addressLabel.setVisible(false);
        addressTextField.setVisible(false);
        addressTextField.clear();
        phoneNumberLabel.setVisible(false);
        phoneNumberTextField.setVisible(false);
        phoneNumberTextField.clear();
        iDLabel.setVisible(false);
        iDTextField.setVisible(false);
        iDTextField.clear();
        rolTextField.setVisible(false);
        rolLabel.setVisible(false);
        exitButton.setVisible(false);
        addButton.setVisible(false);
    }

    public static void visible() {
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        nameTextField.clear();
        passwordLabel.setVisible(true);
        passwordTextField.setVisible(true);
        passwordTextField.clear();
        emailLabel.setVisible(true);
        emailTextField.setVisible(true);
        emailTextField.clear();
        addressLabel.setVisible(true);
        addressTextField.setVisible(true);
        addressTextField.clear();
        phoneNumberLabel.setVisible(true);
        phoneNumberTextField.setVisible(true);
        phoneNumberTextField.clear();
        iDLabel.setVisible(true);
        iDTextField.setVisible(true);
        iDTextField.clear();
        rolTextField.setVisible(true);
        rolLabel.setVisible(true);
        exitButton.setVisible(true);
        addButton.setVisible(true);
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
            refresh();
            return true;
        }
        return false;
    }

    @Override
    public Pane getPane() {
        return addUserForm();
    }

    public boolean validateNumber(String data) {
        return data.matches("[0-9]");
    }
}
