package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ConfirmTodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ConfirmTodo() {
    	
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		if (request.getParameter("todo_id") != null) {
			
			int todo_id = Integer.parseInt(request.getParameter("todo_id"));
			
			Todo todo = TodoDao.confirm_todo(todo_id);
			
			int status = todo != null ? 1 : 0;
			
			if (todo.getIs_confirmed()) {
				
				response.sendRedirect("ViewTodos?status=" + status + "&action=Unconfirm");
				
			} else {
				
				response.sendRedirect("ViewTodos?status=" + status + "&action=Confirm");
				
			}
			
			
			
		} else {
			
			response.sendRedirect("ViewTodos?todo=no_id");
			
		}
		
	}

}
