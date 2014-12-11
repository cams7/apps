/*
 * Copyright 2008-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.cams7.apps.jpa.domain;

import java.io.Serializable;

/**
 * Abstract base class for entities. Allows parameterization of id type, chooses
 * auto-generation and implements {@link #equals(Object)} and
 * {@link #hashCode()} based on that id.
 * 
 * @author Oliver Gierke
 * @param <PK>
 *            the the of the entity
 */
public abstract class BaseEntity<PK extends Serializable> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	public static final byte ALLOCATION_SIZE = 1;
	public static final byte INITIAL_VALUE = 1;

	public BaseEntity() {
		super();
	}

	/**
	 * @param id
	 */
	public BaseEntity(PK id) {
		this();

		setId(id);
	}

	public abstract PK getId();

	public abstract void setId(PK id);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.domain.Persistable#isNew()
	 */
	public boolean isNew() {
		return null == getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass()
				.getName(), getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object entity) {

		if (null == entity) {
			return false;
		}

		if (this == entity) {
			return true;
		}

		if (!getClass().equals(entity.getClass())) {
			return false;
		}

		BaseEntity<?> e = (BaseEntity<?>) entity;

		return null == this.getId() ? false : this.getId().equals(e.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		int hashCode = 17;

		hashCode += null == getId() ? 0 : getId().hashCode() * 31;

		return hashCode;
	}

}