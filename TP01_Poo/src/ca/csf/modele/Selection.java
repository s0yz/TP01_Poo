package ca.csf.modele;
package ca.csf.formes.*;

public class Selection implements ElementGraphique {
	
	private Integer p_posX;
	private Integer p_posY; 
	private ElementGraphique formeContour;
	
	void setForme (ElementGraphique p_Element) {
		int ajoutContour = 2 * p_Element.getLargeurTrait();
		this.formeContour.setPosition(p_Element.getX() + ajoutContour, p_Element.getY() + ajoutContour);
	}
	
}
