package ca.csf.modele;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import ca.csf.formes.*;

public class Selection implements ElementGraphique {
	
	private Integer p_posX;
	private Integer p_posY;
	private ElementGraphique formeContour;
	
	void setForme (ElementGraphique p_Element) {
		int ajoutContour = 2 * p_Element.getLargeurTrait();
		this.formeContour.setPosition(p_Element.getX() + ajoutContour, p_Element.getY() + ajoutContour);
	}

	@Override
	public void dessiner(Graphics2D p_Graphic) {
	}

	@Override
	public int getX() {
		return 0;
	}

	@Override
	public int getY() {
		return 0;
	}

	@Override
	public Point getPosition() {
		return null;
	}

	@Override
	public int getLargeur() {
		return 0;
	}

	@Override
	public int getHauteur() {
		return 0;
	}

	@Override
	public Color getCouleur() {
		return null;
	}

	@Override
	public int getLargeurTrait() {
		return 0;
	}

	@Override
	public Color getCouleurTrait() {
		return null;
	}

	@Override
	public boolean contient(int p_X, int p_Y) {
		return false;
	}

	@Override
	public boolean contient(Point p_Point) {
		return false;
	}

	@Override
	public String getNom() {
		return null;
	}

	@Override
	public void setPosition(Point p_Position) {
	}

	@Override
	public void setPosition(int p_X, int p_Y) {
	}

	@Override
	public void setLargeur(int p_Largeur) {
	}

	@Override
	public void setHauteur(int p_Hauteur) {
	}

	@Override
	public void setCouleur(Color p_Couleur) {
	}

	@Override
	public void setLargeurTrait(int p_PX) {
	}

	@Override
	public void setCouleurTrait(Color p_Couleur) {
	}
	
}
