package org.ksk_team.projects.CorporateCardControlProject.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Hibernate;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.dto.transaction.Transaction;
import org.ksk_team.projects.CorporateCardControlProject.service.dao.hibernate.HibernateService;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
    	HibernateService instance = HibernateService.getInstance("WEB-INF/classes/hibernate/hibernate.cfg.xml");
    	Transaction transaction = instance.read("d0c31fb8-0feb-4168-b9c1-fd42e7f60a56", Transaction.class);
        return transaction.toString();
    }
}
