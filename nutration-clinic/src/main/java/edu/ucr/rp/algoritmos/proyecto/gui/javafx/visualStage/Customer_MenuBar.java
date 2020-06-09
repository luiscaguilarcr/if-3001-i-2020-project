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

public class Customer_MenuBar implements PaneViewer {
    private Stage stage;

    public Customer_MenuBar(Stage stage) {
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

        // PersonalProgress_MenuItem
        ImageView PersonalProgress_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem PersonalProgress_MenuItem = new MenuItem("Personal progress", PersonalProgress_ImageView);

        // LogOut_MenuItem
        ImageView LogOut_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem LogOut_MenuItem = new MenuItem("Log out", LogOut_ImageView);

        // Exit_MenuItem
        ImageView Exit_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem Exit_MenuItem = new MenuItem("Exit", Exit_ImageView);

        ChangePassword_MenuItem.setOnAction((event) -> {
        });

        PersonalProgress_MenuItem.setOnAction((event)->{
        });

        LogOut_MenuItem.setOnAction((event) -> {
        });

        Exit_MenuItem.setOnAction((event) -> Platform.exit());
        Exit_MenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));

        Config_Menu.getItems().addAll(ChangePassword_MenuItem, PersonalProgress_MenuItem, LogOut_MenuItem, Exit_MenuItem);


        ////////////////////////////////////////////////////////////////////////// Menu "DATES"
        Menu Dates_Menu = new Menu("DATES");

        ////////////////////////////////////////// MenuItems for Dates_Menu
        // ManageCustomer_MenuItem
        ImageView ManageDates_ImageView = new ImageView(new Image("seeIcon.png"));
        MenuItem ManageCustomer_SubMenu = new MenuItem("Manage dates", ManageDates_ImageView);

        Dates_Menu.getItems().addAll(ManageCustomer_SubMenu);

        // Get the Menus in MenuBar
        View_MenuBar.getMenus().addAll(Config_Menu, Dates_Menu);
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
