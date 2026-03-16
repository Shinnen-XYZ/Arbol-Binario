package BTS_Proyect;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * 
 * 
 * 
 * 
 * 
 * @author Jefer
 */
public class Main {
    
    public static void main(String[] args) {
 
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Usando por defecto.");
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new VentanaPrincipal();
            }
        });
    }
}
