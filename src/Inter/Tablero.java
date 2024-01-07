package Inter;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import dibujoArbol.Controlador;
import dibujoArbol.Lienzo;
import estructuras.*;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

import Guardado.ArchivoJuego;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Toolkit;
/**
 * Este es el tablero sobre el cual se va a jugar
 *
 */
public class Tablero extends JFrame {

	private JPanel contentPane;
	private JPanel tableroArbol;
	private JLabel textAlias;
	private JTextField textPuntuacionActual;
	private JTextField textPalabrasColocadas;
	private JTextField textRecordPuntos;
	public static int contador=0;
	public static int puntajeporpalabra=0;
	private int puntaje;
	public static boolean existepalabra=false;
	public static NodoBinario nodoaseguir=null;
	boolean primeravez=true;
	boolean termino=false;
	public static boolean bandera = false;
	String Texto="";
	public static String palabra="";
	Arbol arboloriginal =  new Arbol();
	static Lista listaoriginal = new Lista();
	Lista listainterna = new Lista();
	static Letras arrayoriginal=new Letras();
	int palabrascolocadas=0;
	Arbol auxiliar=new Arbol();
	int auxpuntospalabraanterior=puntajeporpalabra;
	public static boolean insertado=false;
	Lienzo cuadrito = new Lienzo();
	Controlador control = new Controlador(cuadrito,arboloriginal);
	Lienzo cuadritoaux = new Lienzo();
	Controlador controlaux = new Controlador(cuadritoaux, auxiliar);
	boolean repetido = false;
	boolean continuar=Ventana1.continuar;
	boolean nuevo =Ventana1.nuevo;
	ArrayList<String> valores = new ArrayList<String>();
	ArrayList<String> reco = new ArrayList<String>();
	ArchivoJuego archivo = new ArchivoJuego();
	private int record;
	JButton btnDeshacer = new JButton("DESHACER");
	
