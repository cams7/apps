/**
 * 
 */
package cams7.siscom.member.service;

import javax.ejb.Local;

import cams7.apps.jee.service.BaseService;
import cams7.siscom.jpa.domain.entity.Member;

/**
 * @author cesar
 *
 */
@Local
public interface MemberService extends BaseService<Member, Long> {

}
