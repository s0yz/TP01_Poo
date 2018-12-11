package ca.csf.io;

/**
 * Interface offrant des méthodes de contrôle de fichiers.
 */
public interface GestionnaireIO {
	
	/**
	 * 
	 */
	void reagirNouveau();

	/**
	 * 
	 */
	void ouvrir(FormatFichier p_Format);

	/**
	 * 
	 */
	void enregistrer(FormatFichier p_FormatFichier);

	/**
	 * 
	 */
	void enregistrerSous(FormatFichier p_FormatFichier);
}
