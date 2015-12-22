package org.kskteam.projects.cardcontrolproject.resources;

import java.io.Serializable;
import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.Transaction;
import org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate.TransactionService;

@Path("/")
public class TransactionResource {
	private TransactionService ts = TransactionService.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Collection<Transaction> getAllTransactions(@PathParam("userId") String userId) {
		return ts.getAllTransactions(userId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addTicket(@PathParam("userId") String userId,
			Transaction transaction, @Context UriInfo uriInfo) {
		Serializable newId = ts.addTransactionToUser(userId, transaction);
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId.toString()).build();
		return Response.created(uri).entity(transaction).build();
	}
}
