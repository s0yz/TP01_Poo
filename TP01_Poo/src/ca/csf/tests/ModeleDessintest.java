package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Rectangle;
import ca.csf.modele.EcouteurModeleGraphique;
import ca.csf.modele.ElementEcoute;
import ca.csf.modele.ModeleDessin;

class ModeleDessinTest {
	

	@Test
	void constructeurDefaut() {
		ModeleDessin md = new ModeleDessin();
		assertEquals(640, md.getLargeur());
		assertEquals(360, md.getHauteur());
	}
	
	@Test
	void constructeurInitialisation() {
		ModeleDessin md = new ModeleDessin(600, 300);
		assertEquals(600, md.getLargeur());
		assertEquals(300, md.getHauteur());
		assertEquals(0, md.getCompte());
	}
	
	@Test
	void constructeurInvalid() {
		assertThrows(IllegalArgumentException.class, () -> new ModeleDessin(-600, 300));
		assertThrows(IllegalArgumentException.class, () -> new ModeleDessin(600, -300));
	}
	
	@Test
	void ajouter() {
		ModeleDessin md = new ModeleDessin();
		ElementGraphique el = new Rectangle();  
		md.ajouter(el);
		assertEquals(1, md.getCompte());
	}
	
	@Test
	void ajouterListeElements() {
		ModeleDessin md = new ModeleDessin();
		ArrayList<ElementGraphique> egList = new ArrayList<ElementGraphique>();  
		ElementGraphique a = new Rectangle(); 
		ElementGraphique b = new Rectangle(); 
		ElementGraphique c = new Rectangle(); 
		egList.add(a);
		egList.add(b);
		egList.add(c);
		md.ajouter(egList);
		assertEquals(3, md.getCompte());
	}
	
	@Test
	void ajouterListeElementsInvalid() {
		ModeleDessin md = new ModeleDessin();
		final ArrayList<ElementGraphique> egList = null;
		assertThrows(IllegalArgumentException.class, () -> md.ajouter(egList));
	}
	
	@Test
	void inserer() {
		ModeleDessin md = new ModeleDessin();
		ElementGraphique el = new Rectangle();
		md.inserer(0, el);
		assertEquals(0, md.getIndiceDe(el));
	}
	
	@Test
	void insererElementNull() {
		ModeleDessin md = new ModeleDessin();
		ElementGraphique el = null;
		assertThrows(IllegalArgumentException.class, () -> md.inserer(0, el));
	}
	
	@Test
	void insererIndiceInvalid() {
		ModeleDessin md = new ModeleDessin();
		ElementGraphique el = new Rectangle();
		assertThrows(IllegalArgumentException.class, () -> md.inserer(3, el));
	}
	
	@Test
	void remplir() {
		ModeleDessin md = new ModeleDessin();
		ArrayList<ElementGraphique> egList = new ArrayList<ElementGraphique>();  
		ElementGraphique a = new Rectangle(); 
		ElementGraphique b = new Rectangle(); 
		ElementGraphique c = new Rectangle(); 
		egList.add(a);
		egList.add(b);
		egList.add(c);
		md.remplir(egList);
		assertEquals(egList.size(), md.getCompte());
	}
	
	@Test
	void retirer() {
		ModeleDessin md = new ModeleDessin();
		ElementGraphique el = new Rectangle();  
		md.ajouter(el);
		md.retirer(el);
		assertEquals(0, md.getCompte());
	}
	
	@Test
	void vider() {
		ModeleDessin md = new ModeleDessin(); 
		ArrayList<ElementGraphique> egList = new ArrayList<ElementGraphique>();  
		ElementGraphique a = new Rectangle(); 
		ElementGraphique b = new Rectangle(); 
		ElementGraphique c = new Rectangle(); 
		egList.add(a);
		egList.add(b);
		egList.add(c);
		md.remplir(egList);
		md.vider();
		assertEquals(0, md.getCompte());
	}
	
	@Test
	void get() {
		ModeleDessin md = new ModeleDessin(); 
		ElementGraphique el = new Rectangle();  
		md.ajouter(el);
		assertEquals(el, md.get(0));
	}
	
	@Test
	void getMauvaisIndice() {
		ModeleDessin md = new ModeleDessin(); 
		ElementGraphique el = new Rectangle();  
		md.ajouter(el);
		assertThrows(IllegalArgumentException.class, () -> md.get(-1));
	}
	
	@Test
	void getPoint() {
		ModeleDessin md = new ModeleDessin(); 
		ElementGraphique el = new Rectangle(0, 0, 300, 300 );
		ElementGraphique el2 = new Rectangle(10, 0, 300, 300 );
		md.ajouter(el);
		assertEquals(el, md.get(150, 150));
		assertNull(md.get(301, 301));
		md.ajouter(el2);
		assertEquals(el2, md.get(150, 150));
	}
	
