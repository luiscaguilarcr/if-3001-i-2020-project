package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;
import logic.interfaces.TestService;
import org.junit.Test;

public class UserServiceTest implements TestService {
    private static UserService userService;
    TestUtility testUtility = new TestUtility();
    Utility utility = new Utility();

    @Test
    public void testAddition() {
        IOUtility ioUtility = new IOUtility();
        ioUtility.verifyAppDir();
        for (int i = 0; i < 20; i++) {
            userService = UserService.getInstance();
            User user = testUtility.randomUser();
            if (userService.add(user)) {
                System.out.println("Se creó");
            } else {
                System.out.println("No se creó");
            }
        }
    }

    @Test
    public void testDelete() {
        userService = UserService.getInstance();
        User user = userService.getByID(123);
        User tempUser = user;
        if (userService.remove(user)) {
            System.out.println("Se borró el usuario " + user.getName() + " ID: " + user.getID());
        } else {
            System.out.println("No se borró");
        }
    }

    @Test
    public void testEdit() {
        testUtility = new TestUtility();
        userService = UserService.getInstance();
        User oldUser = userService.getByID(123);
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

    @Test
    public void getByCustomerID() {
        userService = UserService.getInstance();
        User user = userService.getByID(123);
        System.out.println(user.getName());
        System.out.println(user.getPassword());
    }

    @Override
    public void getByDoctorID() {
    }

    @Override
    public void test() {
    }

}
