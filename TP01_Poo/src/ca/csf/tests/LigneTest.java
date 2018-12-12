package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Ligne;

class LigneTest {
	
	@Test
	void constructeurDefault() {
		Ligne ligne = new Ligne();
		assertEquals(0, ligne.getX());
		assertEquals(0, ligne.getX2());
		assertEquals(0, ligne.getY());
		assertEquals(0, ligne.getY2());
		assertEquals(0, ligne.getLargeur());
		assertEquals(0, ligne.getHauteur());
		assertEquals(0, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
		assertEquals("Ligne", ligne.getNom());
	}
	
	@Test
	void constructeurInitialisation_01() {
		double x1 = 45.5, y1 = 24.33, x2 = 5756, y2 = 9999.99;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x2, ligne.getX2());
		assertEquals(y1, ligne.getY());
		assertEquals(y2, ligne.getY2());
		assertEquals(x2 - x1, ligne.getLargeur());
		assertEquals(y2 - y1, ligne.getHauteur());
		assertEquals(0, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
		assertEquals("Ligne", ligne.getNom());
	}
	
	@Test
	void constructeurInitialisation_02() {
		double x1 = -456, y1 = 34, x2 = 656, y2 = -20;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x2, ligne.getX2());
		assertEquals(y1, ligne.getY());
		assertEquals(y2, ligne.getY2());
		assertEquals(x2 - x1, ligne.getLargeur());
		assertEquals(y2 - y1, ligne.getHauteur());
	}
	
	@Test
	void constructeurInitialisation_03() {
		double x1 = -45, y1 = -24, x2 = -56, y2 = -67;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x2, ligne.getX2());
		assertEquals(y1, ligne.getY());
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
		
		assertFalse(new Ligne().contient(0, 0));
	}
	
	@Test
	void setPosition() {
		double x1 = 0, y1 = 0,  x2 = 50, y2 = 70;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		
		// même position
		ligne.setPosition(x1, y1);
		assertEquals(x1, ligne.getX());
		assertEquals(y1, ligne.getY());
		assertEquals(x2, ligne.getX2());
		assertEquals(y2, ligne.getY2());
		
		// position pôsitive
		double d1 = 25;
		ligne.setPosition(d1, d1);
		assertEquals(d1, ligne.getX());
		assertEquals(d1, ligne.getY());
		assertEquals((x2 = x2 + d1), ligne.getX2());
		assertEquals((y2 = y2 + d1), ligne.getY2());
		
		// position négative
		double d2 = -900, d3 = - 300;
		ligne.setPosition(d2, d3);
		assertEquals(d2, ligne.getX());
		assertEquals(d3, ligne.getY());
		assertEquals(x2 + d2 - d1, ligne.getX2());
		assertEquals(y2 + d3 - d1, ligne.getY2());
	}
	
	@Test
	void setPoint1() {
		Ligne ligne = new Ligne(355, 5, 50, 98);
		
		ligne.setPoint1(0, 0);		
		assertEquals(0, ligne.getX());
		assertEquals(0, ligne.getY());	
		assertEquals(50, ligne.getX2());
		assertEquals(98, ligne.getY2());
		
		ligne.setPoint1(Double.MAX_VALUE, Double.NEGATIVE_INFINITY);
		assertEquals(Double.MAX_VALUE, ligne.getX());
		assertEquals(Double.NEGATIVE_INFINITY, ligne.getY());
	}
	
	@Test
	void setPoint2() {
		Ligne ligne = new Ligne(355, 5, 50, 98);
		
		ligne.setPoint2(0, 0);
		assertEquals(355, ligne.getX());
		assertEquals(5, ligne.getY());
		assertEquals(0, ligne.getX2());
		assertEquals(0, ligne.getY2());
		
		ligne.setPoint2(Double.MAX_VALUE, Double.NEGATIVE_INFINITY);
		assertEquals(Double.MAX_VALUE, ligne.getX2());
		assertEquals(Double.NEGATIVE_INFINITY, ligne.getY2());
	}
	
	@Test
	void setLargeur() {
		Ligne ligne = new Ligne(0, 0, 50, 50);
		
		ligne.setLargeur(100);
		assertEquals(0, ligne.getX());
		assertEquals(0, ligne.getY());
		assertEquals(100, ligne.getX2());
		assertEquals(50, ligne.getY2());
		assertEquals(100, ligne.getLargeur());
		assertEquals(50, ligne.getHauteur());
		
		ligne.setLargeur(-100);
		assertEquals(-100, ligne.getX2());
		assertEquals(50, ligne.getHauteur());
		assertEquals(-100, ligne.getLargeur());
		assertEquals(50, ligne.getY2());
		
		ligne.setLargeur(0);
		assertEquals(0, ligne.getX2());
		assertEquals(50, ligne.getHauteur());
		assertEquals(0, ligne.getLargeur());
		assertEquals(50, ligne.getY2());
	}
	
	@Test
	void setHauteur() {
		Ligne ligne = new Ligne(0, 0, 50, 50);
		
		ligne.setHauteur(100);
		assertEquals(0, ligne.getX());
		assertEquals(0, ligne.getY());
		assertEquals(50, ligne.getX2());
		assertEquals(100, ligne.getY2());
		assertEquals(50, ligne.getLargeur());
		assertEquals(100, ligne.getHauteur());
		
		ligne.setHauteur(-100);		
		assertEquals(50, ligne.getX2());
		assertEquals(-100, ligne.getY2());
		assertEquals(50, ligne.getLargeur());
		assertEquals(-100, ligne.getHauteur());		
		
		ligne.setHauteur(0);
		assertEquals(50, ligne.getX2());
		assertEquals(0, ligne.getY2());
		assertEquals(50, ligne.getLargeur());
		assertEquals(0, ligne.getHauteur());
	}
	
	//...
}
