package administradorprocesos;

import administradorprocesos.modelo.ProcesoInfo;
import administradorprocesos.servicio.ServicioProcesos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private final ServicioProcesos servicio = new ServicioProcesos();
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private JTextField txtBuscar;
    private JTextField txtIniciar;
    private JLabel lblTotal;

    public VentanaPrincipal() {
        configurarInterfaz();
        cargarProcesos();
    }

    private void configurarInterfaz() {
        setTitle("Administrador de Procesos - Java");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel();
        txtBuscar = new JTextField(15);
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnFinalizar = new JButton("Finalizar proceso");
        txtIniciar = new JTextField(15);
        JButton btnIniciar = new JButton("Iniciar");

        panelSuperior.add(new JLabel("Buscar:"));
        panelSuperior.add(txtBuscar);
        panelSuperior.add(btnActualizar);
        panelSuperior.add(btnFinalizar);
        panelSuperior.add(new JLabel("Programa:"));
        panelSuperior.add(txtIniciar);
        panelSuperior.add(btnIniciar);

        modeloTabla = new DefaultTableModel(
                new Object[]{"PID", "Nombre", "Usuario", "Hora inicio", "Activo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabla = new JTable(modeloTabla);

        lblTotal = new JLabel("Total de procesos: 0");

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(lblTotal, BorderLayout.SOUTH);

        btnActualizar.addActionListener(e -> cargarProcesos());
        btnFinalizar.addActionListener(e -> finalizarSeleccionado());
        btnIniciar.addActionListener(e -> iniciarPrograma());
        txtBuscar.addActionListener(e -> filtrarProcesos());

        // Actualiza la lista automaticamente cada 5 segundos.
        Timer timerAuto = new Timer(5000, e -> cargarProcesos());
        timerAuto.start();
    }

    private void cargarProcesos() {
        llenarTabla(servicio.obtenerProcesos());
    }

    private void filtrarProcesos() {
        String texto = txtBuscar.getText().toLowerCase();
        List<ProcesoInfo> procesos = servicio.obtenerProcesos().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(texto))
                .toList();
        llenarTabla(procesos);
    }

    private void llenarTabla(List<ProcesoInfo> procesos) {
        modeloTabla.setRowCount(0);
        for (ProcesoInfo p : procesos) {
            modeloTabla.addRow(new Object[]{
                p.getPid(), p.getNombre(), p.getUsuario(), p.getHoraInicio(), p.isActivo() ? "Si" : "No"
            });
        }
        lblTotal.setText("Total de procesos: " + procesos.size());
    }

    private void finalizarSeleccionado() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un proceso de la lista primero.");
            return;
        }

        long pid = (long) modeloTabla.getValueAt(fila, 0);
        String nombre = (String) modeloTabla.getValueAt(fila, 1);

        int confirmar = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas finalizar el proceso '" + nombre + "' (PID " + pid + ")?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirmar == JOptionPane.YES_OPTION) {
            boolean exito = servicio.finalizarProceso(pid);
            JOptionPane.showMessageDialog(this, exito
                    ? "Proceso finalizado correctamente."
                    : "No se pudo finalizar el proceso (puede requerir permisos de administrador).");
            cargarProcesos();
        }
    }

    private void iniciarPrograma() {
        String comando = txtIniciar.getText().trim();
        if (comando.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe el nombre o ruta del programa a iniciar.");
            return;
        }
        boolean exito = servicio.iniciarProceso(comando);
        JOptionPane.showMessageDialog(this, exito ? "Proceso iniciado." : "No se pudo iniciar el proceso.");
        cargarProcesos();
    }
}

