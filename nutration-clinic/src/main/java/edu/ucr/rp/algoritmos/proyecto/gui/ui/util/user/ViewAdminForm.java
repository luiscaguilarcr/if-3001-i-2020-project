/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddAdminForm.serviceInstance;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class ViewAdminForm implements PaneViewer {

    private static UserService userService;
    private static Button deleteButton;
    private static Button exitButton;
    private static Button selectCustomerButton;
    private static Label nameLabel;
    private static Label emailLabel;
    private static Label passwordLabel;
    private static Label addressLabel;
    private static Label phoneNumberLabel;
    private static Label selectCustomerLabel;
    private static Label selectAdminLabel;
    private static Label rolLabel;
    private static TextField nameTextField;
    private static TextField passwordTextField;
    private static TextField emailTextField;
    private static TextField addressTextField;
    private static TextField phoneNumberTextField;
    private static ComboBox selectCustomerComboBox;
    private static ComboBox rolComboBox;
    private static int rol;
    private static ObservableList<String> selectCustomerObservableList;
    private ObservableList<Integer> rolObservableList;
    private static GridPane pane;
    private Label titleLabel;

    public GridPane ViewCustomer() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        unShow();

        return pane;
    }

    public static void serviceInstance() {

        userService = UserService.getInstance();
    }

    private void setupControls() {
        titleLabel = PaneUtil.buildLabel(pane, "Select a Admin", 1, 2);
        selectCustomerObservableList = FXCollections.observableArrayList(userService.getAdminNames());
        selectCustomerComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 3);

        nameLabel = PaneUtil.buildLabel(pane, "Name", 1, 4);
        nameTextField = PaneUtil.buildTextInput(pane, 1, 5);
        emailLabel = PaneUtil.buildLabel(pane, "Email", 1, 8);
        emailTextField = PaneUtil.buildTextInput(pane, 1, 9);
        addressLabel = PaneUtil.buildLabel(pane, "Address", 1, 10);
        addressTextField = PaneUtil.buildTextInput(pane, 1, 11);
        phoneNumberLabel = PaneUtil.buildLabel(pane, "Phone Number", 1, 12);
        phoneNumberTextField = PaneUtil.buildTextInput(pane, 1, 13);
        rolObservableList = FXCollections.observableArrayList(1, 14);
        rolLabel = PaneUtil.buildLabel(pane, "Rol", 1, 14);
        rolComboBox = PaneUtil.buildComboBox(pane, rolObservableList, 1, 15);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 5);
        deleteButton = PaneUtil.buildButtonImage(new Image("cleanApp.png"), pane, 2, 4);
        selectCustomerButton = PaneUtil.buildButtonImage(new Image("seeIcon.png"), pane, 2, 3);
    }

    private void addHandlers() {
        exitButton.setOnAction(e -> {
            MainManagePane.clearPane();
        });

        deleteButton.setOnAction(e -> {

            deleteCustomer();
        });
        selectCustomerButton.setOnAction(event -> {
            refreshFillItems();
            show();
        });
    }

    private void deleteCustomer() {

        Utility utility = new Utility();
        User previousUser = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());

        if (userService.remove(previousUser)) {
            PaneUtil.showAlert(Alert.AlertType.INFORMATION, "User removed", "The user was removed correctly");
            MainManagePane.clearPane();

            unShow();
            //refreshItems();
        } else {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The user was not removed");

        }
    }

    private void refreshFillItems() {
        User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
        nameTextField.setText(user.getName());
        nameTextField.setDisable(true);
        addressTextField.setText(user.getAddress());
        addressTextField.setDisable(true);
        rolComboBox.getSelectionModel().select(Integer.toString(user.getRol()));
        rolComboBox.setDisable(true);
        phoneNumberTextField.setText(Integer.toString(user.getPhoneNumber()));
        phoneNumberTextField.setDisable(true);
        emailTextField.setText(user.getEmail());
        emailTextField.setDisable(true);
    }

    @Override
    public Pane getPane() {
        return ViewCustomer();
    }

    private void refreshItems() {

        nameTextField.clear();
        passwordTextField.clear();
        emailTextField.clear();
        addressTextField.clear();
        phoneNumberTextField.clear();
    }

    public static void unShow() {
        nameLabel.setVisible(false);
        nameTextField.setVisible(false);
        //passwordLabel.setVisible(false);
        //passwordTextField.setVisible(false);
        emailLabel.setVisible(false);
        emailTextField.setVisible(false);
        addressLabel.setVisible(false);
        addressTextField.setVisible(false);
        phoneNumberLabel.setVisible(false);
        phoneNumberTextField.setVisible(false);
        rolComboBox.setVisible(false);
        //  exitButton.setVisible(false);
        //deleteButton.setVisible(false);
        rolLabel.setVisible(false);

        selectCustomerComboBox.setVisible(true);
        selectCustomerButton.setVisible(true);
    }

    private void show() {
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        //passwordLabel.setVisible(true);
        //passwordTextField.setVisible(true);
        emailLabel.setVisible(true);
        emailTextField.setVisible(true);
        addressLabel.setVisible(true);
        addressTextField.setVisible(true);
        phoneNumberLabel.setVisible(true);
        phoneNumberTextField.setVisible(true);
        rolComboBox.setVisible(true);
        //exitButton.setVisible(true);
        //deleteButton.setVisible(true);
        rolLabel.setVisible(true);

        //selectAdminLabel.setVisible(false);
        selectCustomerLabel.setVisible(false);
        selectCustomerComboBox.setVisible(false);
        selectCustomerButton.setVisible(false);
    }
}
