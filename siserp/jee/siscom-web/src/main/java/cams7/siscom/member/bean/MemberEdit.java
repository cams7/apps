package cams7.siscom.member.bean;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import cams7.apps.jee.bean.BaseEdit;
import cams7.siscom.jpa.domain.entity.Member;
import cams7.siscom.member.service.MemberService;

@Named("memberRegistration")
@RequestScoped
public class MemberEdit extends BaseEdit<MemberService, Member> {

	public MemberEdit() {
		super();
	}

	@Named("newMember")
	@Produces
	@Override
	public Member getNewEntity() {
		return super.getNewEntity();
	}

}
