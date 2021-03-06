package br.com.cams7.siscom.member.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cams7.siscom.jpa.domain.entity.Member;
import br.com.cams7.siscom.member.service.MemberService;

/**
 * JAX-RS Example
 * 
 * This class produces a RESTful service to read the contents of the members
 * table.
 */
@Path("/member")
@RequestScoped
public class MemberResourceRESTService {
	@Inject
	private MemberService service;

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createPerson(Member member) {
		member = service.save(member);

		String output = "New member -> id : " + member.getId();
		System.out.println(output);

		return Response.status(200).entity(output).build();
	}

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> listAllMembers() {
		final List<Member> results = (List<Member>) service.findAll();
		return results;
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member lookupMemberById(@PathParam("id") long id) {
		return service.findOne(id);
	}
}
