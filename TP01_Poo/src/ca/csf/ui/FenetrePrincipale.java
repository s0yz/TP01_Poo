package ca.csf.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ca.csf.modele.ModeleGraphiques;

/**
 * @author Cedric Mariage
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = -4586998190495167383L;
	
	private ModeleGraphiques m_Modele;
	private EspaceTravail m_Espace;
	
	JMenuBar menuBar = new JMenuBar();
	JMenu fichier = new JMenu("Fichier");
	JMenu selection = new JMenu("Selection");
	JMenu formes = new JMenu("Formes");
	
	Button boutonSelection = new Button("1");
	Button boutonElipse = new Button("2");
	Button boutonRect= new Button("3");
	Button boutonLine = new Button("4");
	
	JMenuItem ouvrir = new JMenuItem("Ouvrir");
	JMenuItem enregistrer = new JMenuItem("Enregistrer");
	JMenuItem exporter = new JMenuItem("Exporter");
	JMenuItem quitter = new JMenuItem("Quitter");
	
	JMenuItem couleur = new JMenuItem("Couleur");
	JMenuItem couleurTrait = new JMenuItem("Couleur de Trait");
	JMenuItem epaisTrait = new JMenuItem("Epaisseur Trait");
	
	
	
	
	public FenetrePrincipale() {
		super("TP01 - Poo");
		this.parametrer();
		this.initialiserComposant();
		super.pack();
		
	}
	public void createMenu() {
		fichier.add(ouvrir);
		fichier.add(enregistrer);
		fichier.add(exporter);
		fichier.addSeparator();
		fichier.add(quitter);
		menuBar.add(fichier);
		
		selection.add(couleur);
		selection.add(couleurTrait);
		selection.add(epaisTrait);
		menuBar.add(selection);
		
		this.setJMenuBar(menuBar);
		
	}
	
	public void parametrer() {
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setLocationRelativeTo(null);
	}
	
	public void initialiserComposant() {
		//
		// center
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		center.setOpaque(true);
		center.setBackground(Color.gray);
		super.add(center, BorderLayout.CENTER);
		//
		// m_Modele
		this.m_Modele = new ModeleGraphiques();
		//
		// m_Espace
		this.m_Espace = new EspaceTravail(this.m_Modele, 640, 360);
		center.add(this.m_Espace);
		//
		//
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent p_e) {
				FenetrePrincipale.this.dispose();
			}
		});
		
	}
}
