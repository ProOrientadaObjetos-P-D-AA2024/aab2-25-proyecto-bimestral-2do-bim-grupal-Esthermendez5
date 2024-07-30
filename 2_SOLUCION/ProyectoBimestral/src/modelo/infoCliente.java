
package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class infoCliente {
    private String nombres;
    private String pasaporteCedula;
    private String ciudad;
    private String marca;
    private String modelo;
    private String numeroCelular;
    private double pagoMensual;
    private String observaciones;
    private String codigoPostal;
    private List<Integer> planes;

    public infoCliente(String nombres, String pasaporteCedula, String ciudad, String marca, String modelo, String numeroCelular, double pagoMensual, String observaciones, String codigoPostal, List<Integer> planes) {
        this.nombres = nombres;
        this.pasaporteCedula = pasaporteCedula;
        this.ciudad = ciudad;
        this.marca = marca;
        this.modelo = modelo;
        this.numeroCelular = numeroCelular;
        this.pagoMensual = pagoMensual;
        this.observaciones = observaciones;
        this.codigoPostal = codigoPostal;
        this.planes = planes;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPasaporteCedula() {
        return pasaporteCedula;
    }

    public void setPasaporteCedula(String pasaporteCedula) {
        this.pasaporteCedula = pasaporteCedula;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public List<Integer> getPlanes() {
        return planes;
    }

    public void setPlanes(List<Integer> planes) {
        this.planes = planes;
    }
    //con el id agrega un nuevo clinte y los planes (max 2) 
    public void agregarPlanId(int planId) {
        if (this.planes.size() < 2) {
            this.planes.add(planId);
        } else {
            throw new IllegalStateException("El cliente no puede tener mas de 2 planes.");
        }
    }
     //cambia de planes a cadena 
     public String planesToString() {
        return String.join(",", planes.stream().map(String::valueOf).toArray(String[]::new));
    }
     // cambia de cadena a planes 
    public static List<Integer> stringToPlanes(String planesStr) {
        if (planesStr == null || planesStr.isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(planesStr.split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}


