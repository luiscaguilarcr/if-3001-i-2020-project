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
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class ViewAdminForm implements PaneViewer{
    private Label titleLabel;
 private static ComboBox selectCustomerComboBox;
    private static UserService userService;
    private static GridPane pane;
    private static TableView tableView;
    private static Integer rol;
    private ObservableList<Integer> rolObservableList;
    private static ObservableList<String> selectCustomerObservableList;
    private static Button exitButton;
private static Button deleteButton;
    public GridPane ViewCustomer() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();

        //visible();
        return pane;
    }

    public static void serviceInstance() {

        userService = UserService.getInstance();
    }

    private void setupControls() {
        titleLabel=PaneUtil.buildLabel(pane, "Select a Admin", 1, 0);
        selectCustomerObservableList = FXCollections.observableArrayList(userService.getAdminNames());
        selectCustomerComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 3);
          exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 4);
        deleteButton = PaneUtil.buildButtonImage(new Image("cleanApp.png"), pane, 2, 3);
    }

    private static void editAdminAndCustomer() {
        if (rol == 2) {
            selectCustomerObservableList.clear();
            selectCustomerObservableList.addAll(userService.getUserNames());

        } else if (rol == 3) {
            selectCustomerObservableList.clear();
            selectCustomerObservableList.addAll(userService.getCustomerNames());
            ;
        }
    }

    public static void setRol(Integer rol1) {
        rol = rol1;
        editAdminAndCustomer();
    }
    
    
    private void addHandlers() {
        exitButton.setOnAction(e -> {
            //refreshItems();
            MainManagePane.clearPane();
        });
                
                
                deleteButton.setOnAction(e -> {
            //refreshItems();
            deleteAdmin();
        });;}
        private void deleteAdmin() {

        Utility utility = new Utility();
        User previousUser = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());

        if (userService.remove(previousUser)) {
            PaneUtil.showAlert(Alert.AlertType.INFORMATION, "User removed", "The user was removed correctly");
            MainManagePane.clearPane();
            //unShow();
            //refreshItems();
        } else {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The user was not edited");

        }
    }
    @Override
    public Pane getPane() {

        return ViewCustomer();
    }
}
