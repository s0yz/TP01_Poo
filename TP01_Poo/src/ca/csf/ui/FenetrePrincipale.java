package ca.csf.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.FormeFactory;
import ca.csf.io.FormatSVG;
import ca.csf.io.FormatXML;
import ca.csf.io.GestionnaireFichier;
import ca.csf.modele.ModeleDessin;

/**
 * Fenetre principale
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = -4586998190495167383L;

	public static final int TAILLE_DEFAUT = 50;

	private ModeleDessin m_Modele;

	private EspaceTravail m_Espace;

	private String m_Forme;

	private GestionnaireFichier m_GestionnaireFichier;

	private JButton btn_Selection;
	private JButton btn_Remplissage;
	private JSpinner spin_LargeurTrait;
	private JButton btn_CouleurTrait;

	public FenetrePrincipale() {
		super("TP01 - Poo");
		this.parametrer();
		this.initialiserComposants();
		super.pack();
	}

	/**
	 * Configure la {@code FenetrePrincipale}.
	 */
	private void parametrer() {
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setLocationRelativeTo(null);
	}

	/**
	 * Initialise les composants de la {@code FenetrePrincipale}.
	 */
	private void initialiserComposants() {
		this.m_Modele = new ModeleDessin();
		this.m_Espace = new EspaceTravail(this.m_Modele);
		this.m_GestionnaireFichier = new GestionnaireFichier(this, this.m_Modele);
		JPanel panel_Centre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_Outils = new JPanel();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu_Fichier = new JMenu("Fichier");
		JMenu menu_Selection = new JMenu("Selection");
		JMenu menu_Trait = new JMenu("Trait");
		JMenuItem item_Nouveau = new JMenuItem("Nouveau");
		JMenuItem item_Ouvrir = new JMenuItem("Ouvrir");
		JMenuItem item_Enregistrer = new JMenuItem("Enregistrer");
		JMenuItem item_EnregistrerSous = new JMenuItem("Enregistrer sous");
		JMenuItem item_Exporter = new JMenuItem("Exporter");
		JMenuItem item_Page = new JMenuItem("Taille de l'image...");
		JMenuItem item_Quitter = new JMenuItem("Quitter");
		JMenuItem item_Couleur = new JMenuItem("Couleur");
		JMenuItem item_CouleurTrait = new JMenuItem("Couleur de Trait");
		JMenuItem item_LargeurTrait = new JMenuItem("Epaisseur Trait");
		this.btn_Selection = new JButton();
		JButton btn_Ellipse = new JButton();
		JButton btn_Rectangle = new JButton();
		JButton btn_Ligne = new JButton();
		this.btn_Remplissage = new JButton();
		JPanel panel_LargeurTrait = new JPanel(new FlowLayout());
		this.spin_LargeurTrait = new JSpinner(new SpinnerNumberModel(1, 0, 24, 1));
		JPanel panel_CouleurTrait = new JPanel(new FlowLayout());
		this.btn_CouleurTrait = new JButton();
		//
		// panel_Centre
		panel_Centre.setOpaque(true);
		panel_Centre.setBackground(Color.gray);
		super.add(panel_Centre, BorderLayout.CENTER);
		//
		// m_Espace
		EouteurSouris eouteurSouris = new EouteurSouris();
		this.m_Espace.addMouseListener(eouteurSouris);
		this.m_Espace.addMouseMotionListener(eouteurSouris);
		panel_Centre.add(this.m_Espace);
		//
		// menuBar
		super.setJMenuBar(menuBar);
		menuBar.add(menu_Fichier);
		menuBar.add(menu_Selection);
		menuBar.add(menu_Trait);
		//
		// menu_Fichier
		menu_Fichier.add(item_Nouveau);
		menu_Fichier.add(item_Ouvrir);
		menu_Fichier.add(item_Enregistrer);
		menu_Fichier.add(item_EnregistrerSous);
		menu_Fichier.add(item_Exporter);
		menu_Fichier.addSeparator();
		menu_Fichier.add(item_Page);
		menu_Fichier.addSeparator();
		menu_Fichier.add(item_Quitter);
		//
		// menu_Selection
		menu_Selection.add(item_Couleur);
		menu_Selection.add(item_CouleurTrait);
		menu_Selection.add(item_LargeurTrait);
		//
		// menu_Formes
		menu_Trait.add(panel_LargeurTrait);
		menu_Trait.add(panel_CouleurTrait);		
		//
		// item_Nouveau
		item_Nouveau.addActionListener(e -> {
			if (this.m_GestionnaireFichier.verifierSauvegarde()) {
				this.m_Modele.vider();
				this.m_GestionnaireFichier.reagirNouveau();
			}
		});
		//
		// item_Ouvrir
		item_Ouvrir.addActionListener(e -> {
			this.m_GestionnaireFichier.ouvrir(new FormatXML(FormeFactory.getInstance()));
		});
		//
		// item_Enregistrer
		item_Enregistrer.addActionListener(e -> {
			this.m_GestionnaireFichier.enregistrer(new FormatXML(FormeFactory.getInstance()));
		});
		//
		// item_EnregistrerSous
		item_EnregistrerSous.addActionListener(e -> {
			this.m_GestionnaireFichier.enregistrerSous(new FormatXML(FormeFactory.getInstance()));
		});
		//
		// item_Exporter
		item_Exporter.addActionListener(e -> {
			this.m_GestionnaireFichier.enregistrerSous(new FormatSVG(FormeFactory.getInstance()));
		});
		//
		// item_Page
		item_Page.addActionListener(e -> {
			this.m_Modele.setLargeur(1000);
			this.m_Modele.setHauteur(1000);
		});
		//
		// item_Quitter
		item_Quitter.addActionListener(e -> {
			if (FenetrePrincipale.this.m_GestionnaireFichier.verifierSauvegarde()) {
				FenetrePrincipale.this.dispose();
			}
		});
		//
		// panel_LargeurTrait
		panel_LargeurTrait.add(new JLabel("Epaisseur : "));
		panel_LargeurTrait.add(spin_LargeurTrait);
		//
		// panel_CouleurTrait
		panel_CouleurTrait.add(new JLabel("Couleur : "));
		panel_CouleurTrait.add(btn_CouleurTrait);
		//
		// spin_LargeurTrait
		this.spin_LargeurTrait.addChangeListener(e -> {
			ElementGraphique element = FenetrePrincipale.this.m_Modele.getSelection();
			if (element != null) {
				element.setLargeurTrait((int) this.spin_LargeurTrait.getValue());
			}
		});
		//
		// btn_couleurTrait
		this.btn_CouleurTrait.setBackground(Color.BLACK);
		this.btn_CouleurTrait.addActionListener(e -> {
			ElementGraphique selection = FenetrePrincipale.this.m_Modele.getSelection();
			Color couleurInitiale = this.btn_CouleurTrait.getBackground();
			Color couleur = JColorChooser.showDialog(FenetrePrincipale.this, "Choisissez une couleur", couleurInitiale);
			if (couleur != null) {
				if (selection != null) {
					selection.setCouleurTrait(couleur);
				}
				this.btn_CouleurTrait.setBackground(couleur);
			}
		});
		//
		// panel_Outils
		panel_Outils.setLayout(new BoxLayout(panel_Outils, BoxLayout.Y_AXIS));
		panel_Outils.add(btn_Selection);
		panel_Outils.add(btn_Ellipse);
		panel_Outils.add(btn_Rectangle);
		panel_Outils.add(btn_Ligne);
		panel_Outils.add(btn_Remplissage);
		super.add(panel_Outils, BorderLayout.WEST);
		//
		// btn_Selection
		btn_Selection.setIcon(FenetrePrincipale.chargerIcone("24_Souris.png"));
		btn_Selection.addActionListener(e -> {
		});
		//
		// btn_Ellipse
		btn_Ellipse.setIcon(FenetrePrincipale.chargerIcone("24_Ellipse.png"));
		btn_Ellipse.addActionListener(e -> {
			this.m_Forme = "Ellipse";
		});
		//
		// btn_Rectangle
		btn_Rectangle.setIcon(FenetrePrincipale.chargerIcone("24_Rectangle.png"));
		btn_Rectangle.addActionListener(e -> {
			this.m_Forme = "Rectangle";
		});
		//
		// btn_Ligne
		btn_Ligne.setIcon(FenetrePrincipale.chargerIcone("24_Ligne.png"));
		btn_Ligne.addActionListener(e -> {
			this.m_Forme = "Ligne";
		});
		//
		// btn_Remplissage
		this.btn_Remplissage.setIcon(FenetrePrincipale.chargerIcone("24_Vide.png"));
		this.btn_Remplissage.setBackground(null);
		this.btn_Remplissage.addActionListener(e -> {
			Color couleur = JColorChooser.showDialog(this, "Choisissez votre couleur", null);
			this.btn_Remplissage.setBackground(couleur);
			if (this.m_Modele.getSelection() != null) {
				this.m_Modele.getSelection().setCouleur(couleur);
			}
		});
		//
		// windowClosing
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent p_e) {
				if (FenetrePrincipale.this.m_GestionnaireFichier.verifierSauvegarde()) {
					FenetrePrincipale.this.dispose();
				}
			}
		});
	}

	/**
	 * Pour obtenir une ImageIcon à partir du nom de l'image spécifié. Le fichier
	 * doit être situé dans le dossier "src/res".
	 * 
	 * @param p_Image le nom de l'image, avec l'extension.
	 * @return un nouvel ImageIcon ou null.
	 */
	private static ImageIcon chargerIcone(String p_Image) {
		ImageIcon icone = null;
		String chemin = "/res/" + p_Image;
		URL url = FenetrePrincipale.class.getResource(chemin);
		try {
			icone = new ImageIcon(url);
		} catch (NullPointerException e) {
			System.err.println("Image introuvable : " + chemin);
		}
		return icone;
	}

	/**
	 * 
	 */
	private class EouteurSouris extends MouseAdapter {
		private double differenceX;
		private double differenceY;
		
		@Override
		public void mousePressed(MouseEvent p_e) {
			if (FenetrePrincipale.this.btn_Selection.hasFocus()) {
				this.selectionner(p_e);
			} else if (FenetrePrincipale.this.m_Forme != null) {
				ElementGraphique forme = FormeFactory.getInstance().getForme(FenetrePrincipale.this.m_Forme);
				forme.setPosition(p_e.getX(), p_e.getY());
				forme.setCouleur(FenetrePrincipale.this.btn_Remplissage.getBackground());
				forme.setLargeurTrait((int) FenetrePrincipale.this.spin_LargeurTrait.getValue());
				forme.setCouleurTrait(FenetrePrincipale.this.btn_CouleurTrait.getBackground());
				FenetrePrincipale.this.m_Modele.ajouter(forme);
			}
		}

		@Override
		public void mouseReleased(MouseEvent p_e) {
			ElementGraphique selection = FenetrePrincipale.this.m_Modele.getSelection();
			if (selection != null) {
				if (selection.getLargeur() == 0 && selection.getHauteur() == 0) {
					int deplacement = -1 * (FenetrePrincipale.TAILLE_DEFAUT >> 1);
					selection.setLargeur(FenetrePrincipale.TAILLE_DEFAUT);
					selection.setHauteur(FenetrePrincipale.TAILLE_DEFAUT);
					selection.deplacer(deplacement, deplacement);
				} else if (selection.getNom() != "Ligne") {
					if (selection.getLargeur() < 0) {
						selection.deplacer(selection.getLargeur(), 0);
						selection.setLargeur(Math.abs(selection.getLargeur()));
					}
					if (selection.getHauteur() < 0) {
						selection.deplacer(0, selection.getHauteur());
						selection.setHauteur(Math.abs(selection.getHauteur()));
					}
				}
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent p_e) {
			ElementGraphique selection = FenetrePrincipale.this.m_Modele.getSelection();
			if (selection != null) {
				if (FenetrePrincipale.this.btn_Selection.hasFocus()) {
					selection.setPosition(p_e.getX() - this.differenceX, p_e.getY() - this.differenceY);
				} else {
					double largeur = p_e.getX() - selection.getX();
					double hauteur = p_e.getY() - selection.getY();
					selection.setDimension(largeur, hauteur);
				}
			}
		}
		
		private void selectionner(MouseEvent p_e) {
			ElementGraphique selection = FenetrePrincipale.this.m_Modele.selectionner(p_e.getX(), p_e.getY());
			if (selection != null) {
				this.differenceX = p_e.getX() - selection.getX();
				this.differenceY = p_e.getY() - selection.getY();
				FenetrePrincipale.this.spin_LargeurTrait.setValue(selection.getLargeurTrait());
			}
		}
	}
}
