package store.business.GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import store.business.util.Client;
import store.business.util.ErreurOuvertureXML;
import store.business.util.Produit;
import store.business.util.Transaction;

/** 
 * This is the class where is the main and where the graphical interface is created
 * @author Elias
 *
 */

@SuppressWarnings("serial")
public class affichage extends JFrame implements ActionListener {
	private Produit produit = new Produit();
	
	private JTextArea Prod_courant_info1 = new JTextArea(0,10);
	private JTextArea Prod_courant_info2 = new JTextArea(0,10);
	private JTextArea Prod_courant_info3 = new JTextArea(0,10);
	
	private JTextField Client_search = new JTextField(11);
	
	private JTextArea Client_courant_nom = new JTextArea(0,10);
	private JTextArea Produit_courant_nom = new JTextArea(0,10);
	
	private String[] choix = {"Tous", "Livres", "DVDs", "Jeux-vidéos"};
	private JComboBox<String> Choice = new JComboBox<String>(choix);
	
	private JComboBox<String> produit_nom = new JComboBox<String>();
	
	private Transaction transaction = new Transaction();
	private JComboBox<String> nb_achat = new JComboBox<String>();
	Client client = new Client();
	private JTextField client_id = new JTextField(10);
	private JTextField client_nom = new JTextField(10);
	private JTextField client_prénom = new JTextField(10);
	private JTextField client_adresse = new JTextField(10);

