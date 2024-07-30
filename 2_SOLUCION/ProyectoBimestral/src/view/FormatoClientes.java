package view;

import controlador.gestionClientes;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import modelo.Factura;
import modelo.Plan;
import modelo.infoCliente;

public class FormatoClientes extends javax.swing.JFrame {
    private gestionClientes gestionClientes;
    private DefaultTableModel modelo;

    public FormatoClientes() {
        initComponents();
        gestionClientes = new gestionClientes();
        modelo = (DefaultTableModel) this.jTable1.getModel();
    }

    private void initComponents() {
        jButton1 = new javax.swing.JButton(); // Botón para agregar un nuevo cliente
        jButton2 = new javax.swing.JButton(); // Botón para mostrar los clientes
        jButton3 = new javax.swing.JButton(); // Botón para actualizar un cliente
        jButton4 = new javax.swing.JButton(); // Botón para eliminar un cliente
        jButton5 = new javax.swing.JButton(); // Botón para asignar un plan a un cliente
        jButton6 = new javax.swing.JButton(); // Botón para generar factura
        jButton7 = new javax.swing.JButton(); // Botón para mostrar facturas
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Agregar Cliente");
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Mostrar Clientes");
        jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Actualizar Cliente");
        jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Eliminar Cliente");
        jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Asignar Plan");
        jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Generar Factura");
        jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Mostrar Facturas");
        jButton7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable1.setModel(new DefaultTableModel(
                new Object[][]{},
                new String[]{
                        "Nombres", "Pasaporte/Cédula", "Ciudad", "Marca", "Modelo", "Número Celular", "Pago Mensual", "Observaciones", "Código Postal", "Planes"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new BoxLayout(panelBotones, BoxLayout.Y_AXIS));
        panelBotones.add(jButton2);
        panelBotones.add(jButton3);
        panelBotones.add(jButton4);
        panelBotones.add(jButton5);
        panelBotones.add(jButton7);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(jButton1, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.WEST);
        panelPrincipal.add(jScrollPane1, BorderLayout.CENTER);

        JPanel panelGenerarFactura = new JPanel();
        panelGenerarFactura.add(jButton6);
        panelPrincipal.add(panelGenerarFactura, BorderLayout.SOUTH);

        add(panelPrincipal);

        pack();
    }
 // Métodos de acción para los botones
   private void jButton1ActionPerformed(ActionEvent evt) {
    // Crear un formulario para ingresar la información del cliente
    JFrame formulario = new JFrame("Agregar Cliente");
    formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Crear campos de texto para ingresar la información
    JLabel labelNombres = new JLabel("Nombres:");
    JTextField fieldNombres = new JTextField(20);

    JLabel labelPasaporteCedula = new JLabel("Pasaporte/Cedula:");
    JTextField fieldPasaporteCedula = new JTextField(20);

    JLabel labelCiudad = new JLabel("Ciudad:");
    JTextField fieldCiudad = new JTextField(20);

    JLabel labelMarca = new JLabel("Marca:");
    JTextField fieldMarca = new JTextField(20);

    JLabel labelModelo = new JLabel("Modelo:");
    JTextField fieldModelo = new JTextField(20);

    JLabel labelNumeroCelular = new JLabel("Numero Celular:");
    JTextField fieldNumeroCelular = new JTextField(20);

    JLabel labelPagoMensual = new JLabel("Pago Mensual:");
    JTextField fieldPagoMensual = new JTextField(20);

    JLabel labelCodigoPostal = new JLabel("Codigo Postal:");
    JTextField fieldCodigoPostal = new JTextField(20);

    JLabel labelObservaciones = new JLabel("Observaciones:");
    JTextField fieldObservaciones = new JTextField(20);

    JLabel labelPlanId1 = new JLabel("Plan ID 1:");
    JTextField fieldPlanId1 = new JTextField(20);

    JLabel labelPlanId2 = new JLabel("Plan ID 2 (Opcional, 0 si no aplica):");
    JTextField fieldPlanId2 = new JTextField(20);

    // Crear un botón para guardar la información
    JButton botonGuardar = new JButton("Guardar");
    botonGuardar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Obtener la información ingresada por el usuario
            String nombres = fieldNombres.getText();
            String pasaporteCedula = fieldPasaporteCedula.getText();
            String ciudad = fieldCiudad.getText();
            String marca = fieldMarca.getText();
            String modelo = fieldModelo.getText();
            String numeroCelular = fieldNumeroCelular.getText();
            double pagoMensual = Double.parseDouble(fieldPagoMensual.getText());
            String codigoPostal = fieldCodigoPostal.getText();
            String observaciones = fieldObservaciones.getText();
            int planId1 = Integer.parseInt(fieldPlanId1.getText());
            int planId2 = Integer.parseInt(fieldPlanId2.getText());
            List<Integer> planesIds = new ArrayList<>();
            planesIds.add(planId1);
            if (planId2 != 0) {
                planesIds.add(planId2);
            }

            // Crear un objeto infoCliente con la información ingresada
            infoCliente cliente = new infoCliente(nombres, pasaporteCedula, ciudad, marca, modelo, numeroCelular, pagoMensual, observaciones, codigoPostal, planesIds);

            // Agregar el cliente a la lista de clientes
            gestionClientes.agregarCliente(cliente);

            // Mostrar la lista de clientes actualizada
            mostrarClientes();

            // Cerrar el formulario
            formulario.dispose();
        }
    });

