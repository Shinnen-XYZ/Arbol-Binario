package BTS_Proyect;

/**
 * 
 * 
 * 
 * @author Jefer
 */
public class Nodo {
    
    int valor;          
    Nodo hijoIzquierdo; 
    Nodo hijoDerecho;   
    
    public Nodo(int valor) {
        this.valor = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }
}
