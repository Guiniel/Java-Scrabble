package estructuras;

/**
 * Clase necesaria para poder crear la lista del area de letras
 * @author Daniel
 *
 */
public class Lista {

	private NodoLista cabeza;
	private int tamanio;
	/**
	 * Constructor
	 */
	public Lista() {
		cabeza = null;
		tamanio = 0;
	}
	/**
	 * 
	 * @return true si esta vacia, false en caso contrario
	 */
	public boolean esVacia() {
		return cabeza == null;
	}
	
	/**
	 * 
	 * @return tamanio
	 */
	public int getTamanio() {
		return tamanio;
	}
	
	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	/**
	 * 
	 * @return cabeza
	 */
	public NodoLista getcabeza() {
		return cabeza;
	}

	/**
	 * Agrega letras en la lista que sera mostrada en el area de letras
	 * @param letra letra a agregar
	 */
	public void agregarAlInicio(String letra){
        NodoLista nuevo = new NodoLista(letra);
        if (esVacia()) 
           cabeza = nuevo;
        else{
            nuevo.setSiguiente(cabeza);
            cabeza = nuevo;
        }
        tamanio++;
    }
	
	/**
	 * Verifica si un nodo se encuentra en la lista
	 * @param letra letra a verificar
	 * @return verdadero si la encuentra, falso en el caso contrario
	 */
	 public boolean buscar(String letra) {
	        NodoLista aux = cabeza;
	        boolean encontrado = false;
	        
	        while(aux != null && encontrado != true){
	        	if (letra.equals(aux.getLetra())) {
	        		encontrado = true;	 
	        	}
	        	else {
	        		aux = aux.getSiguiente();	
	        	}
	        }
	        return encontrado;
	    }

	 /**
	  * Esto es para cuando un boton es presionado, entonces es tomado ese nodo de el area de letras
	  * @param aux Lista de las letras
	  * @return si la lista esta vacia no devuelve nada, si lo encuentra lo activa y lo devuelve
	  */
	 public NodoLista activarnodo(NodoLista aux) {
		 aux=cabeza;
		 while(aux.getActivado()){
			aux=aux.getSiguiente();
			if(aux==null)
			break;
		 }
		 if (aux!=null) {
			 aux.setActivado(true);
			 return aux; 
		 }		
		 else
			 return null;
	 }
	 
	 /**
	  * Para buscar si hay alguno desactivado en la lista
	  * @param aux Lista de letras
	  * @return verdadero si lo encuentra, falso en caso contrario
	  */
	 public boolean buscardesactivadosboolean(NodoLista aux) {
		 aux=cabeza;
		 while(aux.getActivado()){
			aux=aux.getSiguiente();
			if(aux==null)
			break;
		 }
		 if (aux!=null)
			 return false; 	
		 else
			 return true;
	 }
	 
	 
	 /**
	  * Verifica si hay algun nodo seleccionado en la lista
	  * @return verdadero si lo encuentra, falso en caso contrario
	  */
	 public boolean verificarSiHaySeleccionado() {
		 NodoLista aux = cabeza;
		 while (aux != null) {
			 if (aux.getSeleccionado())
				 return true;
			 else
				 aux = aux.getSiguiente();
		 }
		 return false;
	 }
	 
	 /**
	  * Este metodo sirve para seleccionar y deseleccionar un nodo de la lista
	  * @param letra Letra a comprobar
	  * @param numero Boton del area de letras
	  */
	 public void seleccionado(String letra,int numero) {
		NodoLista aux=cabeza;
		 while(!(aux.getLetra().equals(letra)&&(aux.getNumerodelnodo()==numero))){
			aux=aux.getSiguiente();
			if(aux==null)
			break;
		 }
		 if (aux!=null) {
			 if (!(aux.getSeleccionado()))
				 aux.setSeleccionado(true);
			 else
				 aux.setSeleccionado(false);
		 }		
		
	 }
	 /**
	  * Enumera la lista nuevamente a partir del 1
	  */
	 public void arreglarlalista(){
	        if (!esVacia()) {
	        	int numero=1;
	            NodoLista aux = cabeza;
	            while(aux != null){
	            	aux.setNumerodelnodo(numero);
	            	aux.setSeleccionado(false);
	            	aux.setActivado(false);
	                aux = aux.getSiguiente();
	                numero++;
	            }
	        }
	    }
	 /**
	  * Remueve una letra del area de letras, si la remueve disminuye el tamano de la lista
	  * Es usado en la clase letras
	  * @param letra letra a remover
	  */
	 public void removerPorReferencia(String letra){

	        if (buscar(letra)) {
	            if (cabeza.getLetra().equals(letra) ) {
	            	cabeza= cabeza.getSiguiente();

	            } else{
	                NodoLista aux = cabeza;
	                while(!(aux.getSiguiente().getLetra().equals(letra))){
	                    aux = aux.getSiguiente();
	                }
	                NodoLista siguiente = aux.getSiguiente().getSiguiente();
	                aux.setSiguiente(siguiente);  
	            }
	            tamanio--;
	        }
	    }
	 /**
	  * Si en el area de letras aparece un comodin, al ser tocada se podra cambiar por la letra deseada
	  * @param letra letra que sustituye al comodin
	  */
	 public void cambiarComodin(String letra) {
		 NodoLista aux = cabeza;
		 while (aux !=null) {
			 if(aux.getLetra().equals("?")) {
				 aux.setLetra(letra);
				 return;
			 }
			 else
				 aux = aux.getSiguiente();
		 }
	 }
	 /**
	  * Es un metodo particular que elimina las letras seleccionadas en la lista
	  * @param letra
	  */
	 public void removerPorReferenciaseleccionado(String letra){
	        if (buscar(letra)) {
	            if (cabeza.getLetra().equals(letra)&&(cabeza.getSeleccionado()) ) {
	                cabeza= cabeza.getSiguiente();
	            } else{
	                NodoLista aux = cabeza;
	                while((!(aux.getSiguiente().getLetra().equals(letra)))&&(!(aux.getSiguiente().getSeleccionado()))){
	                    aux = aux.getSiguiente();
	                }
	                NodoLista siguiente = aux.getSiguiente().getSiguiente();
	                aux.setSiguiente(siguiente);  
	            }
	            tamanio--;
	        }
	    } 
}
