/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Mikel
 */
public class ViewDate implements PaneViewer {

    private static GridPane pane;
    private static Button cancelButton;
    private static Button viewButton;
    private static Button deleteButton;
    private static Label dateLabel;
    private static Label hourLabel;
    private static Label customerLabel;
    private static Label doctorLabel;
    private static TextField dateTextField;
    private static TextField hourTextField;
    private static TextField customerTextField;
    private static TextField doctorTextField;
    private static CustomerDateService customerDateService;
    private static ComboBox selectCustomerComboBox;
    private static Button viewDateCustomerButton;
    private static Label selectCustomerLabel;
    private static ObservableList<String> selectCustomerObservableList;
    private static UserService userService;

    private Pane viewDate() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        serviceInstance();
        return pane;
    }

    public static void serviceInstance() {
        customerDateService = CustomerDateService.getInstance();
        userService = UserService.getInstance();
    }

    private void setupControls() {
        selectCustomerLabel = PaneUtil.buildLabel(pane, "Select a customer", 0, 3);
        selectCustomerObservableList = FXCollections.observableArrayList();
        selectCustomerComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 3);
        viewDateCustomerButton = PaneUtil.buildButtonImage(new Image("seeIcon.png"), pane, 2, 3);
        dateLabel = PaneUtil.buildLabel(pane, " Date ", 0, 1);
        dateTextField = PaneUtil.buildTextField(pane, 1);
        dateTextField.setDisable(true);
        hourLabel = PaneUtil.buildLabel(pane, " Hour ", 0, 2);
        hourTextField = PaneUtil.buildTextField(pane, 2);
        hourTextField.setDisable(true);
        customerLabel = PaneUtil.buildLabel(pane, " Customer ID ", 0, 3);
        customerTextField = PaneUtil.buildTextField(pane, 3);
        customerTextField.setDisable(true);
        doctorLabel = PaneUtil.buildLabel(pane, " Doctor ID ", 0, 4);
        doctorTextField = PaneUtil.buildTextField(pane, 4);
        doctorTextField.setDisable(true);
        viewButton = PaneUtil.buildButtonImage(new Image("seeIcon.png"), pane, 0, 0);
        cancelButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 1, 0);
        deleteButton = PaneUtil.buildButtonImage(new Image("cleanApp.png"), pane, 2, 0);
    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        viewButton.setOnAction(e -> {
            view();
        });

        viewDateCustomerButton.setOnAction(event -> {
            User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
            if (customerDateService.getByID(user.getID()) == null) {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "This user can't add another date");
                MainManagePane.clearPane();
            } else {
                show();
            }
        });

        deleteButton.setOnAction(e -> {
            delete();
        });
    }

    private void view() {
        serviceInstance();
        int rol = LogIn.getUser().getRol();
        if (rol == 3) {
            if (customerDateService.getByID(LogIn.getUser().getID()) != null) {
                dateTextField.setText(customerDateService.getByID(LogIn.getUser().getID()).getDate());
                hourTextField.setText(customerDateService.getByID(LogIn.getUser().getID()).getHour());
                customerTextField.setText(customerDateService.getByID(LogIn.getUser().getID()).getCustomerID() + " ");
                doctorTextField.setText(customerDateService.getByID(LogIn.getUser().getID()).getAdminID() + " ");
                serviceInstance();
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You must first add a new date");
            }
        } else {
            User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
            if (customerDateService.getByID(user.getID()) != null) {
                dateTextField.setText(customerDateService.getByID(user.getID()).getDate());
                hourTextField.setText(customerDateService.getByID(user.getID()).getHour());
                customerTextField.setText(customerDateService.getByID(user.getID()).getCustomerID() + " ");
                doctorTextField.setText(customerDateService.getByID(user.getID()).getAdminID() + " ");
                serviceInstance();
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "This user must first add a new date");
            }
        }
    }

    private void delete() {
        serviceInstance();
        int rol = LogIn.getUser().getRol();
        if (rol == 3) {
            if (customerDateService.getByID(LogIn.getUser().getID()) != null) {
                customerDateService.remove(customerDateService.getByID(LogIn.getUser().getID()));
                dateTextField.clear();
                hourTextField.clear();
                customerTextField.clear();
                doctorTextField.clear();
                PaneUtil.showAlert(Alert.AlertType.CONFIRMATION, "Date Delete", "The date was deleted correctly");
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You cannot delete a date, you must add a date");
            }
        } else {
            User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
            if (customerDateService.getByID(user.getID()) != null) {
                customerDateService.remove(customerDateService.getByID(user.getID()));
                dateTextField.clear();
                hourTextField.clear();
                customerTextField.clear();
                doctorTextField.clear();
                PaneUtil.showAlert(Alert.AlertType.CONFIRMATION, "Date Delete", "The date was deleted correctly");
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You cannot delete a date, you must add a date");
            }
        }
    }

    private static void unShow() {
        hourTextField.setVisible(false);
        hourLabel.setVisible(false);
        doctorTextField.setVisible(false);
        doctorLabel.setVisible(false);
        dateTextField.setVisible(false);
        dateLabel.setVisible(false);
        customerTextField.setVisible(false);
        customerLabel.setVisible(false);
        viewButton.setVisible(false);
        cancelButton.setVisible(false);
        deleteButton.setVisible(false);
        selectCustomerLabel.setVisible(true);
        selectCustomerComboBox.setVisible(true);
        viewDateCustomerButton.setVisible(true);
    }

    private static void show() {
        hourTextField.setVisible(true);
        hourLabel.setVisible(true);
        doctorTextField.setVisible(true);
        doctorLabel.setVisible(true);
        dateTextField.setVisible(true);
        dateLabel.setVisible(true);
        customerTextField.setVisible(true);
        customerLabel.setVisible(true);
        viewButton.setVisible(true);
        cancelButton.setVisible(true);
        deleteButton.setVisible(true);
        selectCustomerLabel.setVisible(false);
        selectCustomerComboBox.setVisible(false);
        viewDateCustomerButton.setVisible(false);
    }

    public static void refresh() {
        serviceInstance();
        int rol = LogIn.getUser().getRol();
        if (rol == 1 || rol == 2) {
            selectCustomerObservableList.clear();
            selectCustomerObservableList.addAll(userService.getCustomerNames());
            unShow();
        } else {
            if (customerDateService.getDatesByAdminID(LogIn.getUser().getID()) != null) {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You can't add another date");
                MainManagePane.clearPane();
            } else {
                show();
            }
        }
    }

    @Override
    public Pane getPane() {
        return viewDate();
    }
}
