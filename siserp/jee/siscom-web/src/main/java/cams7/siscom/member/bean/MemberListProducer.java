package cams7.siscom.member.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import cams7.siscom.jpa.domain.entity.Member;
import cams7.siscom.member.service.MemberService;

@RequestScoped
public class MemberListProducer {
	@Inject
	private MemberService service;

	private List<Member> members;

	// @Named provides access the return value via the EL variable name
	// "members" in the UI (e.g.,
	// Facelets or JSP view)
	@Produces
	@Named
	public List<Member> getMembers() {
		return members;
	}

	public void onMemberListChanged(
			@Observes(notifyObserver = Reception.IF_EXISTS) final Member member) {
		retrieveAllMembers();
	}

	@PostConstruct
	public void retrieveAllMembers() {
		members = (List<Member>) service.findAll();
	}
}