	public affichage() {
		
		JLabel Courant_client_name = new JLabel("Nom du client courant");
		JPanel Info_commande = new JPanel();
		JButton Acheter = new JButton("Acheter");
		JLabel Nom_Magasin = new JLabel("Nom du Magasin");
		JLabel Categorie = new JLabel("Catégories produits");
		JLabel Produit_disp = new JLabel(" Les produits disponibles");
		JLabel Prod_courant = new JLabel("Produit Courant");
		JPanel Produit = new JPanel();
		JButton Chercher = new JButton("Chercher");
		JPanel Client_name = new JPanel();
		JButton Client_recherche = new JButton("Cherche");
	JLabel Client = new JLabel("Client (nom)");
	JLabel Courant_produit_name = new JLabel("Nom du produit courant");
	JPanel Menu = new JPanel();
	
	this.setSize(500, 500);
	this.setLocationRelativeTo(null);
	this.setTitle("Magasin");
	GridLayout Grid = new GridLayout(4,0);
	this.setLayout(Grid);
	
	Menu.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.weightx = 4;
	gbc.weighty = 2;
	gbc.gridx = 0;
	gbc.gridy = 0;
	Menu.add(Nom_Magasin,gbc);
	gbc.gridx = 1;
	gbc.gridy = 0;
	Menu.add(Categorie,gbc);
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	
	File file = new File("./files/logo.png");
	if (!file.exists()) {
	try {
		throw new ErreurOuvertureXML("Can't find logo, unable to start program");
	} catch (ErreurOuvertureXML e) {
		e.printStackTrace();
	}
	}
	ImageIcon icon = new ImageIcon(file.getPath()); 	
	JLabel img = new JLabel(icon); 
	gbc.anchor = GridBagConstraints.LINE_START;
	gbc.insets = new Insets(-50, 15, 0, 0);
	gbc.gridx=0;
	gbc.gridy=1;
	gbc.gridheight=2;
	Menu.add(img,gbc);
	gbc.gridx=1;
	gbc.gridy=1;
	gbc.gridheight=2;
	gbc.gridwidth=2;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	Menu.add(Choice,gbc);
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	gbc.gridx=4;
	gbc.gridy=1;
	gbc.fill = GridBagConstraints.HORIZONTAL;
	Menu.add(Chercher,gbc);
	gbc.gridwidth = GridBagConstraints.REMAINDER;


	Produit.setLayout(new GridBagLayout());
	GridBagConstraints gbc2 = new GridBagConstraints();
	gbc2.anchor = GridBagConstraints.LINE_START;
	gbc2.weightx = 3;
	gbc2.weighty = 1;
	gbc2.gridx=0;
	gbc2.gridy=0;
	Produit.add(Produit_disp,gbc2);
	gbc2.gridx=1;
	gbc2.gridy=0;
	Produit.add(Prod_courant,gbc2);	
	gbc2.gridx=0;
	gbc2.gridy=1;
	 Produit.add(produit_nom,gbc2);
	 get_produit(produit_nom, "Tous");
	gbc2.gridx=1;
	gbc2.gridy=1;
	 Produit.add(Prod_courant_info1,gbc2);
	gbc2.gridx=1;
	gbc2.gridy=2;
	 Produit.add(Prod_courant_info2,gbc2);
	gbc2.gridx=1;
	gbc2.gridy=3;
	 Produit.add(Prod_courant_info3,gbc2);
	 	
	Client_name.setLayout(new GridBagLayout());
	GridBagConstraints gbc3 = new GridBagConstraints();
	gbc3.weightx = 3;
	gbc3.weighty = 2;
	gbc3.gridx=0;
	gbc3.gridy=0;
	gbc3.anchor = GridBagConstraints.LINE_START;
	gbc3.insets = new Insets(0, 15, 0, 0);
	Client_name.add(Client,gbc3);
	gbc3.gridx=0;
	gbc3.gridy=1;
	gbc3.gridwidth=2;
	Client_name.add(Client_search,gbc3);
	gbc3.gridx=0;
	gbc3.gridy=2;
	Client_name.add(Client_recherche,gbc3);
	gbc3.gridx=1;
	gbc3.gridy=0;
	Client_name.add(client_id,gbc3);
	gbc3.gridx=1;
	gbc3.gridy=1;
	Client_name.add(client_nom,gbc3);
	gbc3.gridx=1;
	gbc3.gridy=2;
	Client_name.add(client_prénom,gbc3);
	gbc3.gridx=1;
	gbc3.gridy=3;
	Client_name.add(client_adresse,gbc3);
	client_id.setVisible(false);
	client_nom.setVisible(false);
	client_prénom.setVisible(false);
	client_adresse.setVisible(false);
	
	Info_commande.setLayout(new GridBagLayout());
	GridBagConstraints gbc4 = new GridBagConstraints();
	gbc4.weightx = 3;
	gbc4.weighty = 2;
	gbc4.gridx=0;
	gbc4.gridy=0;
	gbc4.anchor = GridBagConstraints.LINE_START;
	gbc4.insets = new Insets(0, 15, 0, 0);
	Info_commande.add(Courant_client_name,gbc4);
	gbc4.gridx=0;
	gbc4.gridy=1;
	Info_commande.add(Client_courant_nom,gbc4);
	gbc4.gridx=1;
	gbc4.gridy=0;
	Info_commande.add(Courant_produit_name,gbc4);
	gbc4.gridx=1;
	gbc4.gridy=1;
	Info_commande.add(Produit_courant_nom,gbc4);
	gbc4.gridx=2;
	gbc4.gridy=2;
	Info_commande.add(nb_achat,gbc4);
	gbc4.gridx=3;
	gbc4.gridy=2;
	Info_commande.add(Acheter,gbc4);
	
	for(int i = 0; i < 10; i++) {
		nb_achat.addItem(""+i);
		}
	this.getContentPane().add(Menu);
	this.getContentPane().add(Produit);
	this.getContentPane().add(Client_name);
	this.getContentPane().add(Info_commande);
	this.setVisible(true);
	this.setResizable(false);
	
	Chercher.setActionCommand("Chercher");
	Chercher.addActionListener(this);
	Client_courant_nom.setEditable(false);
	Produit_courant_nom.setEditable(false);
	Prod_courant_info1.setEditable(false);
	Prod_courant_info2.setEditable(false);
	Prod_courant_info3.setEditable(false);
	produit_nom.setActionCommand("Produit");
	produit_nom.addActionListener(this);
	Client_recherche.setActionCommand("Chercher_client");
	Client_recherche.addActionListener(this);
	
	Acheter.setActionCommand("Acheter");
	Acheter.addActionListener(this);
	
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void get_produit(JComboBox<String> produit_nom,String choix) 
	{
		List<String> list_produit = new ArrayList<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		 list_produit = produit.get_name(choix);
		 for(int i=0; i < list_produit.size(); i++) {
		model.addElement(list_produit.get(i));
		 }
		 
		 produit_nom.setModel(model);
		 produit_nom.updateUI();
		 produit_nom.setVisible(true);
		 list_produit.clear();

	}
	
	public void set_imgProduct(JLabel zone_img, String product ) {
		
		String path = produit.get_information(Choice.getSelectedItem().toString(), produit_nom.getSelectedItem().toString(), "img");
		
		File file = new File(path);
		if (!file.exists()) 
		{
			file = new File("./files/no-image-icon.png");
			ImageIcon icon = new ImageIcon(file.getPath()); 
			zone_img = new JLabel(icon);
		}
		else {
			ImageIcon icon = new ImageIcon(file.getPath()); 
			zone_img = new JLabel(icon);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		switch(e.getActionCommand()) {
		
		case "Chercher":
			
			if(Choice.getSelectedItem().toString().equals("Tous")) {
				get_produit(produit_nom, "Tous");
				
				}
			
			if(Choice.getSelectedItem().toString().equals("Livres")) {
			get_produit(produit_nom,"livres");
			}
			
			if(Choice.getSelectedItem().toString().equals("DVDs")) {
				get_produit(produit_nom,"DVDs");
				}
			
			if(Choice.getSelectedItem().toString().equals("Jeux-vidéos")) {
				get_produit(produit_nom,"Jeux-vidéos");
				}
		
		break;
		
		case "Produit":

			Produit_courant_nom.setText(produit_nom.getSelectedItem().toString());
			Prod_courant_info1.setText(produit_nom.getSelectedItem().toString());
			
			Prod_courant_info2.setText(produit.get_description(produit_nom.getSelectedItem().toString()));
		if( produit.get_information(Choice.getSelectedItem().toString(), produit_nom.getSelectedItem().toString(), "nombre") == "0") {
				Prod_courant_info3.setText("ÉPUISÉ");
				}
				else {
					Prod_courant_info3.setText("EN STOCK");
				}
			
			Prod_courant_info1.updateUI(); 
			Prod_courant_info2.updateUI();
			Prod_courant_info3.updateUI();
			break;
	

		
		case "Chercher_client" :
			
			if (client.vérification_existenceClient(Client_search.getText()) == false) {
				 JOptionPane.showMessageDialog(null,"Client inexistant, création d'une fiche client");
				 String nom = (String)JOptionPane.showInputDialog(null, "Nom du client");
				 String prénom = (String)JOptionPane.showInputDialog(null, "Prénom du client");
				 String adresse = (String)JOptionPane.showInputDialog(null, "Adresse du client");

					client.modif_xml(adresse, nom, prénom);
							
			}
			
			Client_courant_nom.setText(Client_search.getText());
			
			client_id.setText("identifiant :"+client.get_informationClient(Client_courant_nom.getText(), "id"));
			client_id.setVisible(true);
			client_id.setEditable(false);
			
			client_nom.setText(Client_search.getText());
			client_nom.setVisible(true);
			client_nom.setEditable(false);
			
			client_prénom.setText("Prénom :"+client.get_informationClient(Client_courant_nom.getText(), "prenom"));
			client_prénom.setVisible(true);
			client_prénom.setEditable(false);
			
			client_adresse.setText("Adresse :"+client.get_informationClient(Client_courant_nom.getText(), "adresse"));
			client_adresse.setEditable(false);
			client_adresse.setVisible(true);
			break;
			
		case "Acheter" :
			transaction.modif_xml(Integer.parseInt(client.get_informationClient(Client_courant_nom.getText(),"id")), Integer.parseInt(nb_achat.getSelectedItem().toString()));
			 JOptionPane.showMessageDialog(null,"Transaction enregistré");
			break;
		}
	}
	
	public static void main(String[] args) {
		
		new affichage();
	}
	
}