    // Agregar los campos de texto y el botón al formulario
    formulario.getContentPane().setLayout(new GridLayout(12, 2));
    formulario.getContentPane().add(labelNombres);
    formulario.getContentPane().add(fieldNombres);
    formulario.getContentPane().add(labelPasaporteCedula);
    formulario.getContentPane().add(fieldPasaporteCedula);
    formulario.getContentPane().add(labelCiudad);
    formulario.getContentPane().add(fieldCiudad);
    formulario.getContentPane().add(labelMarca);
    formulario.getContentPane().add(fieldMarca);
    formulario.getContentPane().add(labelModelo);
    formulario.getContentPane().add(fieldModelo);
    formulario.getContentPane().add(labelNumeroCelular);
    formulario.getContentPane().add(fieldNumeroCelular);
    formulario.getContentPane().add(labelPagoMensual);
    formulario.getContentPane().add(fieldPagoMensual);
    formulario.getContentPane().add(labelCodigoPostal);
    formulario.getContentPane().add(fieldCodigoPostal);
    formulario.getContentPane().add(labelObservaciones);
    formulario.getContentPane().add(fieldObservaciones);
    formulario.getContentPane().add(labelPlanId1);
    formulario.getContentPane().add(fieldPlanId1);
    formulario.getContentPane().add(labelPlanId2);
    formulario.getContentPane().add(fieldPlanId2);
    formulario.getContentPane().add(botonGuardar);

