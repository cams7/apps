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
package org.jboss.as.quickstarts.cdi.interceptors.test;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.cdi.service.ItemModel;
import org.jboss.as.quickstarts.cdi.service.ItemServiceBean;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mastertheboss.util.Resources;

@RunWith(Arquillian.class)
public class InterceptorsDisabledTest {

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap
				.create(WebArchive.class, "test-jboss-cdi-interceptors.war")
				.addClasses(Resources.class)
				.addPackages(true, "org.jboss.as.quickstarts.cdi")
				// enable JPA
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				// enable CDI and passing empty beans.xml descriptor to war
				// archive, so cdi interceptors are disabled.
				.addAsManifestResource("beans.xml", "beans.xml")
				// Deploy test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	private ItemServiceBean itemService;

	@Test
	public void testAuditInterceptor() {
		ItemModel item = new ItemModel();
		item.setName("testItem");
//		itemService.create(item);
		// item = itemService.getList().get(0);
		// // assert that no history entries were added since interceptors are
		// // disabled
		// assertEquals(0, History.getItemHistory().size());
	}

}
