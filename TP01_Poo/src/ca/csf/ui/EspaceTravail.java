package ca.csf.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.AbstractAction;
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
 * 
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

	private ModeleElementGraphique m_ModeleGraphique;

	public EspaceTravail(ModeleElementGraphique p_Modele) {
		this.m_ModeleGraphique = p_Modele;
		this.m_ModeleGraphique.ajouterEcouteur(this);
		this.setOpaque(true);
		this.setBackground(this.m_ModeleGraphique.getArrierePlan());
		this.setPreferredSize(new Dimension((int) p_Modele.getLargeur(), (int) p_Modele.getHauteur()));
		this.parametrerActionsTouche();
	}

	private void deplacerSelection(int p_X, int p_Y) {
		if (this.m_ModeleGraphique.getSelection() != null) {
			this.m_ModeleGraphique.getSelection().deplacer(p_X, p_Y);
		}
	}

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
			this.m_ModeleGraphique.retirer(this.m_ModeleGraphique.getSelection());
		}));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics p_Graphics) {
		Graphics2D graphics2d = (Graphics2D) p_Graphics;
		ElementGraphique element = this.m_ModeleGraphique.getSelection();
		super.paintComponent(p_Graphics);
		this.m_ModeleGraphique.forEach(e -> e.dessiner(graphics2d));
		if (element != null) {
			int x = (int) Math.min(element.getX(), element.getX() + element.getLargeur());
			int y = (int) Math.min(element.getY(), element.getY() + element.getHauteur());
			int largeur = (int) Math.abs(element.getLargeur());
			int hauteur = (int) Math.abs(element.getHauteur());
			graphics2d.setColor(Color.CYAN);
			graphics2d.setStroke(new BasicStroke(1));
			graphics2d.drawRect(x, y, largeur, hauteur);
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
	public void reagirNouvelleTaille(double p_Largeur, double p_Hauteur) {
		this.setPreferredSize(new Dimension((int) p_Largeur, (int) p_Hauteur));
		this.updateUI();
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleCouleurDeFond(Color p_Couleur) {
		this.setBackground(p_Couleur);
		this.updateUI();
	};

	/**
	 * Redessine la zone contenant l'élément spécifié en tenant compte de la largeur
	 * du trait.
	 * 
	 * @param p_Element
	 */
	private void redessinerElement(ElementGraphique p_Element) {
		int x = (int) p_Element.getX();
		int y = (int) p_Element.getY();
		int largeur = (int) p_Element.getLargeur();
		int hauteur = (int) p_Element.getHauteur();
		if (largeur < 0) {
			x += largeur;
			largeur *= -1;
		}
		if (hauteur < 0) {
			y += hauteur;
			hauteur *= -1;
		}
		x -= p_Element.getLargeurTrait();
		y -= p_Element.getLargeurTrait();
		largeur += 2 * p_Element.getLargeurTrait();
		hauteur += 2 * p_Element.getLargeurTrait();
		this.repaint(x, y, largeur, hauteur);
	}

	/**
	 * Simplement pour créer des AbstractAction plu
	 */
	private class ActionTouche extends AbstractAction {

		private static final long serialVersionUID = -3281540826837033741L;

		Consumer<ActionEvent> m_Action;

		public ActionTouche(Consumer<ActionEvent> p_Action) {
			this.m_Action = p_Action;
		}

		@Override
		public void actionPerformed(ActionEvent p_e) {
			this.m_Action.accept(p_e);
		}
	}
}
