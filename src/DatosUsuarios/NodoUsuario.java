package DatosUsuarios;

/**
 * Clase que define que tendra cada Nodo de usuario
 */
public class NodoUsuario {
	
	private String email;
	private String username;
	private NodoUsuario left;
	private NodoUsuario right;
	
	/**
	 * Constructor del nodo usuario
	 * @param email
	 * @param username
	 */
	public NodoUsuario(String email,String username) {
		this.email = email;
		this.username = username;
		this.left=null;
		this.right=null;
	}

	/**
	 * Otro constructor
	 */
	public NodoUsuario() {
		
	}
	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setter email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * setter username
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return left
	 */
	public NodoUsuario getLeft() {
		return left;
	}
	 
	/**
	 * setter left
	 * @param left
	 */
	public void setLeft(NodoUsuario left) {
		this.left = left;
	}
	
	/**
	 * 
	 * @return right
	 */
	public NodoUsuario getRight() {
		return right;
	}

	/**
	 * setter right
	 * @param right
	 */
	public void setRight(NodoUsuario right) {
		this.right = right;
	}
	
	/**
	 * Metodo toString
	 */
	public String toString(){
		return("Node [EMAIL = "+ email+ "---- USUARIO = "+ username+ "]");
	} 

}
