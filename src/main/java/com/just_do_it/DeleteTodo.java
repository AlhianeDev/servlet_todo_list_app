package com.just_do_it;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteTodo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public DeleteTodo() {
    	
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		if (request.getParameter("todo_id") != null) {
			
			int todo_id = Integer.parseInt(request.getParameter("todo_id"));
			
			int status = TodoDao.delete_todo_by_id(todo_id);
			
			response.sendRedirect("ViewTodos?status=" + status + "&action=Delete");
		
		} else {
			
			response.sendRedirect("ViewTodos?todo=no_id");
			
		}
		
	}

}
