/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Alvaro Miranda Cort
 */
public class ViewDate implements PaneViewer {
private static GridPane pane;
    

    private Pane viewDate() {
          pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }
   

    private void setupControls() {
       
    }

    private void addHandlers() {
       
    }
@Override
    public Pane getPane() {
       return viewDate();
    }
}