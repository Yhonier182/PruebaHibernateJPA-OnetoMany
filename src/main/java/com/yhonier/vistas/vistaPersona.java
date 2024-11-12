package com.yhonier.vistas;

import com.yhonier.controlador.controladorPersona;
import com.yhonier.entidades.Persona;
import com.yhonier.entidades.PersonasProductos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class vistaPersona extends JFrame {

    private final controladorPersona personaController = new controladorPersona();

    private JTextField txtIdPersona, txtNombrePersona, txtTelefonoPersona, txtProfesionPersona, txtTipoPersona;
    private JTable tablaPersonas, tablaProductos;
    private JButton btnRegistrarPersona, btnActualizarPersona, btnEliminarPersona, btnCancelar;

    public vistaPersona() {
        setTitle("GESTIÓN DE PERSONAS");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(2000, 1600);
        // Hacer que la ventana ocupe toda la pantalla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        cargarTablaPersonas();
        tablaPersonas.getSelectionModel().addListSelectionListener(e -> cargarProductosPersonaSeleccionada());
        setButtonActions();
    }

    private void initComponents() {
        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelPrincipal.setBackground(Color.decode("#1a1a1a")); // Fondo oscuro principal

        JPanel panelDatosPersona = createDatosPersonaPanel();
        JPanel panelBotones = createButtonPanel();
        JPanel panelTablas = createTablesPanel();

        panelPrincipal.add(panelDatosPersona, BorderLayout.NORTH);
        panelPrincipal.add(panelBotones, BorderLayout.CENTER);
        panelPrincipal.add(panelTablas, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
        pack();
    }

    private JPanel createDatosPersonaPanel() {
        JPanel panelDatosPersona = new JPanel(new GridLayout(6, 2, 5, 5));
        panelDatosPersona.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#FF5722"), 3), "Datos de la Persona"));
        panelDatosPersona.setBackground(Color.decode("#333333"));

        Font labelFont = new Font("Comic Sans MS", Font.BOLD, 14);
        txtIdPersona = new JTextField();
        txtNombrePersona = new JTextField();
        txtTelefonoPersona = new JTextField();
        txtProfesionPersona = new JTextField();
        txtTipoPersona = new JTextField();

        JLabel lblId = new JLabel("Número de Documento:");
        lblId.setFont(labelFont);
        lblId.setForeground(Color.WHITE);
        panelDatosPersona.add(lblId);
        panelDatosPersona.add(txtIdPersona);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(labelFont);
        lblNombre.setForeground(Color.WHITE);
        panelDatosPersona.add(lblNombre);
        panelDatosPersona.add(txtNombrePersona);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(labelFont);
        lblTelefono.setForeground(Color.WHITE);
        panelDatosPersona.add(lblTelefono);
        panelDatosPersona.add(txtTelefonoPersona);

        JLabel lblProfesion = new JLabel("Profesión:");
        lblProfesion.setFont(labelFont);
        lblProfesion.setForeground(Color.WHITE);
        panelDatosPersona.add(lblProfesion);
        panelDatosPersona.add(txtProfesionPersona);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(labelFont);
        lblTipo.setForeground(Color.WHITE);
        panelDatosPersona.add(lblTipo);
        panelDatosPersona.add(txtTipoPersona);

        return panelDatosPersona;
    }

    private JPanel createButtonPanel() {
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        panelBotones.setBackground(Color.decode("#1a1a1a"));

        btnRegistrarPersona = new JButton("Registrar");
        btnActualizarPersona = new JButton("Actualizar");
        btnEliminarPersona = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");

        // Colores vibrantes y bordes de botones
        Color btnColor = Color.decode("#FF5722"); // Naranja brillante
        Color btnHoverColor = Color.decode("#E64A19"); // Naranja más oscuro

        JButton[] buttons = {btnRegistrarPersona, btnActualizarPersona, btnEliminarPersona, btnCancelar};

        for (JButton button : buttons) {
            button.setBackground(btnColor);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setPreferredSize(new Dimension(150, 40));
            button.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.decode("#FF5722"), 3),
                    BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(btnHoverColor);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(btnColor);
                }
            });
            panelBotones.add(button);
        }

        return panelBotones;
    }

    private JPanel createTablesPanel() {
        JPanel panelTablas = new JPanel(new GridLayout(1, 2, 10, 10));
        panelTablas.setBackground(Color.decode("#1a1a1a"));

        tablaPersonas = new JTable();
        tablaProductos = new JTable();

        JScrollPane scrollPersonas = new JScrollPane(tablaPersonas);
        JScrollPane scrollProductos = new JScrollPane(tablaProductos);

        scrollPersonas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#FF5722"), 3), "Lista de Personas"));
        scrollProductos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.decode("#FF5722"), 3), "Productos Comprados"));

        panelTablas.add(scrollPersonas);
        panelTablas.add(scrollProductos);

        return panelTablas;
    }

    private void setButtonActions() {
        btnRegistrarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPersona();

            }
        });

        btnActualizarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPersona();
            }
        });

        btnEliminarPersona.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPersona();
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    private void cargarTablaPersonas() {
        List<Persona> personas = personaController.consultarListaPersonas();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Nombre", "Teléfono", "Profesión", "Tipo"}, 0);

        for (Persona p : personas) {
            model.addRow(new Object[]{p.getIdPersona(), p.getNombre(), p.getTelefono(), p.getProfesion(), p.getTipo()});
        }
        tablaPersonas.setModel(model);

        tablaPersonas.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int filaSeleccionada = tablaPersonas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    txtIdPersona.setText(tablaPersonas.getValueAt(filaSeleccionada, 0).toString());
                    txtNombrePersona.setText(tablaPersonas.getValueAt(filaSeleccionada, 1).toString());
                    txtTelefonoPersona.setText(tablaPersonas.getValueAt(filaSeleccionada, 2).toString());
                    txtProfesionPersona.setText(tablaPersonas.getValueAt(filaSeleccionada, 3).toString());
                    txtTipoPersona.setText(tablaPersonas.getValueAt(filaSeleccionada, 4).toString());
                }
            }
        });
    }

    private void cargarProductosPersonaSeleccionada() {
        int filaSeleccionada = tablaPersonas.getSelectedRow();
        if (filaSeleccionada != -1) {
            Long personaId = (Long) tablaPersonas.getValueAt(filaSeleccionada, 0);
            cargarTablaProductos(personaId);
        }
    }

    private void cargarTablaProductos(Long personaId) {
        List<PersonasProductos> compras = personaController.obtenerProductosPorPersona(personaId);
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Producto", "Cantidad", "Fecha de Compra"}, 0);

        for (PersonasProductos compra : compras) {
            model.addRow(new Object[]{compra.getProductoId(), compra.getCantidad(), compra.getFechaCompra()});
        }
        tablaProductos.setModel(model);
    }

    private void registrarPersona() {
        Persona persona = new Persona();
        persona.setIdPersona(Long.parseLong(txtIdPersona.getText()));
        persona.setNombre(txtNombrePersona.getText());
        persona.setTelefono(txtTelefonoPersona.getText());
        persona.setProfesion(txtProfesionPersona.getText());
        persona.setTipo(Integer.parseInt(txtTipoPersona.getText()));

        String mensaje = personaController.registrarPersona(persona);
        JOptionPane.showMessageDialog(this, mensaje);
        limpiarCampos();
    }

    private void actualizarPersona() {
        Persona persona = new Persona();
        persona.setIdPersona(Long.parseLong(txtIdPersona.getText()));
        persona.setNombre(txtNombrePersona.getText());
        persona.setTelefono(txtTelefonoPersona.getText());
        persona.setProfesion(txtProfesionPersona.getText());
        persona.setTipo(Integer.parseInt(txtTipoPersona.getText()));

        String mensaje = personaController.actualizarPersona(persona);
        JOptionPane.showMessageDialog(this, mensaje);
        cargarTablaPersonas();
        limpiarCampos();
    }

    private void eliminarPersona() {
        Long idPersona = Long.parseLong(txtIdPersona.getText());
        String mensaje = personaController.eliminarPersona(idPersona);
        JOptionPane.showMessageDialog(this, mensaje);
        cargarTablaPersonas();
        limpiarCampos();
    }

    private void limpiarCampos() {
        txtIdPersona.setText("");
        txtNombrePersona.setText("");
        txtTelefonoPersona.setText("");
        txtProfesionPersona.setText("");
        txtTipoPersona.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new vistaPersona().setVisible(true));
    }
}
