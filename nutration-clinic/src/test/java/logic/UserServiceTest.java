package logic;

import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import org.junit.Test;

public class UserServiceTest {
    private static UserService userService;

    @Test
    public void testAddition() {
        userService = UserService.getInstance();
        User user = Utility.randomUser();
        if(userService.add(user)){
            System.out.println("Se creó");
        }else {
            System.out.println("No se creó");
        }
    }

    @Test
    public void testDelete() {
        userService = UserService.getInstance();
        User user = userService.getAll().get(1);
        User tempUser = user;
        if(userService.remove(user)){
            System.out.println("Se borró el usuario "+user.getName()+ " ID: "+user.getiD());
        }else {
            System.out.println("No se borró");
        }
    }

    @Test //SI FUNCIONA
    public void testEdit() {
        userService = UserService.getInstance();
        User oldUser = userService.getAll().get(1);
        User newUser = oldUser;
        newUser.setName("ojo al tejo");
        if(userService.edit(oldUser, newUser)){
            System.out.println("Se editó");
        }else {
            System.out.println("No se editó");
        }
    }

    @Test //SI FUNCIONA
    public void getByID() {
        userService = UserService.getInstance();
        User user = userService.getById(205);
        System.out.println(user.getName());
    }

}
