package DatosUsuarios;

/**
 * clase que marca el inicio de los usuarios
 */
public class UsuarioArbol {
		
	private NodoUsuario primer;

	/**
	 * Constructor
	 */
	public UsuarioArbol(){
		this.primer = null;
	}


	/**
	 * 
	 * @return primer
	 */
	public NodoUsuario getPrimero() {
		return primer;
	}
	
	/**
	 * agrega un nuevo usario a la estructura
	 * @param n usuario agregado
	 */
	public void insertarElementoPrimero(NodoUsuario n){
		if (primer == null)
			primer = n;
		else{
			NodoUsuario aux = primer;
			primer = n;
			primer.setRight(aux);
			}			
	}
			
}

