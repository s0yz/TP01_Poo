package ca.csf.modele;

import java.awt.Color;
import java.util.ArrayList;

import ca.csf.formes.DecorateurElementGraphique;
import ca.csf.formes.ElementGraphique;

public class ElementEcoute extends DecorateurElementGraphique {

	/**
	 * 
	 */
	private ArrayList<EcouteurModeleGraphique> m_Ecouteurs;
	
	
	public ElementEcoute(ElementGraphique p_Element, ArrayList<EcouteurModeleGraphique> p_Ecouteurs) {
		super(p_Element);
		this.m_Ecouteurs = p_Ecouteurs;
	}
	
	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setPosition(double p_X, double p_Y) {		
		super.setPosition(p_X, p_Y);
		this.m_Ecouteurs.forEach(e -> e.reagirModifications());
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(double p_Largeur) {
		super.setLargeur(p_Largeur);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(double p_Hauteur) {
		super.setHauteur(p_Hauteur);
		this.avertirModifications(this);
	}

	/** 
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(double p_Largeur, double p_Hauteur) {
		this.avertirModifications(this);
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
			
	private void avertirModifications(ElementGraphique p_Element) {
		this.m_Ecouteurs.forEach(e-> e.reagirModifications(p_Element));
	}
}
