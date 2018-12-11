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
}
