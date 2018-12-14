package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Ellipse;
import ca.csf.formes.Forme;

class EllipseTest {

	@Test
	void ConstructeurDefault() {
		Ellipse ellipse = new Ellipse();
		assertEquals(0, ellipse.getX());
		assertEquals(0, ellipse.getY());
		assertEquals(0, ellipse.getLargeur());
		assertEquals(0.0, ellipse.getHauteur());
		assertEquals(0, ellipse.getLargeurTrait());
		assertEquals("Ellipse", ellipse.getNom());
		assertNull(ellipse.getCouleur());
		assertNull(ellipse.getCouleurTrait());
	}

	@Test
	void ConstructeurInitialisationXY() {
		Forme el = new Ellipse(-1, 400);
		assertEquals(-1, el.getX());
		assertEquals(400, el.getY());
		assertEquals(0, el.getLargeur());
		assertEquals(0, el.getHauteur());
		assertEquals(0, el.getLargeurTrait());
	}

	@Test
	void ConstructeurInitialisationXYLH() {
		Ellipse ell = new Ellipse(1, 2, 3, 4);
		assertEquals(3, ell.getLargeur());
		assertEquals(4, ell.getHauteur());
		assertEquals(1, ell.getX());
		assertEquals(2, ell.getY());

		ell = new Ellipse(-50, -25, 50, 25);
		assertEquals(-50, ell.getX());
		assertEquals(-25, ell.getY());
		assertNotEquals(-25, ell.getHauteur());
		assertNotEquals(-50, ell.getLargeur());

		ell = new Ellipse(0, 0, 0, 0);
		assertEquals(0, ell.getX());
		assertEquals(0, ell.getY());
		assertEquals(0, ell.getHauteur());
		assertEquals(0, ell.getLargeur());
	}

	@Test
	void contient() {
		Forme el = new Ellipse(2, 2, 4, 4);
		assertFalse(el.contient(0, 0));
		assertFalse(el.contient(1.99, 1.99));
		assertFalse(el.contient(0, 2));
		assertFalse(el.contient(6, 2));

		assertTrue(el.contient(3, 3));
		assertTrue(el.contient(5, 3));
		assertTrue(el.contient(3, 5));
		assertTrue(el.contient(5, 5));

		assertFalse(new Ellipse().contient(0, 0));
	}

	@Test
	void EllipseSet() {
		Ellipse el = new Ellipse();
		el.setHauteur(33);
		el.setLargeur(10);
		assertEquals(0, el.getX());
		assertEquals(0, el.getY());
		assertEquals(33, el.getHauteur());
		assertEquals(10, el.getLargeur());
		assertThrows(IllegalArgumentException.class, () -> el.setLargeurTrait(-20));
		el.setCouleur(Color.BLUE);
		el.setCouleurTrait(Color.gray);
		assertEquals(Color.BLUE, el.getCouleur());
		assertEquals(Color.GRAY, el.getCouleurTrait());
		assertThrows(IllegalArgumentException.class, () -> el.setLargeur(-10));
		assertThrows(IllegalArgumentException.class, () -> el.setHauteur(-20));
	}

	@Test
	void EllipsesetPosition() {
		Ellipse el = new Ellipse();
		el.setDimension(10, 10);
		assertEquals(0, el.getX());
		assertEquals(0, el.getY());
		el.setPosition(33, 44);
		assertEquals(33, el.getX());
		assertEquals(44, el.getY());

	}

	@Test
	void EllipseTraitTest() {
		Ellipse el = new Ellipse(0.0, 0.0, 10, 10);
		el.setLargeurTrait(20);
		el.setCouleurTrait(Color.GREEN);

		assertEquals(Color.GREEN, el.getCouleurTrait());
		assertEquals(20, el.getLargeurTrait());
		assertNotEquals(0, el.getLargeurTrait());
		assertNotEquals(Color.BLACK, el.getCouleurTrait());

		el = new Ellipse(10, 10, 0.0, 0.0);
		el.setDimension(9999999999999.9999, 9999999999999.9999);
		el.setLargeurTrait(0);
		el.setCouleurTrait(Color.BLACK);

		assertNotEquals(9999999999999.999, el.getLargeur());
		assertEquals(0, el.getLargeurTrait());
		assertEquals(Color.BLACK, el.getCouleurTrait());

	}

