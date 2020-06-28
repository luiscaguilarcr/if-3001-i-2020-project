package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDatesHistoryService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import logic.interfaces.TestService;
import org.junit.Test;

public class CustomerDatesHistoryDateServiceTest implements TestService {
    CustomerDatesHistoryService customerDatesHistoryService;
    CustomerDateService customerDateService;

    private void instances(){
        customerDatesHistoryService = CustomerDatesHistoryService.getInstance();
        customerDateService = CustomerDateService.getInstance();
    }

    @Test
    @Override
    public void testAddition() {
        instances();
        CustomerDate customerDate = customerDateService.getByID(151);
        if(customerDatesHistoryService.add(customerDate)){
            System.out.println("Se agregó");
        }else {
            System.out.println("No se agregó");
        }
    }

    @Test
    @Override
    public void testDelete() {

    }

    @Test
    @Override
    public void testEdit() {

    }

    @Test
    @Override
    public void getByCustomerID() {

    }

    @Test
    @Override
    public void getByDoctorID() {

    }

    @Test
    @Override
    public void test() {

    }
}
