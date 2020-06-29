/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddAdminForm.serviceInstance;
import static edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.ViewAdminForm.tableView;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Noel
 */
public class ViewCustomer_Form implements PaneViewer {
     private static UserService userService;
    private static GridPane pane;
    private static TableView tableView;
 private static ObservableList<String> customerObservableList;
    public GridPane ViewCustomer() {
        pane = PaneUtil.buildPaneBig();
              serviceInstance();
        setupControls();
        //addHandlers();
       
        //visible();
        return pane;
    }
 public static void serviceInstance() {
       
        userService = UserService.getInstance();
    }
    
    private void setupControls() {
        customerObservableList =FXCollections.observableArrayList(userService.getCustomerNames());
    tableView= PaneUtil.buildTableViewUser(pane, customerObservableList,1, 2);

}
    
    
    
    @Override
    public Pane getPane() {

        return ViewCustomer();
    }
}
