package ca.csf.tests.classes;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;

/**
 * Utilisée pour tester {@link ca.csf.modele.ModeleElementGraphique} et {@link ca.csf.modele.ElementEcoute}
 */
public class EcouteurModeleTest implements EcouteurModeleGraphique {
	
	private boolean m_Modifications = false;
	private boolean m_ModificationsElements = false;
	private boolean m_NouvelleTaille = false;
	private boolean m_NouvelleCouleurDeFond = false;
	

	public boolean aReagitModifications() {
		return m_Modifications;
	}

	public boolean aReagitModificationsElements() {
		return m_ModificationsElements;
	}

	public boolean aReagitNouvelleTaille() {
		return m_NouvelleTaille;
	}

	public boolean aReagitNouvelleCouleurDeFond() {
		return m_NouvelleCouleurDeFond;
	}
	
	@Override
	public void reagirModifications() {
		this.m_Modifications = true;
	}

	@Override
	public void reagirModifications(ElementGraphique p_Element) {
		this.m_ModificationsElements = true;
	}

	@Override
	public void reagirNouvelleTaille() {
		this.m_NouvelleTaille = true;
	}

	@Override
	public void reagirNouvelleCouleurDeFond() {
		this.m_NouvelleCouleurDeFond = true;
	}

}
