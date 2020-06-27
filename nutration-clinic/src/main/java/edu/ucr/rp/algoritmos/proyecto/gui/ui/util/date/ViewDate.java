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
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Alvaro Miranda Cort
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
    private static DateService dateService;
    

    private Pane viewDate() {
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
        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 1, 0);
        deleteButton = PaneUtil.buildButtonImage(new Image("remove.png"), pane, 2, 0);
    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        viewButton.setOnAction(e -> {
            if(dateService.getByID(LogIn.getUser().getID()) != null){
            dateTextField.setText(dateService.getByID(LogIn.getUser().getID()).getDate());
            hourTextField.setText(dateService.getByID(LogIn.getUser().getID()).getHour());
            customerTextField.setText(dateService.getByID(LogIn.getUser().getID()).getCustomerID() + " ");
            doctorTextField.setText(dateService.getByID(LogIn.getUser().getID()).getAdminID() + " ");
            serviceInstance();
            }else
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You cannot view a date, you must add a date");
        });

        deleteButton.setOnAction(e -> {
             if(dateService.getByID(LogIn.getUser().getID()) != null){
            dateService.remove(dateService.getByID(LogIn.getUser().getID()));            
             dateTextField.clear();
             hourTextField.clear();
             customerTextField.clear();
             doctorTextField.clear();
             PaneUtil.showAlert(Alert.AlertType.CONFIRMATION, "Date Delete", "The date was deleted correctly");
             }else
                  PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You cannot delete a date, you must add a date");
        });
    }

    @Override
    public Pane getPane() {
        return viewDate();
    }
}
