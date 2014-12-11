package com.mastertheboss.test;

import java.util.logging.Logger;

import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstarts.cdi.injection.TranslateService;
import org.jboss.as.quickstarts.cdi.injection.qualifier.English;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.cams7.apps.jee.AbstractBase;
import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.apps.util.ApplicationException;
import br.com.cams7.siscom.jpa.domain.entity.Member;
import br.com.cams7.siscom.member.backing.MemberEditBean;
import br.com.cams7.siscom.member.service.MemberService;

import com.mastertheboss.util.Resources;

@RunWith(Arquillian.class)
public class MemberRegistrationTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackages(true, AbstractBase.class.getPackage(),
						English.class.getPackage(),
						TranslateService.class.getPackage())
				.addClasses(ApplicationException.class, BaseEntity.class,
						Member.class, MemberService.class, Resources.class,
						MemberEditBean.class)

				// enable JPA
				.addAsResource("META-INF/test-persistence.xml",
						"META-INF/persistence.xml")
				// add sample data
				.addAsResource("import.sql")
				// enable CDI
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	MemberEditBean memberRegistration;

	@Inject
	Logger log;

	@Test
	public void testRegister() throws Exception {
//		Member newMember = memberRegistration.getEntity();
//		newMember.setName("Jane Doe");
//		newMember.setEmail("jane@mailinator.com");
//		newMember.setPhoneNumber("2125551234");
//		memberRegistration.salva();
//		assertNotNull(newMember.getId());
//		log.info(newMember.getName() + " was persisted with id "
//				+ newMember.getId());
	}

}
