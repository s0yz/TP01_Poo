package ca.csf.modele;

import java.awt.Color;
import java.util.List;

import ca.csf.formes.DecorateurElementGraphique;
import ca.csf.formes.ElementGraphique;

/**
 * Décorateur concret d'{@code ElementGraphiqe}.
 * 
 * ElementGraphiqe signalant toutes modifications à une liste
 * d'{@code EcouteurModeleGraphique}s.
 */
public class ElementEcoute extends DecorateurElementGraphique {

	/**
	 * Liste d'écouteurs
	 */
	private List<EcouteurModeleGraphique> m_Ecouteurs;

	/**
	 * Construit un ElementEcouté.
	 * 
	 * @param p_Element   élément décoré.
	 * @param p_Ecouteurs liste d'écouteurs.
	 * @throws IllegalArgumentException si p_Ecouteurs est null.
	 */
	public ElementEcoute(ElementGraphique p_Element, List<EcouteurModeleGraphique> p_Ecouteurs) {
		super(p_Element);
		if (p_Ecouteurs == null) {
			throw new IllegalArgumentException("p_Ecouteurs est null");
		}
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
	
	/**
	 * Avertir les écouteurs d'une modification.
	 * @param p_Element Element ayant été modifié.
	 */
	private void avertirModifications(ElementGraphique p_Element) {
		this.m_Ecouteurs.forEach(e -> e.reagirModifications(p_Element));
	}
}
