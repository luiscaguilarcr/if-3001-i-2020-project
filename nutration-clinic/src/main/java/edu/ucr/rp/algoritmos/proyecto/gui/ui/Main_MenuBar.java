package edu.ucr.rp.algoritmos.proyecto.gui.ui;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneName;
import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
import edu.ucr.rp.algoritmos.proyecto.gui.managepane.MainManagePane;
import edu.ucr.rp.algoritmos.proyecto.gui.ui.util.AddDatesForm;
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

public class Main_MenuBar implements PaneViewer {
    private Stage stage;
    private Menu admin_Menu;
    private Menu dates_Menu;
    private Menu customer_Menu;
    private MenuItem generateReport_MenuItem;
    private MenuItem personalProgress_MenuItem;

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
        ImageView changePasswordInventory_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem changePassword_MenuItem = new MenuItem("Change password", changePasswordInventory_ImageView);

        // generateReport_MenuItem
        ImageView generateReport_ImageView = new ImageView(new Image("seeIcon.png"));
        generateReport_MenuItem = new MenuItem("Generate report", generateReport_ImageView);

        // personalProgress_MenuItem
        ImageView personalProgress_ImageView = new ImageView(new Image("seeIcon.png"));
        personalProgress_MenuItem = new MenuItem("Personal progress", personalProgress_ImageView);

        // logOut_MenuItem
        ImageView logOut_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem logOut_MenuItem = new MenuItem("Log out", logOut_ImageView);

        // exit_MenuItem
        ImageView exit_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem exit_MenuItem = new MenuItem("Exit", exit_ImageView);

        changePassword_MenuItem.setOnAction((event) -> {
        });

        generateReport_MenuItem.setOnAction((event) -> {
        });

        personalProgress_MenuItem.setOnAction((event) -> {
        });

        logOut_MenuItem.setOnAction((event) -> {
        });

        exit_MenuItem.setOnAction((event) -> Platform.exit());
        exit_MenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        config_Menu.getItems().addAll(changePassword_MenuItem, generateReport_MenuItem, personalProgress_MenuItem, logOut_MenuItem, exit_MenuItem);

        ////////////////////////////////////////////////////////////////////////// Menu "ADMIN"
        admin_Menu = new Menu("ADMIN");

        ////////////////////////////////////////// MenuItems for admin_Menu
        // manageAdmin_MenuItem
        ImageView addAdmin_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem addAdmin_MenuItem = new MenuItem("Add", addAdmin_ImageView);

        // manageAdmin_MenuItem
        ImageView modifyAdmin_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem modifyAdmin_MenuItem = new MenuItem("Modify", modifyAdmin_ImageView);

        // manageAdmin_MenuItem
        ImageView viewAdmin_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem viewAdmin_MenuItem = new MenuItem("View", viewAdmin_ImageView);

        addAdmin_MenuItem.setOnAction((event) -> {
            //MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_USER_FORM));
            //AddUserForm.setUserRol(2);
            //ModifyUserForm.setUserRol(2);
            //ViewUserForm.setUserRol(2);
        });

        modifyAdmin_MenuItem.setOnAction((event) -> {
        });

        viewAdmin_MenuItem.setOnAction((event) -> {
        });

        admin_Menu.getItems().addAll(addAdmin_MenuItem, modifyAdmin_MenuItem, viewAdmin_MenuItem);


        ////////////////////////////////////////////////////////////////////////// Menu "CUSTOMER"
        customer_Menu = new Menu("CUSTOMER");

        ////////////////////////////////////////// MenuItems for customer_Menu
        // addCustomer_MenuItem
        ImageView addCustomer_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem addCustomer_MenuItem = new MenuItem("Add", addCustomer_ImageView);

        // modifyCustomer_MenuItem
        ImageView modifyCustomer_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem modifyCustomer_MenuItem = new MenuItem("Modify", modifyCustomer_ImageView);

        // viewCustomer_MenuItem
        ImageView viewCustomer_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem viewCustomer_MenuItem = new MenuItem("View", viewCustomer_ImageView);

        addCustomer_MenuItem.setOnAction((event) -> {
            //MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_USER_FORM));
            //AddUserForm.setUserRol(3);
            //ModifyUserForm.setUserRol(3);
            //ViewUserForm.setUserRol(3);
        });

        modifyCustomer_MenuItem.setOnAction((event) -> {
        });

        viewCustomer_MenuItem.setOnAction((event) -> {
        });

        customer_Menu.getItems().addAll(addCustomer_MenuItem, modifyCustomer_MenuItem, viewCustomer_MenuItem);


        ////////////////////////////////////////////////////////////////////////// Menu "DATES"
        dates_Menu = new Menu("DATES");

        ////////////////////////////////////////// MenuItems for dates_Menu
        // addDate_MenuItem
        ImageView addDate_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem addDate_MenuItem = new MenuItem("Add", addDate_ImageView);

        // modifyDate_MenuItem
        ImageView modifyDate_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem modifyDate_MenuItem = new MenuItem("Modify", modifyDate_ImageView);

        // viewDate_MenuItem
        ImageView viewDate_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem viewDate_MenuItem = new MenuItem("View", viewDate_ImageView);

        addDate_MenuItem.setOnAction((event) -> {
            MainManagePane.setCenterPane(MainManagePane.getPanes().get(PaneName.ADD_DATE_FORM));
            AddDatesForm.refresh();
        });

        modifyDate_MenuItem.setOnAction((event) -> {
        });

        viewDate_MenuItem.setOnAction((event) -> {
        });

        dates_Menu.getItems().addAll(addDate_MenuItem, modifyDate_MenuItem, viewDate_MenuItem);

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
                dates_Menu.setVisible(false);
            } else if (rol == 2) {
                personalProgress_MenuItem.setVisible(false);
                admin_Menu.setVisible(false);
            } else if (rol == 3) {
                generateReport_MenuItem.setVisible(false);
                admin_Menu.setVisible(false);
                customer_Menu.setVisible(false);
            }
        }
    }
}
