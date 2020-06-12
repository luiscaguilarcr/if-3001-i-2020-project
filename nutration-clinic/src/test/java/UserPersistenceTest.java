import edu.ucr.rp.algoritmos.proyecto.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import org.junit.Test;

public class UserPersistenceTest {
    UserService userService = new UserService();
    private User user = new User();

    @Test
    public void testAddition(){
        user.setName("Lolo Andrés");
        user.setPassword("12345");
        user.setEmail("lolo11@gmail.com");
        user.setAddress("Cartago");
        user.setiD(305290615);
        user.setPhoneNumber(45646564);
        user.setRol(1);

        if(userService.add(user)){
            System.out.println("se agregó el usuario "+ user.getName());
        }else {
            System.out.println("no se agregó el usuario"+ user.getName());
        }
    }
}