    // Mostrar el formulario
    formulario.pack();
    formulario.setVisible(true);
}

    private void jButton2ActionPerformed(ActionEvent evt) {
        mostrarClientes();
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
    // Crear un formulario para actualizar un cliente
    JFrame formulario = new JFrame("Actualizar Cliente");
    formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Crear campos de texto para ingresar la información
    JLabel labelPasaporteCedula = new JLabel("Pasaporte/Cedula:");
    JTextField fieldPasaporteCedula = new JTextField(20);

    JLabel labelNombres = new JLabel("Nombres:");
    JTextField fieldNombres = new JTextField(20);

    JLabel labelCiudad = new JLabel("Ciudad:");
    JTextField fieldCiudad = new JTextField(20);

    JLabel labelMarca = new JLabel("Marca:");
    JTextField fieldMarca = new JTextField(20);

    JLabel labelModelo = new JLabel("Modelo:");
    JTextField fieldModelo = new JTextField(20);

    JLabel labelNumeroCelular = new JLabel("Numero Celular:");
    JTextField fieldNumeroCelular = new JTextField(20);

    JLabel labelPagoMensual = new JLabel("Pago Mensual:");
    JTextField fieldPagoMensual = new JTextField(20);

    JLabel labelCodigoPostal = new JLabel("Codigo Postal:");
    JTextField fieldCodigoPostal = new JTextField(20);

    JLabel labelObservaciones = new JLabel("Observaciones:");
    JTextField fieldObservaciones = new JTextField(20);

    JLabel labelPlanId1 = new JLabel("Plan ID 1:");
    JTextField fieldPlanId1 = new JTextField(20);

    JLabel labelPlanId2 = new JLabel("Plan ID 2 (Opcional, 0 si no aplica):");
    JTextField fieldPlanId2 = new JTextField(20);

    // Crear un botón para actualizar el cliente
    JButton botonActualizar = new JButton("Actualizar Cliente");
    botonActualizar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String pasaporteCedula = fieldPasaporteCedula.getText();
            infoCliente clienteExistente = buscarCliente(pasaporteCedula);
            if (clienteExistente!= null) {
                String nombres = fieldNombres.getText();
                String ciudad = fieldCiudad.getText();
                String marca = fieldMarca.getText();
                String modelo = fieldModelo.getText();
                String numeroCelular = fieldNumeroCelular.getText();
                double pagoMensual = Double.parseDouble(fieldPagoMensual.getText());
                String codigoPostal = fieldCodigoPostal.getText();
                String observaciones = fieldObservaciones.getText();
                int planId1 = Integer.parseInt(fieldPlanId1.getText());
                int planId2 = Integer.parseInt(fieldPlanId2.getText());

                List<Integer> planesIds = new ArrayList<>();
                planesIds.add(planId1);
                if (planId2!= 0) {
                    planesIds.add(planId2);
                }

                infoCliente clienteActualizado = new infoCliente(nombres, pasaporteCedula, ciudad, marca, modelo, numeroCelular, pagoMensual, observaciones, codigoPostal, planesIds);
                gestionClientes.actualizarCliente(clienteActualizado);
                mostrarClientes();
                formulario.dispose();
            } else {
                JOptionPane.showMessageDialog(formulario, "Cliente no encontrado");
            }
        }
    });

    // Agregar los campos de texto y el botón al formulario
    formulario.getContentPane().setLayout(new GridLayout(12, 2));
    formulario.getContentPane().add(labelPasaporteCedula);
    formulario.getContentPane().add(fieldPasaporteCedula);
    formulario.getContentPane().add(labelNombres);
    formulario.getContentPane().add(fieldNombres);
    formulario.getContentPane().add(labelCiudad);
    formulario.getContentPane().add(fieldCiudad);
    formulario.getContentPane().add(labelMarca);
    formulario.getContentPane().add(fieldMarca);
    formulario.getContentPane().add(labelModelo);
    formulario.getContentPane().add(fieldModelo);
    formulario.getContentPane().add(labelNumeroCelular);
    formulario.getContentPane().add(fieldNumeroCelular);
    formulario.getContentPane().add(labelPagoMensual);
    formulario.getContentPane().add(fieldPagoMensual);
    formulario.getContentPane().add(labelCodigoPostal);
    formulario.getContentPane().add(fieldCodigoPostal);
    formulario.getContentPane().add(labelObservaciones);
    formulario.getContentPane().add(fieldObservaciones);
    formulario.getContentPane().add(labelPlanId1);
    formulario.getContentPane().add(fieldPlanId1);
    formulario.getContentPane().add(labelPlanId2);
    formulario.getContentPane().add(fieldPlanId2);
    formulario.getContentPane().add(botonActualizar);

    // Mostrar el formulario
    formulario.pack();
    formulario.setVisible(true);
}

    private void jButton4ActionPerformed(ActionEvent evt) {
    // Crear un formulario para eliminar un cliente
    JFrame formulario = new JFrame("Eliminar Cliente");
    formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Crear un campo de texto para ingresar la información
    JLabel labelPasaporteCedula = new JLabel("Pasaporte/Cedula del cliente a eliminar:");
    JTextField fieldPasaporteCedula = new JTextField(20);

    // Crear un botón para eliminar el cliente
    JButton botonEliminar = new JButton("Eliminar Cliente");
    botonEliminar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String pasaporteCedula = fieldPasaporteCedula.getText();
            gestionClientes.eliminarCliente(pasaporteCedula);
            mostrarClientes();
            formulario.dispose();
        }
    });

    // Agregar el campo de texto y el botón al formulario
    formulario.getContentPane().setLayout(new GridLayout(2, 1));
    formulario.getContentPane().add(labelPasaporteCedula);
    formulario.getContentPane().add(fieldPasaporteCedula);
    formulario.getContentPane().add(botonEliminar);

    // Mostrar el formulario
    formulario.pack();
    formulario.setVisible(true);
}

    private void jButton5ActionPerformed(ActionEvent evt) {
    // Crear un formulario para asignar un plan a un cliente
    JFrame formulario = new JFrame("Asignar Plan a Cliente");
    formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Crear campos de texto para ingresar la información
    JLabel labelPasaporteCedula = new JLabel("Pasaporte/Cedula del cliente:");
    JTextField fieldPasaporteCedula = new JTextField(20);

    JLabel labelPlanId = new JLabel("ID del plan a asignar:");
    JTextField fieldPlanId = new JTextField(20);

    // Crear un botón para asignar el plan
    JButton botonAsignar = new JButton("Asignar Plan");
    botonAsignar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String pasaporteCedula = fieldPasaporteCedula.getText();
            int planId = Integer.parseInt(fieldPlanId.getText());
            gestionClientes.asignarPlanACliente(pasaporteCedula, planId);
            mostrarClientes();
            formulario.dispose();
        }
    });

    // Agregar los campos de texto y el botón al formulario
    formulario.getContentPane().setLayout(new GridLayout(3, 1));
    formulario.getContentPane().add(labelPasaporteCedula);
    formulario.getContentPane().add(fieldPasaporteCedula);
    formulario.getContentPane().add(labelPlanId);
    formulario.getContentPane().add(fieldPlanId);
    formulario.getContentPane().add(botonAsignar);

    // Mostrar el formulario
    formulario.pack();
    formulario.setVisible(true);
}

    private void jButton6ActionPerformed(ActionEvent evt) {
    // Crear un formulario para generar una factura
    JFrame formulario = new JFrame("Generar Factura");
    formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Crear un campo de texto para ingresar la información
    JLabel labelPasaporteCedula = new JLabel("Pasaporte/Cédula del cliente para generar la factura:");
    JTextField fieldPasaporteCedula = new JTextField(20);

    // Crear un botón para generar la factura
    JButton botonGenerar = new JButton("Generar Factura");
    botonGenerar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String pasaporteCedula = fieldPasaporteCedula.getText();
            gestionClientes.generarFactura(pasaporteCedula);
            JOptionPane.showMessageDialog(formulario, "Factura generada correctamente.");
            formulario.dispose();
        }
    });

    // Agregar el campo de texto y el botón al formulario
    formulario.getContentPane().setLayout(new GridLayout(2, 1));
    formulario.getContentPane().add(labelPasaporteCedula);
    formulario.getContentPane().add(fieldPasaporteCedula);
    formulario.getContentPane().add(botonGenerar);

    // Mostrar el formulario
    formulario.pack();
    formulario.setVisible(true);
}

    private void jButton7ActionPerformed(ActionEvent evt) {
        mostrarFacturas();
    }

    /**
     * Muestra los clientes en la tabla.
     */
    private void mostrarClientes() {
        modelo.setRowCount(0); // Limpiar la tabla
        for (infoCliente cliente : gestionClientes.getClientes()) {
            modelo.addRow(new Object[]{cliente.getNombres(), cliente.getPasaporteCedula(), cliente.getCiudad(), cliente.getMarca(), cliente.getModelo(), cliente.getNumeroCelular(), cliente.getPagoMensual(),  cliente.getObservaciones(),cliente.getCodigoPostal(), cliente.getPlanes().toString()});
        }
    }

    /**
     * Muestra las facturas en una nueva ventana.
     */
    private void mostrarFacturas() {
        DefaultTableModel modeloFacturas = new DefaultTableModel(new Object[]{"ID", "Cliente", "Total", "Fecha"}, 0);
        for (Factura factura : gestionClientes.obtenerFacturas()) {
            modeloFacturas.addRow(new Object[]{factura.getId(), factura.getPasaporteCedulaCliente(), factura.getTotal(), factura.getFecha()});
        }

        JTable tableFacturas = new JTable(modeloFacturas);
        JScrollPane scrollPane = new JScrollPane(tableFacturas);
        JFrame frameFacturas = new JFrame("Facturas Generadas");
        frameFacturas.add(scrollPane);
        frameFacturas.setSize(800, 600);
        frameFacturas.setVisible(true);
    }

    private infoCliente buscarCliente(String pasaporteCedula) {
        for (infoCliente cliente : gestionClientes.getClientes()) {
            if (cliente.getPasaporteCedula().equals(pasaporteCedula)) {
                return cliente;
            }
        }
        return null;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormatoClientes().setVisible(true);
            }
        });
    }

    // Declaración de variables de los botones
    private javax.swing.JButton jButton1; // Boton para agregar un nuevo cliente
    private javax.swing.JButton jButton2; // Boton para mostrar los clientes
    private javax.swing.JButton jButton3; // Boton para actualizar un cliente
    private javax.swing.JButton jButton4; // Boton para eliminar un cliente
    private javax.swing.JButton jButton5; // Boton para asignar un plan a un cliente
    private javax.swing.JButton jButton6; // Boton para generar factura
    private javax.swing.JButton jButton7; // Boton para mostrar facturas
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;

}
