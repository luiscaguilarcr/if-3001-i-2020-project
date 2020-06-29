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
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAvailabilityGeneralService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;

import java.time.LocalDate;
import java.util.*;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.collections.*;
import javafx.scene.control.Alert;

/**
 * @author Mikel y Luis Carlos
 */
public class AddDatesForm implements PaneViewer {
    private static AdminAvailabilityGeneralService adminAvailabilityService;
    private static CustomerDateService customerDateService;
    private static UserService userService;
    public static CustomerDate customerDateOLD;
    private static GridPane pane;
    private static Label addDateTitleLabel;
    private static Label dateFieldLabel;
    private static Label selectHoursLabel;
    private static Label selectDoctorLabel;
    private static Label selectCustomerLabel;
    private static Button cancelButton;
    private static Button addDateButton;
    private static Button selectCustomerButton;
    private static ComboBox hoursComboBox;
    private static ComboBox doctorsComboBox;
    private static ComboBox selectCustomerComboBox;
    private static DatePicker checkInDatePicker;
    private ObservableList<String> selectHourObservableList;
    private ObservableList<String> selectDoctorObservableList;
    private ObservableList<String> selectCustomerObservableList;

    public GridPane addDatesForm() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        return pane;
    }

    public static void serviceInstance() {
        customerDateService = CustomerDateService.getInstance();
        userService = UserService.getInstance();
        adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
    }

    private void setupControls() {
        selectCustomerLabel = PaneUtil.buildLabel(pane, "Select a customer", 0, 3);
        selectCustomerObservableList = FXCollections.observableArrayList(userService.getCustomerNames());
        selectCustomerComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 3);
        selectCustomerButton = PaneUtil.buildButtonImage(new Image("add.png"), pane, 2, 3);
        selectHourObservableList = FXCollections.observableArrayList();
        selectDoctorObservableList = FXCollections.observableArrayList();
        addDateTitleLabel = PaneUtil.buildLabel(pane, "Book appointment", 0, 0);
        dateFieldLabel = PaneUtil.buildLabel(pane, "Date Field", 0, 1);
        checkInDatePicker = PaneUtil.buildDatePicker(pane, 1, 1);
        checkInDatePicker.setValue(LocalDate.now());//establece la fecha actual
        checkInDatePicker.setShowWeekNumbers(true);//habilita numeros de la semana
        selectDoctorLabel = PaneUtil.buildLabel(pane, " Select doctor ", 0, 2);
        doctorsComboBox = PaneUtil.buildComboBox(pane, selectDoctorObservableList, 1, 2);
        selectHoursLabel = PaneUtil.buildLabel(pane, " Select time ", 0, 3);
        hoursComboBox = PaneUtil.buildComboBox(pane, selectHourObservableList, 1, 3);
        addDateButton = PaneUtil.buildButton("Add Date", pane, 1, 5);
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 2, 5);
        addDateButton.setVisible(false);
        doctorsComboBox.setDisable(true);
        hoursComboBox.setDisable(true);
    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        addDateButton.setOnAction(e -> {
            addDate();
            serviceInstance();
        });
        checkInDatePicker.setOnAction(event -> {
            doctorsComboBox.setDisable(false);
            refreshItems();
            String date = checkInDatePicker.getEditor().getText();
            selectDoctorObservableList.addAll(adminAvailabilityService.getNamesListByDate(date));
        });

        doctorsComboBox.setOnAction(event -> {
            hoursComboBox.setDisable(false);
            selectHourObservableList.clear();
            User user = userService.getByName(doctorsComboBox.getSelectionModel().getSelectedItem().toString());
            Map<String, List> map = adminAvailabilityService.getByID2(user.getID());
            String date = checkInDatePicker.getEditor().getText();
            List<String> hours = map.get(date);
            selectHourObservableList.clear();
            selectHourObservableList.addAll(hours);
        });

        hoursComboBox.setOnAction(event -> {
            addDateButton.setVisible(true);
        });

        selectCustomerButton.setOnAction(event -> {
            User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
            if (customerDateService.getByID(user.getID()) != null) {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "This user can't add another date");
                MainManagePane.clearPane();
            } else {
                show();
            }
        });

    }

    private void addDate() {
        boolean verify = true;
        if (doctorsComboBox.getSelectionModel().getSelectedItem() == null) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You must to select a doctor to add a new date");
            verify = false;
        }
        if (hoursComboBox.getSelectionModel().getSelectedItem() == null) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You must to select a valid hour to add a new date");
            verify = false;
        }

        if (verify) {
            CustomerDate customerDate = new CustomerDate();
            User admin = userService.getByName(doctorsComboBox.getSelectionModel().getSelectedItem().toString());
            customerDate.setAdminID(admin.getID());
            if (LogIn.getUser().getRol() == 3) {
                customerDate.setCustomerID(LogIn.getUser().getID());
            } else {
                User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
                customerDate.setCustomerID(user.getID());
            }
            customerDate.setDate(checkInDatePicker.getEditor().getText());
            customerDate.setHour(hoursComboBox.getSelectionModel().getSelectedItem().toString());
            customerDateOLD = customerDate;
            customerDate.setHour(hoursComboBox.getSelectionModel().getSelectedItem().toString());

            if (customerDateService.add(customerDate)) {
                MainManagePane.clearPane();
                refreshItems();
                PaneUtil.showAlert(Alert.AlertType.INFORMATION, "Date added", "The date was added correctly");
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error when adding the date", "The date was not added");
            }
        }
    }

    /**
     * Valida que el usuario no tenga agregada una cita y el rol
     */
    public static void refresh() {
        serviceInstance();
        int rol = LogIn.getUser().getRol();
        if (rol == 1 || rol == 2) {
            unShow();
        } else {
            if (customerDateService.getByID(LogIn.getUser().getID()) != null) {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You can't add another date");
                MainManagePane.clearPane();
            } else {
                show();
            }
        }
    }

    private void refreshItems(){
        selectDoctorObservableList.clear();
        selectHourObservableList.clear();
    }

    private static void unShow() {
        addDateTitleLabel.setVisible(false);
        dateFieldLabel.setVisible(false);
        checkInDatePicker.setVisible(false);
        selectDoctorLabel.setVisible(false);
        doctorsComboBox.setVisible(false);
        hoursComboBox.setVisible(false);
        addDateButton.setVisible(false);
        selectHoursLabel.setVisible(false);
    }

    private static void show() {
        addDateTitleLabel.setVisible(true);
        dateFieldLabel.setVisible(true);
        checkInDatePicker.setVisible(true);
        selectDoctorLabel.setVisible(true);
        doctorsComboBox.setVisible(true);
        hoursComboBox.setVisible(true);
        addDateButton.setVisible(true);
        selectHoursLabel.setVisible(true);
        selectCustomerLabel.setVisible(false);
        selectCustomerComboBox.setVisible(false);
        selectCustomerButton.setVisible(false);
    }


    @Override
    public Pane getPane() {
        return addDatesForm();
    }
}
