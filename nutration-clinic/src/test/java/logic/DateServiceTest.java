package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import logic.interfaces.TestService;
import org.junit.Test;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;

import java.util.List;

public class DateServiceTest implements TestService {
    private static DateService dateService = DateService.getInstance();
    TestUtility testUtility = new TestUtility();

    @Test
    @Override
    public void testAddition() {
        CustomerDate customerDate = testUtility.generateDate("30/6/2020", "11:00", 149);
        if (customerDate != null) {
            if (dateService.add(customerDate)) {
                System.out.println("DATE ADDED");
            } else {
                System.out.println("ERROR WHEN ADDING");
            }
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void testDelete() {
        DateService dateService = DateService.getInstance();
        if (dateService.getAll() != null) {
            CustomerDate customerDate = dateService.getByID(261);
            if (dateService.remove(customerDate)) {
                System.out.println("DATE DELETED");
            } else {
                System.out.println("ERROR WHEN DELETING");
            }
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void testEdit() {
        DateService dateService = DateService.getInstance();
        CustomerDate oldCustomerDate = dateService.getByID(129);

        CustomerDate newCustomerDate = new CustomerDate();
        newCustomerDate.setAdminID(184);
        newCustomerDate.setCustomerID(oldCustomerDate.getCustomerID());
        newCustomerDate.setDate("27/6/2020");
        newCustomerDate.setHour("17:00");

        if (dateService.edit(oldCustomerDate, newCustomerDate)) {
            System.out.println("Old user:" + oldCustomerDate.getHour());
            System.out.println("Edited user: " + newCustomerDate.getHour());
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void getByCustomerID() {
        dateService = DateService.getInstance();
        if (dateService.getByID(231) != null) {
            CustomerDate customerDate = dateService.getByID(231);
            System.out.println(customerDate.getCustomerID());
        } else {
            System.out.println("ERROR");
        }

        if (dateService.getByID(231) != null) {
            CustomerDate customerDate = dateService.getByID(231);
            System.out.println(customerDate.getCustomerID());
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void getByDoctorID() {
        if (dateService.getDatesByAdminID(184) != null) {
            List customerDateStack = dateService.getDatesByAdminID(184);
            for (int i = 0; i < customerDateStack.size(); i++) {
                System.out.println(customerDateStack.get(i));
            }
        } else {
            System.out.println("ERROR");
        }
    }

    @Override
    @Test
    public void test() {
    }
}
