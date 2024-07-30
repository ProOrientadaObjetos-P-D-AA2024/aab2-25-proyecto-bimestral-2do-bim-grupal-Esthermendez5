
package modelo;
import java.util.ArrayList;
import java.util.List;
import modelo.infoCliente;
public class ClienteTest {
    public static void main(String[] args) {
        ClienteTest test = new ClienteTest();
        test.testAgregarPlanId();
        test.testPlanesIdsToString();
        test.testStringToPlanesIds();
    }

    public void testAgregarPlanId() {
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        infoCliente cliente = new infoCliente("Esther Mendez", "1106012634", "Loja", "iphone", "14 pro max", "0992105521", 25.0, "110601", "ninguna", planesIds);

        try {
            cliente.agregarPlanId(2);
            if (cliente.getPlanes().size() == 2) {
                System.out.println("testAgregarPlanId Aprobado");
            } else {
                System.out.println("testAgregarPlanId Fallido");
            }
        } catch (IllegalStateException e) {
            System.out.println("testAgregarPlanId Fallido");
        }

        try {
            cliente.agregarPlanId(3);
            System.out.println("testAgregarPlanId Fallido");
        } catch (IllegalStateException e) {
            System.out.println("testAgregarPlanId Aprobado");
        }
    }

    public void testPlanesIdsToString() {
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        infoCliente cliente = new infoCliente("Esther Mendez", "1106012634", "Loja", "iphone", "14 pro max", "0992105521", 25.0, "110601", "ninguna", planesIds);

        if ("1".equals(cliente.planesToString())) {
            cliente.agregarPlanId(2);
            if ("1,2".equals(cliente.planesToString())) {
                System.out.println("testPlanesIdsToString Aprobado");
            } else {
                System.out.println("testPlanesIdsToString Fallido");
            }
        } else {
            System.out.println("testPlanesIdsToString Fallido");
        }
    }

    public void testStringToPlanesIds() {
        String planesIdsStr = "1,2";
        List<Integer> planesIds = infoCliente.stringToPlanes(planesIdsStr);
        if (planesIds.size() == 2 && planesIds.get(0) == 1 && planesIds.get(1) == 2) {
            System.out.println("testStringToPlanesIds Aprobado");
        } else {
            System.out.println("testStringToPlanesIds Fallido");
        }
    }
    
}
