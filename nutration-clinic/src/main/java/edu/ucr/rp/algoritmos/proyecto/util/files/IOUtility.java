package edu.ucr.rp.algoritmos.proyecto.util.files;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class IOUtility {
    public static String getFileAppSize()
    {
        File file =new File("/");

        if(file.exists())
        {
            double bytes = file.length();
            double kilobytes = (bytes / 1024);
            double megabytes = (kilobytes / 1024);
            double gigabytes = (megabytes / 1024);

            if (kilobytes < 1)
                return  String.valueOf(bytes) + " bytes.";
            else if (megabytes < 1)
                return String.valueOf(kilobytes) + " kB.";
            else if (gigabytes < 1)
                return String.valueOf(megabytes) + " MG.";
            else
                return String.valueOf(gigabytes) + " GB.";
        }
        else
        {
            return "Error";
        }
    }

    /**
     * Verifica si el directorio principal existe, si no, lo crea con un usuario SuperAdmin predeterminado.
     */
    public void verifyAppDir(){
        // Validate files/
        UserService userService = UserService.getInstance();
        File root = new File("files/");
        Utility utility = new Utility();
        if(!root.exists()) {
            root.mkdir();
            User superAdmin = new User();
            superAdmin.setID(1234567890);
            superAdmin.setName("admin");
            superAdmin.setPassword(utility.encrypt("admin"));
            superAdmin.setRol(1);
            userService.add(superAdmin);
        }
    }

    /**
     * Vacía la información guardada en la aplicación por completo.
     */
    public boolean deleteAppDir(){
        // Validate files/
            try {
                FileUtils.forceDelete(new File("files/"));
                return true;
            }catch (IOException e){
                return false;
            }

    }
}
