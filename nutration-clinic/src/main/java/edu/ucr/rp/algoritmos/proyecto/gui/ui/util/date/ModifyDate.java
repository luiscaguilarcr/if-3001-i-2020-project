/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.AddDatesForm;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
 *
 * @author Alvaro Miranda Cort
 */
public class ModifyDate implements PaneViewer {

    private static GridPane pane;
    private static Label modifyDateTitleLabel;
    private static Label DateFieldLabel;
    private static Button cancelButton;
    private static Button modifyDateButton;
    private DatePicker checkInDatePicker;
    private ComboBox hoursComboBox;
    private ComboBox doctorsComboBox;
    private static Label SelecthoursLabel;
    private static Label SelectdoctorLabel;
    private static DateService dateService;
    private ObservableList<Integer> observableDoctor;
    public static CustomerDate customerFinal;
    
    public GridPane modifyDate() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        serviceInstance();
        return pane;
    }

    public static void serviceInstance() {
        dateService = DateService.getInstance();

    }

    private void setupControls() {
        List<String> list = new ArrayList<String>();
        list.add("8:00am");
        list.add("9:00am");
        list.add("10:00am");
        list.add("11:00am");
        list.add("12:00pm");
        list.add("1:00pm");
        list.add("2:00pm");
        list.add("3:00pm");
        list.add("4:00pm");
        list.add("5:00pm");
        ObservableList<String> observableList = FXCollections.observableList(list);
        //************************************************************************************++
        List<Integer> listdoctor = new ArrayList<Integer>();
//        if(LogIn.getUser().getRol() == 2){
        listdoctor.add(222);
//        }     
        observableDoctor = FXCollections.observableArrayList(listdoctor);
           //************************************************************************************++
//        CustomerDateStack customerDateStack = dateService.getDatesByAdminID(LogIn.getUser().getID());
//        List namesList = dateService.getNamesOfCustomersByDates(customerDateStack);
//        observableDoctor = FXCollections.observableArrayList(namesList);

        
        modifyDateTitleLabel = PaneUtil.buildLabel(pane, "Modify appointment", 0, 0);
        DateFieldLabel = PaneUtil.buildLabel(pane, "Date Field", 0, 1);
        checkInDatePicker = PaneUtil.buildDatePicker(pane, 1, 1);
        checkInDatePicker.setValue(LocalDate.now());//establece la fecha actual
        checkInDatePicker.setShowWeekNumbers(true);//habilita numeros de la semana
        SelecthoursLabel = PaneUtil.buildLabel(pane, " Select time ", 0, 2);
        hoursComboBox = PaneUtil.buildComboBox(pane, observableList, 1, 2);
        SelectdoctorLabel = PaneUtil.buildLabel(pane, " Select doctor ", 0, 3);
        doctorsComboBox = PaneUtil.buildComboBox(pane, observableDoctor, 1, 3);
        modifyDateButton = PaneUtil.buildButton("Modify Date", pane, 1, 5);
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 2, 5);
    }

    private void addHandlers() {

        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        modifyDateButton.setOnAction(e -> {
            modify();
            serviceInstance();

        });

    }

    private void modify() {
        boolean verify = true;
        CustomerDateStack cu = new CustomerDateStack();
        if (doctorsComboBox == null) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You must to select a doctor to add a new date");
            verify = false;
        }
        if (hoursComboBox == null) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You must to select a valid hour to add a new date");
            verify = false;
        }

        if (verify) {
            CustomerDate customerNew = new CustomerDate();
            customerNew.setCustomerID(LogIn.getUser().getID());
            customerNew.setAdminID(222);
            customerNew.setDate(checkInDatePicker.getEditor().getText());
            customerNew.setHour(hoursComboBox.getSelectionModel().getSelectedItem().toString());
               customerFinal = customerNew;
            if (dateService.edit(dateService.getByID(LogIn.getUser().getID()), customerNew)) {              
                PaneUtil.showAlert(Alert.AlertType.CONFIRMATION, "Date modified", "The date was modified correctly");
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error when modified the date", "The date was not modified");
            }
        }
    }

    public static void refresh() {
        serviceInstance();
        if (dateService.getByID(LogIn.getUser().getID()) == null) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You cannot modify a date, you must add a date");
            MainManagePane.clearPane();
        }
    }

    @Override
    public Pane getPane() {
        return modifyDate();
    }
}
