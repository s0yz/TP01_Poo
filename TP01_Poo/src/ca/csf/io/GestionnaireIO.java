package ca.csf.io;

import java.io.File;

/**
 * @author Cedric Mariage
 */
public interface GestionnaireIO {
	void ouvrir(File p_Fichier);
	void enregistrer(FormatFichier p_FormatFichier);
	// TODO
}
