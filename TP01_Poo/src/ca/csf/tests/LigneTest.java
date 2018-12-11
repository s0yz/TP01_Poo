package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Ligne;

class LigneTest {

	static final double Delta = 0.000000000001;
	
	@Test
	void constructeurDefault() {
		Ligne ligne = new Ligne();
		assertEquals(0, ligne.getX());
		assertEquals(0, ligne.getX1());
		assertEquals(0, ligne.getX2());
		assertEquals(0, ligne.getY());
		assertEquals(0, ligne.getY1());
		assertEquals(0, ligne.getY2());
		assertEquals(0, ligne.getLargeur());
		assertEquals(0, ligne.getHauteur());
		assertEquals(0, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
	}
	
	@Test
	void constructeurInitialisation_01() {
		double x1 = 45.5, y1 = 24.33, x2 = 5756, y2 = 9999.99;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x1, ligne.getX1());
		assertEquals(x2, ligne.getX2());
		assertEquals(y1, ligne.getY());
		assertEquals(y1, ligne.getY1());
		assertEquals(y2, ligne.getY2());
		assertEquals(x2 - x1, ligne.getLargeur());
		assertEquals(y2 - y1, ligne.getHauteur());
		assertEquals(0, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
	}
	
	@Test
	void constructeurInitialisation_02() {
		double x1 = -456.87, y1 = 34, x2 = 656, y2 = -20;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x1, ligne.getX1());
		assertEquals(x2, ligne.getX2(), Delta);
		assertEquals(y1, ligne.getY());
		assertEquals(y1, ligne.getY1());
		assertEquals(y2, ligne.getY2());
		assertEquals(x2 - x1, ligne.getLargeur());
		assertEquals(y2 - y1, ligne.getHauteur());
	}
	
	@Test
	void constructeurInitialisation_03() {
		double x1 = -45, y1 = -24, x2 = -56, y2 = -67;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x1, ligne.getX1());
		assertEquals(x2, ligne.getX2());
		assertEquals(y1, ligne.getY());
		assertEquals(y1, ligne.getY1());
		assertEquals(y2, ligne.getY2());
		assertEquals(x2 - x1, ligne.getLargeur());
		assertEquals(y2 - y1, ligne.getHauteur());
	}
	
	@Test
	void contient() {
		Ligne ligne = new Ligne(0, 0, 50, 0);
		assertTrue(ligne.contient(25, 0));
		assertTrue(ligne.contient(0, 0));
		assertTrue(ligne.contient(50, 0));
		
		assertFalse(ligne.contient(-1, 0));
		assertFalse(ligne.contient(51, 0));		
		assertFalse(ligne.contient(25, 1));
		assertFalse(ligne.contient(0, -1));
	}
	
}