	@Test
	void deplacer() {
		Ellipse el = new Ellipse();
		el.setPosition(-10, -10);
		el.deplacer(1, 1);
		assertEquals(-9, el.getX());
		assertEquals(-9, el.getY());
		el.deplacer(-9, -9);
		assertEquals(-18, el.getX());
		assertEquals(-18, el.getY());
		el.deplacer(0, 0);
		assertEquals(-18, el.getX());
		assertEquals(-18, el.getY());
		el.deplacer(Double.MAX_VALUE, Double.MIN_VALUE);

	}

	@Test
	void equals() {
		Ellipse e1 = new Ellipse(2, 2, 2, 2);
		Ellipse e2 = new Ellipse(2, 2, 2, 2);

		assertEquals(e1, e2);

		assertNotSame(e1, e2);

		Ellipse e3 = new Ellipse(2, 2, 1, 1);
		assertNotEquals(e1, e3);
		assertNotEquals(e3, e1);

		Ellipse e4 = new Ellipse(10, 20, 0, 0);
		Ellipse e5 = new Ellipse(10, 19, 0, 0);
		e4.setLargeurTrait(9);
		e5.setLargeurTrait(10);
		assertNotEquals(e4, e5);
		assertNotEquals(e4.getLargeurTrait(), e5.getLargeurTrait());

		e4.setLargeurTrait(10);
		e5.setLargeurTrait(10);
		assertEquals(e4.getLargeurTrait(), e5.getLargeurTrait());

		e4 = new Ellipse(10, 20, 0, 0);
		e5 = new Ellipse(10, 20, 0, 0);
		e4.setCouleur(Color.BLACK);
		e5.setCouleur(Color.BLACK);
		assertTrue(e4.equals(e5));
		e4.setCouleur(Color.BLUE);
		assertFalse(e4.equals(e5));

		e4.setCouleur(Color.BLACK);
		assertTrue(e4.equals(e5));
		e4.setCouleurTrait(Color.BLUE);
		e5.setCouleurTrait(Color.BLUE);
		assertTrue(e4.equals(e5));
		e4.setCouleurTrait(Color.BLACK);
		assertFalse(e4.equals(e5));

		Ellipse ellipse1 = new Ellipse(0, 0, 0, 0);
		Ellipse ellipse2 = new Ellipse(0, 0, 0, 0);
		assertEquals(ellipse1, ellipse2);
		assertEquals(ellipse2, ellipse1);
		ellipse2.setLargeurTrait(0);
		assertEquals(ellipse1, ellipse2);
		ellipse1.setCouleurTrait(Color.BLACK);
		ellipse2.setCouleurTrait(Color.BLUE);
		assertNotEquals(ellipse1, ellipse2);

		ellipse1.setCouleur(Color.BLACK);
		ellipse2.setCouleur(Color.BLUE);
		assertNotEquals(ellipse1, ellipse2);
		ellipse1.setCouleurTrait(Color.BLACK);
		ellipse2.setCouleurTrait(Color.BLACK);
		assertNotEquals(ellipse1, ellipse2);

		Ellipse ellipse3 = new Ellipse(9999, 9999, 9999, 9999);
		Ellipse ellipse4 = new Ellipse(9999.01, 9999.01, 9999.01, 9999.01);
		assertNotEquals(9999, ellipse4);
		assertNotEquals(null, ellipse4);

		assertNotEquals(2, ellipse4);
		assertNotEquals(ellipse3, ellipse4);

		Ellipse ellipse = new Ellipse(10, 10, 10, 10);
		assertEquals(ellipse, ellipse);
	}
}