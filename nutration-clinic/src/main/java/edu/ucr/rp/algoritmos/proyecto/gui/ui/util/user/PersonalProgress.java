/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user;

import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.LogIn;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAnnotationService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.AdminAnnotationQueue;
import edu.ucr.rp.algoritmos.proyecto.util.fx.PaneUtil;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Noel
 */
public class PersonalProgress implements PaneViewer {
    private static GridPane pane;
    private static CategoryAxis graphic;
    private static AdminAnnotationService adminAnnotationService;
    private static Button fatButton;
    private static Button weightButton;
    private static Button heightButton;
    private static Button backButton;
    private LineChart fatLineChart = new LineChart(new CategoryAxis(), new NumberAxis());
    private LineChart weightLineChart = new LineChart(new CategoryAxis(), new NumberAxis());
    private LineChart heightLineChart = new LineChart(new CategoryAxis(), new NumberAxis());

    public GridPane graphic() {
        pane = PaneUtil.buildPaneBig();
        initializeInstances();
        addHandlers();
        addControls();
        return pane;
    }

    private void initializeInstances() {
        adminAnnotationService = AdminAnnotationService.getInstance();
    }

    private void addHandlers() {
        fatButton = PaneUtil.buildButton("Fat progress", pane, 2, 4);
        weightButton = PaneUtil.buildButton("Weight progress", pane, 2, 5);
        heightButton = PaneUtil.buildButton("Height progress", pane, 2, 6);
        backButton = PaneUtil.buildButtonImage(new Image("exit.png"), pane, 3, 5);
    }

    private void addControls() {
        fatButton.setOnAction(event -> {
            User user = LogIn.getUser();
            AdminAnnotationQueue adminAnnotationQueue = adminAnnotationService.getAllByID(user.getID());
            notVisibleForFat();
            buildFatChart(adminAnnotationQueue);
        });

        weightButton.setOnAction(event -> {
            User user = LogIn.getUser();
            AdminAnnotationQueue adminAnnotationQueue = adminAnnotationService.getAllByID(user.getID());
            notVisibleForWeight();
            buildWeightChart(adminAnnotationQueue);
        });

        heightButton.setOnAction(event -> {
            User user = LogIn.getUser();
            AdminAnnotationQueue adminAnnotationQueue = adminAnnotationService.getAllByID(user.getID());
            notVisibleForHeight();
            buildHeightChart(adminAnnotationQueue);
        });

        backButton.setOnAction(event -> {
            MainManagePane.clearPane();
            visible();
        });
    }

    private void visible(){
        fatButton.setVisible(true);
        heightButton.setVisible(true);
        weightButton.setVisible(true);

        fatLineChart.setVisible(false);
        heightLineChart.setVisible(false);
        weightLineChart.setVisible(false);
    }

    private void notVisibleForFat() {
        weightLineChart.setVisible(false);
        heightLineChart.setVisible(false);
    }

    private void notVisibleForWeight() {
        fatLineChart.setVisible(false);
        heightLineChart.setVisible(false);
    }

    private void notVisibleForHeight() {
        fatLineChart.setVisible(false);
        weightLineChart.setVisible(false);
    }

    private void buildFatChart(AdminAnnotationQueue adminAnnotationQueue) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(130, 150, 200);
        yAxis.setLabel("FAT %");
        fatLineChart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        int size = adminAnnotationQueue.size();
        for (int i = 0; i < size; i++) {
            AdminAnnotation adminAnnotation = adminAnnotationQueue.deQueue();
            series.getData().add(new XYChart.Data(adminAnnotation.getDate(), adminAnnotation.getFat()));
        }
        series.setName("Fat progress");
        fatLineChart.getData().add(series);
        pane.add(fatLineChart, 2, 1);
    }

    private void buildWeightChart(AdminAnnotationQueue adminAnnotationQueue) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(10, 15, 20);
        yAxis.setLabel("WEIGHT (KG)");
        weightLineChart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        int size = adminAnnotationQueue.size();
        for (int i = 0; i < size; i++) {
            AdminAnnotation adminAnnotation = adminAnnotationQueue.deQueue();
            series.getData().add(new XYChart.Data(adminAnnotation.getDate(), adminAnnotation.getWeight()));
        }
        series.setName("Weight progress");
        weightLineChart.getData().add(series);
        pane.add(weightLineChart, 2, 1);
    }

    private void buildHeightChart(AdminAnnotationQueue adminAnnotationQueue) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(50, 75, 100);
        yAxis.setLabel("HEIGHT (KG)");
        heightLineChart = new LineChart(xAxis, yAxis);
        XYChart.Series series = new XYChart.Series();
        int size = adminAnnotationQueue.size();
        for (int i = 0; i < size; i++) {
            AdminAnnotation adminAnnotation = adminAnnotationQueue.deQueue();
            series.getData().add(new XYChart.Data(adminAnnotation.getDate(), adminAnnotation.getHeight()));
        }
        series.setName("Height progress");
        heightLineChart.getData().add(series);
        pane.add(heightLineChart, 2, 1);
    }

    @Override
    public Pane getPane() {
        return graphic();
    }

}
