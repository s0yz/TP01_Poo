package ca.csf.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ModeleElementGraphique;

/**
 * 
 * @author
 */
public class EspaceTravail extends JPanel implements EcouteurModeleGraphique {

	private static final long serialVersionUID = -7570189304007187337L;
		
	private ModeleElementGraphique m_ModeleGraphique;
		
	public EspaceTravail(ModeleElementGraphique p_Modele) {
		this.m_ModeleGraphique = p_Modele;
		this.setOpaque(true);
		this.setBackground(this.m_ModeleGraphique.getCouleurArrierePlan());
		this.m_ModeleGraphique.ajouterEcouteur(this);
		this.setPreferredSize(new Dimension(p_Modele.getLargeur(), p_Modele.getHauteur()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics p_Graphics) {
		Graphics2D graphics2d = (Graphics2D) p_Graphics;
		super.paintComponent(p_Graphics);
		this.m_ModeleGraphique.forEach(e -> e.dessiner(graphics2d));
		if (this.m_ModeleGraphique.getSelection() != null) {
			this.m_ModeleGraphique.getSelection().dessiner(graphics2d);
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
	public void reagirNouvelleTaille(int p_Largeur, int p_Hauteur) {
		this.setPreferredSize(new Dimension(p_Largeur, p_Hauteur));
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
		int x = p_Element.getX() - p_Element.getLargeurTrait();
		int y = p_Element.getY() - p_Element.getLargeurTrait();
		int largeur = p_Element.getLargeur();
		int hauteur = p_Element.getHauteur();
		if (largeur < 0) {
			x += largeur;
			largeur *= -1;
		}
		if (hauteur < 0) {
			y += hauteur;
			hauteur *= -1;
		}
		largeur += 2 * p_Element.getLargeurTrait();
		hauteur += 2 * p_Element.getLargeurTrait();
		this.repaint(x, y, largeur, hauteur);
	}	
}
