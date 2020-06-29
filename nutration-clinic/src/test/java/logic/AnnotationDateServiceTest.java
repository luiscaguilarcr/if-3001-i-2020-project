package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.AdminAnnotation;
import edu.ucr.rp.algoritmos.proyecto.logic.domain.CustomerDate;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.AdminAnnotationService;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.CustomerDateService;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import logic.interfaces.TestService;
import org.junit.Test;

public class AnnotationDateServiceTest implements TestService {
    TestUtility testUtility = new TestUtility();

    @Test
    @Override
    public void testAddition() {
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        CustomerDate customerDate = customerDateService.getByID(132);
        AdminAnnotation adminAnnotation = testUtility.generateAnnotations(customerDate);
        AdminAnnotationService adminAnnotationService = AdminAnnotationService.getInstance();
        if(adminAnnotationService.add(adminAnnotation)){
            System.out.println("Se agregaron las anotaciones");
        }else {
            System.out.println("NO se agregaron las anotaciones");
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
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        AdminAnnotation adminAnnotation = testUtility.generateAnnotations(customerDateService.getByID(251));
        AdminAnnotationService adminAnnotationService = AdminAnnotationService.getInstance();
        if(adminAnnotationService.add(adminAnnotation)){
            System.out.println("Se agregaron las anotaciones");
        }else {
            System.out.println("NO se agregaron las anotaciones");
        }
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
