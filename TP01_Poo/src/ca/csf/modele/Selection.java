package ca.csf.modele;

import java.awt.Shape;

import ca.csf.formes.*;

public class Selection extends Forme {

	Selection(ElementGraphique p_Element, int p_SourisX, int p_SourisY) {
		super(p_Element.getNom(), p_Element.getX(), p_Element.getY(), p_SourisX, p_SourisY);
	}

	/**
	 * Décalage en x par rapport 
	 */
	private Integer p_differenceX;
	private Integer p_differenceY;
	
	private ElementGraphique formeContour;

	void setForme(ElementGraphique p_Element) {
		int ajoutContour = 2 * p_Element.getLargeurTrait();
		this.formeContour.setPosition(p_Element.getX() + ajoutContour, p_Element.getY() + ajoutContour);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {
		int x = p_X - p_differenceX;
		int y = p_Y - p_differenceY;
		this.formeContour.setPosition(x, y);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Shape getShape() {
		return new java.awt.Rectangle(this.getX(), this.getY(), this.getHauteur(), this.getLargeur());
	}

}
