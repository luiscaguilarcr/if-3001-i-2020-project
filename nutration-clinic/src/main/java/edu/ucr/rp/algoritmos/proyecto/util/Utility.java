package edu.ucr.rp.algoritmos.proyecto.util;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.HistoryApp;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
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
        DateService dateService = DateService.getInstance();
        if (iD == 3) {
            if (dateService.getByID(iD) != null) {
                dateService.remove(dateService.getByID(iD));
            }
        }
    }
}
