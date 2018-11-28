package ca.csf.modele;

import java.util.ArrayList;
import java.util.Iterator;

import ca.csf.formes.ElementGraphique;

/**
 * 
 * 
 * @author
 */
public class ModeleGraphiques implements ModeleElementGraphique {
	
	
	
	
	
	private ArrayList<ElementGraphique> m_Liste;
	private ArrayList<EcouteurModeleGraphique> m_Ecouteurs;
	
	
	
	
	
	public ModeleGraphiques() {
		this.m_Liste = new ArrayList<ElementGraphique>();
		this.m_Ecouteurs = new ArrayList<EcouteurModeleGraphique>();
	}
	
	
	@Override
	public Iterator<ElementGraphique> iterator() {
		
		return m_Liste.iterator();
	}

	
	@Override
	public void ajouterEcouteur(EcouteurModeleGraphique p_Ecouteur) {
		this.m_Ecouteurs.add(p_Ecouteur);
		
	}
	public void removeEcouteur(EcouteurModeleGraphique p_Ecouteur) {
		this.m_Ecouteurs.remove(p_Ecouteur);
	}
	
	public void ajouter (ElementGraphique p_Element) {
		this.m_Liste.add(p_Element);
		this.avertirmodifications(p_Element);
	}
	public void retirer(ElementGraphique p_Element) {
		this.m_Liste.add(p_Element);
		this.avertirmodifications(p_Element);
	}
	
	private void avertirmodifications(ElementGraphique p_Element) {
		this.m_Ecouteurs.forEach( e-> e.reagirNouvelElement(p_Element));
	}
	
	
	
}
