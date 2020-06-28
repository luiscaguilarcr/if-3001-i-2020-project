package edu.ucr.rp.algoritmos.proyecto.util.test;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.*;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AnnotationService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.EatingPlanService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
        String[] name = {"Carlitos Aguilar", "Braulio Carrillo", "Andre Turrio", "Hern Olivar", "Rosa Marquez", "Lolo Quesada", "Fran Mars", "Yeiny Misun"};
        user.setName(name[random(name.length)]+random(10000000));
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
        int valorDado = r.nextInt(4) + 2;
        if (valorDado == 4) {
            valorDado -= 2;
        } else if (valorDado == 5) {
            valorDado -= 2;
        }

        return valorDado;
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
    private String[] carbohydrates = {"pan", "cereal", "arroz", "galletas", "leche", "papa", "maíz", "frijoles", "ponche de frutas", "yogur", "jugo"};
    private String[] fruits = {"aguacate", "ananá", "banana ", "arándano", "cereza ", "ciruela", "coco", "durazno ", "frambuesa", "fresa ", "Jugo", "granada"};
    String carbohydratesList = "";
    String fruitsList = "";

    public EatingPlan generateEatingPlan() {
        initializeService();
        EatingPlan eatingPlan = new EatingPlan();
        EatingPlanService eatingPlanService = EatingPlanService.getInstance();
        eatingPlan.setID(eatingPlanService.getAll().size() + 1);

        for (int i = 0; i < 3; i++) {
            carbohydratesList += carbohydrates[random(carbohydrates.length)] + ", ";
            fruitsList += fruits[random(fruits.length)] + ", ";
        }
        eatingPlan.setCarbohydrates(carbohydratesList);
        eatingPlan.setFruits(fruitsList);
        eatingPlan.setFat(random(30));
        return eatingPlan;
    }

    ////////////////////////////////////////////////////////////// GENERADOR DISPONIBILIDAD DE ADMIN
    AdminAvailability adminAvailability;

    public void update() {
        adminAvailability = new AdminAvailability();
    }

    public AdminAvailability generateAdminAvailability(int adminID) {
        update();
        generateAdminAvailableDayAndHour();
        adminAvailability.setAdminID(adminID);
        return adminAvailability;
    }

    public void generateAdminAvailableDayAndHour() {
        List<String> hours = new ArrayList<>();
        for (int i = 8; i < 18; i++) {
            hours.add(i + ":00");
        }
        Map<String, List> availability = new HashMap<>();
        LocalDate localDate = LocalDate.now();

        for (int i = 0; i < 6; i++) {
            availability.put(localDate.getDayOfMonth() + i + "/" + localDate.getMonthValue() + "/" + localDate.getYear(), hours);
        }

        adminAvailability.setAdminAvailability(availability);
    }

    ////////////////////////////////////////////////////////////// GENERADOR ANOTACIONES DE ADMIN EN CITA
    AdminAnnotation adminAnnotation;

    private void refresh() {
        adminAnnotation = new AdminAnnotation();
    }

    public AdminAnnotation generateAnnotations(CustomerDate customerDate) {
        refresh();
        EatingPlanService eatingPlanService = EatingPlanService.getInstance();
        adminAnnotation.setDate(customerDate.getDate());
        adminAnnotation.setCustomerID(customerDate.getCustomerID());
        adminAnnotation.setDocID(customerDate.getAdminID());
        EatingPlan eatingPlan = eatingPlanService.getByID(5);
        adminAnnotation.setEatingPlan(eatingPlan);
        generateRandomAnnotations();
        return adminAnnotation;
    }

    public void generateRandomAnnotations() {
        Object[] fat = {"16%","17%","18%","19%","20%","21%","22%","23%","24%"};
        Object[] weight = {"50 kg","55 kg","60 kg","65 kg","70 kg","75 kg","80 kg","85 kg","90 kg"};
        Object[] height = {"110 cm","115 cm","120 cm","125 cm","130 cm","135 cm","140 cm", "145 cm", "150 cm"};
        int randomNumber = random(fat.length);
        adminAnnotation.setFat(fat[randomNumber]);
        adminAnnotation.setWeight(weight[randomNumber]);
        adminAnnotation.setHeight(height[randomNumber]);
    }
}
