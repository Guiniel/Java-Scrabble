package dibujoArbol;

import estructuras.Arbol;

/**
 * Esta es la clase que nos permtite modificar el arbol
 *
 */
public class Controlador {
	
	private Lienzo cuadrito;
	private Arbol arbolito;
	
	/**
	 * constructor del controlador
	 * @param cuadrito
	 * @param arbolito
	 */
	public Controlador(Lienzo cuadrito, Arbol arbolito) {
		this.cuadrito = cuadrito;
		this.arbolito = arbolito;
	}
	/**
	 * Aqui modifica el arbol finalmente
	 */
	public void iniciar() {
		cuadrito.setArbolito(arbolito);
	}
}
