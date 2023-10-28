package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class TodoUpdated extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public TodoUpdated() {
    	
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		HttpSession httpSession = request.getSession();
		
		String new_todo_title = request.getParameter("update-input");
		
		if (new_todo_title != null) {
		
			int todo_id = (int) httpSession.getAttribute("todo_id");
			
			Todo todo = new Todo();
			
			todo.setTodo_id(todo_id);
			
			todo.setTodo_title(new_todo_title);
			
			int status = TodoDao.update_todo_by_id(todo);
			
			response.sendRedirect("ViewTodos?status=" + status + "&action=Update");
		
		} else {
			
			response.sendRedirect("ViewTodos?todo=no_id");
			
		}
		
	}

}
