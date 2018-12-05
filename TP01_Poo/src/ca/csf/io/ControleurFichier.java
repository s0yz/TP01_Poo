package ca.csf.io;

import java.util.*;
import ca.csf.formes.*;
import ca.csf.modele.ModeleGraphiques;

public class ControleurFichier {

	private ArrayList<ElementGraphique> m_Elements;
	
	
	public ControleurFichier(ModeleGraphiques p_modele) {
		// TODO Auto-generated constructor stub
		
		 for (ElementGraphique elementGraphique : p_modele) {
			m_Elements.add(elementGraphique);
		}
		 
			
	}
	
	public void enregistrer(FormatFichier p_format) {
		
		p_format.enregistrer(m_Elements);
	}
	
}
