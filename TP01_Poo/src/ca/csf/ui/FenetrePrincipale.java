package ca.csf.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.HashMap;

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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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

	/**
	 * Largeur par défaut de l'espcae de travail.
	 */
	public static final int LARGEUR_DEFAULT = 640;

	/**
	 * Hauteur par défaut de l'espace de travail.
	 */
	public static final int HAUTEUR_DEFAULT = 360;

	/**
	 * Taille par défaut des éléments.
	 */
	public static final int TAILLE_DEFAUT = 50;

	/**
	 * Couleur par défaut des éléments.
	 */
	private static final Color COULEUR_DEFAUT = new Color(175, 200, 225);
	
	/**
	 * Couleur par défaut du trait des éléments.
	 */
	private static final Color COULEUR_TRAIT_DEFAUT = Color.BLACK;
	
	/**
	 * 
	 */
	private ModeleDessin m_Modele;

	/**
	 * 
	 */
	private EspaceTravail m_Espace;

	/**
	 * Détermine la prochaine forme dessiner.
	 */
	private String m_Forme = "";

	private GestionnaireFichier m_GestionnaireFichier;

	private JButton btn_Selection;
	private JButton btn_Remplissage;
	private JSpinner spin_LargeurTrait;
	private JButton btn_CouleurTrait;
	private HashMap<String, JButton> m_BtnOutils;

	/**
	 * Construit la fenêtre.
	 */
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
		this.m_BtnOutils = new HashMap<>();
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
		JMenuItem item_Page = new JMenuItem("Propriétés de l'image...");
		JMenuItem item_Quitter = new JMenuItem("Quitter");
		JMenuItem item_Avancer = new JMenuItem("Avancer");
		JMenuItem item_Reculer = new JMenuItem("Reculer");
		JMenuItem item_Supprimer = new JMenuItem("Supprimer");
		this.btn_Selection = new JButton();
		this.btn_Remplissage = new JButton();
		JButton btn_Ellipse = new JButton();
		JButton btn_Rectangle = new JButton();
		JButton btn_Ligne = new JButton();
		JPanel panel_LargeurTrait = new JPanel();
		this.spin_LargeurTrait = new JSpinner(new SpinnerNumberModel(2, 0, 24, 1));
		JPanel panel_CouleurTrait = new JPanel(new FlowLayout(FlowLayout.LEFT));
		this.btn_CouleurTrait = new JButton();
		ManipulateurElement ecouteurSouris = new ManipulateurElement(m_Espace);
		//
		// panel_Centre
		panel_Centre.setOpaque(true);
		panel_Centre.setBackground(Color.gray);
		super.add(panel_Centre, BorderLayout.CENTER);
		//
		// m_Espace
		//EouteurSouris eouteurSouris = new EouteurSouris();
		this.m_Espace.addMouseListener(ecouteurSouris);
		this.m_Espace.addMouseMotionListener(ecouteurSouris);
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
		menu_Selection.add(item_Avancer);
		menu_Selection.add(item_Reculer);
		menu_Selection.add(item_Supprimer);
		//
		// menu_Formes
		menu_Trait.add(panel_LargeurTrait);
		menu_Trait.add(panel_CouleurTrait);
		menu_Trait.addMenuListener(new MenuListener() {			
			@Override
			public void menuSelected(MenuEvent p_e) {
				ElementGraphique element = FenetrePrincipale.this.m_Espace.getSelection();
				if (element != null) {
					FenetrePrincipale.this.spin_LargeurTrait.setValue(element.getLargeurTrait());
				}
			}			
			public void menuDeselected(MenuEvent p_e) {}
			public void menuCanceled(MenuEvent p_e) {}
		});
		//
		// item_Nouveau
		item_Nouveau.addActionListener(e -> {
			if (this.m_GestionnaireFichier.verifierSauvegarde()) {
				this.m_Modele.vider();
				this.m_Modele.setDimension(LARGEUR_DEFAULT, HAUTEUR_DEFAULT);
				this.m_Modele.setArrierePlan(Color.WHITE);
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
			DialoguePage dialogueParametre = new DialoguePage(this);
			dialogueParametre.montrer((int) Math.round(this.m_Modele.getLargeur()),
					(int) Math.round(this.m_Modele.getHauteur()), this.m_Modele.getArrierePlan());
			if (dialogueParametre.getResultat()) {
				this.m_Modele.setDimension(dialogueParametre.getHauteur(), dialogueParametre.getLargeur());
				this.m_Modele.setArrierePlan(dialogueParametre.getCouleur());
			}
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
			int epaisseur = (int) this.spin_LargeurTrait.getValue();
			ecouteurSouris.setLargeurTrait(epaisseur);
			ElementGraphique element = FenetrePrincipale.this.m_Espace.getSelection();
			if (element != null) {
				element.setLargeurTrait(epaisseur);
			}
		});
		//
		// btn_couleurTrait
		this.btn_CouleurTrait.setBackground(FenetrePrincipale.COULEUR_TRAIT_DEFAUT);
		this.btn_CouleurTrait.addActionListener(e -> {
			ElementGraphique selection = FenetrePrincipale.this.m_Espace.getSelection();
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
		// m_BtnOutils
		this.m_BtnOutils.put("", this.btn_Selection);
		this.m_BtnOutils.put("Rectangle", btn_Rectangle);
		this.m_BtnOutils.put("Ellipse", btn_Ellipse);
		this.m_BtnOutils.put("Ligne", btn_Ligne);
		//
		// panel_Outils
		panel_Outils.setLayout(new BoxLayout(panel_Outils, BoxLayout.Y_AXIS));
		panel_Outils.add(this.btn_Selection);
		panel_Outils.add(btn_Ellipse);
		panel_Outils.add(btn_Rectangle);
		panel_Outils.add(btn_Ligne);
		panel_Outils.add(this.btn_Remplissage);
		super.add(panel_Outils, BorderLayout.WEST);
		//
		// btn_Selection
		this.btn_Selection.setIcon(FenetrePrincipale.chargerIcone("24_Souris.png"));
		this.btn_Selection.addActionListener(e -> {
			this.m_Forme = "";
			ecouteurSouris.setNom("");
		});
		//
		// btn_Ellipse
		btn_Ellipse.setIcon(FenetrePrincipale.chargerIcone("24_Ellipse.png"));
		btn_Ellipse.addActionListener(e -> {
			this.m_Forme = "Ellipse";
			ecouteurSouris.setNom("Ellipse");
		});
		//
		// btn_Rectangle
		btn_Rectangle.setIcon(FenetrePrincipale.chargerIcone("24_Rectangle.png"));
		btn_Rectangle.addActionListener(e -> {
			this.m_Forme = "Rectangle";
			ecouteurSouris.setNom("Rectangle");
		});
		//
		// btn_Ligne
		btn_Ligne.setIcon(FenetrePrincipale.chargerIcone("24_Ligne.png"));
		btn_Ligne.setPressedIcon(FenetrePrincipale.chargerIcone("24_Ligne.png"));
		btn_Ligne.addActionListener(e -> {
			this.m_Forme = "Ligne";
			ecouteurSouris.setNom("Ligne");
		});

		//
		// btn_Remplissage
		this.btn_Remplissage.setIcon(FenetrePrincipale.chargerIcone("24_Vide.png"));
		this.btn_Remplissage.setBackground(FenetrePrincipale.COULEUR_DEFAUT);
		ecouteurSouris.setCouleur(FenetrePrincipale.COULEUR_DEFAUT);
		this.btn_Remplissage.addActionListener(e -> {
			Color couleur = JColorChooser.showDialog(this, "Choisissez votre couleur", null);
			this.btn_Remplissage.setBackground(couleur);
			ecouteurSouris.setCouleur(couleur);
			if (this.m_Espace.getSelection() != null) {
				this.m_Espace.getSelection().setCouleur(couleur);
			}
			FenetrePrincipale.this.focusserOutil();
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
	 * Focusse l'outil {@code (JButton)} associé à {@code this.m_Forme}.
	 */
	private void focusserOutil() {
		this.m_BtnOutils.get(this.m_Forme).requestFocus();
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
}
