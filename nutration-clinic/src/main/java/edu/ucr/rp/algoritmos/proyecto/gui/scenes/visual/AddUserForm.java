/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.scenes.visual;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class AddUserForm implements PaneViewer {

    private static GridPane pane;
    private static Label userNameLabel;
    private static Label passwordNameLabel;
    private static Label emailLabel;
    private static Label addressLabel;
    private static Label phoneNumberLabel;
    private static Label idLabel;
    private static Label rolLabel;
    private static ComboBox rolComboBox;
    private static ObservableList observableList;

    private static TextField userNameTextField;
    private static TextField passwordTextField;
    private static TextField emailTextField;
    private static TextField addressTextField;
    private static TextField phoneNumberTextField;
    private static TextField IdTextField;
    
    private static Button extiButton;
    private static Button registersUserButton;
    private static UserService userService;
    
    public GridPane AddUserForm() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }

    
   /*private void updateUserService(){
       userService = UserService.getInstance;
   }*/
    
    private void setupControls() {
        userNameLabel = PaneUtil.buildLabel(pane, "User name", 0, 0);
        userNameTextField = PaneUtil.buildTextInput(pane, 1, 0);
        emailLabel = PaneUtil.buildLabel(pane, "Email", 0, 1);
        emailTextField = PaneUtil.buildTextInput(pane, 1, 1);
        addressLabel = PaneUtil.buildLabel(pane, "Address ", 0, 2);
        addressTextField = PaneUtil.buildTextInput(pane, 1, 2);
        phoneNumberLabel = PaneUtil.buildLabel(pane, "Phone number", 0, 3);
        phoneNumberTextField = PaneUtil.buildTextInput(pane, 1, 3);
        idLabel = PaneUtil.buildLabel(pane, "ID", 0, 4);
        IdTextField = PaneUtil.buildTextInput(pane, 1, 4);
        passwordNameLabel = PaneUtil.buildLabel(pane, "Password", 0, 5);
        passwordTextField = PaneUtil.buildTextInput(pane, 1, 5);
        rolLabel = PaneUtil.buildLabel(pane, "Select rol", 0, 6);
        buildComboBox();
        registersUserButton = PaneUtil.buildButton("Add User", pane, 0, 7);
        registersUserButton.setAlignment(Pos.CENTER_RIGHT);
        extiButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 8);

    }

    private void buildComboBox(){
        observableList = FXCollections.observableArrayList("2", "3");
        rolComboBox = PaneUtil.buildComboBox(pane, observableList, 1, 6);
    }
    
    private void addHandlers() {
        extiButton.setOnAction(e -> Platform.exit());
        userNameTextField.setOnMouseClicked(event -> {
            userNameTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        passwordTextField.setOnAction(event -> {
            passwordTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        registersUserButton.setOnAction(e -> {
            validateLogIn();
        });
        emailTextField.setOnMouseClicked(event -> {
            emailTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        addressTextField.setOnMouseClicked(event -> {
            addressTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        phoneNumberTextField.setOnMouseClicked(event -> {
            phoneNumberTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        IdTextField.setOnMouseClicked(event -> {
            IdTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        
        
    }

    private boolean validateLogIn() {
        if (userNameTextField.getText().isEmpty()) {
            userNameTextField.setPromptText("Obligatory field");
            userNameTextField.setStyle("-fx-background-color: #FDC7C7");
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
        if (IdTextField.getText().isEmpty()) {
            IdTextField.setPromptText("Obligatory field");
            IdTextField.setStyle("-fx-background-color: #FDC7C7");

        }

        if (!userNameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !addressTextField.getText().isEmpty() && !phoneNumberTextField.getText().isEmpty() && !IdTextField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    @Override
    public Pane getPane() {
        return AddUserForm();
    }
}// end class
