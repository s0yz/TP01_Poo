package ca.csf.ui;

import javax.swing.JPanel;

/**
 * 
 * 
 * @author
 * @deprecated je sais pas si on en a vraiment besoin dans le fond.
 * 
 */
public abstract class BarreOutil<T> extends JPanel {
	
	private static final long serialVersionUID = -6978165627710931411L;
		
	public abstract T getSelection();
}
