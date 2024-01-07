package estructuras;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Inter.Tablero;

/**
 * Esta es la clase que Define todas las operaciones del arbol
 *
 */
public class Arbol {

	public NodoBinario raiz;
	public boolean encontrado=false;
	public static ArrayList<String> reco=new ArrayList<String>() ;
	
	/**
	 * Constructor
	 */
	public Arbol() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor
	 * @param raiz
	 */
	public Arbol(NodoBinario raiz) {
		this.raiz=raiz;
	}
	
	/**
	 * 
	 * @return raiz
	 */
	public NodoBinario getRaiz() {
		return raiz;
	}
	
	/**
	 * setter raiz
	 * @param raiz
	 */
	public void setRaiz(NodoBinario raiz) {
		this.raiz=raiz;
	}
	
	/**
	 * Este metodo Inserta en el arbol las letras que se le vayan colocando
	 * @param letra Letra que sera ingresada
	 * @param primeravez booleano que indica si es la primera vez colocando una palabra
	 * @param nodoaseguir Una referencia para saber donde debe colocar cada letra en el Arbol
	 */
	public void InsertaArbol(String letra,boolean primeravez,NodoBinario nodoaseguir) {
		
		boolean insertado=Tablero.insertado;
		int x=-1;
		String respuesta="";
		String palabra=Tablero.palabra;
		int puntaje=Tablero.puntajeporpalabra;
		NodoBinario n=new NodoBinario(letra);
	
		 //Si el arbol esta vacio entonces el programa asigna la letra como raiz del arbol

		if (raiz==null) {
			raiz=n;
			puntaje++;
			palabra=letra;
			insertado=true;
		}
		/*
		 * Cuando es la primera palabra añade las letras automaticamente hacia la izquierda
		 */
		else {
			if(primeravez) {
				NodoBinario aux=raiz;
			  while(aux.getHizq()!=null)
				  aux=aux.getHizq();
			  aux.setHizq(n);
			  n.setNivel(aux.getNivel()+1);
			  puntaje=(n.getNivel()*2)+puntaje;
			  palabra=palabra+n.getLetra();
			  insertado=true;
		/*
		 * Cuando ya no es la primera palabra tiene que verificar por cual letra quiere empezar la nueva palabra
		 * Si Ambos hijos de la letra seleccionada estan vacios entonces pregunta hacia que lado desea anadir
		 * Si ya tiene un hijo entonces anade automaticamente en el hijo vacio
		 * Si el usuario busca una letra que no se encuentra en el nodo entonces no anade letra al arbol
		 * Si la letra se encuentra pero el usuario Elige que no la quiere entonces no anade la letra al arbol
		 * Si la letra esta en el nivel 5 entonces no anade debido a que ese es el nivel maximo que alcanza el arbol
		 * Si la letra tiene ambos hijos ocupados vuelve a preguntar donde la quiere colocar o da la opcion de no agregar letra
		 */
			}else {
				NodoBinario aux = null;
				if(nodoaseguir==null) {
					String letraAbuscar=null;
					do {
						letraAbuscar = JOptionPane.showInputDialog("Por cual letra quiere empezar su siguiente palabra.?\n Escriba 1 para cancelar.\n");
						if (letraAbuscar.equals(null)) 
							return;
						if (letraAbuscar.equals("1")) {
							return;
						}	
						letraAbuscar = (letraAbuscar).toUpperCase();
						aux = buscar(raiz,letraAbuscar);
						
						if (aux==null) {
							JOptionPane.showMessageDialog(null, "Letra no encontrada");
							return;
						}

						if (aux.getNivel() == 5) {
							JOptionPane.showMessageDialog(null, "Esta letra esta en su nivel maximo.");
							return;
						}
						
						encontrado=false;
						if (letraAbuscar.equals(null)) {
							JOptionPane.showMessageDialog(null, "Ya no hay mas letras "+ letraAbuscar);
							return;
						}
						
						
						if ((aux.getHizq()!=null)&&(aux.getHder()!=null)){	
							do {
								respuesta= JOptionPane.showInputDialog( "Aqui no se puede colocar.\n1. Buscar otra vez.\n2. No agregar letra.");
								
							if (respuesta.equals("1"))
								{letraAbuscar = JOptionPane.showInputDialog("Por cual letra quiere empezar su siguiente palabra (que se encuentre en el arbol)?\n Esriba 1 para no agregar nodo");
								letraAbuscar = (letraAbuscar).toUpperCase();
								aux=buscar(raiz,letraAbuscar);
								}
							if (respuesta.equals("2"))
								return;
							}while(!respuesta.equals("1") && !respuesta.equals("2"));
						}
						
					}while((aux==null));
					if (aux.getNivel()== 0)
						puntaje++;
					else 
						puntaje = (aux.getNivel()*2)+puntaje;
					palabra="";
					palabra=palabra+aux.getLetra();
				}
				else
					aux=nodoaseguir;
				if((aux.getHizq()==null)&&(aux.getHder()==null)) {
					while((x!=1) && (x!=2)) {
						x=Integer.parseInt(JOptionPane.showInputDialog("En que direccion desea agregar.\n1. Izquierda \n 2. Derecha \n 3. Cancelar"));
						switch(x){
						case 1:
							if (aux.getNivel()==5) {
								JOptionPane.showMessageDialog(null, "Esta letra esta en su nivel maximo.");
								return;
							}
							else {
							n.setNivel(aux.getNivel()+1);
							aux.setHizq(n);
							puntaje=(n.getNivel()*2)+puntaje;
							insertado=true;
							break;}
						case 2:
							if (aux.getNivel()==5) {
								JOptionPane.showMessageDialog(null, "Esta letra esta en su nivel maximo.");
								return;
							}
							else {
							n.setNivel(aux.getNivel()+1);
							aux.setHder(n);
							puntaje=(n.getNivel()*2)+puntaje;
							insertado=true;
							break;}
						case 3:
							return;
						default:
							JOptionPane.showMessageDialog(null, "ERROR: NUMERO NO VALIDO");
							break;
						}
					}	
				}
				else {
					if(aux.getHizq()==null) {
						if (aux.getNivel()==5) {
							JOptionPane.showMessageDialog(null, "Esta letra esta en su nivel maximo.");
							return;
						}
						else {
						n.setNivel(aux.getNivel()+1);
						aux.setHizq(n);
						puntaje=(n.getNivel()*2)+puntaje;
						insertado=true;
						}
					}	
					else
					{
						if(aux.getHder()==null) {
							if (aux.getNivel()==5) {
								JOptionPane.showMessageDialog(null, "Esta letra esta en su nivel maximo.");
								return;
							}
							else {
							n.setNivel(aux.getNivel()+1);
							aux.setHder(n);
							puntaje=(n.getNivel()*2)+puntaje;
							insertado=true;
							}
						}	
						else
						{
							if ((aux.getHizq()!=null)&&(aux.getHder()!=null))
								JOptionPane.showMessageDialog(null, "No se puede colocar.");
						}
					}
				}	
				
				if((insertado)&&(!primeravez)) {
					palabra=palabra+n.getLetra(); 
				}	
			}
			Tablero.nodoaseguir = n;
		}
		Tablero.palabra=palabra;
		Tablero.puntajeporpalabra=puntaje;
		Tablero.insertado=insertado;
	}
	
