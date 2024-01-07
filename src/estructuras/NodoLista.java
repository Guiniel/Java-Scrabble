package estructuras;

/**
 * Esta clase indica los atributos y getters y setters de los nodos de la lista
 *
 */
public class NodoLista {

	private String letra;
	private boolean activado=false;
	private NodoLista siguiente;
	private int numerodelnodo;
	private boolean seleccionado=false;
	
	/**
	 * Constructor
	 */
	public NodoLista() {
		
	}
	/**
	 * setter letra
	 * @param letra
	 */
	public NodoLista(String letra) {
		this.letra = letra;
	}
	/**
	 * 
	 * @return letra
	 */
	public String getLetra() {
		return letra;
	}
	
	/**
	 * setter letra
	 * @param letra
	 */
	public void setLetra(String letra) {
		this.letra = letra;
	}
	
	/**
	 * 
	 * @return activado
	 */
	public boolean getActivado() {
		return activado;
	}
	/**
	 * setter activado
	 * @param activado
	 */
	public void setActivado(boolean activado) {
		this.activado = activado;
	}
	/**
	 * 
	 * @return siguiente
	 */
	public NodoLista getSiguiente() {
		return siguiente;
	}
	
	/**
	 * 
	 * @param siguiente
	 */
	public void setSiguiente(NodoLista siguiente) {
		this.siguiente = siguiente;
	}
	
	
	/**
	 * 
	 * @return numerodelnodo
	 */
	public int getNumerodelnodo() {
		return numerodelnodo;
	}
	/**
	 * setternumerodelnodo
	 * @param numerodelnodo
	 */
	public void setNumerodelnodo(int numerodelnodo) {
		this.numerodelnodo = numerodelnodo;
	}
	/**
	 * 
	 * @return seleccionado
	 */
	public boolean getSeleccionado() {
		return seleccionado;
	}
	/**
	 * setter seleccionado
	 * @param seleccionado
	 */
	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}
}
