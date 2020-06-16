package edu.ucr.rp.algoritmos.proyecto.gui.scenes.visual;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LogIn implements PaneViewer {
    private static GridPane pane;
    private static Label iDLabel;
    private static Label passwordNameLabel;
    private static TextField iDTextField;
    private static PasswordField passwordTextField;
    private static Button extiButton;
    private static Button logInButton;
    private Stage stage;

    public LogIn(Stage stage) {
        this.stage = stage;
    }

    public GridPane LogIn() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }

    private void setupControls() {
        iDLabel = PaneUtil.buildLabel(pane, "ID", 0, 0);
        iDTextField = PaneUtil.buildTextInput(pane, 1, 0);
        passwordNameLabel = PaneUtil.buildLabel(pane, "Password", 0, 1);
        passwordTextField = PaneUtil.buildPasswordField(pane, 1, 1);
        logInButton = PaneUtil.buildButton("LOGIN", pane, 1, 2);
        logInButton.setAlignment(Pos.CENTER);
        extiButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 2);

    }

    private void addHandlers() {
        extiButton.setOnAction(e -> Platform.exit());
        iDTextField.setOnMouseClicked(event -> {
            iDTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        passwordTextField.setOnAction(event -> {
            iDTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        logInButton.setOnAction(e -> {
            if(validateLogIn()){
                if(LogInUser() == null){
                    PaneUtil.showAlert(Alert.AlertType.INFORMATION, "Error when logging in to the account", "Check the user ID and password, and try again");
                }
            }
        });
    }

    private boolean validateLogIn() {
        if (iDTextField.getText().isEmpty()) {
            iDTextField.setPromptText("Obligatory field");
            iDTextField.setStyle("-fx-background-color: #FDC7C7");
        } else {
            iDTextField.setStyle("-fx-background-color: #FFFFFF");
        }
        if (passwordTextField.getText().isEmpty()) {
            passwordTextField.setPromptText("Obligatory field");
            passwordTextField.setStyle("-fx-background-color: #FDC7C7");
        } else {
            passwordTextField.setStyle("-fx-background-color: #FFFFFF");
        }
        if(!iDTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()){
            return true;
        }
        return false;
    }

    public User LogInUser(){
        UserService userService = UserService.getInstance();
        if(userService.getById(Integer.parseInt(iDTextField.getText())) != null){
            User user = userService.getById(Integer.parseInt(iDTextField.getText()));
            String passwordUser = user.getPassword();
            String passwordEnter = encrypt(passwordTextField.getText());
            if((user.getPassword()).equals(encrypt(passwordTextField.getText()))){
                return user;
            }
        }
        return null;
    }

    @Override
    public Pane getPane() {
        return LogIn();
    }

    private String encrypt(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();

        } catch (java.security.NoSuchAlgorithmException me) {
            return null;
        }

    }
}
