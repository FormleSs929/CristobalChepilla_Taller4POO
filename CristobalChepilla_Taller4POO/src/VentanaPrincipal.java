
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Interfaz Grafica principal interactiva estructurada en 2 pestanas.
 * 
 * @author Tu Nombre
 * @version 1.0
 */
public class VentanaPrincipal extends JFrame {
    private final ColeccionSystem sistema;
    
    // Componentes Administrativos (Pestana 1)
    private JTextField txtNombre, txtRareza, txtExtra1, txtExtra2;
    private JComboBox<String> cbTipo;
    private JLabel lblExtra1, lblExtra2;
    private JList<Carta> listaAdmin;
    private DefaultListModel<Carta> modelAdmin;

    // Componentes Visualizacion (Pestana 2)
    private JList<Carta> listaColeccion;
    private DefaultListModel<Carta> modelColeccion;
    private JComboBox<String> cbOrdenamiento;

    public VentanaPrincipal() {
        this.sistema = ColeccionSystem.getInstance();
        setTitle("Pokemon TCG Collection - Sutrostian & POOsandon");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Administracion", crearPanelAdministon());
        tabbedPane.addTab("Ver Coleccion", crearPanelColeccion());

        add(tabbedPane);
        actualizarListas();
    }

    private JPanel crearPanelAdministon() {
        JPanel panel = new JPanel(new BorderLayout());

        // Formulario lateral izquierdo para agregar
        JPanel form = new JPanel(new GridLayout(7, 2, 5, 5));
        form.setBorder(BorderFactory.createTitledBorder("Datos de la Carta"));

        txtNombre = new JTextField();
        txtRareza = new JTextField();
        cbTipo = new JComboBox<>(new String[]{"Pokemon", "Item", "Supporter", "Energy"});
        txtExtra1 = new JTextField();
        txtExtra2 = new JTextField();

        lblExtra1 = new JLabel("Daño:");
        lblExtra2 = new JLabel("Cant. Energías:");

        form.add(new JLabel("Nombre:")); form.add(txtNombre);
        form.add(new JLabel("Rareza (Nivel):")); form.add(txtRareza);
        form.add(new JLabel("Tipo:")); form.add(cbTipo);
        form.add(lblExtra1); form.add(txtExtra1);
        form.add(lblExtra2); form.add(txtExtra2);

        JButton btnAgregar = new JButton("Agregar Carta");
        form.add(btnAgregar);

        // Cambiar etiquetas dinamicamente segun el tipo de carta seleccionado
        cbTipo.addActionListener(e -> ajustarCamposFormulario());

        // Lista de cartas derecha con botones de eliminar/modificar abajo
        JPanel rigthPanel = new JPanel(new BorderLayout());
        modelAdmin = new DefaultListModel<>();
        listaAdmin = new JList<>(modelAdmin);
        rigthPanel.add(new JScrollPane(listaAdmin), BorderLayout.CENTER);

        JPanel btnActions = new JPanel(new FlowLayout());
        JButton btnEliminar = new JButton("Eliminar Seleccionada");
        JButton btnModificar = new JButton("Modificar Atributos Extra");
        btnActions.add(btnModificar);
        btnActions.add(btnEliminar);
        rigthPanel.add(btnActions, BorderLayout.SOUTH);

        panel.add(form, BorderLayout.WEST);
        panel.add(rigthPanel, BorderLayout.CENTER);

        // Listeners de Eventos
        btnAgregar.addActionListener(e -> accionAgregar());
        btnEliminar.addActionListener(e -> accionEliminar());
        btnModificar.addActionListener(e -> accionModificar());

        return panel;
    }

    private JPanel crearPanelColeccion() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(new JLabel("Ordenar por:"));
        cbOrdenamiento = new JComboBox<>(new String[]{"Nombre", "Rareza", "Poder"});
        topBar.add(cbOrdenamiento);
        
        cbOrdenamiento.addActionListener(e -> accionOrdenar());

        modelColeccion = new DefaultListModel<>();
        listaColeccion = new JList<>(modelColeccion);
        panel.add(topBar, BorderLayout.NORTH);
        panel.add(new JScrollPane(listaColeccion), BorderLayout.CENTER);

