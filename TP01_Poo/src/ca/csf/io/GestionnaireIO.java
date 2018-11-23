package ca.csf.io;

import java.io.File;

import ca.csf.modele.EcouteurModeleGraphique;

/**
 * @author Cedric Mariage
 */
public interface GestionnaireIO extends EcouteurModeleGraphique {
	void ouvrir(File p_Fichier);
	void enregistrer();
	// TODO
}
