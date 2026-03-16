package BTS_Proyect;

/**
 * 
 * 
 * 
 * @author Jefer
 */
public class ArbolBST {

    Nodo raiz;
    public ArbolBST() {
        this.raiz = null;
    }
    
    public void insertar(int valor) {
        raiz = insertarRecursivo(raiz, valor);
    }
 
    private Nodo insertarRecursivo(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return new Nodo(valor);
        }
        if (valor < nodoActual.valor) {
            nodoActual.hijoIzquierdo = insertarRecursivo(nodoActual.hijoIzquierdo, valor);
        }
        else if (valor > nodoActual.valor) {
            nodoActual.hijoDerecho = insertarRecursivo(nodoActual.hijoDerecho, valor);
        }
        return nodoActual;
    }
    
    public int buscar(int valor) {
        return buscarRecursivo(raiz, valor, 0);
    }
    
    private int buscarRecursivo(Nodo nodoActual, int valor, int nivel) {
        if (nodoActual == null) {
            return -1;
        }
        if (valor == nodoActual.valor) {
            return nivel;
        }
        if (valor < nodoActual.valor) {
            return buscarRecursivo(nodoActual.hijoIzquierdo, valor, nivel + 1);
        }
        else {
            return buscarRecursivo(nodoActual.hijoDerecho, valor, nivel + 1);
        }
    }

    public boolean eliminar(int valor) {
        if (buscar(valor) == -1) {
            return false; 
        }
        raiz = eliminarRecursivo(raiz, valor);
        return true;
    }
    private Nodo eliminarRecursivo(Nodo nodoActual, int valor) {
        if (nodoActual == null) {
            return null;
        }
        
        if (valor < nodoActual.valor) {
            nodoActual.hijoIzquierdo = eliminarRecursivo(nodoActual.hijoIzquierdo, valor);
        } else if (valor > nodoActual.valor) {
            nodoActual.hijoDerecho = eliminarRecursivo(nodoActual.hijoDerecho, valor);
        } else {
            if (nodoActual.hijoIzquierdo == null && nodoActual.hijoDerecho == null) {
                return null;
            }
            if (nodoActual.hijoIzquierdo == null) {
                return nodoActual.hijoDerecho;
            }
            if (nodoActual.hijoDerecho == null) {
                return nodoActual.hijoIzquierdo;
            }
            int sucesor = encontrarMinimo(nodoActual.hijoDerecho);
            nodoActual.valor = sucesor;
            nodoActual.hijoDerecho = eliminarRecursivo(nodoActual.hijoDerecho, sucesor);
        }        
        return nodoActual;
    }
    
    private int encontrarMinimo(Nodo nodo) {
        while (nodo.hijoIzquierdo != null) {
            nodo = nodo.hijoIzquierdo;
        }
        return nodo.valor;
    }   
    public String recorridoInorden() {
        StringBuilder sb = new StringBuilder();
        //inordenRecursivo(raiz, sb);
        return sb.toString().trim();
    }   
    void InOrden(Nodo nodo){
        if (nodo != null){
            InOrden(nodo.hijoIzquierdo);
            System.out.println(nodo.valor + "");
            InOrden(nodo.hijoDerecho);
        }   
    }
    private void inordenRecursivo(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            inordenRecursivo(nodo.hijoIzquierdo, sb);
            sb.append(nodo.valor).append(" ");
            inordenRecursivo(nodo.hijoDerecho, sb);
        }
       
    }
    
    public String recorridoPreorden() {
        StringBuilder sb = new StringBuilder();
        preordenRecursivo(raiz, sb);
        return sb.toString().trim();
        
    }
    
    private void preordenRecursivo(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.valor).append(" ");
            preordenRecursivo(nodo.hijoIzquierdo, sb);
            preordenRecursivo(nodo.hijoDerecho, sb);
        }
    }
    
    public String recorridoPostorden() {
        StringBuilder sb = new StringBuilder();
        postordenRecursivo(raiz, sb);
        return sb.toString().trim();
    }
    
    private void postordenRecursivo(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            postordenRecursivo(nodo.hijoIzquierdo, sb);
            postordenRecursivo(nodo.hijoDerecho, sb);
            sb.append(nodo.valor).append(" ");
        }
    }
    public int obtenerAltura() {
        return calcularAltura(raiz);
    }
    private int calcularAltura(Nodo nodo) {
        if (nodo == null) return -1;
        int alturaIzq = calcularAltura(nodo.hijoIzquierdo);
        int alturaDer = calcularAltura(nodo.hijoDerecho);
        return 1 + Math.max(alturaIzq, alturaDer);
    }
    public boolean estaVacio() {
        return raiz == null;
    }
}
