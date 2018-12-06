package ca.csf.formes;

/**
 * @author Cedric Mariage
 *
 */
public class FormeFactory implements Forme2DFactory {

	private static FormeFactory m_Instance = new FormeFactory();

	private FormeFactory() {
	}

	public static FormeFactory getInstance() {
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
	public ElementGraphique getForme(String p_Nom, int p_X, int p_Y) {
		return this.getForme(p_Nom, p_X, p_Y, 0, 0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getForme(String p_Nom, int p_X, int p_Y, int p_Largeur, int p_Hauteur) {
		switch (p_Nom.toLowerCase()) {
		case "ligne":
			return new Ligne(p_X, p_Y, p_Largeur, p_Hauteur);
		case "rectangle":
			return new Rectangle(p_X, p_Y, p_Largeur, p_Hauteur);
		case "ellipse":
			return new Ellipse(p_X, p_Y, p_Largeur, p_Hauteur);
		default:
			return null;
		}
	}
}
