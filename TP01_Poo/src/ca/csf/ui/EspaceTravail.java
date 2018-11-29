package ca.csf.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Rectangle;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * 
 * @author
 */
public class EspaceTravail extends JPanel implements EcouteurModeleGraphique {

	private static final long serialVersionUID = -7570189304007187337L;
	private ModeleElementGraphique m_ModeleGraphique;
	private ElementGraphique m_Rectangle;

	public EspaceTravail(ModeleElementGraphique p_Modele, int p_largeur, int p_hauteur) {
		this.m_ModeleGraphique = p_Modele;
		this.m_ModeleGraphique.ajouterEcouteur(this);
		this.m_Rectangle = new Rectangle(0, 0, p_largeur, p_hauteur);
		this.m_Rectangle.setCouleur(Color.WHITE);
		this.setPreferredSize(new Dimension(p_largeur, p_hauteur));
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics p_Graphics) {
		Graphics2D graphics2d = (Graphics2D) p_Graphics;
		super.paintComponent(p_Graphics);
		this.m_Rectangle.dessiner(graphics2d);
		// TODO
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
	 * Redessine la zone contenant l'élément spécifié en tenant compte de la largeur
	 * du trait.
	 * 
	 * @param p_selectedShape
	 */
	private void redessinerElement(ElementGraphique p_Element) {
		this.repaint(p_Element.getX() - p_Element.getLargeurTrait(), p_Element.getY() - p_Element.getLargeurTrait(),
				p_Element.getLargeur() + 2 * p_Element.getLargeurTrait(),
				p_Element.getHauteur() + 2 * p_Element.getLargeurTrait());
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	
	void reagirNouvelleTaille(Integer p_Hauteur, Integer p_Largeur) {
		this.setPreferredSize(p_Hauteur, p_Largeur);
	};
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	void reagirNouvelleCouleur(Color p_Couleur) {
		this.setBackground(p_Couleur);
	};
}
