package ca.csf.modele;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.csf.formes.DecorateurElementGraphique;
import ca.csf.formes.ElementGraphique;

public class FormeSelection extends DecorateurElementGraphique {

	/**
	 * 
	 */
	private ArrayList<EcouteurModeleGraphique> m_Ecouteurs;
	
	/**
	 * 
	 */
	int differenceX;

	/**
	 * 
	 */
	int differenceY;
	
	public FormeSelection(ArrayList<EcouteurModeleGraphique> p_Ecouteurs, ElementGraphique p_Element) {
		super(p_Element);
		this.m_Ecouteurs = p_Ecouteurs;
	}
	
	public void setPoigne(int p_X, int p_Y) {
		this.differenceX = p_X - this.getX();
		this.differenceY = p_Y - this.getY();
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(int p_X, int p_Y) {		
		super.setPosition(p_X - this.differenceX, p_Y - this.differenceY);
		this.m_Ecouteurs.forEach(e -> e.reagirModifications());
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		super.setLargeur(p_Largeur);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		super.setHauteur(p_Hauteur);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(int p_Largeur, int p_Hauteur) {
		this.avertirModifications(this);
//		if (p_Largeur < 0) {
//			super.setPosition(this.getX() + p_Largeur, this.getY());
//			p_Largeur = Math.abs(p_Largeur);
//		}
//		if (p_Hauteur < 0) {
//			super.setPosition(this.getX(), this.getY() + p_Hauteur);
//			p_Hauteur = Math.abs(p_Hauteur);
//		}
		super.setDimension(p_Largeur, p_Hauteur);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleur(Color p_Couleur) {
		super.setCouleur(p_Couleur);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeurTrait(int p_PX) {
		super.setLargeurTrait(p_PX);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleurTrait(Color p_Couleur) {
		super.setCouleurTrait(p_Couleur);
		this.avertirModifications(this);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dessiner(Graphics2D p_Graphic) {
		super.dessiner(p_Graphic);
		p_Graphic.setColor(Color.CYAN);
		p_Graphic.drawRect(super.getX(), super.getY(),
				super.getLargeur(), super.getHauteur());
	}
	
	private void avertirModifications(ElementGraphique p_Element) {
		this.m_Ecouteurs.forEach(e-> e.reagirModifications(p_Element));
	}
}
