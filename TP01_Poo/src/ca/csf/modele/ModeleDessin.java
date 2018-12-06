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
	private int m_Hauteur;

	/**
	 * 
	 */
	private int m_Largeur;

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
		this.setLargeur(p_Largeur);
		this.setHauteur(p_Hauteur);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(ElementGraphique p_Element) {
		this.deselectionner();
		this.m_Elements.add(p_Element);
		this.m_Selection = p_Element;
		this.avertirModifications(p_Element);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ajouter(Iterable<ElementGraphique> p_Elements) {
		p_Elements.forEach(this.m_Elements::add);
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
		if (!this.m_Elements.isEmpty()) {
			this.m_Selection = this.m_Elements.getLast();
		}
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
	public ElementGraphique selectionner(int p_X, int p_Y) {
		this.deselectionner();
		for (int i = this.m_Elements.size(); --i >= 0 && this.m_Selection == null;) {
			if (this.m_Elements.get(i).contient(p_X, p_Y)) {
				this.m_Selection = this.m_Elements.get(i);
			}
		}
		if (this.m_Selection != null) {
			this.avertirModifications(this.m_Selection);
		}
		return this.m_Selection;
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
	public int getLargeur() {
		return this.m_Largeur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getHauteur() {
		return this.m_Hauteur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getCouleurArrierePlan() {
		return this.m_Couleur;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setLargeur(int p_Largeur) {
		this.m_Largeur = p_Largeur;
		this.avertirNouvelleTaille();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setHauteur(int p_Hauteur) {
		this.m_Hauteur = p_Hauteur;
		this.avertirNouvelleTaille();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCouleurArrierePlan(Color p_Couleur) {
		this.m_Couleur = p_Couleur;
		this.m_Ecouteurs.forEach(e -> e.reagirNouvelleCouleurDeFond(p_Couleur));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getSelection() {
		return this.m_Selection == null ? null : new FormeSelection(this.m_Ecouteurs, this.m_Selection);
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
		this.m_Ecouteurs.forEach(e -> e.reagirNouvelleTaille(this.m_Largeur, this.m_Hauteur));
	}

	private void deselectionner() {
		ElementGraphique selection = this.m_Selection;
		this.m_Selection = null;
		if (selection != null) {
			this.avertirModifications(selection);
		}
	}
}
