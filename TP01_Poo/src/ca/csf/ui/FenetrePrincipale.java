package ca.csf.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.UsineForme;
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
	 * 
	 */
	private GestionnaireFichier m_GestionnaireFichier;
	
	/**
	 *
	 */

	private JButton btn_Selection;
	private JButton btn_Remplissage;
	private JSpinner spin_LargeurTrait;
	private JButton btn_CouleurTrait;

	/**
	 * Construit la fenêtre principale sans initialiser les composants.
	 * 
	 * 
	 * @see #initialiserComposants()
	 */
	public FenetrePrincipale() {
		super("TP01 - Poo");
		this.parametrer();
		this.initialiserComposants();
		super.pack();
	}

	/**
	 * Construit un {@code ElementGraphique} à partir de l'interface et des
	 * propriétés spécifiées.
	 * 
	 * @param p_Nom nom de la forme.
	 * @param p_X   coordonnée en x.
	 * @param p_Y   coordonnée en y.
	 * 
	 * @return l'élément construit selon l'interface et les propriétés spécifiées.
	 */
	public ElementGraphique construireElementDepuisUI(String p_Nom) {
		UsineForme usine = UsineForme.getInstance();
		ElementGraphique forme = usine.getForme(p_Nom);
		if (forme == null) {
			throw new IllegalArgumentException("Nom inconnu");
		}
		forme.setCouleur(this.btn_Remplissage.getBackground());
		forme.setLargeurTrait((int) this.spin_LargeurTrait.getValue());
		forme.setCouleurTrait(this.btn_CouleurTrait.getBackground());
		return forme;
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
	public void initialiserComposants() {
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
		JMenuItem item_Page = new JMenuItem("Propriétés de l'image...");
		JMenuItem item_Quitter = new JMenuItem("Quitter");
		JMenuItem item_Avancer = new JMenuItem("Avancer");
		JMenuItem item_Reculer = new JMenuItem("Reculer");
		JMenuItem item_PremierPlan = new JMenuItem("Mettre au premier plan");
		JMenuItem item_ArrierePlan = new JMenuItem("Mette en arrière plan");
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
		EspaceTravail.EcouteurSourisEG ecouteurSouris = m_Espace.new EcouteurSourisEG();
		//
		// panel_Centre
		panel_Centre.setOpaque(true);
		panel_Centre.setBackground(Color.gray);
		super.add(panel_Centre, BorderLayout.CENTER);
		//
		// m_Espace
		this.m_Espace.addMouseListener(ecouteurSouris);
		this.m_Espace.addMouseMotionListener(ecouteurSouris);
		panel_Centre.add(this.m_Espace);
		//
		// m_GestionnaireFichier
		this.m_GestionnaireFichier.setFormat(new FormatXML(UsineForme.getInstance()));
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
		menu_Selection.add(item_PremierPlan);
		menu_Selection.add(item_ArrierePlan);
		menu_Selection.addSeparator();
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
				this.m_Modele.setDimension(ModeleDessin.LARGEUR_DEFAULT, ModeleDessin.HAUTEUR_DEFAULT);
				this.m_Modele.setArrierePlan(Color.WHITE);
				this.m_GestionnaireFichier.reagirNouveau();
			}
		});
		//
		// item_Ouvrir
		item_Ouvrir.addActionListener(e -> {
			this.m_GestionnaireFichier.ouvrir(new FormatXML(UsineForme.getInstance()));
		});
		//
		// item_Enregistrer
		item_Enregistrer.addActionListener(e -> {
			this.m_GestionnaireFichier.enregistrer(new FormatXML(UsineForme.getInstance()));
		});
		//
		// item_EnregistrerSous
		item_EnregistrerSous.addActionListener(e -> {
			this.m_GestionnaireFichier.enregistrerSous(new FormatXML(UsineForme.getInstance()));
		});
		//
		// item_Exporter
		item_Exporter.addActionListener(e -> {
			this.m_GestionnaireFichier.enregistrerSous(new FormatSVG(UsineForme.getInstance()));
		});
		//
		// item_Page  
		item_Page.addActionListener(e -> {
			DialoguePage dialogueParametre = new DialoguePage(this);
			int largeur = (int) this.m_Modele.getLargeur();
			int hauteur = (int) this.m_Modele.getHauteur();
			Color couleur = this.m_Modele.getArrierePlan();
			dialogueParametre.montrer(largeur, hauteur, couleur);
			if (dialogueParametre.getResultat()) {
				this.m_Modele.setDimension(dialogueParametre.getLargeur(), dialogueParametre.getHauteur());
				this.m_Modele.setArrierePlan(dialogueParametre.getCouleur());
				if (this.getExtendedState() != JFrame.MAXIMIZED_BOTH) {
					this.pack();
				}
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
		// item_Avancer
		item_Avancer.addActionListener(e -> {
			ElementGraphique element = this.m_Espace.getSelection();
			int indice = this.m_Modele.getIndiceDe(element) + 1;
			if (indice != this.m_Modele.getCompte()) {
				this.m_Modele.retirer(element);
				this.m_Modele.inserer(indice, element);
				this.m_Espace.setSelection(this.m_Modele.get(indice));
			}
		});
		//
		// item_Reculer
		item_Reculer.addActionListener(e -> {
			ElementGraphique element = this.m_Espace.getSelection();
			int indice = this.m_Modele.getIndiceDe(element) - 1;
			if (indice >= 0) {
				this.m_Modele.retirer(element);
				this.m_Modele.inserer(indice, element);
				this.m_Espace.setSelection(this.m_Modele.get(indice));
			}			
		});
		//
		// item_PremierPlan
		item_PremierPlan.addActionListener(e -> {
			ElementGraphique element = this.m_Espace.getSelection();
			this.m_Modele.retirer(element);
			this.m_Modele.ajouter(element);
			this.m_Espace.setSelection(this.m_Modele.getDernier());
		});
		//
		// item_ArrierePlan
		item_ArrierePlan.addActionListener(e -> {
			ElementGraphique element = this.m_Espace.getSelection();
			this.m_Modele.retirer(element);
			this.m_Modele.inserer(0, element);
			this.m_Espace.setSelection(this.m_Modele.get(0));
		});
		//
		// item_Supprimer
		item_Supprimer.addActionListener(e -> {
			
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
			ElementGraphique element = FenetrePrincipale.this.m_Espace.getSelection();
			if (element != null) {
				element.setLargeurTrait((int) this.spin_LargeurTrait.getValue());
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
			ecouteurSouris.setFournisseur(null);
		});
		//
		// btn_Ellipse
		btn_Ellipse.setIcon(FenetrePrincipale.chargerIcone("24_Ellipse.png"));
		btn_Ellipse.addActionListener(e -> {
			ecouteurSouris.setFournisseur(() -> this.construireElementDepuisUI("Ellipse"));
		});
		//
		// btn_Rectangle
		btn_Rectangle.setIcon(FenetrePrincipale.chargerIcone("24_Rectangle.png"));
		btn_Rectangle.addActionListener(e -> {
			ecouteurSouris.setFournisseur(() -> this.construireElementDepuisUI("Rectangle"));
		});
		//
		// btn_Ligne
		btn_Ligne.setIcon(FenetrePrincipale.chargerIcone("24_Ligne.png"));
		btn_Ligne.setPressedIcon(FenetrePrincipale.chargerIcone("24_Ligne.png"));
		btn_Ligne.addActionListener(e -> {
			ecouteurSouris.setFournisseur(() -> this.construireElementDepuisUI("Ligne"));
		});
		//
		// btn_Remplissage
		this.btn_Remplissage.setIcon(FenetrePrincipale.chargerIcone("24_Vide.png"));
		this.btn_Remplissage.setBackground(FenetrePrincipale.COULEUR_DEFAUT);
		this.btn_Remplissage.addActionListener(e -> {
			Color couleur = this.btn_Remplissage.getBackground();
			couleur = JColorChooser.showDialog(this, "Choisissez votre couleur", couleur);
			if (couleur != null) {
				this.btn_Remplissage.setBackground(couleur);
				if (this.m_Espace.getSelection() != null) {
					this.m_Espace.getSelection().setCouleur(couleur);
				}
				FenetrePrincipale.this.btn_Selection.requestFocus();
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
}
