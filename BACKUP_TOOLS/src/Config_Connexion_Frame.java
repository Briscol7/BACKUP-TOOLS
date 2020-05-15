import java.awt.*;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;



public class Config_Connexion_Frame {

	private JFrame frame;
	private JTextField textField_NomServ;
	private JTextField textField_User;
	private JPasswordField passwordField_passConnexion;
	private JPasswordField passwordField_ConfirmationPassConnexion;
	private JTextField textField_Port;
	
	Configuration conf;
	Fichier fichier;
	static Config_frame conf_frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
						
						Config_Connexion_Frame window = new Config_Connexion_Frame();
						window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public Config_Connexion_Frame() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame();
		
		conf = new Configuration();
		//System.out.println("emplacement "+fichier.getFilePath());
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.setIconImage(new ImageIcon("./configurations/ico.png").getImage());
		frame.setPreferredSize(new Dimension(450,370));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Paramètres de connexion - BACKUP TOOLS");
		frame.getContentPane().setLayout(null);
		
		JLabel lblNomDuServeur = new JLabel("Nom du serveur : ");
		lblNomDuServeur.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNomDuServeur.setBounds(10, 43, 112, 14);
		frame.getContentPane().add(lblNomDuServeur);
		
		textField_NomServ = new JTextField();
		textField_NomServ.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_NomServ.setBounds(111, 40, 268, 20);
		frame.getContentPane().add(textField_NomServ);
		textField_NomServ.setColumns(10);
		
		JLabel lblUtilisateurDeConnexion = new JLabel("Utilisateur  : ");
		lblUtilisateurDeConnexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUtilisateurDeConnexion.setBounds(10, 96, 86, 14);
		frame.getContentPane().add(lblUtilisateurDeConnexion);
		
		textField_User = new JTextField();
		textField_User.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_User.setBounds(111, 93, 268, 20);
		frame.getContentPane().add(textField_User);
		textField_User.setColumns(10);
		
		JLabel lblMotDePasse = new JLabel("Mot de passe : ");
		lblMotDePasse.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMotDePasse.setBounds(10, 159, 98, 14);
		frame.getContentPane().add(lblMotDePasse);
		
		passwordField_passConnexion = new JPasswordField();
		passwordField_passConnexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordField_passConnexion.setBounds(111, 156, 268, 20);
		frame.getContentPane().add(passwordField_passConnexion);
		
		JLabel lblConfirmation = new JLabel("Confirmation : ");
		lblConfirmation.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConfirmation.setBounds(10, 213, 98, 14);
		frame.getContentPane().add(lblConfirmation);
		
		passwordField_ConfirmationPassConnexion = new JPasswordField();
		passwordField_ConfirmationPassConnexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passwordField_ConfirmationPassConnexion.setBounds(111, 210, 268, 20);
		frame.getContentPane().add(passwordField_ConfirmationPassConnexion);
		
		JButton btnQuitter_Connexion = new JButton("Quitter");
		btnQuitter_Connexion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnQuitter_Connexion.setBounds(31, 308, 89, 23);
		frame.getContentPane().add(btnQuitter_Connexion);
		btnQuitter_Connexion.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rep = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter?",
						"Confirmation de sortie", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(rep==JOptionPane.OK_OPTION){
					frame.dispose();
				}
				
			}
		});
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEnregistrer.setBounds(318, 308, 89, 23);
		frame.getContentPane().add(btnEnregistrer);
		
		btnEnregistrer.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fichier = new Fichier("./configurations/param.conf");
				try {
					fichier.WriteFile("./configurations/param.conf");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JLabel lblPort = new JLabel("Port : ");
		lblPort.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblPort.setBounds(10, 266, 46, 14);
		frame.getContentPane().add(lblPort);
		
		textField_Port = new JTextField();
		textField_Port.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_Port.setBounds(111, 263, 86, 20);
		frame.getContentPane().add(textField_Port);
		textField_Port.setColumns(10);
		
		JButton btnTesterConnexion = new JButton("Tester connexion");
		btnTesterConnexion.setBounds(157, 308, 125, 23);
		frame.getContentPane().add(btnTesterConnexion);
	}
}
