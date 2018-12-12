package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Rectangle;

class RectangleTest {

	@Test
	void ConstructeurDefault() {
		Rectangle rect = new Rectangle();
		assertEquals(0,rect.getX());
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
	void ConstructeurInitialisationXYLH( ) {
		Rectangle rect = new Rectangle(1,2,3,4);
		assertEquals(3, rect.getLargeur());
		assertEquals(4, rect.getHauteur());
		assertEquals(1,rect.getX());
		assertEquals(2,rect.getY());
		
		Rectangle re = new Rectangle(-50,-25,50,25);
		assertEquals(-50, re.getX());
		assertEquals(-25, re.getY());
		assertNotEquals(-25, re.getHauteur());
		assertNotEquals(-50, re.getLargeur());
		
		Rectangle rectangle = new Rectangle(0,0,0,0);
		assertEquals(0, rectangle.getX());
		assertEquals(0, rectangle.getY());
		assertEquals(0, rectangle.getHauteur());
		assertEquals(0, rectangle.getLargeur());
	}

	@Test
	void TestMethodeContient() {
		Rectangle rect = new Rectangle(2,2,3,4);
		assertFalse(rect.contient(0, 0));
		assertFalse(rect.contient(1.99, 1.99));
		assertFalse(rect.contient(0, 2));
		assertFalse(rect.contient(6, 2));
		
		assertTrue(rect.contient(2, 2));
//		assertTrue(rect.contient(2, 6));
//		assertTrue(rect.contient(5, 2));
//		assertTrue(rect.contient(5, 6));
		
		assertFalse(rect.contient(1.99, 2));
		assertFalse(rect.contient(2, 1.99));
			
		assertFalse(rect.contient(1.99, 6));
		assertFalse(rect.contient(2, 6.01));
		assertFalse(rect.contient(1.99, 6.01));
		assertFalse(rect.contient(5, 1.99));
		assertFalse(rect.contient(5.01, 2));
		assertFalse(rect.contient(5.01, 6));
		assertFalse(rect.contient(5, 6.01));
		
	}
	@Test
	void RectangleSet() {		
		Rectangle rect = new Rectangle();
		rect.setHauteur(33);
		rect.setLargeur(10);
		assertEquals(0, rect.getX());
		assertEquals(0, rect.getY());
		assertEquals(33,rect.getHauteur());
		assertEquals(10,rect.getLargeur());
		assertThrows(IllegalArgumentException.class, () -> rect.setLargeurTrait(-20));
		rect.setCouleur(Color.BLUE);
		rect.setCouleurTrait(Color.gray);
		assertEquals(Color.BLUE, rect.getCouleur());
		assertEquals(Color.GRAY, rect.getCouleurTrait());
		assertThrows(IllegalArgumentException.class, () -> rect.setLargeur(-10));
		assertThrows(IllegalArgumentException.class, () -> rect.setHauteur(-20));		
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
	@Test
	void RectangleEquals() {
		Rectangle rectangle1 = new Rectangle(2,2,2,2);
		Rectangle rectangle2 = new Rectangle(2,2,2,2);
		
		assertEquals(rectangle1, rectangle2);
		assertNotSame(rectangle1, rectangle2);
		
		Rectangle rectangle3 = new Rectangle(2,2,1,1);
		assertNotEquals(rectangle1, rectangle3);
		assertNotEquals(rectangle3, rectangle1);
		
		Rectangle rect1 = new Rectangle(10,20,0,0);
		Rectangle rect2 = new Rectangle(10,19,0,0);
		assertNotEquals(rect1, rect2);
		assertNotEquals(rect2, rect1);
		
		Rectangle rect3 = new Rectangle(0,0,0,0);
		Rectangle rect4 = new Rectangle(0,0,0,0);
		assertEquals(rect3, rect4);
		assertEquals(rect4, rect3);
		
		rect3.setCouleur(Color.BLACK);
		rect4.setCouleur(Color.BLUE);
		assertNotEquals(rect3, rect4);
		rect3.setCouleurTrait(Color.BLACK);
		rect4.setCouleurTrait(Color.BLACK);
		assertNotEquals(rect4, rect3);
		
		
		Rectangle rect5 = new Rectangle(9999,9999,9999,9999);
		Rectangle rect6 = new Rectangle(9999,9999,9999,9999);
		assertEquals();
		assertEquals();
		
		
		
		
		
	}
}
