/**
 * 
 */
package br.com.cams7.siscom.member.service;

import javax.ejb.Stateless;

import br.com.cams7.apps.jee.service.BaseServiceImpl;
import br.com.cams7.siscom.jpa.domain.entity.Member;

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
