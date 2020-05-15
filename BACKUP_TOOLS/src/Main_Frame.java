import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;




public class Main_Frame {
	Configuration conf;
	DateHeure date;
	Services_tools services;

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame window = new Main_Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public Main_Frame() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	private void initialize() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame();
		conf = new Configuration();
		date = new DateHeure();
		services = new Services_tools();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./configurations/ico.png"));
		frame.getContentPane().setForeground(Color.GRAY);
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 494, 21);
		frame.getContentPane().add(menuBar);
		
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		JMenuItem mntmExporter = new JMenuItem("Exporter");
		mnFichier.add(mntmExporter);
		
		JMenuItem mntmRedmarrer = new JMenuItem("Red\u00E9marrer");
		mnFichier.add(mntmRedmarrer);
		
		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mnFichier.add(mntmQuitter);
		mntmQuitter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int rep = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter?",
						"Confirmation de sortie", 
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(rep==JOptionPane.OK_OPTION){
					System.exit(0);
				}
			}
		});
		
		JMenu mnServices = new JMenu("Services");
		menuBar.add(mnServices);
		
		JMenuItem mntmStats = new JMenuItem("Statistiques");
		mnServices.add(mntmStats);
		
		JMenu mnSauvegarde = new JMenu("Sauvegarde");
		mnServices.add(mnSauvegarde);
		
		JMenuItem mntmSauvegarder = new JMenuItem("Sauvegarder");
		mnSauvegarde.add(mntmSauvegarder);
		mntmSauvegarder.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					services.run_save();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JMenu mnCompression = new JMenu("Compression");
		mnServices.add(mnCompression);
		
		JMenuItem mntmCompressionRecente = new JMenuItem("Compression des fichiers r\u00E9cents");
		mnCompression.add(mntmCompressionRecente);
		mntmCompressionRecente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					services.run_archive();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmCompressionAnciens = new JMenuItem("Compression des fichiers anciens");
		mnCompression.add(mntmCompressionAnciens);
		
		JMenu mnConfigurations = new JMenu("Configurations");
		menuBar.add(mnConfigurations);
		
		JMenuItem mntmParamtresDeConnexion = new JMenuItem("Param\u00E8tres de connexion");
		mnConfigurations.add(mntmParamtresDeConnexion);
		
		JMenuItem mntmConfigurationsDesServices = new JMenuItem("Configurations des services");
		mnConfigurations.add(mntmConfigurationsDesServices);
		frame.setPreferredSize(new Dimension(500,300));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("BACKUP TOOLS 1.0 - Utilitaire de sauvegarde et compression");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
