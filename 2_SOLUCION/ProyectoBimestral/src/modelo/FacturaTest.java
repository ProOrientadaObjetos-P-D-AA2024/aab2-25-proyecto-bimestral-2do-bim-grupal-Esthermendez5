
package modelo;

import java.util.Date;
import modelo.Factura;

public class FacturaTest {
    public static void main(String[] args) {
        FacturaTest test = new FacturaTest();
        test.testGetters();
        test.testSetters();
    }

    public void testGetters() {
        Date date = new Date();
        Factura factura = new Factura(1, "1106012634", 100.0, date);

        if (factura.getId() == 1 &&
            "1106012634".equals(factura.getPasaporteCedulaCliente()) &&
            factura.getTotal() == 100.0 &&
            factura.getFecha().equals(date)) {
            System.out.println("testGetters Aprobado");
        } else {
            System.out.println("testGetters Fallido");
        }
    }

    public void testSetters() {
        Factura factura = new Factura(1, "1106012634", 80.0, new Date());

        factura.setId(2);
        factura.setPasaporteCedulaCliente("0992105521");
        factura.setTotal(200.0);
        Date newDate = new Date();
        factura.setFecha(newDate);

        if (factura.getId() == 2 &&
            "0987654321".equals(factura.getPasaporteCedulaCliente()) &&
            factura.getTotal() == 200.0 &&
            factura.getFecha().equals(newDate)) {
            System.out.println("testSetters Aprobado");
        } else {
            System.out.println("testSetters Fallido");
        }
    }
}

