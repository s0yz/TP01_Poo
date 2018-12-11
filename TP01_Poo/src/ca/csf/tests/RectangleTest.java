package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Shape;

import org.junit.jupiter.api.Test;



import ca.csf.formes.Rectangle;

class RectangleTest {

	@Test
	void ConstructeurDefault() {
		Rectangle rect = new Rectangle();
		assertEquals(0.0, rect.getHauteur());
		assertEquals(0, rect.getLargeur());
		assertNotEquals(0, rect);
		assertNotEquals(0, rect.getLargeurTrait());
		assertEquals(1, rect.getLargeurTrait());
		assertEquals("Rectangle", rect.getNom());
		assertNull(rect.getCouleur());
		assertEquals(0,rect.getX());
		assertEquals(0, rect.getY());
		
	}
	@Test
	void ConstructeurInitialisation()
	{
		Rectangle rectangle = new Rectangle(-1,400);
		assertEquals(-1, rectangle.getX());
		assertEquals(400, rectangle.getY());
		assertEquals(0, rectangle.getLargeur());
		assertEquals(0, rectangle.getHauteur());
		
	}
	@Test
	void RectangleSet() {
		Rectangle rec = new Rectangle();
		rec.setHauteur(33);
		rec.setLargeur(10);
		rec.setLargeurTrait(-20);
		assertEquals(0, rec.getX());
		assertEquals(0, rec.getY());
		assertEquals(33,rec.getHauteur());
		assertEquals(10,rec.getLargeur());
		//assertThrows(IllegalArgumentException.class, () -> rec.setLargeurTrait(-20));
		rec.setCouleur(Color.BLUE);
		rec.setCouleurTrait(Color.gray);
		assertEquals(Color.BLUE, rec.getCouleur());
		assertEquals(Color.GRAY, rec.getCouleurTrait());
		assertThrows(IllegalArgumentException.class, () -> rec.setLargeur(-10));
		assertThrows(IllegalArgumentException.class, () -> rec.setHauteur(-20));
		
		
	}
	@Test
	void RectangleSetPosition() {
		Rectangle rect = new Rectangle();
		rect.setDimension(10, 10);
		assertEquals(0,rect.getX());
		assertEquals(0,rect.getY());
		rect.setPosition(33, 44);
		assertEquals(33, rect.getX());
		assertEquals(44, rect.getY());
		
	}
	@Test
	void RectangleDeplacement() {
		Rectangle rect = new Rectangle();
		rect.setPosition(-10, -10);
		rect.deplacer(1, 1);
		assertEquals(-9, rect.getX());
		assertEquals(-9, rect.getY());
		rect.deplacer(-9, -9);
		assertEquals(-18, rect.getX());
		assertEquals(-18, rect.getY());
		rect.deplacer(0, 0);
		assertEquals(-18,rect.getX());
		assertEquals(-18, rect.getY());
		rect.deplacer(Double.MAX_VALUE, Double.MIN_VALUE);
		
		//assertThrows(IllegalArgumentException.class, () -> rect.setPosition(-18 + Double.MAX_VALUE, -18 + Double.MIN_VALUE));
	}
}
