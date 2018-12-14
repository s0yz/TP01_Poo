package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Ellipse;
import ca.csf.io.GestionnaireFichier;
import ca.csf.modele.ModeleDessin;

class GestionnaireFichierTest {

	@Test
	void constructeur() {
		GestionnaireFichier gf = new GestionnaireFichier(null, new ModeleDessin());
		assertTrue(gf.estEnregistrer());
		assertThrows(IllegalArgumentException.class, () -> new GestionnaireFichier(null, null));
	}

	@Test
	void estEnregistrer_reagirModifications01() {
		ModeleDessin md = new ModeleDessin();
		GestionnaireFichier gf = new GestionnaireFichier(null, md);
		md.ajouter(new Ellipse());
		assertFalse(gf.estEnregistrer());
	}
	
	@Test
	void estEnregistrer_reagirModifications02() {
		ModeleDessin md = new ModeleDessin();
		Ellipse e = new Ellipse();
		md.ajouter(e);
		
		GestionnaireFichier gf = new GestionnaireFichier(null, md);
		md.retirer(e);
		assertFalse(gf.estEnregistrer());
	}
	
	@Test
	void estEnregistrer_reagirNouvelleTaille() {
		ModeleDessin md = new ModeleDessin();
		GestionnaireFichier gf = new GestionnaireFichier(null, md);
		md.setDimension(34, 34);
		assertFalse(gf.estEnregistrer());
	}
	
	@Test
	void estEnregistrer_reagirNouvelleCouleurDeFond() {
		ModeleDessin md = new ModeleDessin();
		GestionnaireFichier gf = new GestionnaireFichier(null, md);
		md.setArrierePlan(Color.BLACK);
		assertFalse(gf.estEnregistrer());
	}
	
	
	//...
}
