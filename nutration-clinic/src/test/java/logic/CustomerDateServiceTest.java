package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import logic.interfaces.TestService;
import org.junit.Test;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;

import java.util.List;

public class CustomerDateServiceTest implements TestService {
    private static CustomerDateService customerDateService = CustomerDateService.getInstance();
    TestUtility testUtility = new TestUtility();

    @Test
    @Override
    public void testAddition() {
        CustomerDate customerDate = testUtility.generateDate("29/6/2020", "10:00", 283);
        if (customerDate != null) {
            if (customerDateService.add(customerDate)) {
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
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        if (customerDateService.getAll() != null) {
            CustomerDate customerDate = customerDateService.getByID(242);
            if (customerDateService.remove(customerDate)) {
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
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        CustomerDate oldCustomerDate = customerDateService.getByID(129);

        CustomerDate newCustomerDate = new CustomerDate();
        newCustomerDate.setAdminID(184);
        newCustomerDate.setCustomerID(oldCustomerDate.getCustomerID());
        newCustomerDate.setDate("27/6/2020");
        newCustomerDate.setHour("17:00");

        if (customerDateService.edit(oldCustomerDate, newCustomerDate)) {
            System.out.println("Old user:" + oldCustomerDate.getHour());
            System.out.println("Edited user: " + newCustomerDate.getHour());
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void getByCustomerID() {
        customerDateService = CustomerDateService.getInstance();
        if (customerDateService.getByID(231) != null) {
            CustomerDate customerDate = customerDateService.getByID(231);
            System.out.println(customerDate.getCustomerID());
        } else {
            System.out.println("ERROR");
        }

        if (customerDateService.getByID(231) != null) {
            CustomerDate customerDate = customerDateService.getByID(231);
            System.out.println(customerDate.getCustomerID());
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void getByDoctorID() {
        if (customerDateService.getDatesByAdminID(184) != null) {
            List customerDateStack = customerDateService.getDatesByAdminID(184);
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
