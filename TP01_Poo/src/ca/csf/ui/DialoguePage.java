package ca.csf.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

/**
 * Boîte de dialogue permettant pour choisir une largeur, une hauteur, et une
 * couleur.
 */
public class DialoguePage extends JDialog {

	private static final long serialVersionUID = -5358152680497754674L;

	public static final boolean OK = true;

	public static final boolean ANNULER = false;
	
	private JSpinner spin_Hauteur;
	private JSpinner spin_Largeur;
	private JButton btn_Couleur;
	
	/**
	 * Résultat du dialogue.
	 */
	private boolean m_Resultat;

	public DialoguePage(JFrame p_Parent) {
		super(p_Parent, "Propriété de l'image", true);
		this.parametrer();
		this.initialiserComposants();
		this.pack();
	}

	/**
	 * Configure le dialogue.
	 */
	private void parametrer() {
		this.setLocationRelativeTo(this.getParent());
		this.setResizable(false);
		this.setLayout(new BorderLayout());
	}

	/**
	 * Initialise les composants du dialogue.
	 */
	private void initialiserComposants() {
		this.spin_Hauteur = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		this.spin_Largeur = new JSpinner(new SpinnerNumberModel(1, 1, 10000, 1));
		this.btn_Couleur = new JButton();
		JPanel panel_Centre = new JPanel();
		JPanel panel_Sud = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panel_Hauteur = new JPanel();
		JPanel panel_Largeur = new JPanel();
		JPanel panel_Couleur = new JPanel();
		JButton btn_terminer = new JButton("Ok");
		JButton btn_annuler = new JButton("Annuler");
		//
		// panel_Centre
		panel_Centre.setLayout(new BoxLayout(panel_Centre, BoxLayout.PAGE_AXIS));
		panel_Centre.add(panel_Largeur);
		panel_Centre.add(panel_Hauteur);
		panel_Centre.add(panel_Couleur);
		this.add(panel_Centre, BorderLayout.CENTER);
		//
		// panel_Sud
		panel_Sud.add(btn_terminer);
		panel_Sud.add(btn_annuler);
		//
		// panel_Hauteur
		this.add(panel_Sud, BorderLayout.SOUTH);
		panel_Hauteur.add(new JLabel("Hauteur : "));
		panel_Hauteur.add(this.spin_Hauteur);
		//
		// panel_Largeur
		panel_Largeur.add(new JLabel("Largeur : "));
		panel_Largeur.add(this.spin_Largeur);
		//
		// panel_Couleur
		panel_Couleur.add(new JLabel("Couleur : "));
		panel_Couleur.add(this.btn_Couleur);
		//
		// btn_Couleur
		this.btn_Couleur.setOpaque(true);
		this.btn_Couleur.setBackground(Color.RED);
		this.btn_Couleur.setBorder(LineBorder.createBlackLineBorder());
		this.btn_Couleur.setPreferredSize(new Dimension(24, 24));
		this.btn_Couleur.addActionListener(event -> {
			Color couleur = JColorChooser.showDialog(null, "Choissisez une couleur", this.btn_Couleur.getBackground());
			if (couleur != null) {
				this.btn_Couleur.setBackground(couleur);
			}
		});
		//
		// btn_terminer
		btn_terminer.requestFocus();
		btn_terminer.addActionListener(e -> {
			this.m_Resultat = true;
			this.setVisible(false);
		});
		//
		// btn_annuler
		btn_annuler.addActionListener(e -> {
			this.setVisible(false);
		});
	}

	/**
	 * Montre le dialogue.
	 * 
	 * @param p_Largeur la largeur initiale.
	 * @param p_Hauteur la hauteur initiale.
	 * @param p_Couleur la couleur initiale.
	 */
	public void montrer(int p_Largeur, int p_Hauteur, Color p_Couleur) {
		this.spin_Largeur.setValue(p_Largeur);
		this.spin_Hauteur.setValue(p_Hauteur);
		this.btn_Couleur.setBackground(p_Couleur);
		this.setVisible(true);
	}

	/**
	 * Pour obtenir la largeur.
	 * 
	 * @return la largeur.
	 */
	public int getLargeur() {
		return (int) this.spin_Largeur.getValue();
	}

	/**
	 * Pour obtenir la hauteur.
	 * 
	 * @return l'hauteur.
	 */
	public int getHauteur() {
		return (int) this.spin_Hauteur.getValue();
	}

	/**
	 * Pour obtenir la couleur.
	 * 
	 * @return la couleur.
	 */
	public Color getCouleur() {
		return this.btn_Couleur.getBackground();
	}

	/**
	 * Pour obtenir le résultat du dialogue, soit {@code DialoguePage.OK} ou
	 * {@code DialoguePage.Annuler}.
	 * 
	 * @return
	 */
	public Boolean getResultat() {
		return this.m_Resultat;
	}
}
