package de.cormag.projectf.entities;

import java.io.Serializable;
import java.util.Comparator;

public class EntityComparator implements Comparator<Entity>, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Entity arg0, Entity arg1) {
		return arg0.compareTo(arg1);
	}

}
