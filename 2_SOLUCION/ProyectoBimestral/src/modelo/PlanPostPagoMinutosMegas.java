
package modelo;

    public class PlanPostPagoMinutosMegas extends Plan {
    private int minutos;
    private double costoMinuto;
    private double megas;
    private double costoGiga;

    public PlanPostPagoMinutosMegas(int id, int minutos, double costoMinuto, double megas, double costoGiga) {
        super(id, "PlanPostPagoMinutosMegas");
        this.minutos = minutos;
        this.costoMinuto = costoMinuto;
        this.megas = megas;
        this.costoGiga = costoGiga;
    }

    
    @Override
    public double calcularCosto() {
        return minutos * costoMinuto + megas * costoGiga;
    }

    
    @Override
    public String parametrosToString() {
        return minutos + "," + costoMinuto + "," + megas + "," + costoGiga;
    }
}

  