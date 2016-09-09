package de.cormag.projectf.entities;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Compares two renderable objects. Objects with a smaller layer are considered
 * to be greater. Because of that they get rendered first, thus being displayed
 * behind greater layer.
 * 
 * @author Zabuza
 *
 */
public final class LayerComparator implements Comparator<Entity>, Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final Entity entityOne, final Entity entityTwo) {
		// Smaller layers should get rendered first to be behind greater
		int layerOrder = Integer.compare(entityOne.getLayer(), entityTwo.getLayer());
		if (layerOrder == 0) {

			if (entityOne.getY() + entityOne.getHeight() < entityTwo.getY() + entityTwo.getHeight()) {
				return -1;
			} else if (entityOne.getY() + entityOne.getHeight() == entityTwo.getY() + entityTwo.getHeight()) {
				return 0;
			} else if (entityOne.getY() + entityOne.getHeight() > entityTwo.getY() + entityTwo.getHeight()) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return layerOrder;
		}
	}

}
