package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import org.junit.Test;

public class UserServiceTest {
    private static UserService userService;
    Utility utility = new Utility();

    @Test
    public void testAddition() {
        userService = UserService.getInstance();
        User user = utility.randomUser();

        /*User user = new User();
        user.setPassword("admin");
        user.setName("admin");
        user.setiD(123);
        user.setRol(1);
        user.setPhoneNumber(0000);
        user.setAddress("");
        user.setEmail("");*/

        if (userService.add(user)) {
            System.out.println("Se creó");
        } else {
            System.out.println("No se creó");
        }
    }

    @Test
    public void testDelete() {
        userService = UserService.getInstance();
        User user = userService.getById(123);
        User tempUser = user;
        if (userService.remove(user)) {
            System.out.println("Se borró el usuario " + user.getName() + " ID: " + user.getiD());
        } else {
            System.out.println("No se borró");
        }
    }

    @Test //SI FUNCIONA
    public void testEdit() {
        utility = new Utility();
        userService = UserService.getInstance();
        User oldUser = userService.getById(123);
        if (oldUser != null) {
            User newUser = oldUser;
            newUser.setPassword(utility.encrypt("adminn"));

            if (userService.edit(oldUser, newUser)) {
                System.out.println("Se editó");
            } else {
                System.out.println("No se editó");
            }
        }
    }

    @Test //SI FUNCIONA
    public void getByID() {
        userService = UserService.getInstance();
        User user = userService.getById(123);
        System.out.println(user.getName());
        System.out.println(user.getPassword());
    }

}
