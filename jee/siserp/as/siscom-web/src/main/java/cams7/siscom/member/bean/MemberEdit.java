package cams7.siscom.member.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import cams7.apps.jee.bean.BaseEdit;
import cams7.siscom.jpa.domain.entity.Member;
import cams7.siscom.member.service.MemberService;

@Named("memberRegistration")
// @ManagedBean(name = "memberRegistration")
@RequestScoped
public class MemberEdit extends BaseEdit<MemberService, Member> implements
		Serializable {

	private static final long serialVersionUID = 1L;

	public MemberEdit() {
		super();
	}

	// @Named("newMember")
	// @Produces
	// // @NewEntity
	// @Override
	public Member getMember() {
		return super.getEntity();
	}

	// @Override
	// protected FacesContext getFacesContext() {
	// return facesContext;
	// }

}
