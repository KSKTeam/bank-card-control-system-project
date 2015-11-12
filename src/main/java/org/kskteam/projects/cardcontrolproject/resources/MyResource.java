package org.kskteam.projects.cardcontrolproject.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.kskteam.projects.cardcontrolproject.service.dao.DatabaseConnection;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.Transaction;
import org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate.HibernateService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

	
	private DatabaseConnection conn = HibernateService.getInstance();
	
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Transaction getIt(@QueryParam("id") String id) {
    	Transaction transaction = conn.read(id, Transaction.class);
        return transaction;
    }
}
