package DatosUsuarios;

import java.io.*;
import java.util.Scanner;

/**
 * 
 * Esta clase sirve para poder guardar los datos de ingreso de un usario
 *
 */
public class Archivo {
	
	/**
	 * Constructor de la clase Archivo
	 */
	public Archivo() {	
	}
	/**
	 * Escribe los datos del usuario en un txt con su alias
	 * @param nodo el nodo del usuario
	 */
	public static void escribirFichero(NodoUsuario nodo) {
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter("src//DatosUsuarios//Registros.txt",true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(nodo.getEmail() + ",");
			bfwriter.write(nodo.getUsername() +"\n");
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
	 * Lee los datos del archivo de texto y los guarda dentro de un nodo de usuario
	 * @return duelve el nodo usuario lleno
	 */
	public static UsuarioArbol leerFichero() {
		
		File file = new File("src//DatosUsuarios//Registros.txt");
		UsuarioArbol usuarios = new UsuarioArbol();	
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String linea = scanner.nextLine();
				@SuppressWarnings("resource")
				Scanner delimitar = new Scanner(linea);
				delimitar.useDelimiter("\\s*,\\s*");
				NodoUsuario nodo= new NodoUsuario();
				nodo.setEmail(delimitar.next());
				nodo.setUsername(delimitar.next());
				usuarios.insertarElementoPrimero(nodo);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuarios;	
	}
}
