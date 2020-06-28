/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddAdminForm.serviceInstance;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class ViewAdminForm implements PaneViewer{
public static TableView tableView;
 private static GridPane pane;

 public GridPane  Table() {
        pane = PaneUtil.buildPane();
        setupControls();
        //addHandlers();
        //serviceInstance();
        //visible();
        return pane;
    }

private void setupControls() {
tableView= PaneUtil.buildTableViewUser(pane, 1, 2);


}

    @Override
    public Pane getPane() {
       return Table();
    }
    
}
