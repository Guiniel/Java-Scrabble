package Guardado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import DatosUsuarios.NodoUsuario;
import DatosUsuarios.UsuarioArbol;
import estructuras.Arbol;
import estructuras.Letras;
import estructuras.Lista;
import estructuras.NodoBinario;
import estructuras.NodoLista;

/**
 * Clase que permite guardado de los datos de una partida
 *
 */
public class ArchivoJuego {
	
	public static String valores[] = null;
	
	/**
	 * constructor
	 */
	public ArchivoJuego() {
		
	}
	/**
	 * Guarda los datos de partida de un usario
	 * @param alias Alias del usario
	 * @param contador Contador generar letras
	 * @param puntaje Puntaje 
	 * @param record Puntaje maximo obtenido
	 * @param listaPal Lista de letras 
	 * @param pal numero
	 * @param arrayofial Array de todas las letras del juego
	 * @param arboloriginal Arbol de letras 
	 */
	public static void escribirFichero(String alias, int contador, int puntaje, int record, Lista listaPal, int pal, Letras arrayoriginal,Arbol arboloriginal) {
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter("src//Guardado//"+alias+".txt",false);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(alias + "\n");
			bfwriter.write(contador + "\n");
			bfwriter.write(puntaje + "\n");
			bfwriter.write(record + "\n");
			bfwriter.write(pal + "\n");
			if (!listaPal.esVacia()) {
				NodoLista aux=listaPal.getcabeza();
				while (aux!=null) {
					bfwriter.write(aux.getLetra() + "-");
					aux=aux.getSiguiente();
				}
			}
			bfwriter.write("\n");
			/*for (int i=0;i<arrayoriginal.getTam();i++) {
				bfwriter.write(arrayoriginal.letras[i]+"-"); //me guarda el string de letras completo
			}*/
			
			arboloriginal.preorden(arboloriginal.getRaiz());
			for (int i=0;i<arboloriginal.reco.size();i++) {
				bfwriter.write(arboloriginal.reco.get(i)+"-");
			}
			

			bfwriter.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Lee el fichero en donde se encuentran los usuarios y guarda ese usuario en la estructura
	 * @param alias Nombre del usuario
	 * @return Un objeto de tipo usuario
	 */
	public ArrayList<String> leerFichero(String alias) {
		ArrayList<String> lista=new ArrayList();
		String delimitador="-";
		FileReader fr=null;
		BufferedReader br;
		try {
			
			fr=new FileReader ("src//Guardado//"+alias+".txt");
			br = new BufferedReader(fr);
			String linea;
			while ((linea=br.readLine())!=null) {
				String[] val=linea.split(delimitador);
				for (int i=0;i<val.length;i++) {
				lista.add(val[i]);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if (fr !=null)
					fr.close();
			}
			catch (IOException e2) {
				e2.printStackTrace();
			}	
		}
		return lista;
	}
	
	/**
	 * Verifica si un archivo existe
	 * @param alias
	 * @return verdadero si existe, falso si no
	 */
	public static boolean archivoExiste(String alias){
		File tmpDir = new File("src//Guardado//"+alias+".txt");
		return tmpDir.exists();
	}
	
}
