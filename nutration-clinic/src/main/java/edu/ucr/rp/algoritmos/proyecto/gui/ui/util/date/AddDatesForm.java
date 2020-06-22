/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.*;
import static java.util.Collections.list;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.collections.ObservableList;
import javafx.collections.*;

/**
 * @author Alvaro Miranda Cort
 */
public class AddDatesForm implements PaneViewer {

    private static GridPane pane;
    private static Label AddDateTitleLabel;
    private static Label DateFieldLabel;
    private static Button modifyButton;
    private static Button exitButton;
    private static Button addDateButton;
    private DatePicker checkInDatePicker;
    private ComboBox horasComboBox;
    private ComboBox doctorsComboBox;
    private static Label SelecthoursLabel;
    private static Label SelectdoctorLabel;
    private static DateService dateService;

    public GridPane addDatesForm() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }

    public static void dateServiceInstance() {
        //dateService = DateService.getInstance();
    }

    /**
     * Inicializar botonesd, textFields, labels....
     */
    private void setupControls() {

        List<String> list = new ArrayList<String>();
        list.add("8:00am");
        list.add("9:00am");
        list.add("10:00am");
        list.add("11:00am");
        list.add("12:00pm");
        list.add("1:00pm");
        list.add("2:00pm");
        list.add("3:00pm");
        list.add("4:00pm");
        list.add("5:00pm");
        ObservableList<String> observableList = FXCollections.observableList(list);
        List<String> listdoctor = new ArrayList<String>();
        listdoctor.add("Doctor 296");
        ObservableList<String> observableDoctor = FXCollections.observableList(listdoctor);
        AddDateTitleLabel = PaneUtil.buildLabel(pane, "Book appointment", 0, 0);
        DateFieldLabel = PaneUtil.buildLabel(pane, "Date Field", 0, 1);
        checkInDatePicker = PaneUtil.buildDatePicker(pane, 1, 1);
        checkInDatePicker.setValue(LocalDate.now());//establece la fecha actual
        checkInDatePicker.setShowWeekNumbers(true);//habilita numeros de la semana
        SelecthoursLabel = PaneUtil.buildLabel(pane, " Select time ", 0, 2);
        horasComboBox = PaneUtil.buildComboBox(pane, observableList, 1, 2);
        SelectdoctorLabel = PaneUtil.buildLabel(pane, " Select doctor ", 0, 3);
        doctorsComboBox = PaneUtil.buildComboBox(pane, observableDoctor, 1, 3);
        addDateButton = PaneUtil.buildButton("Add Date", pane, 0, 5);
        modifyButton = PaneUtil.buildButton("Modify Date", pane, 1, 5);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 5);

    }

    /**
     * Agregar accion de evento
     */
    private void addHandlers() {
        exitButton.setOnAction(e -> Platform.exit());
        addDateButton.setOnAction(e -> {
        });

        modifyButton.setOnAction(e -> {
        });

    }

    public static void refresh() {
        dateServiceInstance();
    }

    @Override
    public Pane getPane() {
        return addDatesForm();
    }
}
