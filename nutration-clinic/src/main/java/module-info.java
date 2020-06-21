module inventory.system {
    requires javafx.controls;
    requires javafx.base;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires commons.io;
    requires org.controlsfx.controls;
    requires java.desktop;
    exports edu.ucr.rp.algoritmos.proyecto.logic.domain to com.fasterxml.jackson.databind;
    exports edu.ucr.rp.algoritmos.proyecto.util to com.fasterxml.jackson.databind;
    exports edu.ucr.rp.algoritmos.proyecto.logic.service.implementation to com.fasterxml.jackson.databind;
    exports edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation to com.fasterxml.jackson.databind;
    exports edu.ucr.rp.algoritmos.proyecto.gui to javafx.graphics;
    opens edu.ucr.rp.algoritmos.proyecto.logic.domain to javafx.base;
}