package ca.csf.formes;

import java.awt.Color;
import java.awt.Graphics2D;

public class DecorateurElementGraphique implements ElementGraphique {

	ElementGraphique m_Element;
	
	public DecorateurElementGraphique(ElementGraphique p_Element) {
		this.m_Element = p_Element;
	}
	
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		this.m_Element.dessiner(p_Graphic);
	}

	@Override
	public int getX() {
		return this.m_Element.getX();
	}

	@Override
	public int getY() {
		return this.m_Element.getY();
	}

	@Override
	public int getLargeur() {
		return this.m_Element.getLargeur();
	}

	@Override
	public int getHauteur() {
		return this.m_Element.getHauteur();
	}

	@Override
	public Color getCouleur() {
		return this.m_Element.getCouleur();
	}

	@Override
	public int getLargeurTrait() {
		return this.m_Element.getLargeurTrait();
	}

	@Override
	public Color getCouleurTrait() {
		return this.m_Element.getCouleurTrait();
	}

	@Override
	public boolean contient(int p_X, int p_Y) {
		return this.m_Element.contient(p_X, p_Y);
	}

	@Override
	public String getNom() {
		return this.m_Element.getNom();
	}

	@Override
	public void setPosition(int p_X, int p_Y) {
		this.m_Element.setPosition(p_X, p_Y);
	}

	@Override
	public void setLargeur(int p_Largeur) {
		this.m_Element.setLargeur(p_Largeur);
	}

	@Override
	public void setHauteur(int p_Hauteur) {
		this.m_Element.setHauteur(p_Hauteur);
	}

	@Override
	public void setDimension(int p_Largeur, int p_Hauteur) {
		this.m_Element.setDimension(p_Largeur, p_Hauteur);
	}

	@Override
	public void setCouleur(Color p_Couleur) {
		this.m_Element.setCouleur(p_Couleur);
	}

	@Override
	public void setLargeurTrait(int p_PX) {
		this.m_Element.setLargeurTrait(p_PX);
	}

	@Override
	public void setCouleurTrait(Color p_Couleur) {
		this.m_Element.setCouleurTrait(p_Couleur);
	}
}
