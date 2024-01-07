package estructuras;
import javax.swing.JOptionPane;
import Inter.Tablero;
/**
 * Clase en la que las letras son generadas
 *
 */
public class Letras {

	public String[] letras = {"A","A","A","A","A","A","A","A","A","A","A","E","E","E","E","E","E","E","E","E","E","E",
					"O","O","O","O","O","O","O","O","S","S","S","S","S","S","S","I","I","I","I","I","I",
					"U","U","U","U","U","U","N","N","N","N","N","L","L","L","L","R","R","R","R","T","T","T","T",
					"C","C","C","C","D","D","D","D","G","G","M","M","M","B","B","B","P","P","F","F","H","H",
					"V","V","Y","J","J","K","LL","Q","RR","W","X","Z","?","?"};
	public int tam = letras.length;
	
	/**
	 * Constructor
	 */
	public Letras() {
	}

	/**
	 * 
	 * @return tam
	 */
	public int getTam() {
		return tam;
	}

	/**
	 * Este metodo sirve para generar las letras que iran en el area de letras, si ya se genero una vez y es para cambiar las letras del tablero
	 * se colocan las letras otra vez en el arreglo y se eliminan todas menos lo que diga el contador, despues se agregarian las letras nuevas 
	 * en el area de letras
	 * @param contador Numero de veces que ha generado letras con el area de letras completa
	 * @param original la lista donde se guardan las letras, es lo que se muestra en el area de letras
	 */
	public void generarLetras(int contador,Lista original) {
		int aux2=original.getTamanio();
		/*
		 * Si el contador es >=0 se empiezan a cambiar las letras menos el numero del contador
		 */
		if (contador<=7) {
			if(contador>=0) {
				if (original.getTamanio()==7) {
					while(original.getTamanio()!=contador) {
						if(!(original.getcabeza().getLetra().equals("?"))) {
						tam++;
						letras[tam]=original.getcabeza().getLetra();
					}
					original.removerPorReferencia(original.getcabeza().getLetra());
					}
				}
			}
			String[] copy = new String[tam - 1];
			String elegido = null;
			int poselegido = 0;	
			int aux = 0;
			int aux3=contador;
			boolean comodin=false;
			if (aux2==7) {
				aux2=0;
			}else
				aux3=0;
			if (aux3==-1)
				aux3=0;
			/*
			 * Aqui es donde se empiezan a generar las letras, si es la primera vez genera 7 nodos
			 */
			while(aux<7-aux3-aux2) {
				do {
					poselegido = (int) (Math.random() * tam); 
					/*
					 * Se busca una letra de forma aleatoria en el arreglo
					 */
					elegido = letras[poselegido];
				}while ( (comodin) && (elegido.equals("?")));
				if (elegido.equals("?"))
					comodin=true;
				/*
				 * Se agrega en la lista
				 */
				original.agregarAlInicio(elegido);
				/*
				 * Se elimina la posicion en el arreglo de letras
				 */
				for (int i = 0, j = 0; i < tam; i++)
					if (i != poselegido) 
						copy[j++] = letras[i];
				
				aux++;
				tam--;
			}
		for(int i=0;i<tam;i++)
			/*
			 * Aqui se devuelven las letras desechadas al arreglo de letras
			 */
			letras[i]=copy[i];
		if (original.getTamanio()==7)
			contador++;	
		Tablero.contador=contador;
		}
	}
}