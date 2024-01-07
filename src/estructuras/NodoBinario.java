package estructuras;

/**
 * Define la clase que indica todo lo referente a los nodos de un arbol
 * @author Daniel
 *
 */
public class NodoBinario {

	private String letra;
	private NodoBinario hizq;
	private NodoBinario hder;
	private boolean repetido=false;
	private int nivel;
	private boolean seleccionado=false;
	
	/**
	 * Constructor
	 */
	public NodoBinario() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor
	 * @param letra
	 */
	public NodoBinario(String letra) {
		super();
		this.letra = letra;
		this.hizq = null;
		this.hder = null;
		this.seleccionado=false;
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
	 * @return hizq
	 */
	public NodoBinario getHizq() {
		return hizq;
	}

	/**
	 * setter hizq
	 * @param hizq
	 */
	public void setHizq(NodoBinario hizq) {
		this.hizq = hizq;
	}
	
	/**
	 * 
	 * @return hder
	 */
	public NodoBinario getHder() {
		return hder;
	}

	/**
	 * setter hder
	 * @param hder
	 */
	public void setHder(NodoBinario hder) {
		this.hder = hder;
	}
	
	/**
	 * 
	 * @return repetido
	 */
	public boolean getRepetido() {
		return repetido;
	}

	/**
	 * setter repetido
	 */
	public void setRepetido(boolean repetido) {
		this.repetido = repetido;
	}

	/**¨
	 * 
	 * @return nivel
	 */
	public int getNivel() {
		return nivel;
	}
	
	/**
	 * setter nivel
	 * @param nivel
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
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
	
	/**
	 * Esta funcion verifica cuantos nodos completos hay a partir de uno. Util al momento de graficar
	 * @param n Arbol en tablero
	 * @return un numero que representa los nodos que hay a partir de uno
	 */
	public int nodosCompletos(NodoBinario n) {
		if (n==null)
			return 0;
		else {
			if (n.getHizq()!=null && n.getHder() != null)
				return nodosCompletos(n.hizq)+nodosCompletos(n.getHder())+1;
			return nodosCompletos(n.hizq)+nodosCompletos(n.getHder());
		}
	}
}
