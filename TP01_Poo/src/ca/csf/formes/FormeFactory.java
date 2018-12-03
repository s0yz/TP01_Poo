package ca.csf.formes;

import java.util.HashMap;
import java.util.function.BiFunction;

/**
 * @author Cedric Mariage
 *
 */
public class FormeFactory implements Forme2DFactory {

	public static final HashMap<String, BiFunction<Integer, Integer, ElementGraphique>> m_Fournisseurs;
	
	static {
		m_Fournisseurs = new HashMap<>();
		m_Fournisseurs.put("Ligne", (x, y) -> new Ligne(x, y));
		m_Fournisseurs.put("Rectangle", (x, y) -> new Rectangle(x, y));
		m_Fournisseurs.put("Ellipse", (x, y) -> new Ellipse(x, y));
	}      
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ElementGraphique getForme(String p_Nom) {	
		return this.getForme(p_Nom, 0, 0);
	}
	
	/**
	 * {@inheritDoc}
	 */
	//@Override
	public ElementGraphique getForme(String p_Nom, int x, int y) {
		return m_Fournisseurs.get(p_Nom) == null ?
				null : m_Fournisseurs.get(p_Nom).apply(x, y);
	}
}
