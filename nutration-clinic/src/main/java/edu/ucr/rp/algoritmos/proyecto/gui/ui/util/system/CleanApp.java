package edu.ucr.rp.algoritmos.proyecto.gui.ui.util.system;

import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;

public class CleanApp {
    public static void cleanApp() {
        IOUtility ioUtility = new IOUtility();
        ioUtility.deleteAppDir();
    }
}
