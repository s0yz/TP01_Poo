package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * @author Cedric Mariage
 */
public interface ElementGraphique {
	
	default void dessiner(Graphics p_Graphic) {}
	
	Point getPosition();
	
	int getLargeur();
	
	int getHauteur();
	
	int getCouleur();
	
	String getNom();
	
	void setPosition(Point p_Position);
	
	void setPosition(int p_X, int p_Y);
	
	void setLargeur(int p_Largeur);
	
	void setHauteur(int p_Hauteur);
	
	void setCouleur(Color p_Couleur);
}
