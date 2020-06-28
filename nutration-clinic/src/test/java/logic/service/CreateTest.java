package logic.service;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.*;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.*;
import edu.ucr.rp.algoritmos.proyecto.util.files.IOUtility;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import logic.interfaces.TestInterfaceService;
import org.junit.Test;

public class CreateTest implements TestInterfaceService {
    TestUtility testUtility = new TestUtility();

    @Test
    @Override
    public void AdminAvailabilityServiceTest() {
        System.out.println("Se implementa implícitamente en user");
    }

    @Test
    @Override
    public void AnnotationServiceTest() {
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        AdminAnnotation adminAnnotation = testUtility.generateAnnotations(customerDateService.getByID(251));
        AnnotationService annotationService = AnnotationService.getInstance();
        if(annotationService.add(adminAnnotation)){
            System.out.println("Se agregaron las anotaciones");
        }else {
            System.out.println("NO se agregaron las anotaciones");
        }
    }

    @Test
    @Override
    public void CustomerReportServiceTest() {
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        customerDateService.remove(customerDateService.getByID(245));
    }

    @Test
    @Override
    public void DateServiceTest() { // works
        CustomerDateService customerDateService = CustomerDateService.getInstance();
        for (int i = 0; i < 10; i++) {
            CustomerDate customerDate = testUtility.generateDate("32/6/2020", "8:00", 305290615);
            if (customerDateService.add(customerDate)) {
                System.out.println("Se agregó cita");
            } else {
                System.out.println("NO se agregó cita");
            }
        }
    }

    @Test
    @Override
    public void EatingPlanServiceTest() { // works
        EatingPlanService eatingPlanService = EatingPlanService.getInstance();
        for (int i = 0; i < 10; i++) {
            EatingPlan eatingPlan = testUtility.generateEatingPlan();
            if (eatingPlanService.add(eatingPlan)) {
                System.out.println("Se agregó plan de comida");
            } else {
                System.out.println("NO se agregó plan de comida");
            }
        }
    }

    @Test
    @Override
    public void HistoryAppServiceTest() {
        HistoryApp historyApp = new HistoryApp();
        HistoryAppService historyAppService = HistoryAppService.getInstance();
        historyApp.setUserID(777);
        historyApp.setDate("26/06/2020");
        historyApp.setInfo("Test 1");
        historyApp.setHour("23:27");
        historyAppService.add(historyApp);
    }

    @Test
    @Override
    public void UserServiceTest() { // works
        verifyUserDir();
        UserService userService = UserService.getInstance();
        for (int i = 0; i < 10; i++) {
            User user = testUtility.randomUser();
            if (userService.add(user)) {
                System.out.println("Se creó el usuario");
            } else {
                System.out.println("No se creó el usuario");
            }
        }
    }

    private void verifyUserDir() {
        IOUtility ioUtility = new IOUtility();
        ioUtility.verifyAppDir();
    }
}
