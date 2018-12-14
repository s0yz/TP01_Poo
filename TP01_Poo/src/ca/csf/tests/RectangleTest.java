package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Ellipse;
import ca.csf.formes.Rectangle;

class RectangleTest {

	@Test
	void ConstructeurDefault() {
		Rectangle rect = new Rectangle();
		assertEquals(0, rect.getX());
		assertEquals(0, rect.getY());
		assertEquals(0, rect.getLargeur());
		assertEquals(0.0, rect.getHauteur());
		assertEquals(0, rect.getLargeurTrait());
		assertEquals("Rectangle", rect.getNom());
		assertNull(rect.getCouleur());
		assertNull(rect.getCouleurTrait());
	}

	@Test
	void ConstructeurInitialisationXY() {
		Rectangle rect = new Rectangle(-1, 400);
		assertEquals(-1, rect.getX());
		assertEquals(400, rect.getY());
		assertEquals(0, rect.getLargeur());
		assertEquals(0, rect.getHauteur());
		assertEquals(0, rect.getLargeurTrait());
	}

	@Test
	void ConstructeurInitialisationXYLH() {
		Rectangle rect = new Rectangle(1, 2, 3, 4);
		assertEquals(3, rect.getLargeur());
		assertEquals(4, rect.getHauteur());
		assertEquals(1, rect.getX());
		assertEquals(2, rect.getY());

		rect = new Rectangle(-50, -25, 50, 25);
		assertEquals(-50, rect.getX());
		assertEquals(-25, rect.getY());
		assertNotEquals(-25, rect.getHauteur());
		assertNotEquals(-50, rect.getLargeur());

		rect = new Rectangle(0, 0, 0, 0);
		assertEquals(0, rect.getX());
		assertEquals(0, rect.getY());
		assertEquals(0, rect.getHauteur());
		assertEquals(0, rect.getLargeur());
	}

	@Test
	void contient() {
		Rectangle rect = new Rectangle(2, 2, 3, 4);
		assertFalse(new Rectangle().contient(0, 0));
		assertFalse(rect.contient(0, 0));
		assertFalse(rect.contient(4.99, 1.99));
		assertFalse(rect.contient(0, 2));
		assertFalse(rect.contient(6, 2));

		assertTrue(rect.contient(2, 2));
		assertTrue(rect.contient(3.5, 4));
		assertTrue(rect.contient(4.99, 2));
		assertTrue(rect.contient(4.9999, 5.9999));
		assertTrue(rect.contient(2, 5.99));
		assertTrue(rect.contient(2, 2));

		assertFalse(rect.contient(2, 6));
		assertFalse(rect.contient(5, 2));
		assertFalse(rect.contient(5, 6));

		assertFalse(rect.contient(1.99, 2));
		assertFalse(rect.contient(2, 1.99));
		assertFalse(rect.contient(1.99, 6));
		assertFalse(rect.contient(2, 6.01));
		assertFalse(rect.contient(1.99, 6.01));
		assertFalse(rect.contient(5, 1.99));
		assertFalse(rect.contient(5.01, 2));
		assertFalse(rect.contient(5.01, 6));
		assertFalse(rect.contient(5, 6.01));

		assertFalse(new Rectangle().contient(0, 0));
	}

	@Test
	void Sets() {
		Rectangle rect = new Rectangle();
		rect.setHauteur(33);
		rect.setLargeur(10);
		assertEquals(0, rect.getX());
		assertEquals(0, rect.getY());
		assertEquals(33, rect.getHauteur());
		assertEquals(10, rect.getLargeur());
		assertThrows(IllegalArgumentException.class, () -> rect.setLargeurTrait(-20));
		rect.setCouleur(Color.BLUE);
		rect.setCouleurTrait(Color.gray);
		assertEquals(Color.BLUE, rect.getCouleur());
		assertEquals(Color.GRAY, rect.getCouleurTrait());
		assertThrows(IllegalArgumentException.class, () -> rect.setLargeur(-10));
		assertThrows(IllegalArgumentException.class, () -> rect.setHauteur(-20));
	}

	@Test
	void setPosition() {
		Rectangle rect = new Rectangle();
		rect.setDimension(10, 10);
		assertEquals(0, rect.getX());
		assertEquals(0, rect.getY());
		rect.setPosition(33, 44);
		assertEquals(33, rect.getX());
		assertEquals(44, rect.getY());

	}

