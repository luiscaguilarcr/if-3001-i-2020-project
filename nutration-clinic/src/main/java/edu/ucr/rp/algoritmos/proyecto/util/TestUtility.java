package edu.ucr.rp.algoritmos.proyecto.util;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;

import java.time.LocalDateTime;
import java.util.Random;

public class TestUtility {
    /////////////////////////////////////////////////// TEST USER
    public static User user;
    Utility utility = new Utility();

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
        user.setName(list[random(list.length)]);
    }

    public void generateEmail() {
        user.setEmail(user.getName() + "123@gmail.com");
    }

    public void generatePassword() {
        user.setPassword(utility.encrypt(user.getName() + "123"));
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
        user.setID(iD);
    }

    public void generateRol() {
        int rol = randomRol();
        user.setRol(rol);
    }

    public int random(int bound) {
        return (int) Math.floor(Math.random() * bound);
    }

    public int randomRol() {
        Random r = new Random();
        int valorDado = r.nextInt(4)+2;
        if(valorDado == 4){
            valorDado -= 2;
        }else if(valorDado == 5){
            valorDado -= 2;
        }

        return  valorDado;
    }

    /////////////////////////////////////////////////// TEST CUSTOMER DATE
    private CustomerDate customerDate;
    private int customerSetID = 0;
    UserService userService;
    DateService dateService;
    UserLinkedList userLinkedList;

    private void initializeService() {
        userService = UserService.getInstance();
        dateService = DateService.getInstance();
        userLinkedList = userService.getAll();
    }

    public boolean validate() {
        initializeService();
        if (userService.getAll() != null) {
            userLinkedList = userService.getAll();
            return true;
        }
        return false;
    }

    public CustomerDate randomDate() {
        initializeService();
        if (validate()) {
            customerDate = new CustomerDate();
            setID();
            //customerDate.setEatingPlanID();
            setDateAndHour();

            return customerDate;
        }
        return null;
    }

    private void setID() {
        initializeService();
        int iD = userService.getAll().get(random(userLinkedList.size())).getID();
        int rol = 0;
        boolean customerID = true;
        boolean adminID = true;
        int accountant = 0;

        while (accountant != 2) {
            rol = userService.getByID(iD).getRol();
            if (rol == 2) {
                if (adminID) {
                    customerDate.setAdminID(iD);
                    adminID = false;
                    accountant++;
                }
            } else if (rol == 3) {
                if (customerID) {
                    customerDate.setCustomerID(iD);
                    customerID = false;
                    accountant++;
                    customerSetID = iD;
                }
            }
            if (accountant != 2) {
                iD = userService.getAll().get(random(userLinkedList.size())).getID();
            }
        }
    }

    public void setDateAndHour() {
        initializeService();
        /*DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        dtf.format(now)*/

        LocalDateTime now = LocalDateTime.now();
        String date = now.getDayOfMonth() + "/" + now.getMonth() + "/" + now.getYear();
        customerDate.setDate(date);

        String hour = now.getHour() + ":" + now.getMinute() + ":" + now.getSecond();
        customerDate.setHour(hour);
    }

    ////////////////////////////////////////////////////////////// GENERADOR DE PLANES DE COMIDA
    private String[] carbohydrates = {"pan" + "cereal" + "arroz" + "galletas" + "leche" + "papa" + "maíz" + "frijoles" + "ponche de frutas" + "yogur" + "jugo"};
    private String[] fruits = {"aguacate" + "ananá" + "banana " + "arándano" + "cereza " + "ciruela" + "coco" + "durazno " + "frambuesa" + "fresa " + "Jugo" + "granada"};
    String carbohydratesList = "";
    String fruitsList = "";

    public void generateEatingPlan() {
        initializeService();
        EatingPlan eatingPlan = new EatingPlan();
        eatingPlan.setID(customerSetID);

        for (int i = 0; i < 3; i++) {
            if (i == 2) {
                carbohydratesList += carbohydrates[random(carbohydrates.length)];
                fruitsList += fruits[random(fruits.length)];
            } else {
                carbohydratesList += carbohydrates[random(carbohydrates.length)] + ",";
                fruitsList += fruits[random(fruits.length)] + ",";
            }
        }
        eatingPlan.setCarbohydrates(carbohydratesList);
        eatingPlan.setFruits(fruitsList);
        eatingPlan.setFat(random(30));
    }
}
