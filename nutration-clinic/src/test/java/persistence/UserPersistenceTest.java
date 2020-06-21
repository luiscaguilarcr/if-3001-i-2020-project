package persistence;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;
import org.junit.Test;

public class UserPersistenceTest {
    UserPersistence userPersistence;
    private static UserLinkedList userLinkedList;
    Utility utility = new Utility();

    @Test
    public void testAddition() {
        IOUtility.verifyUsersDir();
        userPersistence = new UserPersistence();
        UserLinkedList tempUserLinkedList = new UserLinkedList();
        if (userPersistence.read() != null) {
            userLinkedList = userPersistence.read();
            for (int i = 0; i < userLinkedList.size(); i++) {
                tempUserLinkedList.add(userLinkedList.get(i));
            }
            User user = utility.randomUser();
            tempUserLinkedList.add(user);

            if (userPersistence.write(tempUserLinkedList)) {
                System.out.println("Se guardó");
                System.out.println("Tamaño " + tempUserLinkedList.size());
            } else {
                System.out.println("No se guardó");
            }
        }

    }

    @Test
    public void testAddSuperAdmin() {
        IOUtility.verifyUsersDir();
        userPersistence = new UserPersistence();
        UserLinkedList tempUserLinkedList = new UserLinkedList();
        if (userPersistence.read() != null) {
            userLinkedList = userPersistence.read();
            for (int i = 0; i < userLinkedList.size(); i++) {
                tempUserLinkedList.add(userLinkedList.get(i));
            }
            User user = new User();
            user.setPassword("admin");
            user.setName("admin");
            user.setiD(123);
            user.setRol(1);
            user.setPhoneNumber(0000);
            user.setAddress("");
            user.setEmail("");
            tempUserLinkedList.add(user);
        }
        if (userPersistence.write(tempUserLinkedList)) {
            System.out.println("Se guardó");
            System.out.println("Tamaño " + tempUserLinkedList.size());
        } else {
            System.out.println("No se guardó");
        }
    }

    @Test
    public void removeAll() {
        userPersistence = new UserPersistence();
        if (userPersistence.deleteAll())
            System.out.println("Se eliminó");
        else
            System.out.println("No se eliminó");
    }

    @Test
    public void testGet() {
        userPersistence = new UserPersistence();
        UserLinkedList userLinkedList = userPersistence.read();
        System.out.println(userLinkedList.get(0).getPhoneNumber());
    }
}