	@Test
	void RectangleTraitTest() {
		Rectangle rect = new Rectangle(0.0, 0.0, 10, 10);
		rect.setLargeurTrait(20);
		rect.setCouleurTrait(Color.GREEN);

		assertEquals(Color.GREEN, rect.getCouleurTrait());
		assertEquals(20, rect.getLargeurTrait());
		assertNotEquals(0, rect.getLargeurTrait());
		assertNotEquals(Color.BLACK, rect.getCouleurTrait());

		Rectangle rectangle = new Rectangle(10, 10, 0.0, 0.0);
		rectangle.setDimension(9999999999999.9999, 9999999999999.9999);
		rectangle.setLargeurTrait(0);
		rectangle.setCouleurTrait(Color.BLACK);

		assertNotEquals(9999999999999.999, rectangle.getLargeur());
		assertEquals(0, rectangle.getLargeurTrait());
		assertEquals(Color.BLACK, rectangle.getCouleurTrait());

	}

	@Test
	void deplacer() {
		Rectangle rect = new Rectangle();
		rect.setPosition(-10, -10);
		rect.deplacer(1, 1);
		assertEquals(-9, rect.getX());
		assertEquals(-9, rect.getY());
		rect.deplacer(-9, -9);
		assertEquals(-18, rect.getX());
		assertEquals(-18, rect.getY());
		rect.deplacer(0, 0);
		assertEquals(-18, rect.getX());
		assertEquals(-18, rect.getY());
		rect.deplacer(Double.MAX_VALUE, Double.MIN_VALUE);

	}

	@Test
	void equals() {
		Rectangle rectangle1 = new Rectangle(2, 2, 2, 2);
		Rectangle rectangle2 = new Rectangle(2, 2, 2, 2);
		assertEquals(rectangle1, rectangle2);

		rectangle2.setCouleur(Color.RED);
		assertNotEquals(rectangle1, rectangle2);

		rectangle1.setCouleur(Color.RED);
		assertEquals(rectangle1, rectangle2);

		rectangle2.setCouleurTrait(Color.RED);
		assertNotEquals(rectangle1, rectangle2);

		rectangle1.setCouleurTrait(Color.RED);
		assertEquals(rectangle1, rectangle2);

		rectangle2.setLargeur(2.1);
		assertNotEquals(rectangle1, rectangle2);

		rectangle1.setLargeur(2.1);
		assertEquals(rectangle1, rectangle2);

		rectangle1 = new Rectangle(2, 2, 2, 2);
		rectangle2 = new Rectangle(2, 2, 2, 1);
		assertNotEquals(rectangle1, rectangle2);
		assertNotEquals(rectangle2, rectangle1);

		rectangle2 = new Rectangle(1, 2, 2, 2);
		assertNotEquals(rectangle1, rectangle2);
		assertNotEquals(rectangle2, rectangle1);

		Rectangle rect1 = new Rectangle(10, 20, 0, 0);
		Rectangle rect2 = new Rectangle(10, 19, 0, 0);
		rect1.setLargeurTrait(10);
		rect2.setLargeurTrait(10);
		assertNotEquals(rect1, rect2);
		assertNotEquals(rect2, rect1);

		Rectangle rect3 = new Rectangle(0, 0, 0, 0);
		Rectangle rect4 = new Rectangle(0, 0, 0, 0);
		assertEquals(rect3, rect4);
		assertEquals(rect4, rect3);
		rect3.setCouleurTrait(Color.BLACK);
		rect4.setCouleurTrait(Color.BLUE);
		assertNotEquals(rect3, rect4);

		rect3.setCouleur(Color.BLACK);
		rect4.setCouleur(Color.BLUE);
		assertNotEquals(rect3, rect4);
		rect3.setCouleurTrait(Color.BLACK);
		rect4.setCouleurTrait(Color.BLACK);
		assertNotEquals(rect4, rect3);

		Rectangle rect5 = new Rectangle(9999, 9999, 9999, 9999);
		Rectangle rect6 = new Rectangle(9999.01, 9999.01, 9999.01, 9999.01);
		assertEquals(rect5, rect5);
		assertEquals(rect6, rect6);
		assertNotEquals(2, rect5);
		assertNotEquals(rect6, rect5);

		Ellipse ellipse = new Ellipse(9999, 9999, 9999, 9999);
		assertNotEquals(ellipse, rect5);
		assertNotEquals(null, rect5);
		assertNotEquals(rect5, null);

	}
}
