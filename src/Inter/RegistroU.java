package Inter;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DatosUsuarios.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Toolkit;
/**
 * Esta es la ventana en la que se realiza el registro
 *
 */
public class RegistroU extends JDialog {
	
	//Atributos
	private final JPanel contentPanel = new JPanel();
	private JTextField textUsername;
	private JTextField textEmail;
	
	UsuarioArbol l1 =  new UsuarioArbol();
	NodoUsuario n1 = null;
	Archivo principal = new Archivo();
	
	/**
	 * Inicia
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegistroU dialog = new RegistroU();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Verifica si el email es valido
	 * @param email email
	 * @return verdadero si el email es valido, en caso contrario devuelve falso
	 */
	private boolean verificarEmail(String email){
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "gmail.com$");
		Matcher mather = pattern.matcher(email);
		if (mather.find() == true) return true;
		else return false;
	}
	
	public RegistroU()  {
		l1 = principal.leerFichero();
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroU.class.getResource("/Img/scrabble1.png")));
		setResizable(false); 
		getContentPane().setBackground(new Color(173, 216, 230));
		setBounds(100, 100, 490, 315);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Coloque el usuario y el correo que quiera registrar.\r\n");
		lblNewLabel_1.setFont(new Font("Georgia", Font.PLAIN, 19));
		lblNewLabel_1.setBounds(23, 67, 438, 23);
		getContentPane().add(lblNewLabel_1);
		contentPanel.setBounds(0, 0, 0, 0);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JLabel lblRegistroDeUsuario = new JLabel("REGISTRO");
			lblRegistroDeUsuario.setForeground(new Color(0, 0, 153));
			lblRegistroDeUsuario.setBackground(new Color(0, 0, 139));
			lblRegistroDeUsuario.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroDeUsuario.setFont(new Font("Georgia", Font.BOLD, 40));
			lblRegistroDeUsuario.setBounds(35, 0, 414, 56);
			getContentPane().add(lblRegistroDeUsuario);
		
			
			JLabel lblUsername = new JLabel("Usuario:");
			lblUsername.setFont(new Font("Georgia", Font.PLAIN, 12));
			lblUsername.setBounds(0, 166, 51, 14);
			getContentPane().add(lblUsername);
			
			JLabel lblEmail = new JLabel("Correo:");
			lblEmail.setFont(new Font("Georgia", Font.PLAIN, 12));
			lblEmail.setBounds(0, 132, 51, 23);
			getContentPane().add(lblEmail);
			
			textUsername = new JTextField();
			textUsername.setColumns(10);
			textUsername.setBounds(61, 163, 250, 20);
			getContentPane().add(textUsername);
			
			textEmail = new JTextField();
			textEmail.setColumns(10);
			textEmail.setBounds(61, 133, 250, 20);
			getContentPane().add(textEmail);

			
			JLabel lblErrorUsuario = new JLabel("*Usuario Invalido");
			lblErrorUsuario.setHorizontalAlignment(SwingConstants.LEFT);
			lblErrorUsuario.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblErrorUsuario.setForeground(new Color(255, 0, 0));
			lblErrorUsuario.setBounds(311, 166, 173, 14);
			lblErrorUsuario.setVisible(false);
			getContentPane().add(lblErrorUsuario);
			
			JLabel lblErrorEmail = new JLabel("*Email invalido");
			lblErrorEmail.setHorizontalAlignment(SwingConstants.LEFT);
			lblErrorEmail.setFont(new Font("Georgia", Font.PLAIN, 11));
			lblErrorEmail.setForeground(Color.RED);
			lblErrorEmail.setBounds(311, 132, 173, 23);
			lblErrorEmail.setVisible(false);
			getContentPane().add(lblErrorEmail);
			
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 484, 58);
			getContentPane().add(buttonPane);
			{
				JButton btnRegistrar = new JButton("Registrar");
				btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnRegistrar.setBounds(136, 9, 156, 38);
				btnRegistrar.addMouseListener(new MouseAdapter() {
					/**
					 * Realiza el registro del usuario y valida todos los datos
					 */
					public void mouseClicked(MouseEvent e) {
						lblErrorUsuario.setVisible(false);
						lblErrorEmail.setVisible(false);
						boolean error=false;
						if (textUsername.getText().isEmpty()){
							lblErrorUsuario.setText("No dejar campo vacio");
							lblErrorUsuario.setVisible(true);
							error=true;
						}
						if (!verificarEmail(textEmail.getText())){
							lblErrorEmail.setText("Correo invalido.");
							lblErrorEmail.setVisible(true);
							error=true;
						}
						if (textEmail.getText().isEmpty()){
							lblErrorEmail.setText("No dejar campo vacio");
							lblErrorEmail.setVisible(true);
							error=true;
						}
						
						if (!error){
							NodoUsuario n1 = new NodoUsuario(textEmail.getText(),textUsername.getText());							
							NodoUsuario aux = l1.getPrimero();
						
							boolean flag1 = false, flag2=false;
							while(aux != null){
								if (textUsername.getText().equals(aux.getUsername())){
									flag1=true;
									break;
								}
								aux = aux.getRight(); 
							}
							aux = l1.getPrimero();
							while(aux != null){
								if (textEmail.getText().equals(aux.getEmail())){
									flag2=true;
									break;
								}
								aux = aux.getRight(); 
							}
							if ((!flag1)&&(!flag2)){
								l1.insertarElementoPrimero(n1);
								principal.escribirFichero(n1);
								JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
								setVisible(false); 
								Menu a = new Menu();
								a.setVisible(true);
								a.setLocationRelativeTo(null);
								dispose();
							}
							else{
								if (flag1==true)
									{lblErrorUsuario.setText("Usuario ya existe");
									lblErrorUsuario.setVisible(true);
									error=true;}
								if (flag2==true){
									lblErrorEmail.setText("Correo en uso");
									lblErrorEmail.setVisible(true);
									error=true;
								}
							}
														
						}
					}
				});
				buttonPane.setLayout(null);
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnCancelar.addMouseListener(new MouseAdapter() {
					@Override
					/**
					 * Cierra  regresa al menu de inicio
					 */
					public void mouseClicked(MouseEvent e) {
						setVisible(false); 
						Menu a = new Menu();
						a.setLocationRelativeTo(null);
						a.setVisible(true);
						dispose(); 
					}
				});
				btnCancelar.setBounds(314, 9, 143, 38);
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		
		JLabel lblNewLabel_2 = new JLabel("Debe ser un correo valido Gmail");
		lblNewLabel_2.setFont(new Font("Georgia", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(23, 98, 404, 23);
		getContentPane().add(lblNewLabel_2);
	}
			

}
