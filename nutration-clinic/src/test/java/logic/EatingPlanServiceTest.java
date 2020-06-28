package logic;

import edu.ucr.rp.algoritmos.proyecto.logic.domain.EatingPlan;
import edu.ucr.rp.algoritmos.proyecto.logic.service.implementation.EatingPlanService;
import edu.ucr.rp.algoritmos.proyecto.util.test.TestUtility;
import logic.interfaces.TestService;
import org.junit.Test;

public class EatingPlanServiceTest implements TestService {
    TestUtility testUtility = new TestUtility();

    @Test
    @Override
    public void testAddition() {
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
