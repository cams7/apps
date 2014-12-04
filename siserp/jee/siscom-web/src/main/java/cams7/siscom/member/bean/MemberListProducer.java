package cams7.siscom.member.bean;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import cams7.apps.jee.bean.BaseListProducer;
import cams7.siscom.jpa.domain.entity.Member;
import cams7.siscom.member.service.MemberService;

@RequestScoped
public class MemberListProducer extends BaseListProducer<MemberService, Member> {

	public MemberListProducer() {
		super();
	}

	@Named("members")
	@Produces
	@Override
	public List<Member> getEntities() {
		return super.getEntities();
	}
}
