package edu.ucr.rp.algoritmos.proyecto.gui.ui;

import edu.ucr.rp.algoritmos.proyecto.gui.App;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneName;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.scenes.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.AddAnnotationForm;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.AddDatesForm;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.system.CleanApp;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.AddAdminForm;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.ModifyDate;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.date.ViewDate;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.ChangePasswordForm;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.user.ModifyUserForm;
import javafx.application.Platform;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.Desktop;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Main_MenuBar implements PaneViewer {

    private Stage stage;
    private Menu admin_Menu;
    private Menu dates_Menu;
    private Menu customer_Menu;
    private MenuItem generateReport_MenuItem;
    private MenuItem personalProgress_MenuItem;
    private MenuItem annotationsDate_MenuItem;
    private MenuItem cleanApp_MenuItem;

    public Main_MenuBar(Stage stage) {
        this.stage = stage;
    }

    public VBox getMenuVBox() {
        VBox MenuBar_VBox = new VBox();

        MenuBar View_MenuBar = new MenuBar();

        ////////////////////////////////////////////////////////////////////////// Menu "CONFIG"
        Menu config_Menu = new Menu("CONFIG");

        ////////////////////////////////////////// MenuItems for config_Menu
        // changePassword_MenuItem
        ImageView changePasswordInventory_ImageView = new ImageView(new Image("password.png"));
        MenuItem changePassword_MenuItem = new MenuItem("Change password", changePasswordInventory_ImageView);

        // generateReport_MenuItem
        ImageView generateReport_ImageView = new ImageView(new Image("report.png"));
        generateReport_MenuItem = new MenuItem("Generate report", generateReport_ImageView);

        // personalProgress_MenuItem
        ImageView personalProgress_ImageView = new ImageView(new Image("progress.png"));
        personalProgress_MenuItem = new MenuItem("Personal progress", personalProgress_ImageView);

        // logOut_MenuItem
        ImageView logOut_ImageView = new ImageView(new Image("logout3.png"));
        MenuItem logOut_MenuItem = new MenuItem("Log out", logOut_ImageView);

        // guide_MenuItem
        ImageView guide_ImageView = new ImageView(new Image("manual1.png"));
        MenuItem guide_MenuItem = new MenuItem("User's guide", guide_ImageView);

        // exit_MenuItem
        ImageView cleanApp_ImageView = new ImageView(new Image("cleanApp.png"));
        cleanApp_MenuItem = new MenuItem("Clean app", cleanApp_ImageView);

        // exit_MenuItem
        ImageView exit_ImageView = new ImageView(new Image("exit3.png"));
        MenuItem exit_MenuItem = new MenuItem("Exit", exit_ImageView);

        changePassword_MenuItem.setOnAction((e) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.CHANGE_PASSWORD));
        });

        generateReport_MenuItem.setOnAction((event) -> {
        });

        personalProgress_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.GRAPHIC));
        });

        guide_MenuItem.setOnAction((event) -> {
            try {
                File path = new File("manual.pdf");
                Desktop.getDesktop().open(path);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        logOut_MenuItem.setOnAction((event) -> {
            LogIn.refresh();
            App app = new App();
            app.start(stage);
        });

        cleanApp_MenuItem.setOnAction((event -> {
            CleanApp.cleanApp();

            LogIn.refresh();
            App app = new App();
            app.start(stage);
        }));

        exit_MenuItem.setOnAction((event) -> Platform.exit());
        exit_MenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        config_Menu.getItems().addAll(changePassword_MenuItem, generateReport_MenuItem, personalProgress_MenuItem, guide_MenuItem, logOut_MenuItem, cleanApp_MenuItem, exit_MenuItem);

        ////////////////////////////////////////////////////////////////////////// Menu "ADMIN"
        admin_Menu = new Menu("ADMIN");

        ////////////////////////////////////////// MenuItems for admin_Menu
        // manageAdmin_MenuItem
        ImageView addAdmin_ImageView = new ImageView(new Image("addAdmin.png"));
        MenuItem addAdmin_MenuItem = new MenuItem("Add", addAdmin_ImageView);

        // manageAdmin_MenuItem
        ImageView modifyAdmin_ImageView = new ImageView(new Image("editUser.png"));
        MenuItem modifyAdmin_MenuItem = new MenuItem("Modify", modifyAdmin_ImageView);

        // manageAdmin_MenuItem
        ImageView viewAdmin_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem viewAdmin_MenuItem = new MenuItem("View", viewAdmin_ImageView);

        addAdmin_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_ADMIN));
        });

        modifyAdmin_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.MODIFY_USER_FORM));
            ModifyUserForm.setRol(2);
            ModifyUserForm.refresh();
        });

        viewAdmin_MenuItem.setOnAction((event) -> {
             MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.VIEW_CUSTOMER_FORM));
        });

        admin_Menu.getItems().addAll(addAdmin_MenuItem, modifyAdmin_MenuItem, viewAdmin_MenuItem);

        ////////////////////////////////////////////////////////////////////////// Menu "CUSTOMER"
        customer_Menu = new Menu("CUSTOMER");

        ////////////////////////////////////////// MenuItems for customer_Menu
        // addCustomer_MenuItem
        ImageView addCustomer_ImageView = new ImageView(new Image("addCustomer.png"));
        MenuItem addCustomer_MenuItem = new MenuItem("Add", addCustomer_ImageView);

        // modifyCustomer_MenuItem
        ImageView modifyCustomer_ImageView = new ImageView(new Image("editUser.png"));
        MenuItem modifyCustomer_MenuItem = new MenuItem("Modify", modifyCustomer_ImageView);

        // viewCustomer_MenuItem
        ImageView viewCustomer_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem viewCustomer_MenuItem = new MenuItem("View", viewCustomer_ImageView);

        addCustomer_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_CUSTOMER_FORM));
        });

        modifyCustomer_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.MODIFY_USER_FORM));
            ModifyUserForm.setRol(3);
            ModifyUserForm.refresh();
        });

        viewCustomer_MenuItem.setOnAction((event) -> {
          
        });

        customer_Menu.getItems().addAll(addCustomer_MenuItem, modifyCustomer_MenuItem, viewCustomer_MenuItem);

        ////////////////////////////////////////////////////////////////////////// Menu "DATES"
        dates_Menu = new Menu("DATES");

        ////////////////////////////////////////// MenuItems for dates_Menu
        // addDate_MenuItem
        ImageView addDate_ImageView = new ImageView(new Image("addDate.png"));
        MenuItem addDate_MenuItem = new MenuItem("Add", addDate_ImageView);

        // modifyDate_MenuItem
        ImageView modifyDate_ImageView = new ImageView(new Image("editDate.png"));
        MenuItem modifyDate_MenuItem = new MenuItem("Modify", modifyDate_ImageView);

        // viewDate_MenuItem
        ImageView viewDate_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem viewDate_MenuItem = new MenuItem("View", viewDate_ImageView);

        // viewDate_MenuItem
        ImageView annotationsDate_ImageView = new ImageView(new Image("annotations.png"));
        annotationsDate_MenuItem = new MenuItem("Annotation", annotationsDate_ImageView);

        addDate_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_DATE_FORM));
            AddDatesForm.refresh();

        });

        modifyDate_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.MODIFY_DATE_FORM));
            ModifyDate.refresh();
        });

        viewDate_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.VIEW_DATES));
            ViewDate.refresh();
        });

        annotationsDate_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_ANNOTATION_FORM));
        });

        dates_Menu.getItems().addAll(addDate_MenuItem, modifyDate_MenuItem, viewDate_MenuItem, annotationsDate_MenuItem);

        validate();

        // Get the Menus in MenuBar
        View_MenuBar.getMenus().addAll(config_Menu, admin_Menu, customer_Menu, dates_Menu);
        View_MenuBar.setMinWidth(2000);

        /// Get the MenuBar in VBox
        MenuBar_VBox.getChildren().addAll(View_MenuBar);

        return MenuBar_VBox;
    }//End VBox()

    @Override
    public Pane getPane() {
        return getMenuVBox();
    }

    private void validate() {
        if (LogIn.getRol() != -1) {
            int rol = LogIn.getRol();
            if (rol == 1) {
                personalProgress_MenuItem.setVisible(false);
                annotationsDate_MenuItem.setVisible(false);
            } else if (rol == 2) {
                personalProgress_MenuItem.setVisible(false);
                cleanApp_MenuItem.setVisible(false);
                admin_Menu.setVisible(false);
            } else if (rol == 3) {
                generateReport_MenuItem.setVisible(false);
                admin_Menu.setVisible(false);
                cleanApp_MenuItem.setVisible(false);
                customer_Menu.setVisible(false);
                annotationsDate_MenuItem.setVisible(false);
            }
        }
    }
}
