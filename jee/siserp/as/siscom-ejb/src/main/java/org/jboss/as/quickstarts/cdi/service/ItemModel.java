/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.cdi.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Simple Entity bean, that can be mapped to database.
 * 
 * @author Ievgen Shulga
 */
@Entity
@Table(name = "ITEM")
@SequenceGenerator(name = "SEQUENCE_ITEM", sequenceName = "SEQ_ITEM")
public class ItemModel extends Item  {
	private static final long serialVersionUID = 1L;

	private Long id;

	public ItemModel() {
		super();
	}

	public ItemModel(Long id) {
		this();
		this.id = id;
	}

	/**
	 * @return id
	 */
	@Id
	@GeneratedValue(generator = "SEQUENCE_ITEM")
	@Column(name = "ID_ITEM")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jboss.as.quickstarts.cdi.service.Item#getName()
	 */
	@Column(name = "NAME_ITEM")
	public String getName() {
		return super.getName();
	}

}
