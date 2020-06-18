/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.scenes.visual.util;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
    private static Button modifyButton;
    private static Button exitButton;
    private static Button addDateButton;

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
        addDateButton = PaneUtil.buildButton("Add Date", pane, 0, 1);
        modifyButton = PaneUtil.buildButton("Add Date", pane, 0, 2);
        exitButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 0, 3);

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
