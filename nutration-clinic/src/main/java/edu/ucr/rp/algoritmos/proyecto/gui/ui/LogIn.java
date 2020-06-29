package edu.ucr.rp.algoritmos.proyecto.gui.ui;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.gui.App;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.LogInManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.SignUpForm;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class LogIn implements PaneViewer {
    private static UserService userService = UserService.getInstance();
    public static User userLog;
    private Utility utility = new Utility();
    private Stage stage;
    private static GridPane pane;
    private static Label nameLabel;
    private static Label passwordNameLabel;
    private static TextField nameTextField;
    private static PasswordField passwordTextField;
    private static Button exitButton;
    private static Button logInButton;
    private static Button registerButton;
    private static boolean validUser = false;

    /**
     * @autor Luis Carlos
     * @param stage principal
     */
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
        nameLabel = PaneUtil.buildLabel(pane, "Name", 0, 0);
        nameTextField = PaneUtil.buildTextInput(pane, 1, 0);
        passwordNameLabel = PaneUtil.buildLabel(pane, "Password", 0, 1);
        passwordTextField = PaneUtil.buildPasswordField(pane, 1, 1);
        logInButton = PaneUtil.buildButton("LOGIN", pane, 1, 2);
        logInButton.setAlignment(Pos.CENTER);
        exitButton = PaneUtil.buildButtonImage(new Image("exit2.png"), pane, 1, 2);
        registerButton = PaneUtil.buildButton("Register", pane, 1, 5);

    }

    private void addHandlers() {
        exitButton.setOnAction(e -> Platform.exit());
        nameTextField.setOnMouseClicked(e -> nameTextField.setStyle("-fx-background-color: #FFFFFF"));
        passwordTextField.setOnAction(e -> nameTextField.setStyle("-fx-background-color: #FFFFFF"));
        logInButton.setOnAction(e -> {
            if (validateLogIn()) {
                if (logInUser() == null) {
                    PaneUtil.showAlert(Alert.AlertType.INFORMATION, "Error when logging in to the account", "Check the user ID and password, and try again");
                }
            }
        });
        registerButton.setOnAction(e -> {
            LogInManagePane.clearPane();
            LogInManagePane.setCenterPane(LogInManagePane.getPanes().get(PaneName.SIGN_UP_FORM));
            SignUpForm.visible();
        });
    }

    private boolean validateLogIn() {
        if (nameTextField.getText().isEmpty()) {
            nameTextField.setPromptText("Obligatory field");
            nameTextField.setStyle("-fx-background-color: #FDC7C7");
        } else {
            nameTextField.setStyle("-fx-background-color: #FFFFFF");
        }
        if (passwordTextField.getText().isEmpty()) {
            passwordTextField.setPromptText("Obligatory field");
            passwordTextField.setStyle("-fx-background-color: #FDC7C7");
        } else {
            passwordTextField.setStyle("-fx-background-color: #FFFFFF");
        }
        if (!nameTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    public User logInUser() {
        if (userService.getByName(nameTextField.getText()) != null) {
            userLog = userService.getByName(nameTextField.getText());
            if ((userLog.getPassword()).equals(utility.encrypt(passwordTextField.getText()))) {
                validUser = true;
                App app = new App();
                app.start(stage);
                return userLog;
            }
        }
        return null;
    }

    public static boolean validateUser() {
        return validUser;
    }

    public static void refresh() {
        validUser = false;
    }

    public static void visible() {
        passwordTextField.setVisible(true);
        nameTextField.setVisible(true);
        exitButton.setVisible(true);
        nameLabel.setVisible(true);
        logInButton.setVisible(true);
        passwordNameLabel.setVisible(true);
        registerButton.setVisible(true);
    }

    public static User getUser() {
        if (validUser) {
            return userLog;
        }
        return null;
    }

    public static int getRol() {
        if (validUser) {
            return getUser().getRol();
        }
        return -1;
    }

    @Override
    public Pane getPane() {
        return LogIn();
    }
}
