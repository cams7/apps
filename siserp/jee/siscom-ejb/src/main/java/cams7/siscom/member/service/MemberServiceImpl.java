/**
 * 
 */
package cams7.siscom.member.service;

import javax.ejb.Stateless;

import cams7.apps.jee.service.BaseServiceImpl;
import cams7.siscom.jpa.domain.entity.Member;

/**
 * @author cesar
 *
 */
@Stateless
public class MemberServiceImpl extends BaseServiceImpl<Member, Long> implements
		MemberService {

	/**
	 * 
	 */
	public MemberServiceImpl() {
		super();
	}

}
