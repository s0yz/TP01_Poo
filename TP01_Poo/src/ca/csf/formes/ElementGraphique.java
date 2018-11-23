package ca.csf.formes;

// import java.awt.Color;
import java.awt.Graphics;
// import java.awt.Point;
import java.awt.Shape;

/**
 * @author Cedric Mariage
 */
public interface ElementGraphique extends Shape {
	
	default void dessiner(Graphics p_Graphic) {}
	
//	Point getPosition();
//	
//	int getLargeur();
//	
//	int getHauteur();
//	
//	int getCouleur();
//	
	String getNom();
//	
//	void setPosition(Point p_Position);
//	
//	void setLargeur(int p_Largeur);
//	
//	void setHauteur(int p_Hauteur);
//	
//	void setCouleur(Color p_Couleur);
}
