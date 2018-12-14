package ca.csf.formes;

/**
 * Fournit des méthodes pour obtenir des {@link Forme}.
 */
public class UsineForme implements UsineElementGraphique {

	private static UsineForme m_Instance = new UsineForme();

	private UsineForme() {}

	/**
	 * Pour obtenir l'instance de l'usine.
	 * @return
	 */
	public static UsineForme getInstance() {
		return m_Instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getForme(String p_Nom) {
		return this.getForme(p_Nom, 0, 0, 0, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getForme(String p_Nom, double p_X, double p_Y, double p_Largeur, double p_Hauteur) {
		if (p_Nom == null) {
			throw new IllegalArgumentException("p_Nom est null");
		}
		switch (p_Nom.toLowerCase()) {
		case "ligne":
			return new Ligne(p_X, p_Y, p_X + p_Largeur, p_Y + p_Hauteur);
		case "rectangle":
			return new Rectangle(p_X, p_Y, p_Largeur, p_Hauteur);
		case "ellipse":
			return new Ellipse(p_X, p_Y, p_Largeur, p_Hauteur);
		default:
			return null;
		}
	}
}
