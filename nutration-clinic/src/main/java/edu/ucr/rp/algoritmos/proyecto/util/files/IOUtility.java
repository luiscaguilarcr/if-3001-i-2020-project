package edu.ucr.rp.algoritmos.proyecto.util.files;

import java.io.File;

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

    //  Methods  \\
    public static void verifyUsersDir(){
        // Validate files/
        File root = new File("files/");
        if(!root.exists()) {
            root.mkdir();
        }
    }
}
