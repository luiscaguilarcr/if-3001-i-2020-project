package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * @author Luis Carlos
 */
public class ModifyUserForm implements PaneViewer {
    private static UserService userService;
    private static Button modifyButton;
    private static Button exitButton;
    private static Button selectCustomerButton;
    private static Label nameLabel;
    private static Label emailLabel;
    private static Label passwordLabel;
    private static Label addressLabel;
    private static Label phoneNumberLabel;
    private static Label selectCustomerLabel;
    private static Label selectAdminLabel;
    private static Label rolLabel;
    private static TextField nameTextField;
    private static TextField passwordTextField;
    private static TextField emailTextField;
    private static TextField addressTextField;
    private static TextField phoneNumberTextField;
    private static ComboBox selectCustomerComboBox;
    private static ComboBox rolComboBox;
    private static int rol;
    private static ObservableList<String> selectCustomerObservableList;
    private ObservableList<Integer> rolObservableList;
    private static GridPane pane;

    public GridPane ModifyAdmin() {
        pane = PaneUtil.buildPane();
        serviceInstance();
        setupControls();
        addHandlers();
        unShow();
        return pane;
    }

    private void setupControls() {
        selectCustomerLabel = PaneUtil.buildLabel(pane, "Select a customer", 0, 3);
        selectAdminLabel = PaneUtil.buildLabel(pane, "Select a Admin", 0, 3);

        selectCustomerObservableList = FXCollections.observableArrayList();
        selectCustomerComboBox = PaneUtil.buildComboBox(pane, selectCustomerObservableList, 1, 3);
        selectCustomerButton = PaneUtil.buildButtonImage(new Image("editUser.png"), pane, 2, 3);

        nameLabel = PaneUtil.buildLabel(pane, "Name", 1, 4);
        nameTextField = PaneUtil.buildTextInput(pane, 1, 5);
        passwordLabel = PaneUtil.buildLabel(pane, "Password", 1, 6);
        passwordTextField = PaneUtil.buildTextInput(pane, 1, 7);
        emailLabel = PaneUtil.buildLabel(pane, "Email", 1, 8);
        emailTextField = PaneUtil.buildTextInput(pane, 1, 9);
        addressLabel = PaneUtil.buildLabel(pane, "Address", 1, 10);
        addressTextField = PaneUtil.buildTextInput(pane, 1, 11);
        phoneNumberLabel = PaneUtil.buildLabel(pane, "Phone Number", 1, 12);
        phoneNumberTextField = PaneUtil.buildTextInput(pane, 1, 13);
        rolObservableList = FXCollections.observableArrayList(2, 3);
        rolLabel = PaneUtil.buildLabel(pane, "Rol", 1, 14);
        rolComboBox = PaneUtil.buildComboBox(pane, rolObservableList, 1, 15);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 16);
        modifyButton = PaneUtil.buildButton("Modify", pane, 4, 16);
    }

    public static void serviceInstance() {
        userService = UserService.getInstance();
    }

    private void addHandlers() {
        exitButton.setOnAction(e -> {
            MainManagePane.clearPane();
            refreshItems();
        });

        modifyButton.setOnAction(e -> {
            modifyUser();
        });

        selectCustomerButton.setOnAction(event -> {
            refreshFillItems();
            show();
        });

        nameTextField.setOnAction(event ->
                nameTextField.setStyle("-fx-background-color: #FFFFFF")
        );
        emailTextField.setOnAction(event ->
                emailTextField.setStyle("-fx-background-color: #FFFFFF")
        );
        addressTextField.setOnAction(event ->
                addressTextField.setStyle("-fx-background-color: #FFFFFF")
        );
        phoneNumberTextField.setOnAction(event ->
                phoneNumberTextField.setStyle("-fx-background-color: #FDC7C7")
        );
        passwordTextField.setOnAction(event ->
                passwordTextField.setStyle("-fx-background-color: #FDC7C7")
        );
    }

    private void modifyUser() {
        if (validateAdd()) {
            Utility utility = new Utility();
            User previousUser = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());

            User user = new User();
            user.setName(nameTextField.getText());
            user.setAddress(addressTextField.getText());
            user.setRol(Integer.parseInt(rolComboBox.getSelectionModel().getSelectedItem().toString()));
            user.setEmail(emailTextField.getText());
            user.setPhoneNumber(Integer.parseInt(phoneNumberTextField.getText()));
            user.setID(previousUser.getID());

            if (passwordTextField.getText().isEmpty()) {
                user.setPassword(previousUser.getPassword());
            } else {
                user.setPassword(utility.encrypt(passwordTextField.getText()));
            }
            user.setID(user.getID());

            if (userService.edit(previousUser, user)) {
                PaneUtil.showAlert(Alert.AlertType.INFORMATION, "User edited", "The user was edited correctly");
                MainManagePane.clearPane();
                unShow();
                refreshItems();
            } else {
                PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "The user was not edited");
            }
        }
    }

    private void refreshItems() {
        nameTextField.clear();
        passwordTextField.clear();
        emailTextField.clear();
        addressTextField.clear();
        phoneNumberTextField.clear();
    }

    private void refreshFillItems() {
        User user = userService.getByName(selectCustomerComboBox.getSelectionModel().getSelectedItem().toString());
        nameTextField.setText(user.getName());
        addressTextField.setText(user.getAddress());
        rolComboBox.getSelectionModel().select(Integer.toString(user.getRol()));
        phoneNumberTextField.setText(Integer.toString(user.getPhoneNumber()));
        emailTextField.setText(user.getEmail());
    }

    private boolean validateAdd() {
        boolean validate = true;
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setPromptText("Obligatory field");
            nameTextField.setStyle("-fx-background-color: #FDC7C7");
            validate = false;
        }
        if (emailTextField.getText().isEmpty()) {
            emailTextField.setPromptText("Obligatory field");
            emailTextField.setStyle("-fx-background-color: #FDC7C7");
            validate = false;
        }

        if (addressTextField.getText().isEmpty()) {
            addressTextField.setPromptText("Obligatory field");
            addressTextField.setStyle("-fx-background-color: #FDC7C7");
            validate = false;
        }
        if (phoneNumberTextField.getText().isEmpty()) {
            phoneNumberTextField.setPromptText("Obligatory field");
            phoneNumberTextField.setStyle("-fx-background-color: #FDC7C7");
            validate = false;
        }
        if (rolComboBox.getSelectionModel().getSelectedItem() == null) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "ERROR", "Select a rol");
        }
        return validate;
    }

    public static void unShow() {
        nameLabel.setVisible(false);
        nameTextField.setVisible(false);
        passwordLabel.setVisible(false);
        passwordTextField.setVisible(false);
        emailLabel.setVisible(false);
        emailTextField.setVisible(false);
        addressLabel.setVisible(false);
        addressTextField.setVisible(false);
        phoneNumberLabel.setVisible(false);
        phoneNumberTextField.setVisible(false);
        rolComboBox.setVisible(false);
        exitButton.setVisible(false);
        modifyButton.setVisible(false);
        rolLabel.setVisible(false);

        selectCustomerComboBox.setVisible(true);
        selectCustomerButton.setVisible(true);
    }

    private void show() {
        nameLabel.setVisible(true);
        nameTextField.setVisible(true);
        passwordLabel.setVisible(true);
        passwordTextField.setVisible(true);
        emailLabel.setVisible(true);
        emailTextField.setVisible(true);
        addressLabel.setVisible(true);
        addressTextField.setVisible(true);
        phoneNumberLabel.setVisible(true);
        phoneNumberTextField.setVisible(true);
        rolComboBox.setVisible(true);
        exitButton.setVisible(true);
        modifyButton.setVisible(true);
        rolLabel.setVisible(true);

        selectAdminLabel.setVisible(false);
        selectCustomerLabel.setVisible(false);
        selectCustomerComboBox.setVisible(false);
        selectCustomerButton.setVisible(false);
    }

    /**
     * Valida que el usuario no tenga agregada una cita
     */
    public static void refresh() {
        serviceInstance();
        if (userService.getAll().isEmpty()) {
            PaneUtil.showAlert(Alert.AlertType.ERROR, "Error", "You must first add an user");
            MainManagePane.clearPane();
        } else {
            unShow();
        }
    }

    public static void setRol(int rol1) {
        rol = rol1;
        editAdminAndCustomer(rol1);
    }

    private static void editAdminAndCustomer(int rol) {
        if (rol == 2) {
            selectCustomerObservableList.clear();
            selectCustomerObservableList.addAll(userService.getAdminNames());
            selectAdminLabel.setVisible(true);
            selectCustomerLabel.setVisible(false);
        } else if (rol == 3) {
            selectCustomerObservableList.clear();
            selectCustomerObservableList.addAll(userService.getCustomerNames());
            selectCustomerLabel.setVisible(true);
            selectAdminLabel.setVisible(false);
        }
    }

    @Override
    public Pane getPane() {
        return ModifyAdmin();
    }
}
