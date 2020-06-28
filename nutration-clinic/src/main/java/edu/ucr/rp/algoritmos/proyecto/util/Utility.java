package edu.ucr.rp.algoritmos.proyecto.util;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.HistoryAppService;

import java.time.LocalDateTime;

public class Utility {
    /**
     * Encripta una contraseña
     * @param password que se quiere encriptar
     * @return contraseña encriptada
     */
    public String encrypt(String password) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");

            byte[] array = md.digest(password.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException me) {
            return null;
        }
    }

    public void historyApp(String action) { //TODO test
        HistoryAppService historyAppService = HistoryAppService.getInstance();
        LocalDateTime now = LocalDateTime.now();
        //int userActionID = LogIn.getUser().getID();
        int userActionID = 777;
        HistoryApp historyApp = new HistoryApp();
        historyApp.setInfo(action);
        historyApp.setDate(now.getDayOfMonth() + "/" + now.getMonth() + "/" + now.getYear());
        historyApp.setHour(now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        historyApp.setUserID(userActionID);
        historyAppService.add(historyApp);
    }

    public void removeCustomerDate(int iD) { //TODO test
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        if (iD == 3) {
            if (customerDateService.getByID(iD) != null) {
                customerDateService.remove(customerDateService.getByID(iD));
            }
        }
    }

    public static String instanceOf(Object a, Object b){
        if(a instanceof Integer&&b instanceof Integer) return "integer";
        if(a instanceof String&&b instanceof String) return "string";
        if(a instanceof Character&&b instanceof Character) return "character";
        if(a instanceof AdminAnnotation&&b instanceof AdminAnnotation) return "adminAnnotation";
        return "unknown"; //desconocido
    }

    public static boolean equals(Object a, Object b){
        switch(instanceOf(a, b)){
            case "integer":
                Integer x=(Integer) a; Integer y=(Integer) b;
                return x==y;
            case "string":
                String v=(String) a; String w=(String) b;
                return v.compareToIgnoreCase(w)==0;
            case "character":
                Character c=(Character) a; Character d=(Character) b;
                return c.compareTo(d)==0;
            case "adminAnnotation":
                AdminAnnotation pa=(AdminAnnotation)a; AdminAnnotation pb=(AdminAnnotation)b;
                return pa.getCustomerID()== pb.getCustomerID();
        }
        return false; //en cualquier otro caso
    }

    public static int random(int bound){
        return 1+(int) Math.floor(Math.random()*bound);
    }

    //less than (menorQ)
    public static boolean lessT(Object a, Object b){
        switch(instanceOf(a, b)){
            case "integer":
                Integer x=(Integer) a; Integer y=(Integer) b;
                return x<y;
            case "string":
                String v=(String) a; String w=(String) b;
                return v.compareToIgnoreCase(w)<0;
        }
        return false; //en cualquier otro caso
    }

    //greater than (mayorQ)
    public static boolean greaterT(Object a, Object b){
        switch(instanceOf(a, b)){
            case "integer":
                Integer x=(Integer) a; Integer y=(Integer) b;
                return x>y;
            case "string":
                String v=(String) a; String w=(String) b;
                return v.compareToIgnoreCase(w)>0;
        }
        return false; //en cualquier otro caso
    }
}
