package cams7.siscom.member.bean;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cams7.siscom.jpa.domain.entity.Member;
import cams7.siscom.member.service.MemberService;

@Named("memberRegistration")
@RequestScoped
public class MemberEdit {

	@Inject
	private Logger log;

	@Inject
	private MemberService service;

	@Inject
	private Event<Member> memberEventSrc;

	private Member newMember;

	@Produces
	@Named
	public Member getNewMember() {
		return newMember;
	}

	public String register() throws Exception {
		log.info("Registering " + newMember.getName());

		service.save(newMember);

		memberEventSrc.fire(newMember);

		initNewMember();
		return null;
	}

	@PostConstruct
	public void initNewMember() {
		newMember = new Member();
	}
}
