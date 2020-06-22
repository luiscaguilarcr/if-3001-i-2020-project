package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.DateService;
import edu.ucr.rp.algoritmos.proyecto.logic.tdamethods.implementation.CustomerDateStack;
import edu.ucr.rp.algoritmos.proyecto.util.TestUtility;
import logic.interfaces.TestService;
import org.junit.Test;

public class DateServiceTest implements TestService {
    private static DateService dateService = DateService.getInstance();
    TestUtility testUtility = new TestUtility();

    @Test
    @Override
    public void testAddition() {
        if (testUtility.randomDate() != null) {
            CustomerDate customerDate = testUtility.randomDate();
            if(dateService.add(customerDate)){
                System.out.println("DATE ADDED");
            }else {
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
            CustomerDate customerDate = dateService.getByID(155);
            if(dateService.remove(customerDate)){
                System.out.println("DATE DELETED");
            }else {
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
        CustomerDate oldCustomerDate = dateService.getByID(244);
        CustomerDate newCustomerDate = oldCustomerDate;
        newCustomerDate.setHour("10:00am");
        if (dateService.edit(oldCustomerDate, newCustomerDate)) {
            System.out.println("Old user:" + oldCustomerDate.getHour());
            System.out.println("Edited user: " + newCustomerDate.getHour());
        } else {
            System.out.println("ERROR");
        }
    }

    @Test
    @Override
    public void getByID() {
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
        if (dateService.getDatesByAdminID(217) != null) {
            CustomerDateStack customerDateStack = dateService.getDatesByAdminID(217);
            for (int i = 0; i < customerDateStack.size(); i++) {
                System.out.println(customerDateStack.getByAcc(i).getCustomerID());
            }
        } else {
            System.out.println("ERROR");
        }
    }

    @Override
    @Test
    public void test() {
        //testUtility.setDateAndHour();
    }
}
