import java.awt.*;

import javax.swing.*;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.event.*;

import javax.swing.filechooser.*;



public class Config_frame {

	JFrame frame;
	private JTextField textField_SavePath;
	private JTextField textField_ArchivePath;
	private JTextField textField_NASPath;
	private JCheckBox chckbxSave;
	Configuration conf;
	DateHeure date;
	Connexion connect = new Connexion();
	private ModeleDynamiqueObjet modele = new ModeleDynamiqueObjet();
	private JTable table;
	JFileChooser chooser;
	private JTextField textField;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Config_frame window = new Config_frame();
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
	public Config_frame() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		frame = new JFrame();
		conf = new Configuration();
		date = new DateHeure();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("./configurations/ico.png"));
		frame.getContentPane().setForeground(Color.GRAY);
		frame.getContentPane().setBackground(new Color(240, 240, 240));
		frame.setPreferredSize(new Dimension(500,540));
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Configuration des sauvegardes et compressions - BACKUP TOOLS");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(58, 11, 421, 2);
		separator_1.setForeground(Color.GRAY);
		frame.getContentPane().add(separator_1);
		
		JLabel lblSource = new JLabel("Source");
		lblSource.setBounds(10, 0, 86, 25);
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblSource);
		
		final JLabel lblBaseDeDonnes = new JLabel("Base de donn\u00E9es : ");
		lblBaseDeDonnes.setBounds(10, 54, 113, 14);
		lblBaseDeDonnes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBaseDeDonnes.setEnabled(false);
		frame.getContentPane().add(lblBaseDeDonnes);
		
		final JComboBox<String> comboBoxListdb = new JComboBox<String>();
		comboBoxListdb.setBounds(109, 51, 241, 20);
		comboBoxListdb.setForeground(Color.BLACK);
		comboBoxListdb.setBackground(SystemColor.control);
		comboBoxListdb.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBoxListdb.setEnabled(false);
		frame.getContentPane().add(comboBoxListdb);
		
		
		
		final JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(360, 50, 104, 23);
		btnAjouter.setBackground(UIManager.getColor("Button.light"));
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAjouter.setEnabled(false);
		frame.getContentPane().add(btnAjouter);
		
		frame.getContentPane().add(new JScrollPane());
		btnAjouter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(modele.getRowCount()==0) modele.AddDatabase(new Database(comboBoxListdb.getSelectedItem().toString()));
				else {
					modele.AddDatabase(new Database(comboBoxListdb.getSelectedItem().toString()));
				}
				
				
				// modele.AddDatabase(new Database(comboBoxListdb.getSelectedItem().toString()));
				
					
			}
		});
		final JButton ButtonSupprimerBase = new JButton("Supprimer");
		ButtonSupprimerBase.setBounds(360, 137, 104, 23);
		ButtonSupprimerBase.setBackground(UIManager.getColor("Button.light"));
		ButtonSupprimerBase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ButtonSupprimerBase.setEnabled(false);
		frame.getContentPane().add(ButtonSupprimerBase);
		ButtonSupprimerBase.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int [] selection = table.getSelectedRows();
				for (int i=selection.length - 1; i>=0; i--){
					modele.RemoveDatabase(selection[i]);
				}
			}
		});
		
		chckbxSave = new JCheckBox("Effectuer les sauvegardes avec cet utilitaire");
		chckbxSave.setBounds(10, 24, 303, 23);
		frame.getContentPane().add(chckbxSave);
		chckbxSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(chckbxSave.isSelected()){
					lblBaseDeDonnes.setEnabled(true);
					comboBoxListdb.setEnabled(true);
					ButtonSupprimerBase.setEnabled(true);
					btnAjouter.setEnabled(true);
				}
				else
				{
					lblBaseDeDonnes.setEnabled(false);
					comboBoxListdb.setEnabled(false);
					ButtonSupprimerBase.setEnabled(false);
					btnAjouter.setEnabled(false);
				}
			}
		});
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(90, 288, 389, 2);
		separator_2.setForeground(Color.GRAY);
		separator_2.setBackground(Color.WHITE);
		frame.getContentPane().add(separator_2);
		
		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setBounds(10, 277, 86, 25);
		lblDestination.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblDestination);
		
		JLabel lblSauvegarde = new JLabel("Sauvegarde : ");
		lblSauvegarde.setBounds(10, 313, 86, 14);
		lblSauvegarde.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblSauvegarde);
		
		textField_SavePath = new JTextField();
		textField_SavePath.setBounds(95, 313, 275, 20);
		textField_SavePath.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(textField_SavePath);
		textField_SavePath.setColumns(10);
		
		
		
		final JButton btnParcourirSave = new JButton("Parcourir...");
		btnParcourirSave.setBounds(386, 313, 93, 23);
		btnParcourirSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(btnParcourirSave);
		btnParcourirSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				chooser.setDialogTitle("Choisir le répertoire des sauvegardes");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int test = chooser.showOpenDialog(btnParcourirSave);
				if(test==JFileChooser.APPROVE_OPTION){
					if(chooser.getSelectedFile().isDirectory()){
						textField_SavePath.setText(chooser.getSelectedFile().toString());
					}
				}
			}
		});
		
		JLabel lblArchive = new JLabel("Archive : ");
		lblArchive.setBounds(10, 357, 46, 14);
		lblArchive.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblArchive);
		
		
		textField_ArchivePath = new JTextField();
		textField_ArchivePath.setBounds(95, 354, 275, 20);
		frame.getContentPane().add(textField_ArchivePath);
		textField_ArchivePath.setColumns(10);
		
		final JButton btnParcourirArchive= new JButton("Parcourir...");
		btnParcourirArchive.setBounds(386, 353, 93, 23);
		btnParcourirArchive.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(btnParcourirArchive);
		btnParcourirArchive.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				chooser.setDialogTitle("Choisir le répertoire des compressions");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int test = chooser.showOpenDialog(btnParcourirArchive);
				if(test==JFileChooser.APPROVE_OPTION){
					if(chooser.getSelectedFile().isDirectory()){
						textField_ArchivePath.setText(chooser.getSelectedFile().toString());
					}
				}
			}
		});
		JLabel lblNas = new JLabel("NAS : ");
		lblNas.setBounds(10, 397, 46, 14);
		lblNas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		frame.getContentPane().add(lblNas);
		
		textField_NASPath = new JTextField();
		textField_NASPath.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField_NASPath.setBounds(95, 397, 275, 20);
		frame.getContentPane().add(textField_NASPath);
		textField_NASPath.setColumns(10);
		
		final JButton button_NAS = new JButton("Parcourir...");
		button_NAS.setFont(new Font("Tahoma", Font.PLAIN, 11));
		button_NAS.setBounds(386, 397, 93, 23);
		frame.getContentPane().add(button_NAS);
		button_NAS.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				chooser.setDialogTitle("Choisir le disque dur réseau(NAS) ");
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int test = chooser.showOpenDialog(button_NAS);
				if(test==JFileChooser.APPROVE_OPTION){
					if(chooser.getSelectedFile().isDirectory()){
						textField_NASPath.setText(chooser.getSelectedFile().toString());
					}
				}
			}
		});
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnQuitter.setBounds(58, 458, 89, 23);
		frame.getContentPane().add(btnQuitter);
		btnQuitter.addActionListener(new ActionListener() {
			
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
		
		JButton btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValider.setBounds(214, 458, 89, 23);
		frame.getContentPane().add(btnValider);
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAnnuler.setBounds(375, 458, 89, 23);
		frame.getContentPane().add(btnAnnuler);
		btnAnnuler.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					textField_SavePath.setText("");
					textField_ArchivePath.setText("");
					textField_NASPath.setText("");
					chckbxSave.setSelected(false);
					for(int i=modele.getRowCount();i>0;i--){
						System.out.println(i);
						modele.RemoveDatabase(i-1);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		modele.RemoveDatabase(0);
		table = new JTable(modele);
		table.setShowGrid(false);
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(modele);
		table.setBounds(10, 77, 327, 143);
		
		frame.getContentPane().add(table);
		
		JLabel lblPriode = new JLabel("P\u00E9riode");
		lblPriode.setBounds(10, 220, 46, 25);
		frame.getContentPane().add(lblPriode);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBounds(58, 231, 421, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblFrquence = new JLabel("Fr\u00E9quence : ");
		lblFrquence.setBounds(10, 244, 86, 22);
		frame.getContentPane().add(lblFrquence);
		
		JComboBox<String> comboBoxFreq = new JComboBox<String>();
		comboBoxFreq.setBounds(100, 245, 120, 20);
		comboBoxFreq.addItem("");
		comboBoxFreq.addItem("Chaque jour");
		comboBoxFreq.addItem("Tous les 2 jours");
		comboBoxFreq.addItem("Tous les 3 jours");
		comboBoxFreq.addItem("Une fois la semaine");
		
		frame.getContentPane().add(comboBoxFreq);
		
		JLabel lblHeure = new JLabel("Heure : ");
		lblHeure.setBounds(325, 243, 46, 24);
		frame.getContentPane().add(lblHeure);
		
		textField = new JTextField();
		textField.setBounds(376, 244, 55, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(date.AfficheDateHeure("HH:mm:ss"));
		
		String [] listdb = conf.ListDataBase();
		if(listdb!=null){
			
			System.out.println("Liste des bases");
			for(int i=listdb.length;i>0;i--){
				//System.out.println(conf.ListDataBase()[i-1]);
				comboBoxListdb.addItem(listdb[i-1]);
		}
		
		}
		frame.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{separator_1, lblSource, lblBaseDeDonnes, comboBoxListdb, btnAjouter, ButtonSupprimerBase, separator_2, lblDestination, lblSauvegarde, textField_SavePath,  btnParcourirSave, lblArchive, textField_ArchivePath, btnParcourirArchive, lblNas}));
		
	}
}
