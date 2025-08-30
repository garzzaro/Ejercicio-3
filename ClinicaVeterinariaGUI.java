import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ClinicaVeterinariaGUI extends JFrame {
    private ClinicaVeterinaria clinica;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    
    private JTextField nombreMascotaField, especieField, razaField, edadField, responsableField, duenoMascotaField;
    private JTextField nombreDuenoField, telefonoField, direccionField;
    private JTextField fechaConsultaField, tratamientoField, veterinarioField, nombreMascotaConsultaField, motivoField;
    private JTextField buscarMascotaField, buscarDuenoField;
    private JTextArea resultadoArea;
    
    public ClinicaVeterinariaGUI(ClinicaVeterinaria clinica) {
        this.clinica = clinica;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Sistema de Clínica Veterinaria - " + clinica.getNombreClinica());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        crearMenu();
        
        mainPanel.add(crearPanelInicio(), "INICIO");
        mainPanel.add(crearPanelRegistrarMascota(), "MASCOTA");
        mainPanel.add(crearPanelRegistrarDueno(), "DUENO");
        mainPanel.add(crearPanelRegistrarConsulta(), "CONSULTA");
        mainPanel.add(crearPanelBuscar(), "BUSCAR");
        mainPanel.add(crearPanelReportes(), "REPORTES");
        
        add(mainPanel, BorderLayout.CENTER);
        cardLayout.show(mainPanel, "INICIO");
    }
    
    private void crearMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu menuRegistrar = new JMenu("Registrar");
        JMenuItem itemMascota = new JMenuItem("Nueva Mascota");
        JMenuItem itemDueno = new JMenuItem("Nuevo Dueño");
        JMenuItem itemConsulta = new JMenuItem("Nueva Consulta");
        
        itemMascota.addActionListener(e -> cardLayout.show(mainPanel, "MASCOTA"));
        itemDueno.addActionListener(e -> cardLayout.show(mainPanel, "DUENO"));
        itemConsulta.addActionListener(e -> cardLayout.show(mainPanel, "CONSULTA"));
        
        menuRegistrar.add(itemMascota);
        menuRegistrar.add(itemDueno);
        menuRegistrar.add(itemConsulta);
        
        JMenu menuConsultar = new JMenu("Consultar");
        JMenuItem itemBuscar = new JMenuItem("Buscar");
        JMenuItem itemReportes = new JMenuItem("Reportes");
        
        itemBuscar.addActionListener(e -> cardLayout.show(mainPanel, "BUSCAR"));
        itemReportes.addActionListener(e -> cardLayout.show(mainPanel, "REPORTES"));
        
        menuConsultar.add(itemBuscar);
        menuConsultar.add(itemReportes);
        
        JMenuItem itemInicio = new JMenuItem("Inicio");
        itemInicio.addActionListener(e -> cardLayout.show(mainPanel, "INICIO"));
        
        menuBar.add(itemInicio);
        menuBar.add(menuRegistrar);
        menuBar.add(menuConsultar);
        
        setJMenuBar(menuBar);
    }
    
    private JPanel crearPanelInicio() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Sistema de Clínica Veterinaria", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titulo, BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel crearPanelRegistrarMascota() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Nueva Mascota"));
        
        nombreMascotaField = new JTextField(20);
        especieField = new JTextField(20);
        razaField = new JTextField(20);
        edadField = new JTextField(20);
        responsableField = new JTextField(20);
        duenoMascotaField = new JTextField(20);
        
        panel.add(crearFilaCampo("Nombre:", nombreMascotaField));
        panel.add(crearFilaCampo("Especie:", especieField));
        panel.add(crearFilaCampo("Raza:", razaField));
        panel.add(crearFilaCampo("Edad:", edadField));
        panel.add(crearFilaCampo("Persona Responsable:", responsableField));
        panel.add(crearFilaCampo("Dueño:", duenoMascotaField));
        
        JButton btnRegistrar = new JButton("Registrar Mascota");
        btnRegistrar.addActionListener(e -> registrarMascota());
        panel.add(btnRegistrar);
        
        return panel;
    }
    
    private JPanel crearPanelRegistrarDueno() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Nuevo Dueño"));
        
        nombreDuenoField = new JTextField(20);
        telefonoField = new JTextField(20);
        direccionField = new JTextField(20);
        
        panel.add(crearFilaCampo("Nombre:", nombreDuenoField));
        panel.add(crearFilaCampo("Teléfono:", telefonoField));
        panel.add(crearFilaCampo("Dirección:", direccionField));
        
        JButton btnRegistrar = new JButton("Registrar Dueño");
        btnRegistrar.addActionListener(e -> registrarDueno());
        panel.add(btnRegistrar);
        
        return panel;
    }
    
    private JPanel crearPanelRegistrarConsulta() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Nueva Consulta"));
        
        fechaConsultaField = new JTextField(20);
        tratamientoField = new JTextField(20);
        veterinarioField = new JTextField(20);
        nombreMascotaConsultaField = new JTextField(20);
        motivoField = new JTextField(20);
        
        panel.add(crearFilaCampo("Fecha (yyyyMMdd):", fechaConsultaField));
        panel.add(crearFilaCampo("Tratamiento:", tratamientoField));
        panel.add(crearFilaCampo("Veterinario:", veterinarioField));
        panel.add(crearFilaCampo("Nombre de Mascota:", nombreMascotaConsultaField));
        panel.add(crearFilaCampo("Motivo:", motivoField));
        
        JButton btnRegistrar = new JButton("Registrar Consulta");
        btnRegistrar.addActionListener(e -> registrarConsulta());
        panel.add(btnRegistrar);
        
        return panel;
    }
    
    private JPanel crearPanelBuscar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Buscar"));
        
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        
        buscarMascotaField = new JTextField(20);
        buscarDuenoField = new JTextField(20);
        
        JPanel mascotaPanel = new JPanel(new FlowLayout());
        mascotaPanel.add(new JLabel("Buscar Mascota:"));
        mascotaPanel.add(buscarMascotaField);
        JButton btnBuscarMascota = new JButton("Buscar");
        btnBuscarMascota.addActionListener(e -> buscarMascota());
        mascotaPanel.add(btnBuscarMascota);
        
        JPanel duenoPanel = new JPanel(new FlowLayout());
        duenoPanel.add(new JLabel("Buscar Dueño:"));
        duenoPanel.add(buscarDuenoField);
        JButton btnBuscarDueno = new JButton("Buscar");
        btnBuscarDueno.addActionListener(e -> buscarDueno());
        duenoPanel.add(btnBuscarDueno);
        
        formPanel.add(mascotaPanel);
        formPanel.add(duenoPanel);
        
        resultadoArea = new JTextArea(10, 40);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);
        
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearPanelReportes() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Reportes"));
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        JButton btnEspecieMasAtendida = new JButton("Especie Más Atendida");
        btnEspecieMasAtendida.addActionListener(e -> mostrarEspecieMasAtendida());
        
        JButton btnMascotaMasConsultas = new JButton("Mascota con Más Consultas");
        btnMascotaMasConsultas.addActionListener(e -> mostrarMascotaMasConsultas());
        
        JButton btnPromedioConsultas = new JButton("Promedio Consultas/Mes");
        btnPromedioConsultas.addActionListener(e -> mostrarPromedioConsultas());
        
        JButton btnReporteCompleto = new JButton("Reporte Completo");
        btnReporteCompleto.addActionListener(e -> mostrarReporteCompleto());
        
        buttonPanel.add(btnEspecieMasAtendida);
        buttonPanel.add(btnMascotaMasConsultas);
        buttonPanel.add(btnPromedioConsultas);
        buttonPanel.add(btnReporteCompleto);
        
        JTextArea reporteArea = new JTextArea(15, 50);
        reporteArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reporteArea);
        
        resultadoArea = reporteArea;
        
        panel.add(buttonPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel crearFilaCampo(String etiqueta, JTextField campo) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(etiqueta);
        label.setPreferredSize(new Dimension(150, 25));
        panel.add(label);
        panel.add(campo);
        return panel;
    }
    
    private void registrarMascota() {
        try {
            int edad = Integer.parseInt(edadField.getText());
            Mascota nuevaMascota = new Mascota(especieField.getText(), razaField.getText(), edad, 
                                             responsableField.getText(), nombreMascotaField.getText(), 
                                             duenoMascotaField.getText());
            clinica.registrarMascota(nuevaMascota);
            JOptionPane.showMessageDialog(this, "Mascota registrada exitosamente");
            limpiarCamposMascota();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La edad debe ser un número válido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar mascota: " + e.getMessage());
        }
    }
    
    private void registrarDueno() {
        try {
            int telefono = Integer.parseInt(telefonoField.getText());
            ArrayList<Mascota> mascotas = new ArrayList<>();
            Dueno nuevoDueno = new Dueno(nombreDuenoField.getText(), telefono, mascotas, direccionField.getText());
            clinica.registrarDueno(nuevoDueno);
            JOptionPane.showMessageDialog(this, "Dueño registrado exitosamente");
            limpiarCamposDueno();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ser un número válido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar dueño: " + e.getMessage());
        }
    }
    
    private void registrarConsulta() {
        try {
            int fecha = Integer.parseInt(fechaConsultaField.getText());
            Mascota mascota = clinica.buscarMascota(nombreMascotaConsultaField.getText());
            if (mascota == null) {
                JOptionPane.showMessageDialog(this, "No se encontró la mascota");
                return;
            }
            
            Consulta nuevaConsulta = new Consulta(fecha, tratamientoField.getText(), 
                                                veterinarioField.getText(), mascota, motivoField.getText());
            clinica.registrarConsulta(nuevaConsulta);
            JOptionPane.showMessageDialog(this, "Consulta registrada exitosamente");
            limpiarCamposConsulta();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La fecha debe ser un número válido (yyyyMMdd)");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al registrar consulta: " + e.getMessage());
        }
    }
    
    private void buscarMascota() {
        Mascota mascota = clinica.buscarMascota(buscarMascotaField.getText());
        if (mascota != null) {
            String resultado = "Mascota encontrada: " + mascota.getNombre() + 
                             "\nEspecie: " + mascota.getEspecie() + 
                             "\nRaza: " + mascota.getRaza() + 
                             "\nDueño: " + mascota.getDueno();
            resultadoArea.setText(resultado);
        } else {
            resultadoArea.setText("Mascota no encontrada");
        }
    }
    
    private void buscarDueno() {
        Dueno dueno = clinica.buscarDueno(buscarDuenoField.getText());
        if (dueno != null) {
            String resultado = "Dueño encontrado: " + dueno.getNombre() + 
                             "\nTeléfono: " + dueno.getTelefono() + 
                             "\nDirección: " + dueno.getDireccion();
            resultadoArea.setText(resultado);
        } else {
            resultadoArea.setText("Dueño no encontrado");
        }
    }
    
    private void mostrarEspecieMasAtendida() {
        String especie = clinica.especieMasAtendida();
        String resultado = especie != null ? "Especie más atendida: " + especie : "No hay consultas registradas";
        resultadoArea.setText(resultado);
    }
    
    private void mostrarMascotaMasConsultas() {
        Mascota mascota = clinica.mascotaConMasConsultas();
        String resultado = mascota != null ? "Mascota con más consultas: " + mascota.getNombre() + 
                                          " (Especie: " + mascota.getEspecie() + ")" : "No hay consultas registradas";
        resultadoArea.setText(resultado);
    }
    
    private void mostrarPromedioConsultas() {
        Double promedio = clinica.promedioConsultaMes();
        resultadoArea.setText("Promedio de consultas por mes: " + String.format("%.2f", promedio));
    }
    
    private void mostrarReporteCompleto() {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTES COMPLETOS\n");
        sb.append("==================\n\n");
        
        String especie = clinica.especieMasAtendida();
        sb.append("Especie más atendida: ").append(especie != null ? especie : "Sin datos").append("\n");
        
        Mascota mascota = clinica.mascotaConMasConsultas();
        sb.append("Mascota con más consultas: ");
        if (mascota != null) {
            sb.append(mascota.getNombre()).append(" (Dueño: ").append(mascota.getDueno()).append(")");
        } else {
            sb.append("Sin datos");
        }
        sb.append("\n");
        
        sb.append("Promedio consultas/mes: ").append(String.format("%.2f", clinica.promedioConsultaMes())).append("\n\n");
        
        sb.append("Lista de dueños:\n");
        if (clinica.getDuenos().isEmpty()) {
            sb.append("No hay dueños registrados.\n");
        } else {
            for (Dueno d : clinica.getDuenos()) {
                sb.append("- ").append(d.getNombre()).append(" | Tel: ").append(d.getTelefono()).append("\n");
            }
        }
        
        sb.append("\nLista de mascotas:\n");
        if (clinica.getMascotas().isEmpty()) {
            sb.append("No hay mascotas registradas.\n");
        } else {
            for (Mascota m : clinica.getMascotas()) {
                sb.append("- ").append(m.getNombre()).append(" (").append(m.getEspecie()).append(")\n");
            }
        }
        
        sb.append("\nLista de consultas:\n");
        if (clinica.getConsultas().isEmpty()) {
            sb.append("No hay consultas registradas.\n");
        } else {
            for (Consulta c : clinica.getConsultas()) {
                sb.append("- Fecha: ").append(c.getFechaDeConsulta())
                  .append(" | Mascota: ").append(c.getMascota().getNombre())
                  .append(" | Motivo: ").append(c.getMotivoVisita()).append("\n");
            }
        }
        
        resultadoArea.setText(sb.toString());
    }
    
    private void limpiarCamposMascota() {
        nombreMascotaField.setText("");
        especieField.setText("");
        razaField.setText("");
        edadField.setText("");
        responsableField.setText("");
        duenoMascotaField.setText("");
    }
    
    private void limpiarCamposDueno() {
        nombreDuenoField.setText("");
        telefonoField.setText("");
        direccionField.setText("");
    }
    
    private void limpiarCamposConsulta() {
        fechaConsultaField.setText("");
        tratamientoField.setText("");
        veterinarioField.setText("");
        nombreMascotaConsultaField.setText("");
        motivoField.setText("");
    }
    
    public static void main(String[] args) {
        ArrayList<Dueno> duenos = new ArrayList<>();
        ArrayList<Mascota> mascotas = new ArrayList<>();
        ArrayList<Consulta> consultas = new ArrayList<>();
        
        ClinicaVeterinaria clinica = new ClinicaVeterinaria("Central", duenos, mascotas, consultas);
        
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ClinicaVeterinariaGUI(clinica).setVisible(true);
        });
    }
}