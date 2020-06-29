package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAvailability;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.User;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAvailabilityGeneralService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.UserService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.UserLinkedList;
import edu.ucr.rp.algoritmos.proyecto.util.Utility;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import logic.interfaces.TestService;
import org.junit.Test;

import java.util.List;

public class AdminAvailabilityDateServiceTest implements TestService {
    AdminAvailabilityGeneralService adminAvailabilityService;
    TestUtility testUtility;

    private void refresh() {
        adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        testUtility = new TestUtility();
    }

    @Test
    @Override
    public void testAddition() {
        refresh();
        UserService userService = UserService.getInstance();
        if (userService.getAll() != null) {
            UserLinkedList userLinkedList = userService.getAll();
            for (int i = 0; i < userLinkedList.size(); i++) {
                User user = userLinkedList.get(i);
                AdminAvailabilityGeneralService adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
                if (user.getRol() == 3) {
                    if (adminAvailabilityService.getByID(user.getID()) == null) {
                        AdminAvailability adminAvailability = testUtility.generateAdminAvailability(user.getID());
                        if (adminAvailabilityService.add(adminAvailability)) {
                            System.out.println("Se agregó la disponibilidad de " + userLinkedList.get(i).getID());
                        } else {
                            System.out.println("ERROR, no se agregó la disponibilidad de " + userLinkedList.get(i).getID());
                        }
                    }else {
                        System.out.println("ERROR, YA EXISTE");
                    }
                }
            }
        }

    }

    @Test
    @Override
    public void testDelete() {
        refresh();
        AdminAvailabilityGeneralService adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        AdminAvailability adminAvailability = adminAvailabilityService.getAll().get(0);

        if (adminAvailabilityService.remove(adminAvailability)) {
            System.out.println("Se removió");
        } else {
            System.out.println("No se removió");
        }
    }

    @Test
    @Override
    public void testEdit() {
        AdminAvailabilityGeneralService adminAvailabilityService = AdminAvailabilityGeneralService.getInstance();
        AdminAvailability adminAvailability = adminAvailabilityService.getAll().get(0);
        AdminAvailability adminAvailability1 = adminAvailability;
        String date = "32/JUNE/2020";
        String hour = "10:00";
        List list = adminAvailability1.getAdminAvailability().get(date);
        //list.remove(hour);
        list.add(hour);
        System.out.println(list.contains(hour));
        if (adminAvailabilityService.edit(adminAvailability, adminAvailability1)) {
            System.out.println("Se editó");
        } else {
            System.out.println("NO se editó");
        }
    }

    @Override
    public void getByCustomerID() {
    }

    @Override
    public void getByDoctorID() {
    }

    @Test
    @Override
    public void test() {
        TestUtility testUtility = new TestUtility();
        testUtility.generateAdminAvailableDayAndHour2();
    }
}
