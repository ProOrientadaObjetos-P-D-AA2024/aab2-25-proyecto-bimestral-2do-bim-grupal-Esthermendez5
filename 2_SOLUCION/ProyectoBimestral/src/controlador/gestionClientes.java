
package controlador;

import java.util.ArrayList;
import java.util.Date;
import modelo.Factura;
import modelo.Plan;
import modelo.infoCliente;
import modelo.conexion;

public class gestionClientes {
    private ArrayList<infoCliente> clientes;
    private conexion conexionDB;

  
    public gestionClientes() {
        this.conexionDB = new conexion();
        this.clientes = conexionDB.obtenerClientes();
    }

    /**
     * Agrega un nuevo cliente a la base de datos y a la lista local.
     * @param cliente Cliente a agregar.
     */
    public void agregarCliente(infoCliente cliente) {
        conexionDB.insertarCliente(cliente);
        clientes.add(cliente);
    }

    /**
     * Actualiza la informaci�n de un cliente en la base de datos y en la lista local.
     * @param cliente Cliente con la informaci�n actualizada.
     */
    public void actualizarCliente(infoCliente cliente) {
        conexionDB.actualizarCliente(cliente);
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getPasaporteCedula().equals(cliente.getPasaporteCedula())) {
                clientes.set(i, cliente);
                break;
            }
        }
    }
    public void eliminarCliente(String pasaporteCedula) {
        conexionDB.eliminarCliente(pasaporteCedula);
        clientes.removeIf(cliente -> cliente.getPasaporteCedula().equals(pasaporteCedula));
    }

    public ArrayList<infoCliente> getClientes() {
        return clientes;
    }

    public void asignarPlanACliente(String pasaporteCedula, int planId) {
        for (infoCliente cliente : clientes) {
            if (cliente.getPasaporteCedula().equals(pasaporteCedula)) {
                cliente.agregarPlanId(planId);
                conexionDB.asignarPlanACliente(pasaporteCedula, planId);
                break;
            }
        }
    }

    public void generarFactura(String pasaporteCedula) {
        infoCliente cliente = null;
        for (infoCliente c : clientes) {
            if (c.getPasaporteCedula().equals(pasaporteCedula)) {
                cliente = c;
                break;
            }
        }

        if (cliente != null) {
            double total = 0;
            for (int planId : cliente.getPlanes()) {
                Plan plan = conexionDB.obtenerPlanPorId(planId);
                if (plan != null) {
                    total += plan.calcularCosto();
                }
            }

            Factura factura = new Factura(0, pasaporteCedula, total, new Date());
            conexionDB.insertarFactura(factura);
        }
    }


    public ArrayList<Factura> obtenerFacturas() {
        return conexionDB.obtenerFacturas();
    }
}
    

