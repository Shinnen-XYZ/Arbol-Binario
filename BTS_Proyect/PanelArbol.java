package BTS_Proyect;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * 
 * 
 * @author Jefer
 */
public class PanelArbol extends JPanel {
    
    private ArbolBST arbol;
    private static final int RADIO_NODO = 22;
    private static final int ESPACIO_H = 50;
    private static final int ESPACIO_V = 60;
    

    private int valorResaltado = -1;
    private boolean hayResaltado = false;
    
    public PanelArbol(ArbolBST arbol) {
        this.arbol = arbol;
        setBackground(new Color(33, 33, 33)); 
        setPreferredSize(new Dimension(900, 500));
    }
    
    public void resaltarNodo(int valor) {
        this.valorResaltado = valor;
        this.hayResaltado = true;
        repaint();
    }
    
    public void quitarResaltado() {
        this.hayResaltado = false;
        repaint();
    }   
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (arbol.estaVacio()) {
            g2.setColor(Color.GRAY);
            g2.setFont(new Font("Arial", Font.ITALIC, 16));
            g2.drawString("No hay nada. Inserte nodo", 
            getWidth()/2 - 200, getHeight()/2);
            return;
        }
        int anchoPanel = getWidth();
        dibujarNodo(g2, arbol.raiz, anchoPanel / 2, 40, anchoPanel / 4);
    }
    
    private void dibujarNodo(Graphics2D g2, Nodo nodo, int x, int y, int offset) {
        if (nodo == null) return;
        
        g2.setColor(new Color(100, 100, 120));
        g2.setStroke(new BasicStroke(2));
        
        if (nodo.hijoIzquierdo != null) {
            g2.drawLine(x, y, x - offset, y + ESPACIO_V);
            dibujarNodo(g2, nodo.hijoIzquierdo, x - offset, y + ESPACIO_V, offset / 2);
        }
        
        if (nodo.hijoDerecho != null) {
            g2.drawLine(x, y, x + offset, y + ESPACIO_V);
            dibujarNodo(g2, nodo.hijoDerecho, x + offset, y + ESPACIO_V, offset / 2);
        }
        
        Color colorFondo;
        Color colorBorde;
        Color colorTexto = Color.WHITE;
        
        if (hayResaltado && nodo.valor == valorResaltado) {
            colorFondo = new Color(46, 204, 113);
            colorBorde = new Color(39, 174, 96);
        } else if (nodo == arbol.raiz) {
            colorFondo = new Color(52, 73, 94);
            colorBorde = new Color(44, 62, 80);
        } else {
            colorFondo = new Color(41, 128, 185);
            colorBorde = new Color(31, 97, 141);
        }
        
        g2.setColor(new Color(0, 0, 0, 40));
        g2.fillOval(x - RADIO_NODO + 3, y - RADIO_NODO + 3, RADIO_NODO * 2, RADIO_NODO * 2);
        
        g2.setColor(colorFondo);
        g2.fillOval(x - RADIO_NODO, y - RADIO_NODO, RADIO_NODO * 2, RADIO_NODO * 2);
        
        g2.setColor(colorBorde);
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(x - RADIO_NODO, y - RADIO_NODO, RADIO_NODO * 2, RADIO_NODO * 2);
        
        g2.setColor(colorTexto);
        g2.setFont(new Font("Arial", Font.BOLD, 13));
        FontMetrics fm = g2.getFontMetrics();
        String texto = String.valueOf(nodo.valor);
        int textoX = x - fm.stringWidth(texto) / 2;
        int textoY = y + fm.getAscent() / 2 - 2;
        g2.drawString(texto, textoX, textoY);
    }
    
    public void actualizar() {
        repaint();
    }
}
