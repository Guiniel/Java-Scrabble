package Inter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DatosUsuarios.Archivo;
import DatosUsuarios.NodoUsuario;
import DatosUsuarios.UsuarioArbol;
import Guardado.ArchivoJuego;
/**
 * Ventana que antecede al inicio del juego, el usuario decide si jugar una partida nueva
 * continuar una anterior o salir de la aplicacion
 */
public class Ventana1 extends JDialog {
		
		private final JPanel contentPanel = new JPanel();
		public static JTextField textUser;
		public static boolean continuar = false;
		private JTextField textEmail;
		UsuarioArbol l1 =  new UsuarioArbol();
		NodoUsuario n1 = null;
		public static boolean nuevo = false;
		Archivo principal = new Archivo();
		ArchivoJuego juego = new ArchivoJuego();
		
		/**
		 * Empieza la aplicacion
		 * @param args
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Ventana1 frame = new Ventana1();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		/**
		 * Create the frame.
		 */
		public Ventana1() {
			setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Img/scrabble1.png")));
			setBounds(100, 100, 349, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBackground(new Color(135, 206, 250));
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			{
				JButton btnNew = new JButton("PARTIDA NUEVA");
				btnNew.setBounds(40, 100, 270, 30);
				contentPanel.add(btnNew);
				btnNew.addMouseListener(new MouseAdapter() {
					/**
					 * Inicia una nueva partida
					 */
					public void mouseClicked(MouseEvent e) {
						nuevo = true;
						Tablero frame= new Tablero();
						frame.setLocationRelativeTo(null);
						frame.setVisible(true);
						setVisible(false);
						dispose();
					}
				});
				btnNew.setActionCommand("OK");
				getRootPane().setDefaultButton(btnNew);
				
			}
			
			{
				JButton btnContinue = new JButton("CONTINUAR PARTIDA");
				btnContinue.setBounds(40, 150, 270, 30);
				contentPanel.add(btnContinue);
				if (!juego.archivoExiste(Menu.textUser.getText())){
					btnContinue.setVisible(false);
				}
				else {
					btnContinue.setVisible(true);
				}
				btnContinue.addMouseListener(new MouseAdapter() {
					/**
					 * Continua una partida guardada anteriormente
					 */
					public void mouseClicked(MouseEvent e) {
						continuar=true;
						Tablero frame= new Tablero();
						frame.setVisible(true);
						frame.setLocationRelativeTo(null);
						setVisible(false);
						dispose();
					}
				});
				btnContinue.setActionCommand("OK");
				getRootPane().setDefaultButton(btnContinue);
				
			}
			
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.setBounds(40, 200, 270, 30);
				btnSalir.addActionListener(new ActionListener() {
					/**
					 * Sale de la aplicacion
					 */
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				btnSalir.setActionCommand("Cancel");
				contentPanel.add(btnSalir);
			}
			
			JLabel label = new JLabel("");
			label.setVerticalAlignment(SwingConstants.TOP);
			label.setBounds(43, 0, 256, 82);
			label.setIcon(new ImageIcon(Menu.class.getResource("/Img/scrabble1.png")));
			contentPanel.add(label);
			}
}
