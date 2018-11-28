package ca.csf.formes;

import java.awt.Color;
import java.awt.Point;

public class Rectangle implements ElementGraphique {
	
	private Integer x;
	private Integer y;
	private Integer largeur;
	private Integer Hauteur;

	public Rectangle (Integer p_x, Integer p_y, Integer p_Largeur, Integer p_Hauteur) {
		this.x = p_x;
		this.y = p_y;
		this.largeur = p_Largeur;
		this.Hauteur = p_Hauteur;
	}
	
	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLargeur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHauteur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCouleur() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Point p_Position) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setPosition(int p_X, int p_Y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLargeur(int p_Largeur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHauteur(int p_Hauteur) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCouleur(Color p_Couleur) {
		// TODO Auto-generated method stub

	}

}
