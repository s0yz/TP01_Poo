package ca.csf.tests;

import ca.csf.formes.ElementGraphique;
import ca.csf.modele.EcouteurModeleGraphique;

/**
 * Utilisée pour tester les ModeleElemen.
 */
public class EcouteurModeleTest implements EcouteurModeleGraphique {
	
	private boolean m_Modifications = false;
	private boolean m_ModificationsElements = false;
	private boolean m_NouvelleTaille = false;
	private boolean m_NouvelleCouleurDeFond = false;
	

	public boolean isaReagitModifications() {
		return m_Modifications;
	}

	public boolean isaReagitModificationsElements() {
		return m_ModificationsElements;
	}

	public boolean isaReagitNouvelleTaille() {
		return m_NouvelleTaille;
	}

	public boolean isaReagitNouvelleCouleurDeFond() {
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
