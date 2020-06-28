/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.ModifyDate.serviceInstance;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddUserForm.serviceInstance;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 *
 * @author Noel
 */
public class ChangePassword implements PaneViewer {

    private static GridPane pane;
    private static Button cancelButton;
    private static Button modifyButton;
    private static TextField currentPasswordTextField;
    private static TextField newPassword1TextField;
    private static TextField newPassword2TextField;
    private static Label currentPasswordLabel;
    private static Label newPassword1Label;
    private static Label newPassword2Label;
    private static UserService userService;
    public static User userFinal;

    public GridPane changePassword() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        serviceInstance();

        //visible();
        return pane;
    }

    private void setupControls() {
        currentPasswordLabel = PaneUtil.buildLabel(pane, "Current password", 0, 2);
        currentPasswordTextField = PaneUtil.buildTextField(pane, 2);
        newPassword1Label = PaneUtil.buildLabel(pane, "New password", 0, 3);
        newPassword1TextField = PaneUtil.buildTextField(pane, 3);
        newPassword2Label = PaneUtil.buildLabel(pane, "Confirm password", 0, 4);
        newPassword2TextField = PaneUtil.buildTextField(pane, 4);
        modifyButton = PaneUtil.buildButton("Confirm", pane, 1, 5);
        cancelButton = PaneUtil.buildButton("Back", pane, 2, 5);
    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
        modifyButton.setOnAction(e -> {
            Utility utility = new Utility();
            String ne = utility.encrypt(newPassword1TextField.getText());

            if (LogIn.getUser().getPassword() == null ? ne != null : !LogIn.getUser().getPassword().equals(ne)) {

                Utility utilityy = new Utility();
                User user = new User();
                user.setName(LogIn.getUser().getName());
                user.setAddress(LogIn.getUser().getAddress());
                user.setRol(LogIn.getUser().getRol());

                user.setEmail(LogIn.getUser().getEmail());
                user.setPassword(ne);
                user.setPhoneNumber(LogIn.getUser().getPhoneNumber());
                user.setID(LogIn.getUser().getID());
                userFinal = user;
                if(userService.edit(LogIn.getUser(), userFinal)){
                PaneUtil.showAlert(Alert.AlertType.ERROR, "La cambio", "la cambio a: " + LogIn.getUser().getPassword());
                }
            
        } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "es igual");
            }
        serviceInstance();
//            if (currentPasswordTextField.getText() == LogIn.getUser().getPassword()) {
//
//                if (validateAdd()) {
//                    LogIn.getUser().setPassword(newPassword2TextField.getText());
////             
//                    JOptionPane.showMessageDialog(null, "si entro al if");
//                } else {
//                      JOptionPane.showMessageDialog(null, "no entro al if");
//                }
//            }
//
//        });

    }


);

//    private boolean validateAdd() {
//        if (currentPasswordTextField.getText().isEmpty()) {
//            currentPasswordTextField.setPromptText("Obligatory field");
//            currentPasswordTextField.setStyle("-fx-background-color: #FDC7C7");
//            return true;
//        }
//        return false;
//
//    }
    }

    public static void serviceInstance() {
        userService = UserService.getInstance();

    }

    @Override
        public Pane getPane() {
        return changePassword();
    }
}
