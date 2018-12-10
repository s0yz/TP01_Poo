package ca.csf.modele;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import ca.csf.formes.ElementGraphique;

/**
 * Classe conservant les données représentant un dessin vectoriel.
 * 
 * Implémentation de {@code ModeleElementGraphique} utilisant une
 * {@code linkedList} pour stocker les {@code ElementGraphique}.
 * 
 * @author
 */
public class ModeleDessin implements ModeleElementGraphique {

	public static final int LARGEUR_DEFAULT = 640;
	
	public static final int HAUTEUR_DEFAULT = 360;
	
	/**
	 * 
	 */
	private double m_Hauteur;

	/**
	 * 
	 */
	private double m_Largeur;

	/**
	 * 
	 */
	private Color m_Couleur = Color.WHITE;

	/**
	 * 
	 */
	private LinkedList<ElementGraphique> m_Elements = new LinkedList<ElementGraphique>();

	/**
	 * 
	 */
	private ArrayList<EcouteurModeleGraphique> m_Ecouteurs = new ArrayList<EcouteurModeleGraphique>();

	public ModeleDessin() {
		this(LARGEUR_DEFAULT, HAUTEUR_DEFAULT);
	}
	
	public ModeleDessin(int p_Largeur, int p_Hauteur) {
		if (p_Largeur < 0 || p_Hauteur < 0) {
			throw new IllegalArgumentException("Dimensions négatives non-supportées");
		}
		this.m_Elements = new LinkedList<ElementGraphique>();
		this.m_Ecouteurs = new ArrayList<EcouteurModeleGraphique>();
		this.setDimension(p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(ElementGraphique p_Element) {
		this.inserer(this.getCompte(), p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(Iterable<ElementGraphique> p_Elements) {
		if (p_Elements == null) {
			throw new IllegalArgumentException("p_Elements est null");
		}
		p_Elements.forEach(this.m_Elements::add);
		this.avertirModifications();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void inserer(int p_Indice, ElementGraphique p_Element) {
		if (p_Indice < 0 || p_Indice > this.getCompte()) {
			throw new IllegalArgumentException("Indice invalide : " + p_Indice);
		} else if (p_Element == null) {
			throw new IllegalArgumentException("p_Elements est null");
		}
		this.m_Elements.add(p_Indice, p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void remplir(Iterable<ElementGraphique> p_Elements) {
		this.m_Elements.clear();
		this.ajouter(p_Elements);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void retirer(ElementGraphique p_Element) {
		this.m_Elements.remove(p_Element);
		this.avertirModifications(p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void vider() {
		this.m_Elements.clear();
		this.avertirModifications();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique get(int p_Indice) {
		return this.convertir(this.m_Elements.get(p_Indice));
	}	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique get(double p_X, double p_Y) {
		ElementGraphique element = null;
		for (int i = this.m_Elements.size(); --i >= 0 && element == null;) {
			if (this.m_Elements.get(i).contient(p_X, p_Y)) {
				element = this.m_Elements.get(i);
			}
		}
		return element == null ? null : this.convertir(element);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getDernier() {
		return this.convertir(this.m_Elements.getLast());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getCompte() {
		return this.m_Elements.size();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getIndiceDe(ElementGraphique p_Element) {
		return this.m_Elements.indexOf(p_Element);
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
	public void retirerEcouteur(EcouteurModeleGraphique p_Ecouteur) {
		this.m_Ecouteurs.remove(p_Ecouteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getLargeur() {
		return this.m_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getHauteur() {
		return this.m_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getArrierePlan() {
		return this.m_Couleur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setDimension(double p_Largeur, double p_Hauteur) {
		this.m_Largeur = p_Largeur;
		this.m_Hauteur = p_Hauteur;
		this.avertirNouvelleTaille();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setArrierePlan(Color p_Couleur) {
		this.m_Couleur = p_Couleur;
		this.m_Ecouteurs.forEach(e -> e.reagirNouvelleCouleurDeFond());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<ElementGraphique> iterator() {
		return new ModeleDessiniterator();
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
	private void avertirModifications(ElementGraphique p_Element) {
		this.m_Ecouteurs.forEach(e -> e.reagirModifications(p_Element));
	}

	/**
	 * 
	 */
	private void avertirNouvelleTaille() {
		this.m_Ecouteurs.forEach(e -> e.reagirNouvelleTaille());
	}
	
	private ElementGraphique convertir(ElementGraphique p_Element) {
		assert (!(p_Element instanceof ElementEcoute)) : "Élément déjà décoré.";
		return new ElementEcoute(p_Element, this.m_Ecouteurs);
	}
	
	private class ModeleDessiniterator implements Iterator<ElementGraphique> {

		private int m_Indice;
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean hasNext() {
			return this.m_Indice < ModeleDessin.this.getCompte();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public ElementGraphique next() {
			if (this.hasNext()) {
				ElementGraphique element = ModeleDessin.this.m_Elements.get(this.m_Indice++);
				return element instanceof ElementEcoute ? element : convertir(element);
			}
			return null;
		}
	}
}
