package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ca.csf.formes.Rectangle;
import ca.csf.modele.*;

class ElementEcouteTest {

	@Test
	void Constructor() {
		ArrayList<EcouteurModeleGraphique> ecouteurs = new ArrayList();
		Rectangle rect = new Rectangle(33, 44, 55, 66);
		ElementEcoute decorateur = new ElementEcoute(rect, ecouteurs);
		assertSame(rect, decorateur.getElement());
	}
}
