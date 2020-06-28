/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddAdminForm.serviceInstance;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.ViewAdminForm.tableView;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class ViewCustomer_Form implements PaneViewer {

    private static GridPane pane;
    private static TableView tableView;

    public GridPane ViewCustomer() {
        pane = PaneUtil.buildPane();
        setupControls();
        //addHandlers();
        //serviceInstance();
        //visible();
        return pane;
    }

    
    private void setupControls() {
//tableView= PaneUtil.buildTableViewUser(pane, 1, 2);

pane= PaneUtil.buildTableViewUser(pane, 1, 0);
}
    
    
    
    @Override
    public Pane getPane() {

        return ViewCustomer();
    }
}
