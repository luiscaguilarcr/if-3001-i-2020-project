package edu.ucr.rp.algoritmos.proyecto.gui.ui;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.gui.App;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.LogInManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddCustomerForm;
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

    private static GridPane pane;
    private static Label iDLabel;
    private static Label passwordNameLabel;
    private static TextField iDTextField;
    private static PasswordField passwordTextField;
    private static Button exitButton;
    private static Button logInButton;
    private static boolean validUser = false;
    private Stage stage;
    private Utility utility = new Utility();
    private static UserService userService = UserService.getInstance();
    private GridPane gridPane;
    private static Button registerButton;

    public LogIn(Stage stage) {
        this.stage = stage;
    }

    public static User userLog;

    public GridPane LogIn() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        //visible();

        return pane;
    }

    private void setupControls() {
        iDLabel = PaneUtil.buildLabel(pane, "ID", 0, 0);
        iDTextField = PaneUtil.buildTextInput(pane, 1, 0);
        passwordNameLabel = PaneUtil.buildLabel(pane, "Password", 0, 1);
        passwordTextField = PaneUtil.buildPasswordField(pane, 1, 1);
        logInButton = PaneUtil.buildButton("LOGIN", pane, 1, 2);
        logInButton.setAlignment(Pos.CENTER);
        exitButton = PaneUtil.buildButtonImage(new Image("exit2.png"), pane, 1, 2);
        registerButton = PaneUtil.buildButton("Register", pane, 1, 5);

    }

    private void addHandlers() {
        exitButton.setOnAction(e -> Platform.exit());
        iDTextField.setOnMouseClicked(e -> iDTextField.setStyle("-fx-background-color: #FFFFFF"));
        passwordTextField.setOnAction(e -> iDTextField.setStyle("-fx-background-color: #FFFFFF"));
        logInButton.setOnAction(e -> {
            if (validateLogIn()) {
                if (logInUser() == null) {
                    PaneUtil.showAlert(Alert.AlertType.INFORMATION, "Error when logging in to the account", "Check the user ID and password, and try again");
                }
            }
        });
        registerButton.setOnAction(e -> {

            //notVisible();
            LogInManagePane.clearPane();
            LogInManagePane.setCenterPane(LogInManagePane.getPanes().get(PaneName.ADD_CUSTOMER_FORM));

            AddCustomerForm.visible();

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
        if (!iDTextField.getText().isEmpty() && !passwordTextField.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    public User logInUser() {
        if (userService.getByID(Integer.parseInt(iDTextField.getText())) != null) {
            userLog = userService.getByID(Integer.parseInt(iDTextField.getText()));
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

    private void notVisible() {
        passwordTextField.setVisible(false);
        iDTextField.setVisible(false);
        exitButton.setVisible(false);
        iDLabel.setVisible(false);
        logInButton.setVisible(false);
        passwordNameLabel.setVisible(false);
        registerButton.setVisible(false);
    }

    public static void visible() {
        passwordTextField.setVisible(true);
        iDTextField.setVisible(true);
        exitButton.setVisible(true);
        iDLabel.setVisible(true);
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
            return userService.getByID(Integer.parseInt(iDTextField.getText())).getRol();
        }
        return -1;
    }

    @Override
    public Pane getPane() {
        return LogIn();
    }
}
