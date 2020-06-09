package edu.ucr.rp.algoritmos.proyecto.gui.javafx.visualStage;

import edu.ucr.rp.algoritmos.proyecto.gui.model.PaneViewer;
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

public class SuperAdmin_MenuBar implements PaneViewer {
    private Stage stage;

    public SuperAdmin_MenuBar(Stage stage) {
        this.stage = stage;
    }

    public VBox getMenuVBox() {
        VBox MenuBar_VBox = new VBox();

        MenuBar View_MenuBar = new MenuBar();

        ////////////////////////////////////////////////////////////////////////// Menu "CONFIG"
        Menu Config_Menu = new Menu("CONFIG");

        ////////////////////////////////////////// MenuItems for Config_Menu
        // ChangePassword_MenuItem
        ImageView ChangePasswordInventory_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem ChangePassword_MenuItem = new MenuItem("Change password", ChangePasswordInventory_ImageView);

        // GenerateReport_MenuItem
        ImageView GenerateReport_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem GenerateReport_MenuItem = new MenuItem("Generate report", GenerateReport_ImageView);

        // LogOut_MenuItem
        ImageView LogOut_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem LogOut_MenuItem = new MenuItem("Log out", LogOut_ImageView);

        // Exit_MenuItem
        ImageView Exit_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem Exit_MenuItem = new MenuItem("Exit", Exit_ImageView);

        ChangePassword_MenuItem.setOnAction((event) -> {
        });

        GenerateReport_MenuItem.setOnAction((event)->{
        });

        LogOut_MenuItem.setOnAction((event) -> {
        });

        Exit_MenuItem.setOnAction((event) -> Platform.exit());
        Exit_MenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        Config_Menu.getItems().addAll(ChangePassword_MenuItem, GenerateReport_MenuItem, LogOut_MenuItem, Exit_MenuItem);


        ////////////////////////////////////////////////////////////////////////// Menu "ADMIN"
        Menu Admin_Menu = new Menu("ADMIN");

        ////////////////////////////////////////// MenuItems for Admin_Menu
        // ManageAdmin_MenuItem
        ImageView ManageAdmin_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem ManageAdmin_MenuItem = new MenuItem("Manage admin", ManageAdmin_ImageView);

        ManageAdmin_MenuItem.setOnAction((event) -> {
        });

        Admin_Menu.getItems().addAll(ManageAdmin_MenuItem);


        ////////////////////////////////////////////////////////////////////////// Menu "CUSTOMER"
        Menu Customer_Menu = new Menu("CUSTOMER");

        ////////////////////////////////////////// MenuItems for Admin_Menu
        // ManageCustomer_MenuItem
        ImageView ManageCustomer_ImageView = new ImageView(new Image("seeIcon.png"));
        Menu ManageCustomer_SubMenu = new Menu("Manage customer", ManageCustomer_ImageView);

        // ManageCustomer_MenuItem
        ImageView Customers_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem Customers_MenuItem = new MenuItem("Customers", Customers_ImageView);

        // ManageCustomer_MenuItem
        ImageView Dates_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem Dates_MenuItem = new MenuItem("Dates", Dates_ImageView);

        ManageCustomer_SubMenu.getItems().addAll(Customers_MenuItem, Dates_MenuItem);

        Customer_Menu.getItems().addAll(ManageCustomer_SubMenu);

        // Get the Menus in MenuBar
        View_MenuBar.getMenus().addAll(Config_Menu, Admin_Menu, Customer_Menu);
        View_MenuBar.setMinWidth(2000);

        /// Get the MenuBar in VBox
        MenuBar_VBox.getChildren().addAll(View_MenuBar);

        return MenuBar_VBox;
    }//End VBox()

    @Override
    public Pane getPane() {
        return getMenuVBox();
    }
}
