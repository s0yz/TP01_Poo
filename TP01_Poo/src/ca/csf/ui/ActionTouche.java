package ca.csf.ui;

import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.AbstractAction;

/**
 * @author Cedric Mariage
 *
 */
public class ActionTouche extends AbstractAction {

	private static final long serialVersionUID = -9116489161460289830L;
	
	/**
	 * Exécutée lors de {@link #actionPerformed(ActionEvent)}.
	 */
	private final Consumer<ActionEvent> m_Action;

	/**
	 * Construit une {@code ActionTouche}.
	 * 
	 * @param p_Action action exécuté lors de {@link #actionPerformed(ActionEvent)}.
	 */
	public ActionTouche(Consumer<ActionEvent> p_Action) {
		if (p_Action == null) {
			throw new IllegalArgumentException("p_Action est null");
		}
		this.m_Action = p_Action;
	}

	/**
	 * Exécute {@link #m_Action}.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent p_e) {
		this.m_Action.accept(p_e);
	}
}
