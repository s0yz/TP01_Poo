package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Rectangle;
import ca.csf.modele.*;
import ca.csf.tests.classes.EcouteurModeleTest;

class ElementEcouteTest {

	@Test
	void Constructor() {
		ArrayList<EcouteurModeleGraphique> ecouteurs = new ArrayList();
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		assertSame(rect, decorateur.getElement());
		assertThrows(IllegalArgumentException.class, () -> new ElementEcoute(rect, null));
	}
	
	@Test
	void setPosition() {
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		EcouteurModeleTest e1 = new EcouteurModeleTest(), e2  = new EcouteurModeleTest();
		List<EcouteurModeleGraphique> ecouteurs = Arrays.asList(e1, e2);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		decorateur.setPosition(44, 55);
		assertTrue(e1.aReagitModifications() || e1.aReagitModificationsElements());
		assertTrue(e2.aReagitModifications() || e2.aReagitModificationsElements());
		assertEquals(44, decorateur.getX());
		assertEquals(55, decorateur.getY());
	}
	
	@Test
	void setDimension() {
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		EcouteurModeleTest e1 = new EcouteurModeleTest(), e2  = new EcouteurModeleTest();
		List<EcouteurModeleGraphique> ecouteurs = Arrays.asList(e1, e2);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		decorateur.setDimension(44, 55);
		assertTrue(e1.aReagitModificationsElements());
		assertTrue(e2.aReagitModificationsElements());
		assertEquals(44, decorateur.getLargeur());
		assertEquals(55, decorateur.getHauteur());
	}
	
	@Test
	void setCouleur() {
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		EcouteurModeleTest e1 = new EcouteurModeleTest(), e2  = new EcouteurModeleTest();
		List<EcouteurModeleGraphique> ecouteurs = Arrays.asList(e1, e2);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		decorateur.setCouleur(Color.BLACK);
		assertTrue(e1.aReagitModificationsElements());
		assertTrue(e2.aReagitModificationsElements());
		assertEquals(Color.BLACK, decorateur.getCouleur());
	}
	
	@Test
	void setLargeurTrait() {
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		EcouteurModeleTest e1 = new EcouteurModeleTest(), e2  = new EcouteurModeleTest();
		List<EcouteurModeleGraphique> ecouteurs = Arrays.asList(e1, e2);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		decorateur.setLargeurTrait(2);
		assertTrue(e1.aReagitModificationsElements());
		assertTrue(e2.aReagitModificationsElements());
		assertEquals(2, decorateur.getLargeurTrait());
	}
	
	@Test
	void setCouleurTrait() {
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		EcouteurModeleTest e1 = new EcouteurModeleTest(), e2  = new EcouteurModeleTest();
		List<EcouteurModeleGraphique> ecouteurs = Arrays.asList(e1, e2);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		decorateur.setCouleurTrait(Color.BLACK);
		assertTrue(e1.aReagitModificationsElements());
		assertTrue(e2.aReagitModificationsElements());
		assertEquals(Color.BLACK, decorateur.getCouleurTrait());
	}
}
