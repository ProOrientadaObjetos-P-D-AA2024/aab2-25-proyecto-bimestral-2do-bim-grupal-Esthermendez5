
package modelo;
import java.util.ArrayList;
import java.util.List;
import modelo.conexion;
import modelo.infoCliente;
public class conexionTest {
    public static void main(String[] args) {
        conexionTest test = new conexionTest();
        test.testInsertarYObtenerClientes();
        test.testActualizarCliente();
        test.testEliminarCliente();
    }

    public void testInsertarYObtenerClientes() {
        conexion conexionDB = new conexion();
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        infoCliente cliente = new infoCliente("Esther Mendez", "1106012634", "Loja", "iphone", "14 pro max", "0992105521", 25.0, "110601", "ninguna", planesIds);
        conexionDB.insertarCliente(cliente);

        ArrayList<infoCliente> clientes = conexionDB.obtenerClientes();
        if (!clientes.isEmpty()) {
            infoCliente clienteObtenido = clientes.get(0);
            if ("Esther Mendez".equals(clienteObtenido.getNombres()) && "1106012634".equals(clienteObtenido.getPasaporteCedula())) {
                System.out.println("testInsertarYObtenerClientes Aprobado");
            } else {
                System.out.println("testInsertarYObtenerClientes Fallido");
            }
        } else {
            System.out.println("testInsertarYObtenerClientes Fallido");
        }
    }

    public void testActualizarCliente() {
        conexion conexionDB = new conexion();
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        infoCliente cliente = new infoCliente("Esther Mendez", "1106012634", "Loja", "iphone", "14 pro max", "0992105521", 25.0, "110601", "ninguna", planesIds);
        conexionDB.insertarCliente(cliente);

        cliente.setNombres("Juan Perez Actualizado");
        conexionDB.actualizarCliente(cliente);

        infoCliente clienteActualizado = conexionDB.obtenerClientes().get(0);
        if ("Juan Perez Actualizado".equals(clienteActualizado.getNombres())) {
            System.out.println("testActualizarCliente Aprobado");
        } else {
            System.out.println("testActualizarCliente Fallido");
        }
    }

    public void testEliminarCliente() {
        conexion conexionDB = new conexion();
        List<Integer> planesIds = new ArrayList<>();
        planesIds.add(1);
        infoCliente cliente = new infoCliente("Esther Mendez", "1106012634", "Loja", "iphone", "14 pro max", "0992105521", 25.0, "110601", "ninguna", planesIds);
        conexionDB.insertarCliente(cliente);

        conexionDB.eliminarCliente("1234567890");
        ArrayList<infoCliente> clientes = conexionDB.obtenerClientes();
        if (clientes.isEmpty()) {
            System.out.println("testEliminarCliente Aprobado");
        } else {
            System.out.println("testEliminarCliente Fallido");
        }
    }
}


