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
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAvailabilityService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Mikel
 */
public class ModifyDate implements PaneViewer {

    private static AdminAvailabilityService adminAvailabilityService;
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
    private static Button modifyDateButton;
    private static Button selectCustomerButton;
    private static ComboBox hoursComboBox;
    private static ComboBox doctorsComboBox;
    private static ComboBox selectCustomerComboBox;
    private static DatePicker checkInDatePicker;
    private static ObservableList<String> selectHourObservableList;
    private static ObservableList<String> selectDoctorObservableList;
    private static ObservableList<String> selectCustomerObservableList;

    public GridPane modifyDate() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        return pane;
    }

    public static void serviceInstance() {
        customerDateService = CustomerDateService.getInstance();
        userService = UserService.getInstance();
    }

    private void setupControls() {
        selectCustomerLabel = PaneUtil.buildLabel(pane, "Select a customer", 0, 3);
        selectCustomerObservableList = FXCollections.observableArrayList(userService.getCustomerNames());
        selectCustomerComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 3);
        selectCustomerButton = PaneUtil.buildButtonImage(new Image("add.png"), pane, 2, 3);
        addDateTitleLabel = PaneUtil.buildLabel(pane, "Book appointment", 0, 0);
        dateFieldLabel = PaneUtil.buildLabel(pane, "Date Field", 0, 1);
        checkInDatePicker = PaneUtil.buildDatePicker(pane, 1, 1);
        checkInDatePicker.setShowWeekNumbers(true);//habilita numeros de la semana
        selectDoctorLabel = PaneUtil.buildLabel(pane, " Select doctor ", 0, 2);
        selectDoctorObservableList = FXCollections.observableArrayList();
        doctorsComboBox = PaneUtil.buildComboBox(pane, selectDoctorObservableList, 1, 2);
        selectHoursLabel = PaneUtil.buildLabel(pane, " Select time ", 0, 3);
        selectHourObservableList = FXCollections.observableArrayList();
        hoursComboBox = PaneUtil.buildComboBox(pane, selectHourObservableList, 1, 3);
        modifyDateButton = PaneUtil.buildButton("Modify Date", pane, 1, 5);
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 2, 5);
        modifyDateButton.setVisible(false);
        doctorsComboBox.setDisable(true);
        hoursComboBox.setDisable(true);
    }

    private static void setupControlsInfo() {
        User user = LogIn.getUser();
        int rol = user.getRol();
        if (rol == 1 || rol == 2) {
            user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
            refreshFill(user);
        } else {
            refreshFill(user);
        }

    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());

        modifyDateButton.setOnAction(e -> {
            modify();
            MainManagePane.clearPane();
            refreshItems();
        });

        checkInDatePicker.setOnAction(event -> {
            doctorsComboBox.setDisable(false);
            selectDoctorObservableList.clear();
            selectHourObservableList.clear();
            String date = checkInDatePicker.getEditor().getText();
            selectDoctorObservableList.addAll(adminAvailabilityService.getNamesListByDate(date));
        });

        doctorsComboBox.setOnAction(event -> {
            hoursComboBox.setDisable(false);
            selectHourObservableList.clear();
            User user = userService.getByName(doctorsComboBox.getSelectionModel().getSelectedItem().toString());
            Map<String, List> map = adminAvailabilityService.getByIDMapAvailability(user.getID());
            String date = checkInDatePicker.getEditor().getText();
            List<String> hours = map.get(date);
            selectHourObservableList.clear();
            selectHourObservableList.addAll(hours);
        });

        hoursComboBox.setOnAction(event -> {
            modifyDateButton.setVisible(true);
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

    private static void refreshFill(User user) {
        CustomerDate customerDate = customerDateService.getByID(user.getID());
        Date date = getDate(customerDate);
        LocalDate localDate = convertToLocalDateViaInstant(date);
        checkInDatePicker.setValue(localDate);
        AdminAvailabilityService adminAvailabilityService = AdminAvailabilityService.getInstance();

        List<String> nameList = adminAvailabilityService.getNamesListByDate(customerDate.getDate());
        selectDoctorObservableList.clear();
        selectDoctorObservableList.addAll(nameList);
        User admin = userService.getByID(customerDate.getAdminID());
        doctorsComboBox.getSelectionModel().select(admin.getName());

        Map<String, List> map = adminAvailabilityService.getByIDMapAvailability(admin.getID());
        List<String> hours = map.get(customerDate.getDate());
        selectHourObservableList.clear();
        selectDoctorObservableList.addAll(hours);
        hoursComboBox.getSelectionModel().select(customerDate.getHour());
    }

    public void refreshItems(){

    }

    public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private static Date getDate(CustomerDate customerDate) {
        LocalDate localDate = LocalDate.now();
        String sDate = customerDate.getDate();
        String sDate1;
        for (int i = 0; i < 8; i++) {
            int dayOfMonth = localDate.getDayOfMonth();
            if (dayOfMonth + i + 1 <= 30) {
                sDate1 = (dayOfMonth + i + 1) + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
            } else {
                sDate1 = (dayOfMonth + i - 29) + "/" + (localDate.getMonthValue() + 1) + "/" + localDate.getYear();
            }
            if(sDate.equals(sDate1)) {
                try {
                    return new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private void modify() {
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
            CustomerDate customerNew = new CustomerDate();
            customerNew.setCustomerID(LogIn.getUser().getID());
            customerNew.setAdminID(222);
            customerNew.setDate(checkInDatePicker.getEditor().getText());
            customerNew.setHour(hoursComboBox.getSelectionModel().getSelectedItem().toString());

            if (customerDateService.edit(customerDateService.getByID(LogIn.getUser().getID()), customerNew)) {
                PaneUtil.showAlert(Alert.AlertType.CONFIRMATION, "Date modified", "The date was modified correctly");
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error when modified the date", "The date was not modified");
            }
        }
    }

    private static void unShow() {
        addDateTitleLabel.setVisible(false);
        dateFieldLabel.setVisible(false);
        checkInDatePicker.setVisible(false);
        selectDoctorLabel.setVisible(false);
        doctorsComboBox.setVisible(false);
        hoursComboBox.setVisible(false);
        modifyDateButton.setVisible(false);
        selectHoursLabel.setVisible(false);
    }

    private static void show() {
        addDateTitleLabel.setVisible(true);
        dateFieldLabel.setVisible(true);
        checkInDatePicker.setVisible(true);
        selectDoctorLabel.setVisible(true);
        doctorsComboBox.setVisible(true);
        hoursComboBox.setVisible(true);
        modifyDateButton.setVisible(true);
        selectHoursLabel.setVisible(true);
        selectCustomerLabel.setVisible(false);
        selectCustomerComboBox.setVisible(false);
        selectCustomerButton.setVisible(false);
    }

    /**
     * Valida que el usuario no tenga agregada una cita
     */
    public static void refresh() {
        serviceInstance();
        int rol = LogIn.getUser().getRol();
        if (rol == 1 || rol == 2) {
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
        return modifyDate();
    }
}
