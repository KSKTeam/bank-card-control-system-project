package org.kskteam.projects.cardcontrolproject.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.kskteam.projects.cardcontrolproject.service.dao.DatabaseConnection;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.transaction.Transaction;
import org.kskteam.projects.cardcontrolproject.service.dao.dto.user.User;
import org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate.HibernateService;
import org.kskteam.projects.cardcontrolproject.service.dao.jpa.hibernate.UserService;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResourse {

	private DatabaseConnection connection = HibernateService.getInstance();
	
	private UserService userService = UserService.getInstance();
	
	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") String id){
		User user = connection.read(id, User.class);
		return user;		
	}
	
	@GET
	public User getUserByLoginAndPassword(@QueryParam("login") String login, @QueryParam("password") String password){
		User user = userService.getUserByLoginAndPassword(login, password);
		return user;		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void addUser(User user){
		connection.insert(user);
	}
	
	@PUT
	@Path("/{userId}")
	public void updateUser(@PathParam("userId") String id, User user){
		user.setId(id);
		connection.update(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public void deleteUser(@PathParam("userId") String id){
		User user = new User();
		user.setId(id);
		connection.delete(user);
	}
	
	
}
