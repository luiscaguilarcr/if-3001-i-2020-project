/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.scenes.visual.util;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import java.time.LocalDate;
import javafx.application.Platform;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
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
    
     
     
     public GridPane addDatesForm() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }

    /**
     * Inicializar botonesd, textFields, labels....
     */
    private void setupControls() {
        
        AddDateTitleLabel = PaneUtil.buildLabel(pane, "Book appointment", 0, 0);
         DateFieldLabel = PaneUtil.buildLabel(pane, "Date Field", 0, 2);
        checkInDatePicker = PaneUtil.buildDatePicker(pane, 0, 3);
        checkInDatePicker.setValue(LocalDate.now());//establece la feca actual
        checkInDatePicker.setShowWeekNumbers (true);//habilita numeros de la semana
        addDateButton = PaneUtil.buildButton("Add Date", pane, 0, 5);
        modifyButton = PaneUtil.buildButton("Add Date", pane, 1, 5);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 2, 5);
    }

    /**
     * Agregar acción de evento
     */
    private void addHandlers() {
        exitButton.setOnAction(e -> Platform.exit());

        addDateButton.setOnAction(e -> {
        });

        modifyButton.setOnAction(e -> {
        });

    }

    @Override
    public Pane getPane() {
        return addDatesForm();
    }
}
