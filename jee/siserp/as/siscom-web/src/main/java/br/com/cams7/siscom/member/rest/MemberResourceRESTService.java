package br.com.cams7.siscom.member.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import br.com.cams7.siscom.jpa.domain.entity.Member;
import br.com.cams7.siscom.member.service.MemberService;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */
@Path("/members")
@RequestScoped
public class MemberResourceRESTService {
	@Inject
	private MemberService service;

	@GET
	@Produces("application/json")
	public List<Member> listAllMembers() {
		final List<Member> results = (List<Member>) service.findAll();
		return results;
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Member lookupMemberById(@PathParam("id") long id) {
		return service.findOne(id);
	}
}
