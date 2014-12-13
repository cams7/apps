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

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * JSF backing bean that demonstrates CDI injection.
 * 
 * Bean name overridden to "itemBean" to be accessible from view with this name.
 * 
 * @author Ievgen Shulga
 */
@Named("itemBean")
@ConversationScoped
public class ItemBean extends Item {

	private static final long serialVersionUID = 1L;

	@Inject
	private ItemServiceBean itemService;

	@Inject
	private FacesContext context;

	@Inject
	private Conversation conversation;

	private List<ItemModel> items;
	private List<String> itemHistory;

	private static final String EMPTY_STRING = "";

	public void initConversation() {
		if (!context.isPostback() && conversation.isTransient())
			conversation.begin();

	}

	// public String endConversation() {
	// if (!conversation.isTransient())
	// conversation.end();
	//
	// return "item?faces-redirect=true";
	// }

	public void add() {
		if (getName().equals(EMPTY_STRING)) {
			FacesMessage fm = new FacesMessage("Name can't be empty");
			context.addMessage(fm.getSummary(), fm);
			refresh();
			return;
		}
		ItemModel item = new ItemModel();
		item.setName(getName());
		itemService.create(item);

		setName(EMPTY_STRING);

		refresh();
	}

	@PostConstruct
	private void refresh() {
		itemHistory = History.getItemHistory();
		items = itemService.getList();
	}

	public List<ItemModel> getItems() {
		return items;
	}

	public List<String> getItemHistory() {
		return itemHistory;
	}

	// public Conversation getConversation() {
	// return conversation;
	// }

}
