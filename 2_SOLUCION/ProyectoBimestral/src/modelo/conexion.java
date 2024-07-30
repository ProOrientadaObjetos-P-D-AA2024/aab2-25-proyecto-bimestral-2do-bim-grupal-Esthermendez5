
package modelo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Factura;
import modelo.Plan;
import modelo.PlanFactory;
import modelo.infoCliente;
public class conexion {
    private Connection connect() {
        String url = "jdbc:sqlite:./db/mov_utpl.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    public void insertarCliente(infoCliente cliente) {
        String sql = "INSERT INTO clientes(nombres, pasaporteCedula, ciudad, marca, modelo, numeroCelular, pagoMensual, cidigoPostal, observaciones, planes) VALUES(?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getPasaporteCedula());
            pstmt.setString(3, cliente.getCiudad());
            pstmt.setString(4, cliente.getMarca());
            pstmt.setString(5, cliente.getModelo());
            pstmt.setString(6, cliente.getNumeroCelular());
            pstmt.setDouble(7, cliente.getPagoMensual());
            pstmt.setString(8, cliente.getCodigoPostal());
            pstmt.setString(9, cliente.getObservaciones());
            pstmt.setString(10, cliente.planesToString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<infoCliente> obtenerClientes() {
        String sql = "SELECT * FROM clientes";
        ArrayList<infoCliente> clientes = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new infoCliente(
                        rs.getString("nombres"),
                        rs.getString("pasaporteCedula"),
                        rs.getString("ciudad"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getString("numeroCelular"),
                        rs.getDouble("pagoMensual"),
                        rs.getString("Codigo Postal"),
                        rs.getString("observaciones"),
                        infoCliente.stringToPlanes(rs.getString("planes"))
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public void actualizarCliente(infoCliente cliente) {
        String sql = "UPDATE clientes SET nombres = ?, ciudad = ?, marca = ?, modelo = ?, numeroCelular = ?, pagoMensual = ?, codigoPostal = ?, observaciones = ?, planes = ? WHERE pasaporteCedula = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getNombres());
            pstmt.setString(2, cliente.getCiudad());
            pstmt.setString(3, cliente.getMarca());
            pstmt.setString(4, cliente.getModelo());
            pstmt.setString(5, cliente.getNumeroCelular());
            pstmt.setDouble(6, cliente.getPagoMensual());
            pstmt.setString(7, cliente.getCodigoPostal());
            pstmt.setString(8, cliente.getObservaciones());
            pstmt.setString(9, cliente.planesToString());
            pstmt.setString(10, cliente.getPasaporteCedula());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void eliminarCliente(String pasaporteCedula) {
        String sql = "DELETE FROM clientes WHERE pasaporteCedula = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pasaporteCedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void asignarPlanACliente(String pasaporteCedula, int planId) {
        String sql = "SELECT planesIds FROM clientes WHERE pasaporteCedula = ?";
        String planesStr = "";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pasaporteCedula);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                planesStr = rs.getString("planes");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        List<Integer> planesIds = infoCliente.stringToPlanes(planesStr);
        planesIds.add(planId);
        String updatedPlanesIdsStr = String.join(",", planesIds.stream().map(String::valueOf).toArray(String[]::new));

        sql = "UPDATE clientes SET planesIds = ? WHERE pasaporteCedula = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, updatedPlanesIdsStr);
            pstmt.setString(2, pasaporteCedula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public void insertarPlan(Plan plan) {
        String sql = "INSERT INTO planes(id, tipo, parametros) VALUES(?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, plan.getId());
            pstmt.setString(2, plan.getTipo());
            pstmt.setString(3, plan.parametrosToString());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   
    public ArrayList<Plan> obtenerPlanes() {
        String sql = "SELECT * FROM planes";
        ArrayList<Plan> planes = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Plan plan = PlanFactory.crearPlan(
                    rs.getInt("id"),
                    rs.getString("tipo"),
                    rs.getString("parametros")
                );
                planes.add(plan);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return planes;
    }
    
    
    public Plan obtenerPlanPorId(int planId) {
        String sql = "SELECT * FROM planes WHERE id = ?";
        Plan plan = null;

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, planId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                plan = PlanFactory.crearPlan(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("parametros")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return plan;
    }

    
    public void actualizarPlan(Plan plan) {
        String sql = "UPDATE planes SET tipo = ?, parametros = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plan.getTipo());
            pstmt.setString(2, plan.parametrosToString());
            pstmt.setInt(3, plan.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void eliminarPlan(int id) {
        String sql = "DELETE FROM planes WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void insertarFactura(Factura factura) {
        String sql = "INSERT INTO facturas(pasaporteCedulaCliente, total, fecha) VALUES(?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, factura.getPasaporteCedulaCliente());
            pstmt.setDouble(2, factura.getTotal());
            pstmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                factura.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

   
    public ArrayList<Factura> obtenerFacturas() {
        String sql = "SELECT * FROM facturas";
        ArrayList<Factura> facturas = new ArrayList<>();

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                facturas.add(new Factura(
                        rs.getInt("id"),
                        rs.getString("pasaporteCedulaCliente"),
                        rs.getDouble("total"),
                        rs.getDate("fecha")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return facturas;
    }

    
    public void actualizarFactura(Factura factura) {
        String sql = "UPDATE facturas SET pasaporteCedulaCliente = ?, total = ?, fecha = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, factura.getPasaporteCedulaCliente());
            pstmt.setDouble(2, factura.getTotal());
            pstmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            pstmt.setInt(4, factura.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    
    public void eliminarFactura(int id) {
        String sql = "DELETE FROM facturas WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}