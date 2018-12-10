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
	private ElementGraphique m_Selection;

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
		this.m_Elements = new LinkedList<ElementGraphique>();
		this.m_Ecouteurs = new ArrayList<EcouteurModeleGraphique>();
		this.setDimension(p_Largeur, p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(ElementGraphique p_Element) {
		this.m_Selection = null;
		this.m_Selection = new ElementEcoute(p_Element, this.m_Ecouteurs);
		this.m_Elements.add(this.m_Selection);
		this.avertirModifications(p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(Iterable<ElementGraphique> p_Elements) {
		p_Elements.forEach(e -> {
			this.m_Elements.add(new ElementEcoute(e, this.m_Ecouteurs));
		});
		if (!this.m_Elements.isEmpty()) {
			this.m_Selection = this.m_Elements.getLast();
		}
		this.avertirModifications();
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
		this.m_Selection = null;
		this.m_Elements.remove(p_Element);
		this.avertirModifications(p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void vider() {
		this.m_Selection = null;
		this.m_Elements.clear();
		this.avertirModifications();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique get(int p_Indice) {
		return this.m_Elements.get(p_Indice);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getDernier() {
		return this.m_Elements.getLast();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique get(double p_X, double p_Y) {
		this.m_Selection = null;
		for (int i = this.m_Elements.size(); --i >= 0 && this.m_Selection == null;) {
			if (this.m_Elements.get(i).contient(p_X, p_Y)) {
				this.m_Selection = this.m_Elements.get(i);
			}
		}
		return this.m_Selection;
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
	public ElementGraphique getSelection() {
		return this.m_Selection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<ElementGraphique> iterator() {
		return m_Elements.iterator();
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
}
