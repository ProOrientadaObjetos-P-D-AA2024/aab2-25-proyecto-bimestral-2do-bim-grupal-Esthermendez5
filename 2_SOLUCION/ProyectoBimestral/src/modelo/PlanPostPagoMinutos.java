
package modelo;

public class PlanPostPagoMinutos extends Plan {
    private int minutosNacionales;
    private double costoMinutoNacional;
    private int minutosInternacionales;
    private double costoMinutoInternacional;

    public PlanPostPagoMinutos(int id, int minutosNacionales, double costoMinutoNacional, int minutosInternacionales, double costoMinutoInternacional) {
        super(id, "PlanPostPagoMinutos");
        this.minutosNacionales = minutosNacionales;
        this.costoMinutoNacional = costoMinutoNacional;
        this.minutosInternacionales = minutosInternacionales;
        this.costoMinutoInternacional = costoMinutoInternacional;
    }

   
    @Override
    public double calcularCosto() {
        return (minutosNacionales * costoMinutoNacional) + (minutosInternacionales * costoMinutoInternacional);
    }

    
    @Override
    public String parametrosToString() {
        return minutosNacionales + "," + costoMinutoNacional + "," + minutosInternacionales + "," + costoMinutoInternacional;
    }
}
