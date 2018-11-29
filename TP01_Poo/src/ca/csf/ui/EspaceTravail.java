package ca.csf.ui;
import java.awt.Graphics;

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
	private ModeleElementGraphique modeleGraphique;
	private ElementGraphique rectangle;
	
	public EspaceTravail(ModeleElementGraphique p_Modele, Integer p_largeur, Integer p_hauteur) {
		this.modeleGraphique = p_Modele;
		this.modeleGraphique.ajouterEcouteur(this);
		this.rectangle = new Rectangle(0, 0, p_largeur, p_hauteur);
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		
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
	public void reagirNouvelElement(ElementGraphique p_Element) {
		this.repaint(p_Element.getPosition().x, p_Element.getPosition().y, p_Element.getLargeur(), p_Element.getHauteur());
	}
}
