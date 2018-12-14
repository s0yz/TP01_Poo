package ca.csf.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ca.csf.ui.ActionTouche;

class ActionToucheTest {

	private boolean actionRealisee = false;
	
	@Test
	void constructeur() {
		assertThrows(IllegalArgumentException.class, () -> new ActionTouche(null));
	}

	@Test
	void actionPerformed() {
		ActionTouche a = new ActionTouche(e -> this.actionRealisee = true);
		assertFalse(this.actionRealisee);
		a.actionPerformed(null);
		assertTrue(this.actionRealisee);
	}	
}