        // Doble click o enter para ver la vista ampliada de la pauta
        listaColeccion.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listaColeccion.getSelectedValue() != null) {
                abrirDetalleCarta(listaColeccion.getSelectedValue());
                listaColeccion.clearSelection();
            }
        });

        return panel;
    }

    private void ajustarCamposFormulario() {
        String tipo = (String) cbTipo.getSelectedItem();
        txtExtra1.setVisible(true);
        txtExtra2.setVisible(true);
        lblExtra1.setVisible(true);
        lblExtra2.setVisible(true);

        switch (tipo) {
            case "Pokemon":
                lblExtra1.setText("Daño:"); lblExtra2.setText("Cant. Energías:");
                break;
            case "Item":
                lblExtra1.setText("Bonificación:"); txtExtra2.setVisible(false); lblExtra2.setVisible(false);
                break;
            case "Supporter":
                lblExtra1.setText("Efectos por Turno:"); txtExtra2.setVisible(false); lblExtra2.setVisible(false);
                break;
            case "Energy":
                lblExtra1.setText("Elemento:"); txtExtra2.setVisible(false); lblExtra2.setVisible(false);
                break;
        }
        revalidate(); repaint();
    }

    private void accionAgregar() {
        try {
            String nombre = txtNombre.getText().trim();
            int rareza = Integer.parseInt(txtRareza.getText().trim());
            String tipo = (String) cbTipo.getSelectedItem();
            
            if(nombre.isEmpty()) throw new Exception("El nombre no puede estar vacio");

            String[] extras;
            if (tipo.equals("Pokemon")) {
                extras = new String[]{txtExtra1.getText().trim(), txtExtra2.getText().trim()};
            } else {
                extras = new String[]{txtExtra1.getText().trim()};
            }

            Carta nueva = CartaFactory.crearCarta(tipo, nombre, rareza, extras);
            if (nueva != null) {
                sistema.agregarCarta(nueva);
                actualizarListas();
                limpiarCampos();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error en el ingreso de datos: " + ex.getMessage(), "Campos incorrectos", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionEliminar() {
        Carta seleccionada = listaAdmin.getSelectedValue();
        if (seleccionada != null) {
            sistema.eliminarCarta(seleccionada);
            actualizarListas();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una carta para eliminar.");
        }
    }

    private void accionModificar() {
        Carta seleccionada = listaAdmin.getSelectedValue();
        if (seleccionada == null) {
            JOptionPane.showMessageDialog(this, "Seleccione una carta de la lista.");
            return;
        }

        // Modifica solo campos especificos tal como exige la pauta (Bloquea Nombre y Rareza)
        String input1 = JOptionPane.showInputDialog(this, "Modificar Atributo Principal (" + lblExtra1.getText() + "):");
        if (input1 == null) return;

        try {
            if (seleccionada instanceof Pokemon) {
                String input2 = JOptionPane.showInputDialog(this, "Modificar Atributo Secundario (" + lblExtra2.getText() + "):");
                if (input2 == null) return;
                ((Pokemon) seleccionada).setDano(Integer.parseInt(input1));
                ((Pokemon) seleccionada).setCantEnergias(Integer.parseInt(input2));
            } else if (seleccionada instanceof Item) {
                ((Item) seleccionada).setBonificacion(Integer.parseInt(input1));
            } else if (seleccionada instanceof Supporter) {
                ((Supporter) seleccionada).setEfectosPorTurno(Integer.parseInt(input1));
            } else if (seleccionada instanceof Energy) {
                ((Energy) seleccionada).setElemento(input1);
            }
            
            sistema.guardarArchivo(); // Persistencia inmediata
            actualizarListas();
            JOptionPane.showMessageDialog(this, "Carta modificada con éxito.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al procesar la modificacion: Valores invalidos.");
        }
    }

    private void accionOrdenar() {
        String criterio = (String) cbOrdenamiento.getSelectedItem();
        if (criterio == null) return;

        switch (criterio) {
            case "Nombre": sistema.ordenarColeccion(new OrdenarPorNombre()); break;
            case "Rareza": sistema.ordenarColeccion(new OrdenarPorRareza()); break;
            case "Poder": sistema.ordenarColeccion(new OrdenarPorPoder()); break;
        }
        actualizarListas();
    }

    private void actualizarListas() {
        modelAdmin.clear();
        modelColeccion.clear();
        for (Carta c : sistema.getColeccion()) {
            modelAdmin.addElement(c);
            modelColeccion.addElement(c);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText(""); txtRareza.setText("");
        txtExtra1.setText(""); txtExtra2.setText("");
    }

    /**
     * Requerimiento: Desplegar ventana ampliada con datos, imagen propia o por defecto y poder calculado.
     */
    private void abrirDetalleCarta(Carta carta) {
        JDialog dialogo = new JDialog(this, "Detalle - " + carta.getNombre(), true);
        dialogo.setSize(400, 450);
        dialogo.setLayout(new BorderLayout(10, 10));
        dialogo.setLocationRelativeTo(this);

        // Panel de informacion basica mas poder calculado via Visitor
        JPanel infoPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        infoPanel.add(new JLabel("Nombre: " + carta.getNombre()));
        infoPanel.add(new JLabel("Tipo: " + carta.getTipo()));
        infoPanel.add(new JLabel("Rareza: " + carta.getRareza()));

        if (carta instanceof Pokemon) {
            infoPanel.add(new JLabel("Daño: " + ((Pokemon) carta).getDano()));
            infoPanel.add(new JLabel("Energias: " + ((Pokemon) carta).getCantEnergias()));
        } else if (carta instanceof Item) {
            infoPanel.add(new JLabel("Bonificación: " + ((Item) carta).getBonificacion()));
        } else if (carta instanceof Supporter) {
            infoPanel.add(new JLabel("Efectos por Turno: " + ((Supporter) carta).getEfectosPorTurno()));
        } else if (carta instanceof Energy) {
            infoPanel.add(new JLabel("Elemento: " + ((Energy) carta).getElemento()));
        }

        double poder = carta.aceptarPoder(new PoderVisitor());
        infoPanel.add(new JLabel("<html><b>Poder Calculado: " + poder + "</b></html>"));

        // Gestion de la Imagen fisica con Contingencia (Por defecto si no existe)
        JLabel lblImagen = new JLabel("", SwingConstants.CENTER);
        String rutaImg = "imagenes/" + carta.getNombre() + ".png"; // O .jpg segun tu eleccion
        File f = new File(rutaImg);
        
        if (f.exists()) {
            lblImagen.setIcon(new ImageIcon(new ImageIcon(rutaImg).getImage().getScaledInstance(180, 240, Image.SCALE_SMOOTH)));
        } else {
            // Reemplazar por tu asset por defecto en el proyecto
            lblImagen.setText("[ Imagen no disponible / Por Defecto ]");
            lblImagen.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        dialogo.add(lblImagen, BorderLayout.CENTER);
        dialogo.add(infoPanel, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }
}