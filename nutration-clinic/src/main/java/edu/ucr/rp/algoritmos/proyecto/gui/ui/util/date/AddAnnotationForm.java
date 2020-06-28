/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAvailabilityService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
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
     private static Label weightKgLabel;
    private static Label fatPorcLabel;
    private static Label heightcmtLabel;
    private static Label dateLabel;
    private static Label eatingPlanLabel;
    private static Label doctorIDLabel;
    private static TextField weightTextField;
    private static TextField fatTextField;
    private static TextField heightTextField;
    private static TextField dateTextField;
    private static ComboBox eatingPlanComboBox;
    private static ComboBox customerIDComboBox;
    private static TextField doctorIDTextField;
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
        weightKgLabel = PaneUtil.buildLabel(pane, "KG", 2, 1);
        weightTextField = PaneUtil.buildTextField(pane, 1);
        fatLabel = PaneUtil.buildLabel(pane, " Insert fat ", 0, 2);
        fatPorcLabel = PaneUtil.buildLabel(pane, "%", 2, 2);
        fatTextField = PaneUtil.buildTextField(pane, 2);
        heightLabel = PaneUtil.buildLabel(pane, " Insert height ", 0, 3);
        heightcmtLabel = PaneUtil.buildLabel(pane, "CM", 2, 3);
        heightTextField = PaneUtil.buildTextField(pane, 3);
        dateLabel = PaneUtil.buildLabel(pane, " Date ", 0, 4);
        dateTextField = PaneUtil.buildTextField(pane, 4);
        dateTextField.setDisable(true);
        eatingPlanLabel = PaneUtil.buildLabel(pane, " Select Eating Plan ", 0, 5);
//      eatingPlanComboBox = PaneUtil.buildComboBox(pane, observableList, 1, 5);
        doctorIDLabel = PaneUtil.buildLabel(pane, " DoctorID ", 0, 6);
        doctorIDTextField = PaneUtil.buildTextField(pane, 6);
        doctorIDTextField.setDisable(true);
        addAnnotationButton = PaneUtil.buildButton("Annotate", pane, 1, 7);
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 2, 7);

    }

    private void addHandlers() {
        LocalDate localDate = LocalDate.now();
        String date = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
        dateTextField.setText(date);
         if(weightTextField.getText().isEmpty()){
              PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "Insert amount of weight");
         }
          if(fatTextField.getText().isEmpty()){
              PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "Insert the amount of fat");
         }
       if(heightTextField.getText().isEmpty()){
              PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "Insert height measure");
         }
        
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        addAnnotationButton.setOnAction(e -> {
            doctorIDTextField.setText(LogIn.getUser().getID() + "");
            AdminAnnotation adminAnnotation = new AdminAnnotation();
            adminAnnotation.setWeight(Integer.parseInt(weightTextField.getText()));
            adminAnnotation.setFat(Integer.parseInt(fatTextField.getText()));
            adminAnnotation.setWeight(Integer.parseInt(heightTextField.getText()));
            User user = userService.getByName(customerIDComboBox.getSelectionModel().getSelectedItem().toString());
            adminAnnotation.setCustomerID(user.getID());
            adminAnnotation.setDocID(LogIn.getUser().getID());
            adminAnnotation.setDate(date);
        });
    }

    @Override
    public Pane getPane() {
        return addAnnotationForm();
    }

}
