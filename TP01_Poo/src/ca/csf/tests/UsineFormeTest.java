package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ca.csf.formes.ElementGraphique;
import ca.csf.formes.Ellipse;
import ca.csf.formes.Ligne;
import ca.csf.formes.Rectangle;
import ca.csf.formes.UsineForme;

class UsineFormeTest {

	@Test
	void getInstance() {
		UsineForme usine = UsineForme.getInstance();
		assertSame(usine, UsineForme.getInstance());
	}
	
	@Test
	void getFormeEllipseDefaut() {
		UsineForme usine = UsineForme.getInstance();
		ElementGraphique e = null;
		List<Class<?>> classes = Arrays.asList(Ellipse.class, Rectangle.class, Ligne.class);
		for (Class<?> c : classes) {
			e = usine.getForme(c.getSimpleName());
			assertEquals(c, e.getClass());
			assertEquals(c.getSimpleName(), e.getNom());
			assertEquals(0, e.getX());
			assertEquals(0, e.getY());
			assertEquals(0, e.getLargeur());
			assertEquals(0, e.getHauteur());
			assertEquals(0, e.getLargeurTrait());
			assertNull(e.getCouleur());
			assertNull(e.getCouleurTrait());
		}
		
		e = usine.getForme("");
		assertNull(e);
		
		assertThrows(IllegalArgumentException.class, () -> usine.getForme(null));
	}
	
	@Test
	void getFormeEllipseXYLH() {
		UsineForme usine = UsineForme.getInstance();
		ElementGraphique e = null;
		List<Class<?>> classes = Arrays.asList(Ellipse.class, Rectangle.class, Ligne.class);
		double i = 1.33;
		for (Class<?> c : classes) {
			e = usine.getForme(c.getSimpleName(), i += i, i, i, i);
			assertNotNull(e);
			assertEquals(c, e.getClass());
			assertEquals(c.getSimpleName(), e.getNom());
			assertEquals(i, e.getX());
			assertEquals(i, e.getY());
			assertEquals(i, e.getLargeur());
			assertEquals(i, e.getHauteur());
			assertEquals(0, e.getLargeurTrait());
			assertNull(e.getCouleur());
			assertNull(e.getCouleurTrait());
		}
		
		e = usine.getForme("", i++, i, i, i);
		assertNull(e);		
		
		assertThrows(IllegalArgumentException.class, () -> usine.getForme(null, 4, 4, 4, 4));
	}
}
