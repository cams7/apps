package br.com.cams7.siscom.member.backing;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.cams7.apps.jee.backing.BaseEditBean;
import br.com.cams7.siscom.jpa.domain.entity.Member;
import br.com.cams7.siscom.member.service.MemberService;

@Named("memberRegistration")
@RequestScoped
public class MemberEditBean extends BaseEditBean<MemberService, Member> {

	public MemberEditBean() {
		super();
	}

	@Named("newMember")
	@Produces
	@Override
	public Member getEntity() {
		return super.getEntity();
	}

	// @Override
	// protected FacesContext getFacesContext() {
	// return facesContext;
	// }

}
