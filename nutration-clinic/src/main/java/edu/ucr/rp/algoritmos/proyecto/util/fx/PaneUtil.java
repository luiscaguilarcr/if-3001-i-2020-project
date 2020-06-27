package edu.ucr.rp.algoritmos.proyecto.util.fx;

import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import org.controlsfx.control.CheckComboBox;

import static edu.ucr.rp.algoritmos.proyecto.util.fx.UIConstants.*;

public class PaneUtil {

    public static GridPane buildPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER_RIGHT);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(LABEL_WITH, LABEL_WITH, LABEL_WITH_MAX);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(INPUT_WITH, INPUT_WITH, INPUT_WITH_MAX);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);
        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    public static BorderPane buildBorderPane(GridPane pane) {
        BorderPane borderPane = new BorderPane();
        borderPane.setRight(pane);
        return borderPane;
    }

    public static TextField buildTextInput(GridPane pane, int column, int row) {
        TextField textField = new TextField();
        pane.add(textField, column, row);
        return textField;
    }

    public static PasswordField buildPasswordField(GridPane pane, int column, int row) {
        PasswordField passwordField = new PasswordField();
        pane.add(passwordField, column, row);
        return passwordField;
    }

    public static DatePicker buildDatePicker(GridPane pane, int column, int row) {
        DatePicker datePicker = new DatePicker();
        pane.add(datePicker, column, row);
        return datePicker;
    }

    public static void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }

    public static TableView buildTableView(GridPane pane,int column, int row) {
        TableView tableview = new TableView();
        tableview.setPrefWidth(10000000);
        tableview.setPrefHeight(5000);
        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        pane.add(tableview,column, row);
        return tableview;
    }
 
    public static Button buildButton(String text, GridPane pane, int column, int row) {
        Button button = new Button(text);
        pane.setHalignment(button, HPos.LEFT);
        pane.setMargin(button, BUTTON_DEFAULT_INSETS);
        pane.add(button, column, row);
        return button;
    }

    public static Button buildButtonImage(Image image, GridPane pane, int column, int row) {
        Button button = new Button("", new ImageView(image));
        pane.setHalignment(button, HPos.RIGHT);
        button.setStyle("-fx-background-color: #00bfa5");
        pane.setMargin(button, BUTTON_DEFAULT_INSETS);
        pane.add(button, column, row);
        return button;
    }

    public static ComboBox buildComboBox(GridPane pane, ObservableList observableList, int column, int row) {
        ComboBox comboBox = new ComboBox(observableList);
        comboBox.setMaxWidth(INPUT_WITH_MAX);
        pane.add(comboBox, column, row);
        return comboBox;
    }

    public static CheckComboBox buildCheckComboBox(GridPane pane, ObservableList<String> observableList, int column, int row) {
        CheckComboBox<String> checkComboBox = new CheckComboBox<>(observableList);
        checkComboBox.setMaxWidth(INPUT_WITH_MAX);
        pane.add(checkComboBox, column, row);
        return checkComboBox;
    }

    public static boolean addCatalogHandlers(ComboBox catalogComboBox, Label catalogIndicationLabel, Button confirmCatalogButton) {
        if (catalogComboBox.getValue() == null) {
            showAlert(Alert.AlertType.INFORMATION, "Error, did not select a catalog", "You must select a catalog");
            return false;
        } else {
            catalogIndicationLabel.setVisible(false);
            catalogComboBox.setVisible(false);
            confirmCatalogButton.setVisible(false);
            return true;
        }
    }

    public static Label buildLabel(GridPane pane, String text, int column, int row) {
        Label label = new Label(text);
        pane.add(label, column, row);
        return label;
    }

    public static Label buildLabelVBox(HBox HBox, String text, int column, int row) {
        Label label = new Label(text);
        HBox.getChildren().add(column, label);
        return label;
    }

    public static TextField buildTextField(GridPane pane, int row) {
        TextField textField = new TextField();
        pane.add(textField, 1, row);
        return textField;
    }
}
