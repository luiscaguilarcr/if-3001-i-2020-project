/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.LogInManagePane;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn.refresh;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import static edu.ucr.rp.algoritmos.proyecto.util.TestUtility.user;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Noel
 */
public class AddUserForm implements PaneViewer {

    private static GridPane pane;
    private static Button addButton;
    private static Button exitButton;

    private static Label nameLabel;
    private static TextField nameTextField;
    private static Label passwordLabel;
    private static TextField passwordTextField;
    private static Label emailLabel;
    private static TextField emailTextField;
    private static Label addressLabel;
    private static TextField adressTextField;
    private static Label phoneNumberLabel;
    private static TextField phoneNumberTextField;
    private static Label iDLabel;
    private static TextField iDTextField;
    private static ComboBox comboBoxRol;
    private static Label rolLabel;
    // private static TextField rolTextField;
    private static UserService userService;
    public static Service service;

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
        adressTextField = PaneUtil.buildTextInput(pane, 1, 8);
        phoneNumberLabel = PaneUtil.buildLabel(pane, "Phone Number", 1, 9);
        phoneNumberTextField = PaneUtil.buildTextInput(pane, 1, 10);
        iDLabel = PaneUtil.buildLabel(pane, "Id", 1, 11);
        iDTextField = PaneUtil.buildTextInput(pane, 1, 12);
        comboBoxRol = PaneUtil.buildComboBox(pane, observableList, 1, 14);
        rolLabel = PaneUtil.buildLabel(pane, "Rol", 1, 13);
        //rolTextField = PaneUtil.buildTextInput(pane, 1, 14);
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
        // exitButton.setOnAction(e -> refresh());

        addButton.setOnAction(e -> {
            

            if (validateAdd()) {
                Utility utility = new Utility();
                User user = new User();
                user.setName(nameTextField.getText());
                user.setAddress(adressTextField.getText());
                user.setRol(Integer.valueOf(comboBoxRol.getSelectionModel().getSelectedItem().toString()));
                user.setEmail(emailTextField.getText());
                user.setPassword(utility.encrypt(passwordTextField.getText()));
                user.setPhoneNumber(Integer.parseInt(phoneNumberTextField.getText()));
                user.setID(Integer.parseInt(iDTextField.getText()));
                                 
                if(userService.add(user)){
                    PaneUtil.showAlert(Alert.AlertType.INFORMATION, "User added", "The user was added correctly");
                    LogIn.visible();
                    notVisible();
                }else{
                    PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The user was not added correctly");
                }
            }

        });
        //            
        //        }
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
        adressTextField.setVisible(false);
        adressTextField.clear();
        phoneNumberLabel.setVisible(false);
        phoneNumberTextField.setVisible(false);
        phoneNumberTextField.clear();
        iDLabel.setVisible(false);
        iDTextField.setVisible(false);
        iDTextField.clear();
        comboBoxRol.setVisible(false);
        comboBoxRol.getSelectionModel().clearSelection();
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
        adressTextField.setVisible(true);
        adressTextField.clear();
        phoneNumberLabel.setVisible(true);
        phoneNumberTextField.setVisible(true);
        phoneNumberTextField.clear();
        iDLabel.setVisible(true);
        iDTextField.setVisible(true);
        iDTextField.clear();
        comboBoxRol.setVisible(true);
        comboBoxRol.getSelectionModel().clearSelection();
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

        if (adressTextField.getText().isEmpty()) {
            adressTextField.setPromptText("Obligatory field");
            adressTextField.setStyle("-fx-background-color: #FDC7C7");
        }
        if (phoneNumberTextField.getText().isEmpty()) {
            phoneNumberTextField.setPromptText("Obligatory field");
            phoneNumberTextField.setStyle("-fx-background-color: #FDC7C7");
        }
//        if (valiteNumber(phoneNumberTextField.getText())){
//         phoneNumberTextField.setPromptText("Only Numbers");
        
        
        if (iDTextField.getText().isEmpty()) {
            iDTextField.setPromptText("Obligatory field");
            iDTextField.setStyle("-fx-background-color: #FDC7C7");
        }
        if (comboBoxRol.getItems().isEmpty()) {
            comboBoxRol.setPromptText("Obligatory field");
            comboBoxRol.setStyle("-fx-background-color: #FDC7C7");
        }

        if (!iDTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty() && !nameTextField.getText().isEmpty() && !emailTextField.getText().isEmpty() && !phoneNumberTextField.getText().isEmpty() && !comboBoxRol.getItems().isEmpty()) {
            refresh();

            return true;
        }
        return false;
    }

    @Override
    public Pane getPane() {
        return addUserForm();
    }
public boolean valiteNumber(String datos){
return datos.matches("[0-9]");

}
}