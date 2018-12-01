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
	
	public static final int LARGEUR_DEFAULT = 640;
	
	public static final int HAUTEUR_DEFAULT = 360;
	
	private ModeleElementGraphique m_ModeleGraphique;
	
	EspaceTravail(ModeleElementGraphique p_Modele) {
		this(p_Modele, EspaceTravail.LARGEUR_DEFAULT, EspaceTravail.HAUTEUR_DEFAULT);
	}
	
	public EspaceTravail(ModeleElementGraphique p_Modele, int p_largeur, int p_hauteur) {
		this.m_ModeleGraphique = p_Modele;
		this.setOpaque(true);
		this.setBackground(Color.WHITE);
		this.m_ModeleGraphique.ajouterEcouteur(this);
		this.setPreferredSize(new Dimension(p_largeur, p_hauteur));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void paintComponent(Graphics p_Graphics) {
		Graphics2D graphics2d = (Graphics2D) p_Graphics;
		super.paintComponent(p_Graphics);
		this.m_ModeleGraphique.forEach(e -> e.dessiner(graphics2d));
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
	public void reagirNouvelleTaille(int p_Hauteur, int p_Largeur) {
		this.setPreferredSize(new Dimension(p_Hauteur, p_Hauteur));
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reagirNouvelleCouleurDeFond(Color p_Couleur) {
		this.setBackground(p_Couleur);
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
		int largeur = p_Element.getLargeur() + 2 * p_Element.getLargeurTrait();
		int hauteur = p_Element.getHauteur() + 2 * p_Element.getLargeurTrait();
		this.repaint(x, y, largeur, hauteur);
				
	}
}
