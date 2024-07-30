
package controlador;

import controlador.gPlanes;
import java.util.ArrayList;
import modelo.Plan;
import modelo.PlanPostPagoMegas;
public class gestionPlanesTest {
    
public static void main(String[] args) {
        gestionPlanesTest test = new gestionPlanesTest();
        test.testAgregarYObtenerPlanes();
        test.testActualizarPlan();
        test.testEliminarPlan();
    }

    public void testAgregarYObtenerPlanes() {
        gPlanes gestionPlanes = new gPlanes();
        Plan plan = new PlanPostPagoMegas(1, 100.0, 3.0, 10.0);
        gestionPlanes.agregarPlan(plan);

        ArrayList<Plan> planes = gestionPlanes.getPlanes();
        if (!planes.isEmpty() && planes.get(0).getId() == 1 && "PlanPostPagoMegas".equals(planes.get(0).getTipo())) {
            System.out.println("testAgregarYObtenerPlanes Aprobado");
        } else {
            System.out.println("testAgregarYObtenerPlanes Fallido");
        }
    }

    public void testActualizarPlan() {
        gPlanes gestionPlanes = new gPlanes();
        Plan plan = new PlanPostPagoMegas(1, 100.0, 3.0, 10.0);
        gestionPlanes.agregarPlan(plan);

        Plan planActualizado = new PlanPostPagoMegas(1, 200.0, 3.0, 10.0);
        gestionPlanes.actualizarPlan(planActualizado);

        Plan planObtenido = gestionPlanes.getPlanes().get(0);
        if ("15.0".equals(planObtenido.parametrosToString().split(",")[0])) {
            System.out.println("testActualizarPlan Aprobado");
        } else {
            System.out.println("testActualizarPlan Fallido");
        }
    }

    public void testEliminarPlan() {
        gPlanes gestionPlanes = new gPlanes();
        Plan plan = new PlanPostPagoMegas(1, 100.0, 3.0, 10.0);
        gestionPlanes.agregarPlan(plan);

        gestionPlanes.eliminarPlan(1);
        ArrayList<Plan> planes = gestionPlanes.getPlanes();
        if (planes.isEmpty()) {
            System.out.println("testEliminarPlan Aprobado");
        } else {
            System.out.println("testEliminarPlan Fallido");
        }
    }
}