	/**
	 * Este metodo busca en el arbol un nodo que no este seleccionado para saber si agrego un nodo en el arbol y revisar
	 * al cargar
	 * @param El arbol del tablero
	 * @return devuelve verdadero si encuentra un nodo no seleccionado y falso en el caso contrario
	 */
	public boolean Buscarnoseleccionado(NodoBinario nodo){
		NodoBinario aux=nodo;
		if(aux == null)
			return false;
		else {
			if(!(aux.getSeleccionado()))
				return true;
			boolean auxbooleano=Buscarnoseleccionado(aux.getHizq());
			if(auxbooleano)
				return (auxbooleano);
			auxbooleano=Buscarnoseleccionado(aux.getHder());
			if(auxbooleano)
				return (auxbooleano);
		}
		return false;	
	}

	/**
	 * Este metodo funciona para que al cargar una palabra la misma sea fijada en el arbol y ya no se pueda modificar
	 * @param nodo Arbol del tablero
	 */
	public void fijarseleccionado(NodoBinario nodo) {
		NodoBinario aux=nodo;
		if(aux == null)
			return;
		else{
			aux.setSeleccionado(true);
			fijarseleccionado(aux.getHizq());
			fijarseleccionado(aux.getHder());
		}
	}	
	
	/**
	 * Este metodo busca en el arbol el nodo por el cual se quiere comenzar la siguiente palabra
	 * @param nodo Arbol en cuestion
	 * @param letra Letra que se va a buscar en el arbol
	 * @return si el arbol esta vacio devuelve null, si lo encuentra devuelve el nodo
	 */
	public NodoBinario buscar(NodoBinario nodo,String letra) {
		int respuesta = 0;
		if(nodo==null)
			return null;
			if ((encontrado))
				return nodo;
			if(nodo.getLetra().equals(letra)){
				do {
					respuesta= JOptionPane.showConfirmDialog(null, "Encontre la letra "+nodo.getLetra()+", quieres esta?");
					//LABEL DE LA LETRA CAMBIA DE COLOR
				if(respuesta==0)
					{
						encontrado=true;
						return nodo;
					}
				}while((respuesta==2));
				
			}
				NodoBinario aux=buscar(nodo.getHizq(),letra);
				if (aux!=null) {
					if (aux.getLetra().equals(letra))
						return aux;
				}
				aux=buscar(nodo.getHder(),letra);
				if(aux!=null) {
					if (aux.getLetra().equals(letra))
						return aux;
				}
			
		return null;
	}
	
