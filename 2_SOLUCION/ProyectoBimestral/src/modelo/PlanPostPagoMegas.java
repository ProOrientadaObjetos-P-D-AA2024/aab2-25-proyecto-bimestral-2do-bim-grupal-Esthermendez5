package modelo;
    public class PlanPostPagoMegas extends Plan {
    private double megas;
    private double costoGiga;
    private double tarifaBase;

    public PlanPostPagoMegas(int id, double megas, double costoGiga, double tarifaBase) {
        super(id, "PlanPostPagoMegas");
        this.megas = megas;
        this.costoGiga = costoGiga;
        this.tarifaBase = tarifaBase;
    }

    
    @Override
    public double calcularCosto() {
        return megas * costoGiga + tarifaBase;
    }
    @Override
    public String parametrosToString() {
        return megas + "," + costoGiga + "," + tarifaBase;
    }
}
    