	/**
	 * @wbp.nonvisual location=285,359
	 */
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		try {
			 Tablero frame = new Tablero();
			 frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			 frame.setLocationRelativeTo(null);
			 frame.setVisible(true);
		} catch (Exception e) {
			 e.printStackTrace();
		}	
	}
	
	/**
	 * Create the frame.
	 */
	public Tablero() {
		setResizable(false);
		NodoLista aux=new NodoLista();
		/*
		 * Carga la partida anterior
		 */
		if (continuar==true){
			valores=archivo.leerFichero(Menu.textUser.getText());
			contador=Integer.parseInt(valores.get(1));
			puntaje=Integer.parseInt(valores.get(2));
			record=Integer.parseInt(valores.get(3));
			palabrascolocadas=Integer.parseInt(valores.get(4));
			for (int i=5;i<=11;i++) {
				listaoriginal.agregarAlInicio(valores.get(i));
				
			}
			listaoriginal.arreglarlalista();
			}else {
		
				arrayoriginal.generarLetras(contador, listaoriginal);		
				listaoriginal.arreglarlalista();
				contador = 0;

			}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Tablero.class.getResource("/Img/scrabble1.png")));
		setFont(new Font("Arial", Font.PLAIN, 17));
		setTitle("SCRABBLE TREE");
		setLocation(new Point(0, 300));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 728);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbltextotermino = new JLabel("Presiona partida nueva para volver a jugar");
		lbltextotermino.setHorizontalAlignment(SwingConstants.CENTER);
		lbltextotermino.setForeground(Color.WHITE);
		lbltextotermino.setVisible(false);
		
		
		JLabel lbltitulopuntajepalabra = new JLabel("PUNTAJE PALABRA");
		lbltitulopuntajepalabra.setVisible(false);
		
		JLabel lblterminojuego = new JLabel("Termino el juego");
		lblterminojuego.setHorizontalAlignment(SwingConstants.CENTER);
		lblterminojuego.setForeground(Color.WHITE);
		lblterminojuego.setVisible(false);
		
		JLabel lblgeneremasletras = new JLabel("Genere mas letras para continuar");
		lblgeneremasletras.setHorizontalAlignment(SwingConstants.CENTER);
		lblgeneremasletras.setForeground(Color.RED);
		lblgeneremasletras.setFont(new Font("Arial", Font.BOLD, 17));
		lblgeneremasletras.setOpaque(true);
		lblgeneremasletras.setBackground(Color.WHITE);
		lblgeneremasletras.setVisible(false);
		
		JLabel lblnopuede = new JLabel("No se puede generar mas letras");
		lblnopuede.setHorizontalAlignment(SwingConstants.CENTER);
		lblnopuede.setOpaque(true);
		lblnopuede.setVisible(false);
		lblnopuede.setBackground(Color.WHITE);
		lblnopuede.setFont(new Font("Arial", Font.BOLD, 17));
		lblnopuede.setForeground(Color.RED);
		lblnopuede.setBounds(749, 463, 281, 64);
		contentPane.add(lblnopuede);
		lblgeneremasletras.setBounds(749, 458, 281, 75);
		contentPane.add(lblgeneremasletras);
		lblterminojuego.setOpaque(true);
		lblterminojuego.setBorder(null);
		lblterminojuego.setBackground(Color.BLUE);
		lblterminojuego.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblterminojuego.setFont(new Font("Arial", Font.BOLD, 25));
		lblterminojuego.setBounds(453, 469, 217, 64);
		contentPane.add(lblterminojuego);

		tableroArbol = new JPanel();
		tableroArbol.setBorder(null);
		tableroArbol.setBackground(new Color(245, 222, 179));
		tableroArbol.setBounds(87, 58, 801, 459);
		contentPane.add(tableroArbol);
		tableroArbol.setLayout(new BorderLayout(0, 0));
		
		lbltitulopuntajepalabra.setFont(new Font("Arial Black", Font.BOLD, 13));
		lbltitulopuntajepalabra.setBounds(897, 115, 155, 20);
		contentPane.add(lbltitulopuntajepalabra);
		
		JLabel lblpuntajepalabra = new JLabel("");
		lblpuntajepalabra.setHorizontalAlignment(SwingConstants.CENTER);
		lblpuntajepalabra.setVisible(false);
		lblpuntajepalabra.setBorder(new LineBorder(new Color(0, 0, 255), 5));
		lblpuntajepalabra.setBounds(941, 66, 63, 51);
		contentPane.add(lblpuntajepalabra);
		lbltextotermino.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbltextotermino.setOpaque(true);
		lbltextotermino.setBackground(Color.BLUE);
		lbltextotermino.setBounds(367, 534, 378, 64);
		contentPane.add(lbltextotermino);
		
		
		JLabel lblNewLabel = new JLabel("Contador");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel.setBounds(0, 103, 89, 14);
		contentPane.add(lblNewLabel);
		
		
		JLabel lblcontador = new JLabel(""+contador+"");
		lblcontador.setHorizontalAlignment(SwingConstants.CENTER);
		lblcontador.setVerticalTextPosition(SwingConstants.TOP);
		lblcontador.setBorder(new LineBorder(Color.BLUE, 6));
		lblcontador.setBounds(20, 58, 46, 37);
		contentPane.add(lblcontador);
		
		JLabel lblRaya2 = new JLabel("");
		lblRaya2.setOpaque(true);
		lblRaya2.setIcon(new ImageIcon(Tablero.class.getResource("/Img/rojo.jpg")));
		lblRaya2.setBackground(Color.RED);
		lblRaya2.setBounds(10, 607, 1020, 4);
		contentPane.add(lblRaya2);
		
		JLabel lblRaya3 = new JLabel("");
		lblRaya3.setBorder(new LineBorder(new Color(255, 0, 0), 50));
		lblRaya3.setIcon(null);
		lblRaya3.setBackground(Color.WHITE);
		lblRaya3.setBounds(10, 43, 1029, 4);
		contentPane.add(lblRaya3);
		
		JLabel lblPuntuacionActual = new JLabel("PUNTUACION ACTUAL");
		lblPuntuacionActual.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblPuntuacionActual.setBounds(218, 0, 139, 14);
		contentPane.add(lblPuntuacionActual);
		
		JLabel lblRecord = new JLabel("RECORD PALABRAS COLOCADAS");
		lblRecord.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblRecord.setBounds(383, 0, 200, 14);
		contentPane.add(lblRecord);
		
		JLabel lblRecordPuntos = new JLabel("RECORD PALABRAS");
		lblRecordPuntos.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		lblRecordPuntos.setBounds(635, 0, 130, 14);
		contentPane.add(lblRecordPuntos);
		
		textAlias = new JLabel();
		textAlias.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		textAlias.setBounds(20, 7, 188, 30);
		if (Menu.textUser!=null)
		textAlias.setText(Menu.textUser.getText());
		contentPane.add(textAlias);
		
		textPuntuacionActual = new JTextField();
		textPuntuacionActual.setEditable(false);
		textPuntuacionActual.setHorizontalAlignment(SwingConstants.CENTER);
		textPuntuacionActual.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textPuntuacionActual.setColumns(10);
		textPuntuacionActual.setBounds(242, 16, 86, 20);
		contentPane.add(textPuntuacionActual);
		
		textPalabrasColocadas = new JTextField();
		textPalabrasColocadas.setEditable(false);
		textPalabrasColocadas.setHorizontalAlignment(SwingConstants.CENTER);
		textPalabrasColocadas.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textPalabrasColocadas.setColumns(10);
		textPalabrasColocadas.setBounds(444, 16, 86, 20);
		contentPane.add(textPalabrasColocadas);
		
		textRecordPuntos = new JTextField();
		textRecordPuntos.setEditable(false);
		textRecordPuntos.setHorizontalAlignment(SwingConstants.CENTER);
		textRecordPuntos.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
		textRecordPuntos.setColumns(10);
		textRecordPuntos.setBounds(656, 16, 86, 20);
		contentPane.add(textRecordPuntos);
		
		JButton btnGenerarLetras = new JButton("GENERAR LETRAS");//BOTON GENERAR LETRAS
		btnGenerarLetras.setForeground(Color.WHITE);
		btnGenerarLetras.setBackground(Color.RED);
		btnGenerarLetras.setBorderPainted(false);
		btnGenerarLetras.setVisible(false);
		btnGenerarLetras.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnGenerarLetras.setBounds(457, 650, 197, 28);
		contentPane.add(btnGenerarLetras);
		
		if (nuevo == true) {
			if (archivo.archivoExiste(Menu.textUser.getText())) {
				valores = archivo.leerFichero(Menu.textUser.getText());
				record = Integer.parseInt(valores.get(3));
				palabrascolocadas = Integer.parseInt(valores.get(4));
				textRecordPuntos.setText(valores.get(3));
				textPalabrasColocadas.setText(valores.get(4));
				}
				else {
					archivo.escribirFichero(Menu.textUser.getText(), contador, puntaje, record, listaoriginal, palabrascolocadas,arrayoriginal, arboloriginal);
				}
		}
		if (continuar==true) {
			btnGenerarLetras.setVisible(true);
			textPuntuacionActual.setText(valores.get(2));
			textRecordPuntos.setText(valores.get(3));
			textPalabrasColocadas.setText(valores.get(4));
			continuar = false;
			primeravez = true;
		}
		
		JButton cuadro1 = new JButton("");
		cuadro1.setBorderPainted(false);
		cuadro1.setForeground(Color.WHITE);
		cuadro1.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro1.setBorder(null);
		cuadro1.setBackground(Color.BLUE);
		cuadro1.setBounds(337,544,54,52);
		/*
		 * Le asigna una letra al cuadro 1
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro1.setText(aux.getLetra());
			cuadro1.setVisible(true);
			
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro1);
		
	    JButton cuadro2 = new JButton("");
		cuadro2.setBorderPainted(false);
		cuadro2.setForeground(Color.WHITE);
		cuadro2.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro2.setBorder(null);
		cuadro2.setBackground(Color.BLUE);
		cuadro2.setBounds(401, 544, 54, 52);
		/*
		 * Asigna una letra al cuadro 2
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro2.setText(aux.getLetra());
			cuadro2.setVisible(true);
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro2);
		
		JButton cuadro3 = new JButton("");
		cuadro3.setBorderPainted(false);
		cuadro3.setForeground(Color.WHITE);
		cuadro3.setBorder(null);
		cuadro3.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro3.setBorder(null);
		cuadro3.setBackground(Color.BLUE);
		cuadro3.setBounds(465, 544, 54, 52);
		/*
		 * Asigna una letra al cuadro 3
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro3.setText(aux.getLetra());
			cuadro3.setVisible(true);
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro3);
		
		JButton cuadro4 = new JButton("");
		cuadro4.setBorderPainted(false);
		cuadro4.setForeground(Color.WHITE);
		cuadro4.setBorder(null);
		cuadro4.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro4.setBorder(null);
		cuadro4.setBackground(Color.BLUE);
		cuadro4.setBounds(529, 544, 54, 52);
		/*
		 * Asigna una letra al cuadro 4
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro4.setText(aux.getLetra());
			cuadro4.setVisible(true);
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro4);
		
		JButton cuadro5 = new JButton("");
		cuadro5.setBorderPainted(false);
		cuadro5.setForeground(Color.WHITE);
		cuadro5.setBorder(null);
		cuadro5.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro5.setBorder(null);
		cuadro5.setBackground(Color.BLUE);
		cuadro5.setBounds(593, 544, 54, 52);
		/*
		 * Asigna una letra al cuadro 5
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro5.setText(aux.getLetra());
			cuadro5.setVisible(true);
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro5);
		
		JButton cuadro6 = new JButton("");
		cuadro6.setBorderPainted(false);
		cuadro6.setForeground(Color.WHITE);
		cuadro6.setBorder(new LineBorder(Color.BLUE, 54));
		cuadro6.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro6.setBorder(null);
		cuadro6.setBackground(Color.BLUE);
		cuadro6.setBounds(657, 544, 54, 52);
		/*
		 * Asigna una letra al cuadro 6
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro6.setText(aux.getLetra());
			cuadro6.setVisible(true);
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro6);
		
		JButton cuadro7 = new JButton("");
		cuadro7.setBorderPainted(false);
		cuadro7.setForeground(Color.WHITE);
		cuadro7.setBorder(new LineBorder(Color.BLUE, 54));
		cuadro7.setFont(new Font("Arial", Font.BOLD, 23));
		cuadro7.setBorder(null);
		cuadro7.setBackground(Color.BLUE);
		cuadro7.setBounds(721, 544, 54, 52);
		/*
		 * Asigna una letra al cuadro 7
		 */
		if (listaoriginal.buscardesactivadosboolean(aux)==false) {
			aux=listaoriginal.activarnodo(aux);
			cuadro7.setText(aux.getLetra());
			cuadro7.setVisible(true);
		}
		if (aux.getSiguiente()!= null)
			aux=aux.getSiguiente();
		contentPane.add(cuadro7);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setBorderPainted(false);
		btnSalir.setBackground(Color.RED);
		btnSalir.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnSalir.setBounds(924, 650, 99, 28);
		contentPane.add(btnSalir);
		
		JButton btnCargar = new JButton("CARGAR");
		btnCargar.setForeground(Color.WHITE);
		btnCargar.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCargar.setBorderPainted(false);
		btnCargar.setBackground(Color.RED);
		btnCargar.setBounds(214, 650, 155, 28);
		contentPane.add(btnCargar);
		
		JButton btnPartidaNueva = new JButton("PARTIDA NUEVA");
		btnPartidaNueva.setForeground(Color.WHITE);
		btnPartidaNueva.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPartidaNueva.setBorderPainted(false);
		btnPartidaNueva.setBackground(Color.RED);
		btnPartidaNueva.setBounds(827, 11, 197, 28);
		contentPane.add(btnPartidaNueva);
		/*
		 * Selecciona el boton, cambia su color y lo a�ane a la palabra actual. 
		 * Si hay un comodin, cambia su valor al deseado
		 * Esto se repite para todos los botones
		 */
		cuadro7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro7.getText().equals("?")) {
						cuadro7.setText((JOptionPane.showInputDialog("Asigne un Valor a ?")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro7.getText());
					}
					
					if (cuadro7.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro7.getText(),7);
						cuadro7.setBackground(Color.CYAN);
						cuadro7.setForeground(Color.BLACK);	
						arboloriginal.InsertaArbol(cuadro7.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
				
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro7.getText(),7);
							cuadro7.setBackground(Color.BLUE);
							cuadro7.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
					lblgeneremasletras.setVisible(true);
			}
		});
		
		cuadro6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro6.getText().equals("?")) {
						cuadro6.setText((JOptionPane.showInputDialog("Que valor le quiere dar al comodin? ")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro6.getText());
					}
				
					if (cuadro6.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro6.getText(),6);
						cuadro6.setBackground(Color.CYAN);
						cuadro6.setForeground(Color.BLACK);
						arboloriginal.InsertaArbol(cuadro6.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro6.getText(),6);
							cuadro6.setBackground(Color.BLUE);
							cuadro6.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
					lblgeneremasletras.setVisible(true);
			}
		});
		
		cuadro5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro5.getText().equals("?")) {
						cuadro5.setText((JOptionPane.showInputDialog("Que valor le quiere dar al comodin? ")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro5.getText());
					}
					
					if (cuadro5.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro5.getText(),5);
						cuadro5.setBackground(Color.CYAN);
						cuadro5.setForeground(Color.BLACK);
						arboloriginal.InsertaArbol(cuadro5.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro5.getText(),5);
							cuadro5.setBackground(Color.BLUE);
							cuadro5.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
					lblgeneremasletras.setVisible(true);
				
			}
		});
		
		cuadro4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro4.getText().equals("?")) {
						cuadro4.setText((JOptionPane.showInputDialog("Que valor le quiere dar al comodin? ")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro4.getText());
					}
					
					if (cuadro4.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro4.getText(),4);
						cuadro4.setBackground(Color.CYAN);
						cuadro4.setForeground(Color.BLACK);
						arboloriginal.InsertaArbol(cuadro4.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro4.getText(),4);
							cuadro4.setBackground(Color.BLUE);
							cuadro4.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
				 lblgeneremasletras.setVisible(true);
			}
		});
		
		cuadro3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro3.getText().equals("?")) {
						cuadro3.setText((JOptionPane.showInputDialog("Que valor le quiere dar al comodin? ")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro3.getText());
					}
					if (cuadro3.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro3.getText(),3);
						cuadro3.setBackground(Color.CYAN);
						cuadro3.setForeground(Color.BLACK);
						arboloriginal.InsertaArbol(cuadro3.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro3.getText(),3);
							cuadro3.setBackground(Color.BLUE);
							cuadro3.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
					 lblgeneremasletras.setVisible(true);
			}
			
		});
				
		cuadro2.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro2.getText().equals("?")) {
						cuadro2.setText((JOptionPane.showInputDialog("Que valor le quiere dar al comodin? ")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro2.getText());
					}
					if (cuadro2.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro2.getText(),2);
						cuadro2.setBackground(Color.CYAN);
						cuadro2.setForeground(Color.BLACK);
						arboloriginal.InsertaArbol(cuadro2.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro2.getText(),2);
							cuadro2.setBackground(Color.BLUE);
							cuadro2.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
					 lblgeneremasletras.setVisible(true);
				
				
			}});
		
		
		cuadro1.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				if(listaoriginal.getTamanio()==7) {
					lblgeneremasletras.setVisible(false);
					if(cuadro1.getText().equals("?")) {
						cuadro1.setText((JOptionPane.showInputDialog("Que valor le quiere dar al comodin? ")).toUpperCase());
						listaoriginal.cambiarComodin(cuadro1.getText());
					}
					if (cuadro1.getBackground()!=Color.CYAN) {
						listaoriginal.seleccionado(cuadro1.getText(),1);
						cuadro1.setBackground(Color.CYAN);
						cuadro1.setForeground(Color.BLACK);
						arboloriginal.InsertaArbol(cuadro1.getText(),primeravez,nodoaseguir);
						control.iniciar();
						tableroArbol.add(cuadrito);
						if (!(insertado)) {
							listaoriginal.seleccionado(cuadro1.getText(),1);
							cuadro1.setBackground(Color.BLUE);
							cuadro1.setForeground(Color.WHITE);
							JOptionPane.showMessageDialog(null, "LETRA NO AGREGADA");
						}else
							insertado=false;
					}
				}else
					 lblgeneremasletras.setVisible(true);
			}});
		
		/*
		 * Si la palabra existe, entonces deja el arbol tal y como esta, se seleccionan todas las letras de las palabras colocadas,
		 * se suman los puntos correspondientes y se eliminan de la lista las letras que se agregaron al arbol
		 * Si la palabra no existe entonces el, se borran los nodos no seleccionados del arbol y se vuelve a pintar tal como estaba antes y no se suman puntos
		 */
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if (arboloriginal.Buscarnoseleccionado(arboloriginal.getRaiz())) {
				if(listaoriginal.getTamanio()==7) {
					primeravez=false;
					nodoaseguir=null;
				
					if (Texto.contains(palabra+",")) {
						repetido = true;
						JOptionPane.showMessageDialog(null, "La palabra "+palabra+" esta repetida.");
					}
					if (!repetido) {
						try {
							API.POSTRequest(palabra);//aqui se revisa la palabra
						}catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else
						existepalabra = false;
					if(!existepalabra){//CUANDO No SE CUMPLE LA PALABRA
						puntajeporpalabra--;
						if (palabrascolocadas == 0)
							primeravez = true;
						nodoaseguir = null;
						while(arboloriginal.Buscarnoseleccionado(arboloriginal.getRaiz()) == true) {
							String ayuda = arboloriginal.hojanoseleccionada(arboloriginal.getRaiz());
							bandera = false;
							arboloriginal.setRaiz(arboloriginal.borrarHojas(arboloriginal.getRaiz(),arboloriginal.hojanoseleccionada(arboloriginal.getRaiz())));
							palabra = palabra.substring(0, palabra.length()-1);
							bandera = false;
							/*
							 * Esto es para cambiar los colores del area de letras si la palabra no se cumple
							 * Siempre que no se agregue se aplica este metodo
							 */
							if (cuadro1.getText().equals(ayuda) && !bandera && cuadro1.getBackground()==Color.CYAN) {
								cuadro1.setBackground(Color.BLUE);
								cuadro1.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro1.getText(), 1);
								bandera = true;
							}
							if (cuadro2.getText().equals(ayuda) && !bandera && cuadro2.getBackground()==Color.CYAN) {
								cuadro2.setBackground(Color.BLUE);
								cuadro2.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro2.getText(), 2);
								bandera = true;
							}
							if (cuadro3.getText().equals(ayuda) && !bandera && cuadro3.getBackground()==Color.CYAN) {
								cuadro3.setBackground(Color.BLUE);
								cuadro3.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro3.getText(), 3);
								bandera = true;
							}
							if (cuadro4.getText().equals(ayuda) && !bandera && cuadro4.getBackground()==Color.CYAN) {
								cuadro4.setBackground(Color.BLUE);
								cuadro4.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro4.getText(), 4);
								bandera = true;
							}
							if (cuadro5.getText().equals(ayuda) && !bandera && cuadro5.getBackground()==Color.CYAN) {    
								cuadro5.setBackground(Color.BLUE);
								cuadro5.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro5.getText(), 5);
							}
							if (cuadro6.getText().equals(ayuda) && !bandera && cuadro6.getBackground()==Color.CYAN) {
								cuadro6.setBackground(Color.BLUE);
								cuadro6.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro6.getText(), 6);
								bandera = true;
							}	
							if (cuadro7.getText().equals(ayuda) && !bandera && cuadro7.getBackground()==Color.CYAN) {
								cuadro7.setBackground(Color.BLUE);
								cuadro7.setForeground(Color.WHITE);
								listaoriginal.seleccionado(cuadro7.getText(), 7);
								bandera = true;
							}	
						control.iniciar();
						tableroArbol.add(cuadrito);	
					}
					control = new Controlador(cuadrito, arboloriginal);
					control.iniciar();
					tableroArbol.add(cuadrito);
				}
				else {  //existe palabra
					existepalabra=false;
					Texto=palabra+","+Texto;
					palabra="";
					String puntajeporpalabrastring=String.valueOf(puntajeporpalabra);
					lblpuntajepalabra.setText(puntajeporpalabrastring);
					puntaje=puntajeporpalabra+puntaje;
					if(puntaje>record)
						record=puntaje;
					String puntajestring=String.valueOf(puntaje);
					textPuntuacionActual.setText(puntajestring);
					String recordstring=String.valueOf(record);
					textRecordPuntos.setText(recordstring);
					puntajeporpalabra=0;
					lblpuntajepalabra.setVisible(true);
					lbltitulopuntajepalabra.setVisible(true);
					NodoLista aux=listaoriginal.getcabeza();
					palabrascolocadas++;
					String palabrascolocadasenstring=String.valueOf(palabrascolocadas);
					textPalabrasColocadas.setText(palabrascolocadasenstring);
					arboloriginal.fijarseleccionado(arboloriginal.getRaiz());						
					nodoaseguir=null;
					btnGenerarLetras.setVisible(true);
					
					/*
					 * Aqui se eliminan del area de letras, las letras que fueron agregadas al arbol
					 */
					while(aux!=null) {
						if(aux.getSeleccionado() ) {
							if ((aux.getLetra().equals(cuadro1.getText()))&&(aux.getNumerodelnodo()==1)) {
								cuadro1.setVisible(false);
							}
							if ((aux.getLetra().equals(cuadro2.getText()))&&(aux.getNumerodelnodo()==2)) {
								cuadro2.setVisible(false);
							}
							if ((aux.getLetra().equals(cuadro3.getText()))&&(aux.getNumerodelnodo()==3)) {
								cuadro3.setVisible(false);
							}				
							if ((aux.getLetra().equals(cuadro4.getText()))&&(aux.getNumerodelnodo()==4)) {
								cuadro4.setVisible(false);	
							}
							if ((aux.getLetra().equals(cuadro5.getText()))&&(aux.getNumerodelnodo()==5)) {
								cuadro5.setVisible(false);	
							}
							if ((aux.getLetra().equals(cuadro6.getText()))&&(aux.getNumerodelnodo()==6)) {
								cuadro6.setVisible(false);	
							}
							if ((aux.getLetra().equals(cuadro7.getText()))&&(aux.getNumerodelnodo()==7)) {
								cuadro7.setVisible(false);
							}
							listaoriginal.removerPorReferenciaseleccionado(aux.getLetra());
						}
						aux=aux.getSiguiente();
						
						/*
						 * Termina el juego si ya no puede generar mas letras y el area de letras no esta completa
						 */
						if (contador == 7 && listaoriginal.getTamanio()!= 7) {  // condicion para terminar juego
							btnGenerarLetras.setVisible(false);
							btnCargar.setVisible(false);     			
							btnPartidaNueva.setBounds(827, 11, 197, 28);
							lblterminojuego.setVisible(true);
							lbltextotermino.setVisible(true);
							btnPartidaNueva.setBackground(Color.RED);
							btnPartidaNueva.setFont(new Font("Times New Roman", Font.BOLD, 35));
							btnPartidaNueva.setBounds(367, 234, 378, 220);
							arboloriginal=new Arbol();
							control = new Controlador(cuadrito,arboloriginal);
							control.iniciar();
							tableroArbol.add(cuadrito);
							cuadro1.setVisible(false);
							cuadro2.setVisible(false);
							cuadro3.setVisible(false);
							cuadro4.setVisible(false);
							cuadro5.setVisible(false);
							cuadro6.setVisible(false);
							cuadro7.setVisible(false);
							btnDeshacer.setVisible(false);
						}
						/*
						 * Termina el juego si el arbol se completa y es de nivel 5
						 */
						if(arboloriginal.ArbolCompleto(arboloriginal.getRaiz())) {  //Condicion para terminar el juego
							btnGenerarLetras.setVisible(false);
							btnCargar.setVisible(false);     			
							btnPartidaNueva.setBounds(827, 11, 197, 28);
							lblterminojuego.setVisible(true);
							lbltextotermino.setVisible(true);
							btnPartidaNueva.setBackground(Color.RED);
							btnPartidaNueva.setFont(new Font("Times New Roman", Font.BOLD, 35));
							btnPartidaNueva.setBounds(367, 234, 378, 220);
							arboloriginal=new Arbol();
							control = new Controlador(cuadrito,arboloriginal);
							control.iniciar();
							tableroArbol.add(cuadrito);	
							cuadro1.setVisible(false);
							cuadro2.setVisible(false);
							cuadro3.setVisible(false);
							cuadro4.setVisible(false);
							cuadro5.setVisible(false);
							cuadro6.setVisible(false);
							cuadro7.setVisible(false);
							btnDeshacer.setVisible(false);
						}
						
					}
					listaoriginal.arreglarlalista();
					aux=listaoriginal.getcabeza();
				}
				cuadro1.setBackground(Color.BLUE);
				cuadro1.setForeground(Color.WHITE);
				cuadro2.setBackground(Color.BLUE);
				cuadro2.setForeground(Color.WHITE);
				cuadro3.setBackground(Color.BLUE);
				cuadro3.setForeground(Color.WHITE);
				cuadro4.setBackground(Color.BLUE);
				cuadro4.setForeground(Color.WHITE);
				cuadro5.setBackground(Color.BLUE);
				cuadro5.setForeground(Color.WHITE);
				cuadro6.setBackground(Color.BLUE);
				cuadro6.setForeground(Color.WHITE);
				cuadro7.setBackground(Color.BLUE);
				cuadro7.setForeground(Color.WHITE);	
				
			}else
				lblgeneremasletras.setVisible(true);
			 
		  }
			  
		}
		});
		
		
		
		/*
		 * Al presionar genera las letras del area de letras, si el area esta completa, entonces se suma al contador
		 * Si no esta completa entonces genera las letras restantes y el contador permanece intacto
		 */
		btnGenerarLetras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (listaoriginal.verificarSiHaySeleccionado()) {
					JOptionPane.showMessageDialog(null, "No puede tener letras seleccionadas al Generar letras");
				}
				else {
					if(contador!=7) {
						palabra="";
						boolean incompleto = false;
						lblgeneremasletras.setVisible(false);
						btnCargar.setVisible(true);
						if (listaoriginal.getTamanio()!=7) {
							incompleto = true;
						}
						arrayoriginal.generarLetras(contador, listaoriginal);
						if (incompleto) 
							contador--;
						listaoriginal.arreglarlalista();
						NodoLista aux=new NodoLista();
						String contadorstring=String.valueOf(contador);
						lblcontador.setText(contadorstring);
						cuadro1.setBackground(Color.BLUE);
						cuadro1.setForeground(Color.WHITE);
						cuadro2.setBackground(Color.BLUE);
						cuadro2.setForeground(Color.WHITE);
						cuadro3.setBackground(Color.BLUE);
						cuadro3.setForeground(Color.WHITE);
						cuadro4.setBackground(Color.BLUE);
						cuadro4.setForeground(Color.WHITE);
						cuadro5.setBackground(Color.BLUE);
						cuadro5.setForeground(Color.WHITE);
						cuadro6.setBackground(Color.BLUE);
						cuadro6.setForeground(Color.WHITE);
						cuadro7.setBackground(Color.BLUE);
						cuadro7.setForeground(Color.WHITE);
						if(listaoriginal.getTamanio()!=0) {
							if (contador<=7) {
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro1.setText(aux.getLetra());
									cuadro1.setVisible(true);
								}
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro2.setText(aux.getLetra());
									cuadro2.setVisible(true);
								}
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro3.setText(aux.getLetra());
									cuadro3.setVisible(true);
								}
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro4.setText(aux.getLetra());
									cuadro4.setVisible(true);
								}
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro5.setText(aux.getLetra());
									cuadro5.setVisible(true);
								}
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro6.setText(aux.getLetra());
									cuadro6.setVisible(true);
								}
								if (listaoriginal.buscardesactivadosboolean(aux)==false) {
									aux=listaoriginal.activarnodo(aux);
									cuadro7.setText(aux.getLetra());
									cuadro7.setVisible(true);
								}
							}
						}
					}else
					{
						lblnopuede.setVisible(true);
						String contadorstring=String.valueOf(contador);
						lblcontador.setText(contadorstring);
					}
				}
			
				}	
		});
		
		
		/*
		 * Limpia el tablero para poder empezar una partida nueva
		 */
		btnPartidaNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nodoaseguir=null;
				primeravez=true;
				btnGenerarLetras.setVisible(false);
				lblgeneremasletras.setVisible(false);
				lblpuntajepalabra.setVisible(false);
				lbltitulopuntajepalabra.setVisible(false);
				lblnopuede.setVisible(false);
				btnDeshacer.setVisible(true);
				puntaje=0;
				String palabrascolocadastring=String.valueOf(palabrascolocadas);
				textPalabrasColocadas.setText(palabrascolocadastring);
				String puntajestring=String.valueOf(puntaje);
				textPuntuacionActual.setText(puntajestring);
				listaoriginal=new Lista();
				arboloriginal=new Arbol();
				control = new Controlador(cuadrito,arboloriginal);
				control.iniciar();
				tableroArbol.add(cuadrito);
				contador=-1;
				arrayoriginal = new Letras();
				arrayoriginal.generarLetras(contador, listaoriginal);
				listaoriginal.arreglarlalista();
				lblcontador.setText("0");
				btnCargar.setVisible(true);
				lblterminojuego.setVisible(false);
				lbltextotermino.setVisible(false);
				btnPartidaNueva.setBounds(827, 11, 197, 28);
				btnPartidaNueva.setFont(new Font("Times New Roman", Font.BOLD, 14));
				NodoLista aux=listaoriginal.getcabeza();
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro1.setText(aux.getLetra());
					cuadro1.setVisible(true);
				}
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro2.setText(aux.getLetra());
					cuadro2.setVisible(true);
				}
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro3.setText(aux.getLetra());
					cuadro3.setVisible(true);
				}
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro4.setText(aux.getLetra());
					cuadro4.setVisible(true);
				}
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro5.setText(aux.getLetra());
					cuadro5.setVisible(true);
				}
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro6.setText(aux.getLetra());
					cuadro6.setVisible(true);
				}
				if (listaoriginal.buscardesactivadosboolean(aux)==false) {
					aux=listaoriginal.activarnodo(aux);
					cuadro7.setText(aux.getLetra());
					cuadro7.setVisible(true);
				}
				cuadro1.setBackground(Color.BLUE);
				cuadro1.setForeground(Color.WHITE);
				cuadro2.setBackground(Color.BLUE);
				cuadro2.setForeground(Color.WHITE);
				cuadro3.setBackground(Color.BLUE);
				cuadro3.setForeground(Color.WHITE);
				cuadro4.setBackground(Color.BLUE);
				cuadro4.setForeground(Color.WHITE);
				cuadro5.setBackground(Color.BLUE);
				cuadro5.setForeground(Color.WHITE);
				cuadro6.setBackground(Color.BLUE);
				cuadro6.setForeground(Color.WHITE);
				cuadro7.setBackground(Color.BLUE);
				cuadro7.setForeground(Color.WHITE);
			 
			}
		});
		
		JLabel lblRaya1 = new JLabel("");
		lblRaya1.setBackground(Color.WHITE);
		lblRaya1.setIcon(new ImageIcon(Tablero.class.getResource("/Img/rojo.jpg")));
		lblRaya1.setBounds(10, 635, 1092, 4);
		contentPane.add(lblRaya1);

		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				archivo.escribirFichero(textAlias.getText(),contador,puntaje,record,listaoriginal,palabrascolocadas,arrayoriginal,arboloriginal);
				System.exit(WIDTH);
			}
		});
		
		/*
		 * Al presionar este boton entonces elimina el ultimo nodo en entrar al arbol que no esta seleccionado
		 * Esto lo hace hasta que llega a un nodo que si esta seleccionado, es decir, hasta que la ultima palabra que no ha sido cargada
		 * es removida
		 */
		
		btnDeshacer.setForeground(Color.WHITE);
		btnDeshacer.setBackground(Color.RED);
		btnDeshacer.setBorderPainted(false);
		btnDeshacer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(arboloriginal.Buscarnoseleccionado(arboloriginal.getRaiz())==true) {
					String ayuda = arboloriginal.hojanoseleccionada(arboloriginal.getRaiz());
					bandera = false;
					arboloriginal.setRaiz(arboloriginal.borrarHojas(arboloriginal.getRaiz(),arboloriginal.hojanoseleccionada(arboloriginal.getRaiz())));
					String ayuda2 = arboloriginal.hojanoseleccionada(arboloriginal.getRaiz());
					System.out.println("AYUDA 2 "+ ayuda2);
					NodoBinario aux = arboloriginal.buscarHojaNoSeleccionada(arboloriginal.getRaiz(),ayuda2);
					nodoaseguir = aux;
					arboloriginal.encontrado = false;
					palabra = palabra.substring(0, palabra.length()-1);
					bandera = false;
					if (cuadro1.getText().equals(ayuda) && !bandera && cuadro1.getBackground()==Color.CYAN) {
						cuadro1.setBackground(Color.BLUE);
						cuadro1.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro1.getText(), 1);
						bandera = true;
					}
					if (cuadro2.getText().equals(ayuda) && !bandera && cuadro2.getBackground()==Color.CYAN) {
						cuadro2.setBackground(Color.BLUE);
						cuadro2.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro2.getText(), 2);
						bandera = true;
					}
					if (cuadro3.getText().equals(ayuda) && !bandera && cuadro3.getBackground()==Color.CYAN) {
						cuadro3.setBackground(Color.BLUE);
						cuadro3.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro3.getText(), 3);
						bandera = true;
					}
					if (cuadro4.getText().equals(ayuda) && !bandera && cuadro4.getBackground()==Color.CYAN) {
						cuadro4.setBackground(Color.BLUE);
						cuadro4.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro4.getText(), 4);
						bandera = true;
					}
					if (cuadro5.getText().equals(ayuda) && !bandera && cuadro5.getBackground()==Color.CYAN) {    
						cuadro5.setBackground(Color.BLUE);
						cuadro5.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro5.getText(), 5);
					}
					if (cuadro6.getText().equals(ayuda) && !bandera && cuadro6.getBackground()==Color.CYAN) {
						cuadro6.setBackground(Color.BLUE);
						cuadro6.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro6.getText(), 6);
						bandera = true;
					}	
					if (cuadro7.getText().equals(ayuda) && !bandera && cuadro7.getBackground()==Color.CYAN) {
						cuadro7.setBackground(Color.BLUE);
						cuadro7.setForeground(Color.WHITE);
						listaoriginal.seleccionado(cuadro7.getText(), 7);
						bandera = true;
					}
					control.iniciar();
					tableroArbol.add(cuadrito);	
				}
				else
					JOptionPane.showMessageDialog(null,"NO SE PUEDEN BORRAR YA ESTAN EN EL ARBOL");
			}
		});
		
		btnDeshacer.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnDeshacer.setBounds(20, 650, 118, 28);
		contentPane.add(btnDeshacer);
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(Tablero.class.getResource("/Img/fondo bueno3.jpg")));
		lblFondo.setBackground(Color.RED);
		lblFondo.setFont(new Font("Arial", Font.BOLD, 16));
		lblFondo.setBounds(0, -13, 1486, 713);
		contentPane.add(lblFondo);
	
		
		cuadro1.setBackground(Color.BLUE);
		cuadro1.setForeground(Color.WHITE);
		cuadro2.setBackground(Color.BLUE);
		cuadro2.setForeground(Color.WHITE);
		cuadro3.setBackground(Color.BLUE);
		cuadro3.setForeground(Color.WHITE);
		cuadro4.setBackground(Color.BLUE);
		cuadro4.setForeground(Color.WHITE);
		cuadro5.setBackground(Color.BLUE);
		cuadro5.setForeground(Color.WHITE);
		cuadro6.setBackground(Color.BLUE);
		cuadro6.setForeground(Color.WHITE);
		cuadro7.setBackground(Color.BLUE);
		cuadro7.setForeground(Color.WHITE);
		
		/*
		 * Termina el juego si el arbol se completa y es de nivel 5
		 */
		if(arboloriginal.ArbolCompleto(arboloriginal.getRaiz())) {  //Condicion para terminar el juego
			btnGenerarLetras.setVisible(false);
			btnCargar.setVisible(false);     			
			btnPartidaNueva.setBounds(827, 11, 197, 28);
			lblterminojuego.setVisible(true);
			lbltextotermino.setVisible(true);
			btnPartidaNueva.setBackground(Color.RED);
			btnPartidaNueva.setFont(new Font("Times New Roman", Font.BOLD, 35));
			btnPartidaNueva.setBounds(367, 234, 378, 220);
			arboloriginal=new Arbol();
			control = new Controlador(cuadrito,arboloriginal);
			control.iniciar();
			tableroArbol.add(cuadrito);	
		}
		
	}
}