package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddTodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AddTodo() {
    	
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		String todo_title = request.getParameter("add-input");
		
		if (todo_title != null) {
			
			Todo todo = new Todo();
			
			todo.setTodo_title(todo_title);
			
			int status = TodoDao.save_todo(todo);
			
			response.sendRedirect("ViewTodos?status=" + status + "&action=Add");
			
		} else {
			
			response.sendRedirect("ViewTodos?todo=no_title");
			
		}
		
	}

}
