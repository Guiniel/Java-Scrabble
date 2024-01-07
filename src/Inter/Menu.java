package Inter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DatosUsuarios.UsuarioArbol;
import DatosUsuarios.Archivo;
import DatosUsuarios.NodoUsuario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
/**
 * Ventana que aparece cuando empieza el juego
 *
 */
public class Menu extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	public static JTextField textUser;
	private JTextField textEmail;
	UsuarioArbol l1 =  new UsuarioArbol();
	NodoUsuario n1 = null;
	Archivo principal = new Archivo();

	/**
	 * Empieza el programa
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Menu dialog = new Menu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Menu() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/Img/scrabble1.png")));
		l1=principal.leerFichero();
		setBounds(100, 100, 349, 246);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(135, 206, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNombreDeUsuario = new JLabel("USUARIO:");
		lblNombreDeUsuario.setBounds(97, 88, 66, 14);
		lblNombreDeUsuario.setFont(new Font("Georgia", Font.ITALIC, 12));
		contentPanel.add(lblNombreDeUsuario);
		
		textUser = new JTextField();
		textUser.setBounds(175, 85, 113, 20);
		contentPanel.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setBounds(97, 127, 66, 15);
		lblEmail.setFont(new Font("Georgia", Font.ITALIC, 12));
		contentPanel.add(lblEmail);
		
		textEmail = new JTextField();
		textEmail.setBackground(Color.WHITE);
		textEmail.setBounds(175, 124, 113, 20);
		contentPanel.add(textEmail);
		
		JLabel lblError = new JLabel("*Usuario o Email invalido");
		lblError.setForeground(new Color(255, 0, 0));
		lblError.setFont(new Font("Georgia", Font.PLAIN, 11));
		lblError.setBounds(101, 160, 187, 14);
		lblError.setVisible(false);
		contentPanel.add(lblError);
		
		JLabel label = new JLabel("");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setBounds(43, 0, 256, 82);
		label.setIcon(new ImageIcon(Menu.class.getResource("/Img/scrabble1.png")));
		contentPanel.add(label);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
			btnRegistrarUsuario.addActionListener(new ActionListener() {
				
				/**
				 * Al presionar el boton de registro, aparece la ventana de registro de usuario	
				 */
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					RegistroU a = new RegistroU();
					a.setVisible(true);
					a.setLocationRelativeTo(null);
					dispose();
				}
			});
			btnRegistrarUsuario.setActionCommand("OK");
			buttonPane.add(btnRegistrarUsuario);
			{
				JButton btnSalir = new JButton("Salir");
				btnSalir.addActionListener(new ActionListener() {
					/**
					 * Sale del juego
					 */
					public void actionPerformed(ActionEvent arg0) {
						System.exit(0);
					}
				});
				{
					JButton btnIngresar = new JButton("Ingresar");
					buttonPane.add(btnIngresar);
					btnIngresar.addMouseListener(new MouseAdapter() {
						/**
						 * Al ingresar si los datos son validos, pasa a la ventana siguiente, si no son validos
						 * indica que hay un error en la validacion
						 */
						public void mouseClicked(MouseEvent e) {
							boolean flag = false;
							NodoUsuario aux = l1.getPrimero();
							boolean error=false;
							while(aux != null){
								if (textUser.getText().equals(aux.getUsername())){
									if (textEmail.getText().equals(aux.getEmail())){
										flag=true;
										break;
									}
								}
								aux = aux.getRight(); 
							}
							if (flag){
								Ventana1 frame= new Ventana1();
								frame.setVisible(true);
								frame.setLocationRelativeTo(null);
								setVisible(false);
								dispose();
							}else{
								lblError.getText();
								lblError.setVisible(true);
								error=true;
							}
						}
					});
					btnIngresar.setActionCommand("OK");
					getRootPane().setDefaultButton(btnIngresar);
				}
				btnSalir.setActionCommand("Cancel");
				buttonPane.add(btnSalir);
			}
		}
	}
}
