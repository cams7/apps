package br.com.cams7.siscom.member.backing;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import br.com.cams7.apps.jee.backing.BaseListBean;
import br.com.cams7.siscom.jpa.domain.entity.Member;
import br.com.cams7.siscom.member.service.MemberService;

@RequestScoped
public class MemberListBean extends BaseListBean<MemberService, Member> {

	public MemberListBean() {
		super();
	}

	@Named("members")
	@Produces
	@Override
	public List<Member> getEntities() {
		return super.getEntities();
	}

}
