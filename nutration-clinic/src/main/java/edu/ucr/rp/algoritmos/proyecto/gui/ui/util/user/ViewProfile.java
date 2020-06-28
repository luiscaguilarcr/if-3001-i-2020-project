/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.ViewDate.serviceInstance;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.interfaces.Service;
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
public class ViewProfile implements PaneViewer {

    private static GridPane pane;
    private static Button ViewButton;
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
    private static TextField rolTextField;
    private static Label rolLabel;
    private static UserService userService;
    public static Service service;
    // private static TextField rolTextField;
   

    private Pane ViewProfile() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        serviceInstance();
        return pane;
    }

    private void setupControls() {
    
    nameLabel = PaneUtil.buildLabel(pane, "Name", 1, 1);

        nameTextField = PaneUtil.buildTextInput(pane, 1, 2);
        nameTextField.setDisable(true);
        passwordLabel = PaneUtil.buildLabel(pane, "Password", 1, 3);
        passwordTextField = PaneUtil.buildTextInput(pane, 1, 4);
         passwordTextField.setDisable(true);
        emailLabel = PaneUtil.buildLabel(pane, "Email", 1, 5);
        emailTextField = PaneUtil.buildTextInput(pane, 1, 6);
         emailTextField.setDisable(true);
        addressLabel = PaneUtil.buildLabel(pane, "Address", 1, 7);
        adressTextField = PaneUtil.buildTextInput(pane, 1, 8);
        adressTextField.setDisable(true);
        phoneNumberLabel = PaneUtil.buildLabel(pane, "Phone Number", 1, 9);
        phoneNumberTextField = PaneUtil.buildTextInput(pane, 1, 10);
        phoneNumberTextField.setDisable(true);
        iDLabel = PaneUtil.buildLabel(pane, "Id", 1, 11);
        iDTextField = PaneUtil.buildTextInput(pane, 1, 12);
        iDTextField.setDisable(true);
       // comboBoxRol = PaneUtil.buildComboBox(pane, observableList, 1, 14);
        rolLabel = PaneUtil.buildLabel(pane, "Rol", 1, 13);
        //rolTextField.setEditable(false);
        rolTextField = PaneUtil.buildTextInput(pane, 1, 14);
        rolTextField.setDisable(true);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 15);
        //addButton = PaneUtil.buildButton("Add", pane, 4, 15);
    
    
    }
    
    
    private void addHandlers() {
        exitButton.setOnAction(e -> MainManagePane.clearPane());
       //ViewButton.setOnAction(e -> {
           // nameTextField.setText(LogIn.getUser().getName());
          //emailTextField.setText(LogIn.getUser().getEmail());
          //phoneNumberTextField.setText(Integer.parseInt(LogIn.getUser().getPhoneNumber()));            adressTextField.setText(LogIn.getUser().getName());
        //DTextField.setText(Integer.parseInt(LogIn.getUser().getID()));
        //  serviceInstance();
      //});

    
    }
    public static void serviceInstance() {
        userService = UserService.getInstance();
    }
    @Override
    public Pane getPane() {
        return ViewProfile();

    }

}
