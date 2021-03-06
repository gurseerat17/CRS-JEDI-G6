/**
 * 
 */
package com.flipkart.restcontroller;

import java.sql.SQLException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.exception.UserAlreadyInUseException;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentOperation;
import com.flipkart.service.UserOperation;

/**
 * @author rutwi
 *
 */
@Path("/user")
public class UserRESTApi {
	
	// TODO:
	/*
	 * - Login a user - POST
	 * - Register a user - POST
	 */
	
	StudentOperation so = new StudentOperation();
	
	
	@POST
	@Path("/login")
	public Response verifyCredentials(
			@NotNull
			@QueryParam("userId") String userId,
			@NotNull
			@QueryParam("password") String password,
			@NotNull
			@QueryParam("role") String role) {
		
		try 
		{
			UserOperation uo = new UserOperation();
			boolean loggedIn = uo.loginUser(userId, password, role);
			
			if(loggedIn)
			{
				return Response.status(200).entity("Login successful").build();
				
			}
			else
			{
				return Response.status(500).entity("Invalid credentials!").build();
			}
		}
		catch (UserNotFoundException e) 
		{
			return Response.status(500).entity(e.getMessage()).build();
		} catch (Exception e) {
			return Response.status(500).entity(e.getMessage()).build();
		}		
		
	}

	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Response RegisterUser(Student student) {
		Student newStudent = new Student();
		 try {
			newStudent = so.addStudent(student.getUserID(), student.getName(), student.getPassword(), student.getDepartment(), student.getContactNumber(), student.getJoiningYear());
		} catch (UserAlreadyInUseException e) {
			return Response.status(500).entity("User Name Already in Use").build();//			e.printStackTrace();
		}
         
		
        String result = "Added student : " + student.getName() +  " | Student ID : "+newStudent.getStudentID();
		
		
		return Response.status(201).entity(result).build();
		
	}  
	
}
