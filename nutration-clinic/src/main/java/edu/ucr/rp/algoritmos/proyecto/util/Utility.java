package edu.ucr.rp.algoritmos.proyecto.util;

import edu.ucr.rp.algoritmos.proyecto.domain.User;

public class Utility {
    public static User user;

    public static User randomUser() {
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

    public static void generateUserName() {
        String[] list = {"Carlitos Aguilar", "Braulio Carrillo", "Andrés Turrio", "Hernán Olivar", "Rosa Marquez"};
        user.setName(list[random(list.length) - 1]);
    }

    public static void generateEmail() {
        user.setEmail(user.getName() + "123@gmail.com");
    }

    public static void generatePassword() {
        user.setPassword(user.getName() + "123321");
    }

    public static void generateAddress() {
        user.setAddress("25 mts al O, casa #" + random(100) + " a nombre de " + user.getName());
    }

    public static void generatePhoneNumber() {
        int phoneNumber = 0;
        for (int i = 0; i < 4; i++) {
            phoneNumber += random(101);
        }
        user.setPhoneNumber(phoneNumber);
    }

    public static void generateID() {
        int iD = 0;
        for (int i = 0; i < 4; i++) {
            iD += random(101);
        }
        user.setiD(iD);
    }

    public static void generateRol(){
        int rol = random(3);
        user.setRol(rol);
    }

    public static int random(int bound) {
        return 1 + (int) Math.floor(Math.random() * bound);
    }
}
