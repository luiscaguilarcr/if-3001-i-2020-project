/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class Graphic implements PaneViewer{
    private static GridPane pane;
    private static CategoryAxis graphic;
    private static BarChart jaja;
public GridPane graphic() {
        pane = PaneUtil.buildPane();
        setupControls();
        //addHandlers();
        //serviceInstance();
        //visible();
        return pane;
    }

private void setupControls() {
graphic  = PaneUtil.buildGraphic(pane);
//jaja = PaneUtil.buildGraphic(pane);
}

    @Override
    public Pane getPane() {
        return graphic();
    }
    
}
