package ca.csf.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.function.Consumer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import ca.csf.formes.Rectangle;
import ca.csf.modele.ModeleGraphiques;

/**
 * @author Cedric Mariage
 */
public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = -4586998190495167383L;

	private ModeleGraphiques m_Modele;
	private EspaceTravail m_Espace;
	private Consumer<MouseEvent> m_action;

	public FenetrePrincipale() {
		super("TP01 - Poo");
		this.parametrer();
		this.initialiserComposants();
		super.pack();
	}

	private void parametrer() {
		super.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		super.setLayout(new BorderLayout());
		super.setLocationRelativeTo(null);
	}

	private void initialiserComposants() {
		this.m_Modele = new ModeleGraphiques();
		this.m_Espace = new EspaceTravail(this.m_Modele);
		JPanel center = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JMenuBar menuBar = new JMenuBar();
		JMenu menu_Fichier = new JMenu("Fichier");
		JMenu menu_Selection = new JMenu("Selection");
		JMenu menu_Formes = new JMenu("Formes");
		JMenuItem item_Ouvrir = new JMenuItem("Ouvrir");
		JMenuItem item_Enregistrer = new JMenuItem("Enregistrer");
		JMenuItem item_Exporter = new JMenuItem("Exporter");
		JMenuItem item_Quitter = new JMenuItem("Quitter");
		JMenuItem item_Couleur = new JMenuItem("Couleur");
		JMenuItem item_CouleurTrait = new JMenuItem("Couleur de Trait");
		JMenuItem item_EpaisTrait = new JMenuItem("Epaisseur Trait");
		JButton btn_Selection = new JButton();
		JButton btn_Elipse = new JButton();
		JButton btn_Rectangle = new JButton();
		JButton btn_Ligne = new JButton();
		JPanel panel_Outils = new JPanel(new GridLayout(4, 1));
		//
		// center
		center.setOpaque(true);
		center.setBackground(Color.gray);
		super.add(center, BorderLayout.CENTER);
		//
		// m_Espace
		this.m_Espace.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (FenetrePrincipale.this.m_action != null)
					FenetrePrincipale.this.m_action.accept(e);
			}
		});
		center.add(this.m_Espace);
		//
		//
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent p_e) {
				FenetrePrincipale.this.dispose();
			}
		});
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
		menu_Fichier.add(item_Exporter);
		menu_Fichier.addSeparator();
		menu_Fichier.add(item_Quitter);
		//
		// menu_Selection
		menu_Selection.add(item_Couleur);
		menu_Selection.add(item_CouleurTrait);
		menu_Selection.add(item_EpaisTrait);
		//
		// menu_Formes
		menu_Formes.add("JMenuItems...");
		//
		// item_Ouvrir
		item_Ouvrir.addActionListener(e -> {
		});
		//
		// item_Enregistrer
		item_Enregistrer.addActionListener(e -> {
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
		panel_Outils.add(btn_Selection);
		panel_Outils.add(btn_Elipse);
		panel_Outils.add(btn_Rectangle);
		panel_Outils.add(btn_Ligne);
		super.add(panel_Outils, BorderLayout.EAST);
		//
		// btn_Selection
		btn_Selection.setIcon(new ImageIcon(FenetrePrincipale.getPNG("cursor")));
		btn_Selection.addActionListener(e -> {
		});
		//
		// btn_Elipse
		btn_Elipse.setIcon(new ImageIcon(FenetrePrincipale.getPNG("circle")));
		btn_Elipse.addActionListener(e -> {
		});
		//
		// btn_Rect
		btn_Rectangle.setIcon(new ImageIcon(FenetrePrincipale.getPNG("rect")));
		btn_Rectangle.addActionListener(e -> {
			this.m_action = me -> {
				Rectangle rectangle = new Rectangle(me.getX(), me.getY(), 50, 60);
				this.m_Modele.ajouter(rectangle);
			};
		});
		//
		// btn_Ligne
		btn_Ligne.setIcon(new ImageIcon(FenetrePrincipale.getPNG("line")));
		btn_Ligne.addActionListener(e -> {
		});
	}

	/**
	 * Pour obtenir l'URL d'une image située dans "/res".
	 * 
	 * @param p_Nom le nom de l'image, sans l'extension.
	 * @return l'URL de l'image.
	 */
	private static URL getPNG(String p_Nom) {
		String chemin = String.format("/res/%s.png", p_Nom);
		URL url = FenetrePrincipale.class.getResource(chemin);
		assert (url == null) : "Image introuvable : " + chemin;
		return url;
	}
}
