package ca.csf.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.FormeFactory;
import ca.csf.io.ControleurFichier;
import ca.csf.io.FormatFichier;
import ca.csf.io.FormatXml;
import ca.csf.modele.ModeleDessin;

/**
 * Fenetre principale
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = -4586998190495167383L;

	private static final Dimension BTN_TAILLE = new Dimension(32, 32);

	private ModeleDessin m_Modele;

	private EspaceTravail m_Espace;

	private String m_Forme;

	private JButton btn_Selection;

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
		JPanel panel_Centre = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JPanel panel_Outils = new JPanel();
		JMenuBar menuBar = new JMenuBar();
		JMenu menu_Fichier = new JMenu("Fichier");
		JMenu menu_Selection = new JMenu("Selection");
		JMenu menu_Formes = new JMenu("Formes");
		JMenuItem item_Ouvrir = new JMenuItem("Ouvrir");
		JMenuItem item_Enregistrer = new JMenuItem("Enregistrer");
		JMenuItem item_EnregistrerSous = new JMenuItem("Enregistrer sous");
		JMenuItem item_Exporter = new JMenuItem("Exporter");
		JMenuItem item_Quitter = new JMenuItem("Quitter");
		JMenuItem item_Couleur = new JMenuItem("Couleur");
		JMenuItem item_CouleurTrait = new JMenuItem("Couleur de Trait");
		JMenuItem item_LargeurTrait = new JMenuItem("Epaisseur Trait");
		this.btn_Selection = new JButton();
		JButton btn_Ellipse = new JButton();
		JButton btn_Rectangle = new JButton();
		JButton btn_Ligne = new JButton();
		//
		// panel_Centre
		panel_Centre.setOpaque(true);
		panel_Centre.setBackground(Color.gray);
		super.add(panel_Centre, BorderLayout.CENTER);
		//
		// m_Espace
		this.m_Espace.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FenetrePrincipale.this.gererClic(e);
			}

			@Override
			public void mouseReleased(MouseEvent p_e) {
				FenetrePrincipale.this.finaliserForme(p_e);
			}
		});
		this.m_Espace.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent p_e) {
				FenetrePrincipale.this.gererDrag(p_e);
			}
		});
		panel_Centre.add(this.m_Espace);
		//
		// menuBar
		super.setJMenuBar(menuBar);
		menuBar.add(menu_Fichier);
		menuBar.add(menu_Selection);
		menuBar.add(menu_Formes);
		//
		// menu_Fichier
		menu_Fichier.add(item_Ouvrir);
		menu_Fichier.add(item_Enregistrer);
		menu_Fichier.add(item_EnregistrerSous);
		menu_Fichier.add(item_Exporter);
		menu_Fichier.addSeparator();
		menu_Fichier.add(item_Quitter);
		//
		// menu_Selection
		menu_Selection.add(item_Couleur);
		menu_Selection.add(item_CouleurTrait);
		menu_Selection.add(item_LargeurTrait);
		//
		// menu_Formes
		menu_Formes.add("JMenuItems...");
		//
		// item_Ouvrir
		item_Ouvrir.addActionListener(e -> {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				ControleurFichier cont = new ControleurFichier(m_Modele);
				FormatFichier x = new FormatXml(FormeFactory.getInstance());
				cont.ouvrir(x, file);
			}
		});
		//
		// item_Enregistrer
		item_Enregistrer.addActionListener(e -> {
		});
		//
		// item_EnregistrerSous
		item_EnregistrerSous.addActionListener(e -> {
			final JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				ControleurFichier cont = new ControleurFichier(m_Modele);
				FormatFichier x = new FormatXml();
				cont.enregistrer(x, file);
			}
		});
		//
		// item_Exporter
		item_Exporter.addActionListener(e -> {
		});
		//
		// item_Quitter
		item_Quitter.addActionListener(e -> {
		});
		//
		// panel_Outils
		panel_Outils.setLayout(new BoxLayout(panel_Outils, BoxLayout.Y_AXIS));
		panel_Outils.add(btn_Selection);
		panel_Outils.add(btn_Ellipse);
		panel_Outils.add(btn_Rectangle);
		panel_Outils.add(btn_Ligne);
		super.add(panel_Outils, BorderLayout.WEST);
		//
		// btn_Selection
		btn_Selection.setIcon(FenetrePrincipale.chargerIcone("24_Souris.png"));
		btn_Selection.setSize(FenetrePrincipale.BTN_TAILLE);
		btn_Selection.addActionListener(e -> {
		});
		//
		// btn_Ellipse
		btn_Ellipse.setIcon(FenetrePrincipale.chargerIcone("24_Ellipse.png"));
		btn_Selection.setSize(FenetrePrincipale.BTN_TAILLE);
		btn_Ellipse.addActionListener(e -> {
			this.m_Forme = "Ellipse";
		});
		//
		// btn_Rectangle
		btn_Rectangle.setIcon(FenetrePrincipale.chargerIcone("24_Rectangle.png"));
		btn_Selection.setSize(FenetrePrincipale.BTN_TAILLE);
		btn_Rectangle.addActionListener(e -> {
			this.m_Forme = "Rectangle";
		});
		//
		// btn_Ligne
		btn_Ligne.setIcon(FenetrePrincipale.chargerIcone("24_Ligne.png"));
		btn_Selection.setPreferredSize(FenetrePrincipale.BTN_TAILLE);
		btn_Ligne.addActionListener(e -> {
			this.m_Forme = "Ligne";
		});
		//
		// windowClosing
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent p_e) {
				FenetrePrincipale.this.dispose();
			}
		});
	}

	private void ajouterForme(MouseEvent p_e) {
		ElementGraphique forme = FormeFactory.getInstance().getForme(this.m_Forme);
		forme.setPosition(p_e.getX(), p_e.getY());
		this.m_Modele.ajouter(forme);
	}

	private void finaliserForme(MouseEvent p_e) {
		ElementGraphique selection = this.m_Modele.getSelection();
		if (selection != null && selection.getLargeur() == 0 && selection.getHauteur() == 0) {
			selection.setLargeur(50);
			selection.setHauteur(50);
			selection.deplacer(-25, -25);
		}
		this.btn_Selection.requestFocusInWindow();
	}

	private void drag(MouseEvent p_e) {

	}

	private void gererClic(MouseEvent p_e) {
		if (this.btn_Selection.hasFocus()) {
			this.m_Modele.selectionner(p_e.getX(), p_e.getY());
		} else if (this.m_Forme != null) {
			this.ajouterForme(p_e);
		}
	}

	private void gererDrag(MouseEvent p_e) {
		ElementGraphique selection = this.m_Modele.getSelection();
		if (selection != null) {
			if (this.btn_Selection.hasFocus() && selection != null &&
					selection.contient(p_e.getX(), p_e.getY())) {
				int milieuX = selection.getLargeur() >> 1;
				int milieuY = selection.getHauteur() >> 1;
				selection.setPosition(p_e.getX() - milieuX, p_e.getY() - milieuY);
			} else {
				int largeur = p_e.getX() - selection.getX();
				int hauteur = p_e.getY() - selection.getY();
				selection.setDimension(largeur, hauteur);
			}
		}
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
