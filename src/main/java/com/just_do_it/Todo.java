package com.just_do_it;

public class Todo {
	
	private int todo_id;
	
	private String todo_title;
	
	private String created_date;
	
	private boolean is_of_new_day;
	
	private boolean is_confirmed;

	public Todo() {}

	public Todo(int todo_id, String todo_title, String created_date,
			
			boolean is_of_new_day, boolean is_confirmed)
	
	{
		
		this.todo_id = todo_id;
		
		this.todo_title = todo_title;
		
		this.created_date = created_date;
		
		this.is_of_new_day = is_of_new_day;
		
		this.is_confirmed = is_confirmed;
		
	}

	public int getTodo_id() {
		
		return todo_id;
		
	}

	public void setTodo_id(int todo_id) {
		
		this.todo_id = todo_id;
		
	}

	public String getTodo_title() {
		
		return todo_title;
		
	}

	public void setTodo_title(String todo_title) {
		
		this.todo_title = todo_title;
		
	}

	public String getCreated_date() {
		
		return created_date;
		
	}

	public void setCreated_date(String created_date) {
		
		this.created_date = created_date;
		
	}

	public boolean getIs_of_new_day() {
		
		return is_of_new_day;
		
	}

	public void setIs_of_new_day(boolean is_of_new_day) {
		
		this.is_of_new_day = is_of_new_day;
		
	}
	
	public boolean getIs_confirmed() {
		
		return is_confirmed;
		
	}

	public void setIs_confirmed(boolean is_confirmed) {
		
		this.is_confirmed = is_confirmed;
		
	}
	
}
