package edu.ucr.rp.algoritmos.proyecto.util;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;

public class Utility {
    public static User user;

    public User randomUser() {
        user = new User();
        generateUserName();
        generateEmail();
        generatePassword();
        generateAddress();
        generatePhoneNumber();
        generateID();
        generateRol();
        return user;
    }

    public void generateUserName() {
        String[] list = {"Carlitos Aguilar", "Braulio Carrillo", "Andrés Turrio", "Hernán Olivar", "Rosa Marquez"};
        user.setName(list[random(list.length) - 1]);
    }

    public void generateEmail() {
        user.setEmail(user.getName() + "123@gmail.com");
    }

    public void generatePassword() {
        user.setPassword(encrypt(user.getName() + "123"));
    }

    public void generateAddress() {
        user.setAddress("25 mts al O, casa #" + random(100) + " a nombre de " + user.getName());
    }

    public void generatePhoneNumber() {
        int phoneNumber = 0;
        for (int i = 0; i < 4; i++) {
            phoneNumber += random(101);
        }
        user.setPhoneNumber(phoneNumber);
    }

    public void generateID() {
        int iD = 0;
        for (int i = 0; i < 4; i++) {
            iD += random(101);
        }
        user.setiD(iD);
    }

    public void generateRol(){
        int rol = random(3);
        user.setRol(rol);
    }

    public int random(int bound) {
        return 1 + (int) Math.floor(Math.random() * bound);
    }

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
