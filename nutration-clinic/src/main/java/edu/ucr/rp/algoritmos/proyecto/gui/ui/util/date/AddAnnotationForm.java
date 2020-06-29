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
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAnnotationService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.EatingPlanService;
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
 * @author Mikel
 */
public class AddAnnotationForm implements PaneViewer {

    private static GridPane pane;
    private static CustomerDateService dateService;
    private static AdminAnnotationService adminAnnotationService;
    private static UserService userService;
    private static Label customerIDLabel;
    private static Label weightLabel;
    private static Label fatLabel;
    private static Label heightLabel;
    private static Label weightKgLabel;
    private static Label fatPorcLabel;
    private static Label heightcmtLabel;
    private static Label dateLabel;
    private static Label doctorIDLabel;
    private static TextField weightTextField;
    private static TextField fatTextField;
    private static TextField heightTextField;
    private static TextField dateTextField;
    private ComboBox customerIDComboBox;
    private static TextField doctorIDTextField;
    private static Button addAnnotationButton;
    private static Button cancelButton;
    private static Button refreshButton;
    private ObservableList<String> selectCustomerObservableList;

    public GridPane addAnnotationForm() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        return pane;
    }

    public static void serviceInstance() {
        dateService = CustomerDateService.getInstance();
        userService = UserService.getInstance();
        adminAnnotationService = AdminAnnotationService.getInstance();
    }

    private void setupControls() {
        customerIDLabel = PaneUtil.buildLabel(pane, " Select Customer ", 0, 0);
        selectCustomerObservableList = FXCollections.observableArrayList();
        customerIDComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 0);
        refreshButton = PaneUtil.buildButtonImage(new Image("refresh.png"), pane, 2, 0);
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
        doctorIDLabel = PaneUtil.buildLabel(pane, " DoctorID ", 0, 5);
        doctorIDTextField = PaneUtil.buildTextField(pane, 5);
        doctorIDTextField.setDisable(true);
        addAnnotationButton = PaneUtil.buildButton("Annotate", pane, 1, 6);
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 2, 6);
    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> {
            MainManagePane.clearPane();
            refreshItems();
        });
        addAnnotationButton.setOnAction(e -> {
            addAnnotation();
        });
        refreshButton.setOnAction(event -> {
            selectCustomerObservableList.clear();
            selectCustomerObservableList = FXCollections.observableArrayList(dateService.getDatesByAdminID(LogIn.getUser().getID()));
        });
    }

    public void addAnnotation() {

        boolean validate = true;
        if (weightTextField.getText().isEmpty()) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "Insert amount of weight");
            validate = false;
        }
        if (fatTextField.getText().isEmpty()) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "Insert the amount of fat");
            validate = false;
        }
        if (heightTextField.getText().isEmpty()) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "Insert height measure");
            validate = false;
        }
        if (validate) {
            AdminAnnotation adminAnnotation = new AdminAnnotation();
            LocalDate localDate = LocalDate.now();
            String date = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
            adminAnnotation.setDate(date);
            dateTextField.setText(date);
            doctorIDTextField.setText(LogIn.getUser().getID() + "");
            adminAnnotation.setWeight(Integer.parseInt(weightTextField.getText()));
            int fat = Integer.parseInt(fatTextField.getText());
            adminAnnotation.setFat(fat);
            adminAnnotation.setWeight(Integer.parseInt(heightTextField.getText()));
            User user = userService.getByName(customerIDComboBox.getSelectionModel().getSelectedItem().toString());
            adminAnnotation.setCustomerID(user.getID());
            adminAnnotation.setDocID(LogIn.getUser().getID());
            EatingPlanService eatingPlanService = EatingPlanService.getInstance();
            adminAnnotation.setEatingPlan(eatingPlanService.getPlanByFat(fat));

            if (adminAnnotationService.add(adminAnnotation)) {
                MainManagePane.clearPane();
                refreshItems();
                PaneUtil.showAlert(Alert.AlertType.INFORMATION, "Annotation added", "The annotation was correctly added");
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "The annotation was not added");
            }
        }
    }

    private void refreshItems() {
        customerIDComboBox.getSelectionModel().clearSelection();
        dateTextField.clear();
        doctorIDTextField.clear();
        weightTextField.clear();
        heightTextField.clear();
        fatTextField.clear();
    }

    @Override
    public Pane getPane() {
        return addAnnotationForm();
    }

}
