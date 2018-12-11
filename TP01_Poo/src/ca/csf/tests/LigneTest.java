package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Ligne;

class LigneTest {

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
		assertEquals(1, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
		
	}
	
	@Test
	void constructeurXY() {
		double x = Double.MAX_VALUE;
		double y = Double.MIN_VALUE;
		Ligne ligne = new Ligne(x, y);
		assertEquals(x, ligne.getX());
		assertEquals(x, ligne.getX1());
		assertEquals(x, ligne.getX2());
		assertEquals(y, ligne.getY());
		assertEquals(y, ligne.getY1());
		assertEquals(y, ligne.getY2());
		assertEquals(0, ligne.getLargeur());
		assertEquals(0, ligne.getHauteur());
		assertEquals(1, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
	}
	
	@Test
	void constructeurXYWH_01() {
		double x1 = 45, y1 = 24, x2 = 5756, y2 = 9999;
		Ligne ligne = new Ligne(x1, y1, x2, y2);
		assertEquals(x1, ligne.getX());
		assertEquals(x1, ligne.getX1());
		assertEquals(x2, ligne.getX2());
		assertEquals(y1, ligne.getY());
		assertEquals(y1, ligne.getY1());
		assertEquals(y2, ligne.getY2());
		assertEquals(x2 - x1, ligne.getLargeur());
		assertEquals(y2 - y1, ligne.getHauteur());
		assertEquals(1, ligne.getLargeurTrait());
		assertNull(ligne.getCouleur());
		assertNull(ligne.getCouleurTrait());
	}
	
	@Test
	void constructeurXYWH_02() {
		double x1 = -456, y1 = 34, x2 = 656, y2 = -20;
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
	void constructeurXYWH_03() {
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
}
