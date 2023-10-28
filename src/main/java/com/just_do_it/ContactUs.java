package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.io.PrintWriter;

public class ContactUs extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
    public ContactUs() {
    	
        super();

    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		String email = request.getParameter("email");
		
		String subject = request.getParameter("subject");
		
		String message = request.getParameter("message");
		
		Mailer.send_email(email, subject, message);
		
		out.print(
		
			"<script type='text/javascript'>"
			
			+ "window.alert('Message has been sent successfully');"
			
			+ "window.location.assign("

			+ "'http://localhost:8888/servlets_todo_list_web_app/contact.html');"
			
			+ "</script>"
				
		);

	}

}
