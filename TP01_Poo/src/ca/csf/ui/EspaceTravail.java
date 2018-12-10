package ca.csf.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * Classe représentant l'espace de dessin.
 */
public class EspaceTravail extends JPanel implements EcouteurModeleGraphique {

	private static final long serialVersionUID = -7570189304007187337L;
	
	/*
	 * Actions
	 */
	private static final String vkGauche = "vkGauche";
	private static final String vkDroite = "vkDroite";
	private static final String vkHaut = "vkHaut";
	private static final String vkBas = "vkBas";
	private static final String vkDelete = "vkDelete";

	/*
	 * Test. To be continued...
	 */
	private BufferedImage m_Dessin;

	/**
	 * Modèle
	 */
	private ModeleElementGraphique m_Modele;

	/**
	 * Element sélectionné.
	 */
	private ElementManipulable m_Selection = new ElementManipulable(null, 0, 0);

	/**
	 * Construit un EspaceTravail
	 * 
	 * @param p_Modele
	 */
	public EspaceTravail(ModeleElementGraphique p_Modele) {
		this.setModeleGraphique(p_Modele);
		this.parametrerActionsTouche();
		this.setOpaque(true);
	}

	/**
	 * Pour déplacer la sélection.
	 * 
	 * @param p_X déplacement en x.
	 * @param p_Y déplacement en y.
	 */
	private void deplacerSelection(int p_X, int p_Y) {
		if (!this.m_Selection.estVide()) {
			this.m_Selection.deplacer(p_X, p_Y);
		}
	}

	/**
	 * Pour obtenir le modèle.
	 * 
	 * @return le modèle.
	 */
	public ModeleElementGraphique getModele() {
		return this.m_Modele;
	}

	/**
	 * Pour changer le modèle.
	 * 
	 * @param p_Modele le nouveau modèle.
	 */
	public void setModeleGraphique(ModeleElementGraphique p_Modele) {
		int largeur;
		int hauteur;
		if (p_Modele == null) {
			throw new IllegalArgumentException("p_modeleGraphique est null");
		}
		if (this.m_Modele != null) {
			this.m_Modele.retirerEcouteur(this);
		}
		this.m_Modele = p_Modele;
		this.m_Modele.ajouterEcouteur(this);
		largeur = (int) Math.round(this.m_Modele.getLargeur());
		hauteur = (int) Math.round(this.m_Modele.getHauteur());
		this.setBackground(this.m_Modele.getArrierePlan());
		this.setPreferredSize(new Dimension(largeur, hauteur));
		this.m_Dessin = new BufferedImage(largeur, hauteur, 1);
		this.repaint();
	}

	/**
	 * Pour obtenir la selection.
	 * 
	 * @return l'élément sélectionner selection.
	 */
	public ElementManipulable getSelection() {
		return m_Selection.estVide() ? null : this.m_Selection;
	}

	/**
	 * Pour modifier la selection.
	 * 
	 * @param p_selection La nouvelle valeur.
	 */
	public void setSelection(ElementManipulable p_selection) {
		if (p_selection == null) {
			throw new IllegalArgumentException("p_selection est null");
		}
		this.m_Selection = p_selection;
		this.repaint();
	}

	/**
	 * Non-utilisée. À suivre...
	 */
	@SuppressWarnings("unused")
	private void dessiner() {
		Graphics2D graphics2d = this.m_Dessin.createGraphics();
		this.m_Modele.forEach(e -> e.dessiner(graphics2d));
		if (!this.m_Selection.estVide()) {
			this.m_Selection.dessiner(graphics2d);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics p_Graphics) {
		Graphics2D graphics2d = (Graphics2D) p_Graphics;
		super.paintComponent(p_Graphics);
		this.m_Modele.forEach(e -> e.dessiner(graphics2d));
		if (!this.m_Selection.estVide()) {
			this.m_Selection.dessiner(graphics2d);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications() {
		this.repaint();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirModifications(ElementGraphique p_Element) {
		this.redessinerElement(p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleTaille() {
		int largeur = (int) Math.floor(this.m_Modele.getLargeur());
		int hauteur = (int) Math.floor(this.m_Modele.getHauteur());
		this.setPreferredSize(new Dimension(largeur, hauteur));
		this.updateUI();
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleCouleurDeFond() {
		this.setBackground(this.m_Modele.getArrierePlan());
		this.updateUI();
	};

	/**
	 * Redessine la zone contenant l'élément spécifié en tenant compte de la largeur
	 * du trait et des dimensions négatives.
	 * 
	 * @param p_Element
	 */
	private void redessinerElement(ElementGraphique p_Element) {
		if (p_Element != null) {
			int x = (int) Math.round(Math.min(p_Element.getX(), p_Element.getX() + p_Element.getLargeur()));
			int y = (int) Math.round(Math.min(p_Element.getY(), p_Element.getY() + p_Element.getHauteur()));
			int largeur = (int) Math.round(Math.abs(p_Element.getLargeur()));
			int hauteur = (int) Math.round(Math.abs(p_Element.getHauteur()));
			x -= p_Element.getLargeurTrait();
			y -= p_Element.getLargeurTrait();
			largeur += 2 * p_Element.getLargeurTrait();
			hauteur += 2 * p_Element.getLargeurTrait();
			this.repaint(x, y, largeur, hauteur);
		}
	}

	/**
	 * Associe des touches du clavier à des actions, soit les flèches pour déplacer
	 * les formes et Delete pur les supprimer.
	 */
	private void parametrerActionsTouche() {
		ActionMap actionMap = this.getActionMap();
		InputMap inputMap = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), vkGauche);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), vkDroite);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), vkHaut);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), vkBas);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), vkDelete);
		actionMap.put(vkGauche, new ActionTouche(e -> {
			this.deplacerSelection(-10, 0);
		}));
		actionMap.put(vkDroite, new ActionTouche(e -> {
			this.deplacerSelection(10, 0);
		}));
		actionMap.put(vkHaut, new ActionTouche(e -> {
			this.deplacerSelection(0, -10);
		}));
		actionMap.put(vkBas, new ActionTouche(e -> {
			this.deplacerSelection(0, 10);
		}));
		actionMap.put(vkDelete, new ActionTouche(e -> {
			if (this.m_Modele.getSelection() != null) {
				this.m_Selection.set(null, 0, 0);
				this.m_Modele.retirer(this.m_Modele.getSelection());
				this.redessinerElement(m_Selection.getCarre());
			}
		}));
	}
}
