package edu.ucr.rp.algoritmos.proyecto.gui.javafx.visualStage;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LogIn implements PaneViewer{
    private static GridPane pane;
    private static Label userNameLabel;
    private static Label passwordNameLabel;
    private static TextField userNameTextField;
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
        userNameLabel = PaneUtil.buildLabel(pane, "User name", 0,0);
        userNameTextField = PaneUtil.buildTextInput(pane, 1, 0);
        passwordNameLabel = PaneUtil.buildLabel(pane, "Password", 0, 1);
        passwordTextField = PaneUtil.buildPasswordField(pane, 1, 1);
        logInButton = PaneUtil.buildButton("LOGIN", pane, 1, 2);
        logInButton.setAlignment(Pos.CENTER);
        extiButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 2);

    }

    private void addHandlers() {
        extiButton.setOnAction(e -> Platform.exit());
        userNameTextField.setOnMouseClicked(event -> {
            userNameTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        passwordTextField.setOnAction(event -> {
            userNameTextField.setStyle("-fx-background-color: #FFFFFF");
        });
        logInButton.setOnAction(e -> {
            validateLogIn();
        });
    }

    private void validateLogIn() {
        if (userNameTextField.getText().isEmpty()) {
            userNameTextField.setPromptText("Obligatory field");
            userNameTextField.setStyle("-fx-background-color: #FDC7C7");
        } else if (passwordTextField.getText().isEmpty()) {
            userNameTextField.setPromptText("Obligatory field");
            userNameTextField.setStyle("-fx-background-color: #FDC7C7");
        } else {
            userNameTextField.clear();
            passwordTextField.clear();
        }
    }

    @Override
    public Pane getPane() {
        return LogIn();
    }
}
