package ca.csf.modele;

/**
 * @author Cedric Mariage
 */
public class ModeleEGEvent {
	
	public static final int MODIFICATIONS			= 0; 
	public static final int ELEMENT_AJOUTE			= 1;
	public static final int ELEMENT_SUPPRIME		= 2;
	public static final int ELEMENT_REDIMENSIONNE	= 3;
	public static final int ELEMENT_DEPLACE			= 4;
	public static final int ELEMENT_MODIFIE			= 5;	
	public static final int TAILLE_CHANGE			= 6;
	public static final int COULEUR_CHANGE			= 7;
	public static final int SELECTION_CHANGE		= 8;
	
	private int m_id;
	private double m_X;
	private double m_Y;
	private double m_Largeur;
	private double m_Hauteur;

	public ModeleEGEvent() { }
	
	public ModeleEGEvent(double p_Largeur, double p_Hauteur) {
		this(ModeleEGEvent.TAILLE_CHANGE, 0, 0, p_Largeur, p_Hauteur);
	}
	
	public ModeleEGEvent(int p_Id, double p_X, double p_Y, double p_Largeur, double p_Hauteur) {
		this.m_id = p_Id;
		this.m_X = p_X;
		this.m_Y = p_Y;
		this.m_Largeur = p_Largeur;
		this.m_Hauteur = p_Hauteur;
	}
	
	public int getId() {
		return this.m_id;
	}

	public double getX() {
		return this.m_X;
	}

	public double getY() {
		return this.m_Y;
	}

	public double getLargeur() {
		return this.m_Largeur;
	}

	public double getHauteur() {
		return this.m_Hauteur;
	}
}
