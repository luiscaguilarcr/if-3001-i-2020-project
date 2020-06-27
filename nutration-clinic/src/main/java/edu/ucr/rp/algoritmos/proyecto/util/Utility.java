package edu.ucr.rp.algoritmos.proyecto.util;

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
}
