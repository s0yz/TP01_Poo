package ca.csf.modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import ca.csf.formes.ElementGraphique;

/**
 * 
 * 
 * @author
 */
public class ModeleGraphiques implements ModeleElementGraphique {
	
	
	private int m_Hauteur;
	private int m_Largeur;
	private Color m_Color;
	/**
	 * 
	 */
	private LinkedList<ElementGraphique> m_Liste;
	
	/**
	 * 
	 */
	private ArrayList<EcouteurModeleGraphique> m_Ecouteurs;
		
	/**
	 * 
	 */
	public ModeleGraphiques() {
		this.m_Liste = new LinkedList<ElementGraphique>();
		this.m_Ecouteurs = new ArrayList<EcouteurModeleGraphique>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(ElementGraphique p_Element) {
		this.m_Liste.add(p_Element);
		this.avertirmodifications(p_Element);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void retirer(ElementGraphique p_Element) {
		this.m_Liste.remove(p_Element);
		this.avertirmodifications(p_Element);
	}
	
	public void vider() {
		this.m_Liste.clear();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouterEcouteur(EcouteurModeleGraphique p_Ecouteur) {
		this.m_Ecouteurs.add(p_Ecouteur);
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeEcouteur(EcouteurModeleGraphique p_Ecouteur) {
		this.m_Ecouteurs.remove(p_Ecouteur);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<ElementGraphique> iterator() {
		return m_Liste.iterator();
	}
	
	/**
	 * 
	 */
	private void avertirModifications() {
		this.m_Ecouteurs.forEach(e -> e.reagirModifications());
	}
	
	/**
	 * 
	 * @param p_Element
	 */
	private void avertirmodifications(ElementGraphique p_Element) {
		this.m_Ecouteurs.forEach(e-> e.reagirModifications(p_Element));
	}

	@Override
	public void setLargeur(int p_Largeur) {
		
		this.m_Largeur = p_Largeur;
	}

	@Override
	public void setHauteur(int p_Hauteur) {
		
		this.m_Hauteur = p_Hauteur;
	}

	@Override
	public void setCouleurArrierePlan(Color p_Color) {
		
		this.m_Color = p_Color;
	}

	@Override
	public int getLargeur() {
		
		
		return this.m_Largeur;
	}

	@Override
	public int getHauteur() {
		
		return this.m_Hauteur;
	}

	@Override
	public Color getCouleurArrierePlan() {
		
		return this.m_Color;
	}
}
