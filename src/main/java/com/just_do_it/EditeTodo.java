package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import java.io.PrintWriter;

public class EditeTodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public EditeTodo() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		HttpSession httpSession = request.getSession();
		
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("todo_id") != null) {
		
			int todo_id = Integer.parseInt(request.getParameter("todo_id"));
			
			httpSession.setAttribute("todo_id", todo_id);
			
			Todo todo = TodoDao.get_todo_by_id(todo_id);
			
			out.print(
				
				"<!DOCTYPE html>\n"
	
				+ "<html>\n"
	
				+ "<head>\n"
	
				+ "<meta charset=\"UTF-8\">\n"
	
				+ "<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\" />\n"
				
				+ "<title>JUST DO IT</title>\n"
	
				+ "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n"
	
				+ "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n"
	
				+ "<link href=\"https://fonts.googleapis.com/css2?family=Work+Sans:wght@300\n"
			
				+ ";400;500;600;700;800;900&display=swap\" rel=\"stylesheet\">\n"
		
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/all.min.css\">\n"
	
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">\n"
	
				+ "</head>\r"
	
				+ "<body>\r"
	
				+ "<header class=\"header\">\n"
		
				+ "<div class=\"container\">\n"
	
				+ "<h2 class=\"brand\"><a href=\"#\">JUST DO IT</a></h2>\n"
				
				+ "<nav class=\"navigation\">\n"
	
				+ "<ul class=\"nav-links\">\n"
	
				+ "<li><a href=\"index.html\">Add</a></li>\n"
	
				+ "<li><a href=\"ViewTodos\">View</a></li>\n"
			
				+ "<li><a href=\"contact.html\">Contact</a></li>\n"
		
				+ "</ul>\n"
				
				+ "</nav>\n"
	
				+ "</div>\n"
	
				+ "</header>\n"
	
				+ "<section class=\"sec landing\">\n"
	
				+ "<div class=\"container\">\n"
				
				+ "<h1 class=\"main-title\">Just do it.</h1>\n"
		
				+ "<form action=\"TodoUpdated\" method=\"get\" class=\"main-form\">\n"
	
				+ "<input type=\"text\" name=\"update-input\" placeholder=\"Update a task\""
				
				+ "value=\"" + todo.getTodo_title() + "\">\n"
	
				+ "<input type=\"submit\" value=\"Update Todo!\">\n"
				
				+ "</form>\n"
	
				+ "</div>\n"
	
				+ "</section>\n"
				
				+ "<footer class=\"footer\">\n"
				
				+ "<div class=\"container\">\n"

				+ "<p class=\"copyright\">\n"

				+ "© 2023 Todo List App <a href=\"index.html\">Just do it</a>. Built using Servlet.\n"

				+ "</p>\n"

				+ "</div>\n"

				+ "</footer>"
	
				+ "<script src=\"js/all.min.js\"></script>\n"
	
				+ "</body>\n"
				
				+ "</html>"
					
			);
		
		} else {
			
			response.sendRedirect("ViewTodos?todo=no_id");
			
		}
		
	}

}
