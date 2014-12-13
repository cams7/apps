package org.jboss.as.quickstarts.cdi.service;

import java.io.Serializable;

public abstract class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
