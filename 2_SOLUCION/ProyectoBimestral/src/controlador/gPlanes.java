
package controlador;
import java.util.ArrayList;
import modelo.Plan;
import modelo.conexion;

public class gPlanes {
    private ArrayList<Plan> planes;
    private conexion conexionDB;
    public gPlanes() {
        this.conexionDB = new conexion();
        this.planes = conexionDB.obtenerPlanes();
    }

  
    public void agregarPlan(Plan plan) {
        conexionDB.insertarPlan(plan);
        planes.add(plan);
    }

   
    public void actualizarPlan(Plan plan) {
        conexionDB.actualizarPlan(plan);
        for (int i = 0; i < planes.size(); i++) {
            if (planes.get(i).getId() == plan.getId()) {
                planes.set(i, plan);
                break;
            }
        }
    }

    public void eliminarPlan(int id) {
        conexionDB.eliminarPlan(id);
        planes.removeIf(plan -> plan.getId() == id);
    }

  
    public ArrayList<Plan> getPlanes() {
        return planes;
    }
}
    