	/**
	 * Este metodo busca La ultima letra que se agrego al arbol, sirve para el boton deshacer
	 * @param nodo Arbol del tablero
	 * @param letra letra a buscar
	 * @return si el arbol esta vacio devuelve null en caso contrario devuelve el ultimo nodo que no ha sido seleccionado
	 */
	public NodoBinario buscarHojaNoSeleccionada(NodoBinario nodo,String letra) {
		if(nodo==null)
			return null;
			if (encontrado)
				return nodo;
			if (nodo.getHizq()!= null && nodo.getHder()!= null) {
				if (nodo.getLetra().equals(letra)){
					if (!nodo.getSeleccionado()) {
						encontrado = true;
						return nodo;
					}
				}
			}
			NodoBinario aux=buscarHojaNoSeleccionada(nodo.getHizq(),letra);
			if (aux!=null) {
				if (aux.getHder()!= null && aux.getHizq()!= null) {
					if (aux.getLetra().equals(letra)) {
						if (!aux.getSeleccionado())
							return aux;
					}
				}
			}
			
			aux=buscarHojaNoSeleccionada(nodo.getHder(),letra);
			if (aux!=null) {
				if (aux.getHder()!= null && aux.getHizq()!= null) {
					if (aux.getLetra().equals(letra)) {
						if (!aux.getSeleccionado())
						return aux;	
					}
				}
			}
		return null;
	}
	
	/**
	 * Este metodo es el usado para el deshacer, se encargar de borrar el ultimo nodo colocado que no esta seleccionado
	 * @param nodo Arbol del tablero
	 * @param letra letra a borrar
	 * @return El Arbol con el ultimo nodo borrado
	 */
	public NodoBinario borrarHojas(NodoBinario nodo, String letra)  {  
		if (nodo == null)  
	         return null;  
	     nodo.setHizq(borrarHojas(nodo.getHizq(), letra)); 
	     nodo.setHder(borrarHojas(nodo.getHder(), letra)) ;
	   
	     if (nodo.getLetra() == letra && nodo.getHizq() == null && nodo.getHder() == null && !nodo.getSeleccionado() && !Tablero.bandera) {
	    	 if (nodo.getNivel() != 0)
	    	 Tablero.puntajeporpalabra = Tablero.puntajeporpalabra - nodo.getNivel()*2;
	    	 else
		    	 Tablero.puntajeporpalabra = Tablero.puntajeporpalabra - 1;
	    	 Tablero.bandera = true;
	         return null;
	     }
	     return nodo;  
	 } 
	
	/**
	 * Busca la ultima hoja no seleccionada, es usado para cuando tenemos que cambiar los colores de los botones
	 * @param nodo Arbol del tablero
	 * @return La ultima letra no seleccionada
	 */
	 public String hojanoseleccionada(NodoBinario nodo) {
			String hojas = "";
			if(nodo == null)
				return hojas;
			else{
				
				if((nodo.getHizq()==null)&&(nodo.getHder()==null))
					if (nodo.getSeleccionado()==false)
						hojas=nodo.getLetra();
					if(!(hojanoseleccionada (nodo.getHizq()).equals("")))
						hojas=hojanoseleccionada(nodo.getHizq());
					if(!(hojanoseleccionada (nodo.getHder()).equals("")))
						hojas=hojanoseleccionada(nodo.getHder());
				}	
			return hojas;
	    }
	
	/**
	 * Este es un booleano que indica si el arbol de nivel 5 esta completo, se usa para terminar el juego
	 * @param nodo Arbol del tablero
	 * @return verdadero si esta completo y falso en el caso contrario
	 */
	public boolean ArbolCompleto(NodoBinario nodo) {
		if (raiz == null) {
			return false;
		}
		if (nodo.getHder()== null && nodo.getHizq() == null && nodo.getNivel() == 5) {
			return true;
		}
		if (nodo.getHizq() != null && nodo.getHder()!= null) {
			return ArbolCompleto(nodo.getHizq()) && ArbolCompleto(nodo.getHder());
		}
		return false;
	}
	
	/**
	 * Metodo que recorre el arbol de forma preorden y lo guarda el recorrido en un array dinamico
	 * @param nodo Nodo del arbol
	 * @param reco arraylist que contiene el recorrido del arbol
	 */
	
	
	public void preorden(NodoBinario nodo) {
		if (nodo==null) {
			return;
		}
		else {
    		reco.add(nodo.getLetra());
			preorden(nodo.getHizq());
			preorden(nodo.getHder());
		}
    }
	
	
}
