/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAvailabilityService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 *
 * @author Alvaro Miranda Cort
 */
public class AddAnnotationForm implements PaneViewer {

    private static GridPane pane;
    private static DateService dateService;
    private static UserService userService;
    private static Label customerIDLabel;
    private static Label weightLabel;
    private static Label fatLabel;
    private static Label heightLabel;
    private static Label dateLabel;
    private static Label eatingPlanLabel;
    private static Label doctorIDLabel;
    private static TextField weightTextField;
    private static TextField fatTextField;
    private static TextField heightTextField;
    private static TextField dateTextField;
    private static ComboBox eatingPlanComboBox;
    private static ComboBox customerIDComboBox;
    private static ComboBox doctorIDComboBox;
    private static Button addAnnotationButton;
    private static Button cancelButton;
    private ObservableList<String> selectCustomerObservableList;
    private ObservableList<String> selectDoctorObservableList;

    public GridPane addAnnotationForm() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        return pane;
    }

    public static void serviceInstance() {
        dateService = DateService.getInstance();
        userService = UserService.getInstance();
    }

    private void setupControls() {
        
        customerIDLabel = PaneUtil.buildLabel(pane, " Select Customer ", 0, 0);
        selectCustomerObservableList = FXCollections.observableArrayList(userService.getCustomerNames());
        customerIDComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 0);
        weightLabel = PaneUtil.buildLabel(pane, " Insert weight ", 0, 1);
        weightTextField = PaneUtil.buildTextField(pane, 1);
        fatLabel = PaneUtil.buildLabel(pane, " Insert fat ", 0, 2);
        fatTextField = PaneUtil.buildTextField(pane, 2);
        heightLabel = PaneUtil.buildLabel(pane, " Insert height ", 0, 3);
        heightTextField = PaneUtil.buildTextField(pane, 3);
        dateLabel = PaneUtil.buildLabel(pane, " Date ", 0, 4);
        dateTextField = PaneUtil.buildTextField(pane, 4);
        eatingPlanLabel = PaneUtil.buildLabel(pane, " Select Eating Plan ", 0, 5);
//      eatingPlanComboBox = PaneUtil.buildComboBox(pane, observableList, 1, 5);
        doctorIDLabel = PaneUtil.buildLabel(pane, " Select Doctor ", 0, 6);
        selectDoctorObservableList = FXCollections.observableArrayList();
        doctorIDComboBox = PaneUtil.buildComboBox(pane, selectDoctorObservableList, 1, 6);
        addAnnotationButton = PaneUtil.buildButton("Annotate", pane, 1, 7);
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 2, 7);

    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        addAnnotationButton.setOnAction(e -> {
            AdminAnnotation adminAnnotation = new AdminAnnotation();
            adminAnnotation.setWeight(Integer.parseInt(weightTextField.getText()));
            adminAnnotation.setFat(Integer.parseInt(fatTextField.getText()));
            adminAnnotation.setWeight(Integer.parseInt(heightTextField.getText()));
            
//            int customerID = customerIDComboBox.getSelectionModel().getSelectedItem().toString();
//            adminAnnotation.setCustomerID(customerIDComboBox.getSelectionModel().getSelectedItem().toString());
        });
    }

    @Override
    public Pane getPane() {
        return addAnnotationForm();
    }
}
