
package modelo;

public abstract class Plan {
    private int id;
    private String tipo;

    public Plan(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }


    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }
    public abstract double calcularCosto();

    public abstract String parametrosToString();
}

     

