package ca.csf.modele;

import ca.csf.formes.ElementGraphique;

/**
 * 
 * 
 * @author
 */
public interface ModeleElementGraphique extends Iterable<ElementGraphique> {
	void ajouterEcouteur(EcouteurModeleGraphique p_Ecouteur);
	
}