	@Test
	void getDernier() {
		ModeleDessin md = new ModeleDessin(); 
		ElementGraphique a = new Rectangle();
		ElementGraphique b = new Rectangle();
		md.ajouter(a);
		md.ajouter(b);
		assertEquals(b, md.getDernier());
	}
	
	@Test
	void retirerEcouteur() {
		ModeleDessin md = new ModeleDessin(); 
		EcouteurModeleTest em = new EcouteurModeleTest();
		md.ajouterEcouteur(em);
		md.retirerEcouteur(em);
		assertEquals(0, md.getCompteEcouteur());
	}
	
	@Test
	void getArrierePlan() {
		ModeleDessin md = new ModeleDessin(); 
		md.setArrierePlan(Color.RED);
		assertEquals(Color.RED, md.getArrierePlan());
	}
	
	@Test
	void iterator() {
		ModeleDessin md = new ModeleDessin(); 
		ArrayList<ElementGraphique> egList = new ArrayList<ElementGraphique>();  
		ElementGraphique a = new Rectangle(); 
		ElementGraphique b = new Rectangle(); 
		ElementGraphique c = new Rectangle(); 
		int increment = 0;
		egList.add(a);
		egList.add(b);
		egList.add(c);
		md.ajouter(egList);
		Iterator it = md.iterator();
		while (it.hasNext()) {
			increment++;
			it.next();
		}
		assertNull(it.next());
		assertEquals(3, increment);
	}
	
	@Test
	void avertirModificationNouvelleCouleurArrierePlan() {
		EcouteurModeleTest e1 = new EcouteurModeleTest();
		EcouteurModeleTest e2 = new EcouteurModeleTest();
			
		ModeleDessin md = new ModeleDessin();
		md.ajouterEcouteur(e1);
		md.ajouterEcouteur(e2);
		
		md.setArrierePlan(Color.RED);
		
		assertTrue(e1.aReagitNouvelleCouleurDeFond());
		assertTrue(e2.aReagitNouvelleCouleurDeFond());
	}
	
	@Test
	void avertirModificationNouvelleNouvelleTaille() {
		EcouteurModeleTest e1 = new EcouteurModeleTest();
		EcouteurModeleTest e2 = new EcouteurModeleTest();
			
		ModeleDessin md = new ModeleDessin();
		md.ajouterEcouteur(e1);
		md.ajouterEcouteur(e2);
		
		md.setDimension(300, 300);
		
		assertTrue(e1.aReagitNouvelleTaille());
		assertTrue(e2.aReagitNouvelleTaille());
	}
	
	@Test
	void avertirModificationAjoutElement() {
		EcouteurModeleTest e1 = new EcouteurModeleTest();
		EcouteurModeleTest e2 = new EcouteurModeleTest();
		ModeleDessin md = new ModeleDessin();
		ArrayList<ElementGraphique> egList = new ArrayList<ElementGraphique>();  
		ElementGraphique a = new Rectangle(); 
		ElementGraphique b = new Rectangle(); 
		ElementGraphique c = new Rectangle(); 
		egList.add(a);
		egList.add(b);
		egList.add(c);
		md.ajouterEcouteur(e1);
		md.ajouterEcouteur(e2);
		
		md.ajouter(egList);
		
		assertTrue(e1.aReagitModifications());
		assertTrue(e2.aReagitModifications());
	}
	
	@Test
	void avertirModificationRetraitElement() {
		EcouteurModeleTest e1 = new EcouteurModeleTest();
		EcouteurModeleTest e2 = new EcouteurModeleTest();
		ModeleDessin md = new ModeleDessin();
		ElementGraphique el = new Rectangle();
		md.ajouterEcouteur(e1);
		md.ajouterEcouteur(e2);
		md.ajouter(el);
		md.retirer(el);
		
		assertTrue(e1.aReagitModificationsElements());
		assertTrue(e2.aReagitModificationsElements());
	}
	
	@Test
	void avertirModificationVider() {
		EcouteurModeleTest e1 = new EcouteurModeleTest();
		EcouteurModeleTest e2 = new EcouteurModeleTest();
		ModeleDessin md = new ModeleDessin();
		ArrayList<ElementGraphique> egList = new ArrayList<ElementGraphique>();  
		ElementGraphique a = new Rectangle(); 
		ElementGraphique b = new Rectangle(); 
		ElementGraphique c = new Rectangle(); 
		egList.add(a);
		egList.add(b);
		egList.add(c);
		md.ajouterEcouteur(e1);
		md.ajouterEcouteur(e2);
		md.remplir(egList);
		md.vider();
		
		assertTrue(e1.aReagitModifications());
		assertTrue(e2.aReagitModifications());
	}
	
}
