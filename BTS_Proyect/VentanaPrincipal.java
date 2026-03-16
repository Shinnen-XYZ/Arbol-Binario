package BTS_Proyect;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

/**
 * 
 * 
 * 
 * @author Jefer
 */
public class VentanaPrincipal extends JFrame {
    private ArbolBST arbol;
    private PanelArbol panelArbol;
    private JTextField campoInsertar;
    private JTextField campoBuscar;
    private JTextField campoEliminar;
    private JTextArea areaResultados;
    private JLabel etiquetaEstado;
    public VentanaPrincipal() {
        arbol = new ArbolBST();
        inicializarInterfaz();
    }
    
    private void inicializarInterfaz() {
        setTitle("Árbol Binario de Búsqueda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 750);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(5, 5));

        JPanel panelTitulo = crearPanelTitulo();
        add(panelTitulo, BorderLayout.NORTH);
        
        panelArbol = new PanelArbol(arbol);
        JScrollPane scrollArbol = new JScrollPane(panelArbol);
        scrollArbol.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(41, 128, 185), 2),
            " Visualización del Árbol ",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 12), new Color(41, 128, 185)
        ));
        add(scrollArbol, BorderLayout.CENTER);
        
        JPanel panelControles = crearPanelControles();
        add(panelControles, BorderLayout.EAST);
        
        JPanel panelInferior = crearPanelInferior();
        add(panelInferior, BorderLayout.SOUTH);
        
        setVisible(true);
    }
    
    private JPanel crearPanelTitulo() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(44, 62, 80));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        JLabel titulo = new JLabel("Árbol Binario de Búsqueda");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        
        panel.add(titulo);
        return panel;
    }
    
    private JPanel crearPanelControles() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(260, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 8, 10, 8));
        panel.setBackground(new Color(236, 240, 241));
        
        panel.add(crearTituloSeccion("OPERACIONES"));
        panel.add(Box.createVerticalStrut(5));
        
        panel.add(crearEtiqueta("Insertar nodo:"));
        campoInsertar = crearCampoTexto();
        panel.add(campoInsertar);
        panel.add(Box.createVerticalStrut(3));
        JButton btnInsertar = crearBoton("Insertar", new Color(46, 204, 113));
        btnInsertar.addActionListener(e -> accionInsertar());
        campoInsertar.addActionListener(e -> accionInsertar());
        panel.add(btnInsertar);
        
        panel.add(Box.createVerticalStrut(10));
        
        panel.add(crearEtiqueta("Buscar nodo:"));
        campoBuscar = crearCampoTexto();
        panel.add(campoBuscar);
        panel.add(Box.createVerticalStrut(3));
        JButton btnBuscar = crearBoton("Buscar", new Color(52, 152, 219));
        btnBuscar.addActionListener(e -> accionBuscar());
        campoBuscar.addActionListener(e -> accionBuscar());
        panel.add(btnBuscar);
        
        panel.add(Box.createVerticalStrut(10));
        
        panel.add(crearEtiqueta("Eliminar nodo:"));
        campoEliminar = crearCampoTexto();
        panel.add(campoEliminar);
        panel.add(Box.createVerticalStrut(3));
        JButton btnEliminar = crearBoton("Eliminar", new Color(231, 76, 60));
        btnEliminar.addActionListener(e -> accionEliminar());
        campoEliminar.addActionListener(e -> accionEliminar());
        panel.add(btnEliminar);
        
        panel.add(Box.createVerticalStrut(15));
        
        panel.add(crearTituloSeccion("RECORRIDOS"));
        panel.add(Box.createVerticalStrut(5));
        
        JButton btnInorden = crearBoton("Inorden", new Color(155, 89, 182));
        btnInorden.addActionListener(e -> accionInorden());
        panel.add(btnInorden);
        panel.add(Box.createVerticalStrut(4));
        
        JButton btnPreorden = crearBoton("Preorden", new Color(155, 89, 182));
        btnPreorden.addActionListener(e -> accionPreorden());
        panel.add(btnPreorden);
        panel.add(Box.createVerticalStrut(4));
        
        JButton btnPostorden = crearBoton("Postorden", new Color(155, 89, 182));
        btnPostorden.addActionListener(e -> accionPostorden());
        panel.add(btnPostorden);
        
        panel.add(Box.createVerticalStrut(15));
        
        panel.add(crearTituloSeccion("ARCHIVOS"));
        panel.add(Box.createVerticalStrut(5));
        
        JButton btnCargar = crearBoton("Cargar desde archivo", new Color(230, 126, 34));
        btnCargar.addActionListener(e -> accionCargarArchivo());
        panel.add(btnCargar);
        panel.add(Box.createVerticalStrut(4));
        
        JButton btnGuardar = crearBoton("Guardar resultados", new Color(22, 160, 133));
        btnGuardar.addActionListener(e -> accionGuardarResultados());
        panel.add(btnGuardar);
        
        panel.add(Box.createVerticalStrut(15));
        
        JButton btnLimpiar = crearBoton("Limpiar árbol", new Color(127, 140, 141));
        btnLimpiar.addActionListener(e -> accionLimpiar());
        panel.add(btnLimpiar);
        
        panel.add(Box.createVerticalGlue()); 
        
        return panel;
    }
    
   
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 8, 8, 8));
        panel.setPreferredSize(new Dimension(0, 150));
        panel.setBackground(new Color(236, 240, 241));

        areaResultados = new JTextArea(5, 30);
        areaResultados.setEditable(false);
        areaResultados.setFont(new Font("Consolas", Font.PLAIN, 13));
        areaResultados.setBackground(new Color(44, 62, 80));
        areaResultados.setForeground(new Color(236, 240, 241));
        areaResultados.setBorder(BorderFactory.createEmptyBorder(5, 8, 5, 8));
        areaResultados.setText(">> Resultados de operaciones y recorridos aparecerán aquí \n");
        
        JScrollPane scrollResultados = new JScrollPane(areaResultados);
        scrollResultados.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(44, 62, 80), 1),
            " Consola de Resultados ",
            TitledBorder.LEFT, TitledBorder.TOP,
            new Font("Arial", Font.BOLD, 11), new Color(44, 62, 80)
        ));
        
        panel.add(scrollResultados, BorderLayout.CENTER);
        
        etiquetaEstado = new JLabel("Listo. Árbol vacío.");
        etiquetaEstado.setFont(new Font("Arial", Font.ITALIC, 11));
        etiquetaEstado.setForeground(new Color(127, 140, 141));
        etiquetaEstado.setBorder(BorderFactory.createEmptyBorder(3, 5, 0, 0));
        panel.add(etiquetaEstado, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void accionInsertar() {
        String texto = campoInsertar.getText().trim();
        if (texto.isEmpty()) {
            mostrarError("Por favor ingresa un valor numérico.");
            return;
        }
        try {
            int valor = Integer.parseInt(texto);
            arbol.insertar(valor);
            panelArbol.quitarResaltado();
            panelArbol.actualizar();
            agregarResultado("Nodo insertado: " + valor + 
                           " | Altura del árbol: " + arbol.obtenerAltura());
            actualizarEstado("Nodo " + valor + " insertado correctamente.");
            campoInsertar.setText("");
            campoInsertar.requestFocus();
        } catch (NumberFormatException ex) {
            mostrarError("Error: '" + texto + "' no es un número válido.");
        }
    }
    
    private void accionBuscar() {
        String texto = campoBuscar.getText().trim();
        if (texto.isEmpty()) {
            mostrarError("Por favor ingresa un valor para buscar.");
            return;
        }
        if (arbol.estaVacio()) {
            mostrarError("El árbol está vacío. No hay nada que buscar.");
            return;
        }
        try {
            int valor = Integer.parseInt(texto);
            int nivel = arbol.buscar(valor);
            
            if (nivel == -1) {
                panelArbol.quitarResaltado();
                agregarResultado("Valor " + valor + " NO encontrado en el árbol.");
                actualizarEstado("Búsqueda: " + valor + " no existe.");
            } else {
                panelArbol.resaltarNodo(valor);
                agregarResultado("Valor " + valor + " encontrado en nivel/altura: " + nivel + 
                               (nivel == 0 ? " (es la raíz)" : ""));
                actualizarEstado("Nodo " + valor + " encontrado en nivel " + nivel + ".");
            }
            campoBuscar.setText("");
        } catch (NumberFormatException ex) {
            mostrarError("Error: '" + texto + "' no es un número válido.");
        }
    }
    
    private void accionEliminar() {
        String texto = campoEliminar.getText().trim();
        if (texto.isEmpty()) {
            mostrarError("Por favor ingresa un valor para eliminar.");
            return;
        }
        if (arbol.estaVacio()) {
            mostrarError("El árbol está vacío. No hay nada que eliminar.");
            return;
        }
        try {
            int valor = Integer.parseInt(texto);
            boolean eliminado = arbol.eliminar(valor);
            panelArbol.quitarResaltado();
            panelArbol.actualizar();
            
            if (eliminado) {
                agregarResultado("Nodo " + valor + " eliminado correctamente.");
                actualizarEstado("Nodo " + valor + " eliminado.");
            } else {
                agregarResultado("El valor " + valor + " no existe en el árbol.");
                actualizarEstado("Error: " + valor + " no encontrado.");
            }
            campoEliminar.setText("");
        } catch (NumberFormatException ex) {
            mostrarError("Error: '" + texto + "' no es un número válido.");
        }
    }
    
    private void accionInorden() {
        if (arbol.estaVacio()) {
            mostrarError("El árbol está vacío.");
            return;
        }
        String resultado = arbol.recorridoInorden();
        agregarResultado("--- RECORRIDO INORDEN  ---");
        agregarResultado("    " + resultado);
        actualizarEstado("Recorrido Inorden ejecutado.");
    }
    
    private void accionPreorden() {
        if (arbol.estaVacio()) {
            mostrarError("El árbol está vacío.");
            return;
        }
        String resultado = arbol.recorridoPreorden();
        agregarResultado("--- RECORRIDO PREORDEN  ---");
        agregarResultado("    " + resultado);
        actualizarEstado("Recorrido Preorden ejecutado.");
    }
    
    private void accionPostorden() {
        if (arbol.estaVacio()) {
            mostrarError("El árbol está vacío.");
            return;
        }
        String resultado = arbol.recorridoPostorden();
        agregarResultado("--- RECORRIDO POSTORDEN  ---");
        agregarResultado("    " + resultado);
        actualizarEstado("Recorrido Postorden ejecutado.");
    }
    
    private void accionCargarArchivo() {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Seleccionar archivo de datos (.txt)");
        selector.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
            "Archivos de texto (*.txt)", "txt"));
        
        int resultado = selector.showOpenDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                Scanner scanner = new Scanner(archivo);
                int contador = 0;
                
                agregarResultado("--- CARGANDO DESDE: " + archivo.getName() + " ---");
                
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine().trim();
                    if (linea.isEmpty()) continue;
                    
                    String[] partes = linea.split(",");
                    for (String parte : partes) {
                        String valorStr = parte.trim();
                        if (!valorStr.isEmpty()) {
                            try {
                                int valor = Integer.parseInt(valorStr);
                                arbol.insertar(valor);
                                contador++;
                            } catch (NumberFormatException ex) {
                                agregarResultado("Valor ignorado (no es número): " + valorStr);
                            }
                        }
                    }
                }
                
                scanner.close();
                panelArbol.quitarResaltado();
                panelArbol.actualizar();
                agregarResultado("Carga completa: " + contador + " nodos insertados.");
                agregarResultado("  Altura del árbol: " + arbol.obtenerAltura());
                actualizarEstado("Archivo cargado: " + contador + " nodos.");
                
            } catch (FileNotFoundException ex) {
                mostrarError("No se pudo abrir el archivo: " + archivo.getName());
            }
        }
    }
    
    private void accionGuardarResultados() {
        if (arbol.estaVacio()) {
            mostrarError("El arbol está vacío. No hay resultados que guardar.");
            return;
        }
        
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Guardar resultados como ");
        selector.setSelectedFile(new File("resultados_bst.txt"));
        
        int resultado = selector.showSaveDialog(this);
        
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            
            if (!archivo.getName().endsWith(".txt")) {
                archivo = new File(archivo.getAbsolutePath() + ".txt");
            }
            
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(archivo));
                
                writer.println("========================================");
                writer.println("  RESULTADOS - ARBOL BINARIO DE BUSQUEDA");
                writer.println("========================================");
                writer.println();
                writer.println("Altura del arbol: " + arbol.obtenerAltura());
                writer.println();
                writer.println("RECORRIDO INORDEN (Izquierda - Raiz - Derecha):");
                writer.println("  " + arbol.recorridoInorden());
                writer.println();
                writer.println("RECORRIDO PREORDEN (Raiz - Izquierda - Derecha):");
                writer.println("  " + arbol.recorridoPreorden());
                writer.println();
                writer.println("RECORRIDO POSTORDEN (Izquierda - Derecha - Raiz):");
                writer.println("  " + arbol.recorridoPostorden());
                writer.println();
                writer.println("========================================");
                writer.println("Generado por: Aplicación BST - Estructuras de Datos");
                
                writer.close();
                
                agregarResultado("Resultados guardados en: " + archivo.getName());
                actualizarEstado("Archivo guardado: " + archivo.getName());
                
                JOptionPane.showMessageDialog(this, 
                    "Archivo guardado exitosamente:\n" + archivo.getAbsolutePath(),
                    "Guardado", JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (IOException ex) {
                mostrarError("No se pudo guardar el archivo: " + ex.getMessage());
            }
        }
    }
    
    private void accionLimpiar() {
        int confirmar = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que quieres limpiar todo el arbol?",
            "Confirmar", JOptionPane.YES_NO_OPTION);
        
        if (confirmar == JOptionPane.YES_OPTION) {
            arbol = new ArbolBST();
            panelArbol = new PanelArbol(arbol);
        
            getContentPane().removeAll();
            inicializarInterfaz();
            agregarResultado("Arbol limpiado. Empieza de nuevo.");
            actualizarEstado("Arbol limpiado.");
        }
    }
    
    private void agregarResultado(String texto) {
        areaResultados.append(texto + "\n");
        areaResultados.setCaretPosition(areaResultados.getDocument().getLength());
    }
    
    private void actualizarEstado(String texto) {
        etiquetaEstado.setText("Estado: " + texto);
    }
    
    private void mostrarError(String mensaje) {
        agregarResultado("ERROR: " + mensaje);
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.WARNING_MESSAGE);
    }
    
    private JLabel crearEtiqueta(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setFont(new Font("Arial", Font.PLAIN, 13));
        return campo;
    }
    
    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 12));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 32));
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        Color colorOscuro = color.darker();
        boton.addMouseListener(new MouseAdapter() {
            @Override public void mouseEntered(MouseEvent e) { boton.setBackground(colorOscuro); }
            @Override public void mouseExited(MouseEvent e) { boton.setBackground(color); }
        });
        
        return boton;
    }
    
    private JLabel crearTituloSeccion(String texto) {
        JLabel label = new JLabel("── " + texto + " ──");
        label.setFont(new Font("Arial", Font.BOLD, 11));
        label.setForeground(new Color(44, 62, 80));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
}
