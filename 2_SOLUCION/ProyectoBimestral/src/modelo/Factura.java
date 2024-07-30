
package modelo;

import java.util.Date;

public class Factura {
    private int id;
    private String pasaporteCedulaCliente;
    private double total;
    private Date fecha;

    public Factura(int id, String pasaporteCedulaCliente, double total, Date fecha) {
        this.id = id;
        this.pasaporteCedulaCliente = pasaporteCedulaCliente;
        this.total = total;
        this.fecha = fecha;
    }

    // M�todos getter y setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasaporteCedulaCliente() {
        return pasaporteCedulaCliente;
    }

    public void setPasaporteCedulaCliente(String pasaporteCedulaCliente) {
        this.pasaporteCedulaCliente = pasaporteCedulaCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
}
