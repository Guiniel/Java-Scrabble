package estructuras;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

import Inter.Tablero;

/**
 * Clase del API
 *
 */
public class API {

	
	/**
	 * Este es un metodo que POST que recibe una palabra, se envia un request que es verificado con un link URL de alli
	 * se sabra si la palabra es valida o no 
	 * @param palabra Palabra que sera validada
	 * @throws IOException Si hay un fallo con el servidor
	 */
	public static void POSTRequest(String palabra) throws IOException {
	    final String POST_PARAMS = palabra;
	    System.out.println(POST_PARAMS); 
	    URL obj = new URL("https://api.paaksing.com/ucab/spellcheck/es");
	    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	    postConnection.setRequestProperty("Accept", "text/plain");
	    postConnection.setRequestProperty("Content-Type", "text/plain");

	    postConnection.setDoOutput(true);
	    OutputStream os = postConnection.getOutputStream();
	    os.write(POST_PARAMS.getBytes());
	    os.flush();
	    os.close();
	    int responseCode = postConnection.getResponseCode();
	    System.out.println("POST Response Code :  " + responseCode);
	    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
	    if (responseCode == HttpURLConnection.HTTP_OK) { //success
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	            postConnection.getInputStream()));
	        String inputLine;
	        StringBuffer response = new StringBuffer();

	        while ((inputLine = in .readLine()) != null) {
	            response.append(inputLine);
	        } in .close();
	        // print result
	        if (response.toString().equals("")) {
	        	JOptionPane.showMessageDialog(null,"Palabra Valida");
	        	Tablero.existepalabra=true;}
	        else
	        	JOptionPane.showMessageDialog(null,"La palabra "+response.toString()+" no es valida." );
	    } else {
	    	JOptionPane.showMessageDialog(null,"Fallo de comunicacion.");
	    }
	}

}