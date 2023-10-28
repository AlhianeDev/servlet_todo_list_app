package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.io.PrintWriter;

import java.util.List;

public class ViewTodos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ViewTodos() {
    	
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		if (request.getParameter("todo") != null &&
				
				request.getParameter("todo").equals("no_id")) {
			
			out.println(
					
					"<script type='text/javascript'>"
					
					+ "window.alert('No Todo Selected To Confirm, Update Or Delete It,"

					+ " No Todo Id!');"
					
					+ "</script>"
					
			);
			
		} else if (request.getParameter("todo") != null &&
				
				request.getParameter("todo").equals("no_title")) {
			
			out.println(
					
					"<script type='text/javascript'>"
					
					+ "window.alert('There Is No Specified Todo To Add It, No Todo Title!');"
					
					+ "</script>"
					
			);
			
		}
		
		if (request.getParameter("status") != null) {
		
			int status = Integer.parseInt(request.getParameter("status"));
			
			String action = request.getParameter("action");
			
			if (status > 0) {
				
				out.println(
						
						"<script type='text/javascript'>"
						
						+ "window.alert('Todo has been " + action + "ed successfuly.');"
						
						+ "</script>"
						
				);
				
				
			} else {
				
				out.println(
						
						"<script type='text/javascript'>"
						
						+ "window.alert('Failed to " + action + " Todo!');"
						
						+ "</script>"
						
				);
				
			}
			
		}
		
		List<Todo> todos = TodoDao.show_todos();
		
		out.print(
				
				"<!DOCTYPE html>\r\n"
				
				+ "<html>"
				
				+ "<head>"
				
				+ "<meta charset=\"UTF-8\">"
				
				+ "<link rel=\"shortcut icon\" type=\"image/png\" href=\"images/favicon.png\" />"
				
				+ "<title>JUST DO IT</title>"
				
				+ "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">"
				
				+ "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>"
				
				+ "<link href=\"https://fonts.googleapis.com/css2?family=Work+Sans:wght@300;400;500;600;700;800;900&display=swap\" rel=\"stylesheet\">"
					
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/all.min.css\">"
					
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/styles.css\">"
				
				+ "</head>"
				
				+ "<body>"
				
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
				
				+ "</header>"
				
				+ "<section class=\"sec view\">\n"
				
				+ "<h2 class=\"main-title\">Just do it.</h2>\n"
				
				+ "<div class=\"container\">\n"
				
				+ "<div class=\"todo-list\">"
				
		);
		
		if (todos.size() > 0) {
		
			for (Todo todo : todos) {
				
				if (todo.getIs_of_new_day())
					
					out.print("<h3 class=\"date\">" + todo.getCreated_date() + "</h3>\n");
				
				if (todo.getIs_confirmed()) {
					
					out.print(
							
							"<div class=\"todo confirmed\">\n"
							
							+ "<h3 class=\"todo-item\">" + todo.getTodo_title() + "</h3>\n"
			
							+ "<div class='actions'>\n"
							
							+ "<a href=\"ConfirmTodo?todo_id="
							
							+ todo.getTodo_id()
							
							+ "\"><i class=\"fa-solid fa-check\"></i></a>\n"
			
							+ "<a href=\"EditeTodo?todo_id=" 
							
							+ todo.getTodo_id() 
							
							+ "\"><i class=\"fa-regular fa-pen-to-square\"></i></a>\n"
			
							+ "<a href=\"DeleteTodo?todo_id="
							
							+ todo.getTodo_id()
							
							+ "\"><i class=\"fa-solid fa-trash\"></i></a>\n"
							
							+ "</div>"
			
							+ "</div>"
								
						);
					
				} else {
					
					out.print(
							
							"<div class=\"todo\">\n"
							
							+ "<h3 class=\"todo-item\">" + todo.getTodo_title() + "</h3>\n"
			
							+ "<div class='actions'>\n"
							
							+ "<a href=\"ConfirmTodo?todo_id="
							
							+ todo.getTodo_id()
							
							+ "\"><i class=\"fa-solid fa-check\"></i></a>\n"
			
							+ "<a href=\"EditeTodo?todo_id=" 
							
							+ todo.getTodo_id() 
							
							+ "\"><i class=\"fa-regular fa-pen-to-square\"></i></a>\n"
			
							+ "<a href=\"DeleteTodo?todo_id="
							
							+ todo.getTodo_id()
							
							+ "\"><i class=\"fa-solid fa-trash\"></i></a>\n"
							
							+ "</div>"
			
							+ "</div>"
								
					);
					
				}
				
			}
		
		} else {
			
			out.print("<p class='error-msg'>No Todo Items To Show!</p>");
			
		}
		
		out.print(
		
			"</div>\n"

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
		
	}

}
