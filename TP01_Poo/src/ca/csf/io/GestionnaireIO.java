package ca.csf.io;

/**
 * Interface offrant des méthodes de contrôle de fichiers.
 * 
 * INUTILISÉE - nous comptions donnée cette interface comme donnée membre de
 * FenetrePrincipale. Nous voulions ensuite lui donner une classe concrete dans
 * le main. Nous aurions aussi pu utilisé une factory method.
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
