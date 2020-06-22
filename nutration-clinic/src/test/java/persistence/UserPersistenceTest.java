package persistence;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.logic.persistance.implementation.UserPersistence;
import edu.ucr.rp.algoritmos.proyecto.util.TestUtility;
import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;
import logic.interfaces.TestPersistence;
import org.junit.Test;

public class UserPersistenceTest implements TestPersistence {
    UserPersistence userPersistence;
    private static UserLinkedList userLinkedList;
    TestUtility testUtility = new TestUtility();
    IOUtility ioUtility = new IOUtility();
    @Test
    public void testAddition() {
        userPersistence = new UserPersistence();
        UserLinkedList tempUserLinkedList = new UserLinkedList();
        if (userPersistence.read() != null) {
            userLinkedList = userPersistence.read();
            for (int i = 0; i < userLinkedList.size(); i++) {
                tempUserLinkedList.add(userLinkedList.get(i));
            }
            User user = testUtility.randomUser();
            tempUserLinkedList.add(user);

            if (userPersistence.write(tempUserLinkedList)) {
                System.out.println("Se guardó");
                System.out.println("Tamaño " + tempUserLinkedList.size());
            } else {
                System.out.println("No se guardó");
            }
        }

    }

    @Override
    @Test
    public void testAddition2() {
        ioUtility.verifyAppDir();
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
            user.setID(123);
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

    @Override
    @Test
    public void test() {
        IOUtility ioUtility = new IOUtility();
        //ioUtility.deleteAppDir();
        ioUtility.verifyAppDir();
    }
}
