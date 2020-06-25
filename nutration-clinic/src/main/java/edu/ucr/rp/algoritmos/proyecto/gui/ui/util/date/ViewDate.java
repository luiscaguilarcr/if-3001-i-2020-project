/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 *
 * @author Alvaro Miranda Cort
 */
public class ViewDate implements PaneViewer {

    private static GridPane pane;
    private static Button cancelButton;
    private static ModifyDate modifyDate = new ModifyDate();

    private Pane viewDate() {
        pane = PaneUtil.buildPane();
        setupControls();
        addHandlers();
        return pane;
    }

    private void setupControls() {

        TableView<CustomerDate> tv_Dates = new TableView();
        tv_Dates = PaneUtil.buildTableView(pane, 1, 1);
        TableColumn<CustomerDate, String> tc_Customer = new TableColumn("Customer ID");
        tc_Customer.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDate()));
        TableColumn tc_Date = new TableColumn("Date");
        TableColumn tc_Hour = new TableColumn("Hour");
        TableColumn tc_Doctor = new TableColumn("Doctor ID");

        tv_Dates.getColumns().addAll(tc_Customer, tc_Date, tc_Hour, tc_Doctor);

        cancelButton = PaneUtil.buildButtonImage(new Image("logout.png"), pane, 0, 0);

    }

    private void addHandlers() {
        cancelButton.setOnAction(e -> MainManagePane.clearPane());
    }

    @Override
    public Pane getPane() {
        return viewDate();
    }
}
