package com.just_do_it;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;

public class TodoDao {
	
	private static Connection get_connection() {
		
		Connection connection = null;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/just_do_it";
			
			connection = DriverManager.getConnection(url, "root", "M64K66L98A01x");
			
		} catch (ClassNotFoundException | SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		}
		
		return connection;
		
	}
	
	public static int save_todo(Todo todo) {
		
		int status = 0;
		
		Connection connection = get_connection();
		
		String now_date = LocalDate.now().toString();
		
		String last_todo_date = created_last_todo_date();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					
				"INSERT INTO Todos (todo_title, created_date, is_of_new_day, is_confirmed) "

				+ "VALUES (?, ?, ?, ?)"
				
			);
			
			preparedStatement.setString(1, todo.getTodo_title());
			
			preparedStatement.setString(2, now_date); 
					
			if (last_todo_date != null) {
				
				if (now_date.equals(last_todo_date))
					
					preparedStatement.setBoolean(3, false);
					
				else
					
					preparedStatement.setBoolean(3, true);
				
			} else {
				
				preparedStatement.setBoolean(3, true);
				
			}
			
			preparedStatement.setBoolean(4, false);
			
			status = preparedStatement.executeUpdate();
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return status;
		
	}
	
	private static String created_last_todo_date() {
		
		Connection connection = get_connection();
		
		try {
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(
				
				"SELECT * FROM Todos ORDER BY todo_id DESC LIMIT 1;"
				
			);
			
			if (resultSet.next()) return resultSet.getString("created_date");
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return null;
		
	}
	
	public static Todo get_todo_by_id(int todo_id) {
		
		Connection connection = get_connection();
		
		Todo todo = null;
		
		try {
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(
				
				"SELECT * FROM Todos WHERE todo_id = " + todo_id
				
			);
			
			if (resultSet.next()) {
				
				todo = new Todo(
				
					resultSet.getInt("todo_id"),
					
					resultSet.getString("todo_title"),
					
					resultSet.getString("created_date"),
					
					resultSet.getBoolean("is_of_new_day"),
					
					resultSet.getBoolean("is_confirmed")
						
				);
				
			}
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return todo;
		
	}
	
	public static Todo confirm_todo(int todo_id) {
		
		Connection connection = get_connection();
		
		Todo todo = null;
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
							
				"UPDATE Todos SET is_confirmed = ? WHERE todo_id = ?"
					
			);
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery(
					
				"SELECT * FROM Todos WHERE todo_id = " + todo_id
					
			);
			
			if (resultSet.next()) {
				
				todo = new Todo(
				
					resultSet.getInt("todo_id"),
					
					resultSet.getString("todo_title"),
					
					resultSet.getString("created_date"),
					
					resultSet.getBoolean("is_of_new_day"),
					
					resultSet.getBoolean("is_confirmed")
						
				);
				
				if (resultSet.getBoolean("is_confirmed"))
					
					preparedStatement.setBoolean(1, false);
					
				else
					
					preparedStatement.setBoolean(1, true);
				
			}
			
			preparedStatement.setInt(2, todo_id);
					
			preparedStatement.executeUpdate();
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return todo;
		
	}
	 
	public static int update_todo_by_id(Todo todo) {
		
		int status = 0;
		
		Connection connection = get_connection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
							
				"UPDATE Todos SET todo_title = ? WHERE todo_id = ?"
					
			);
			
			preparedStatement.setString(1, todo.getTodo_title());
			
			preparedStatement.setInt(2, todo.getTodo_id());
			
			status = preparedStatement.executeUpdate();
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return status;
		
	}
	
	public static List<Todo> show_todos() {
		
		Connection connection = get_connection();
		
		List<Todo> todos = new ArrayList<Todo>();
		
		try {
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Todos");
			
			while (resultSet.next()) {
				
				Todo todo = new Todo(
				
						resultSet.getInt("todo_id"),
						
						resultSet.getString("todo_title"),
						
						resultSet.getString("created_date"),
						
						resultSet.getBoolean("is_of_new_day"),
						
						resultSet.getBoolean("is_confirmed")
						
				);
				
				todos.add(todo);
				
			}
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return todos;
		
	}
	
	public static int delete_todo_by_id(int id) {
		
		int status = 0;
		
		Connection connection = get_connection();
		
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					
				"DELETE FROM Todos WHERE todo_id = ?"
					
			);
			
			preparedStatement.setInt(1, id);
			
			status = preparedStatement.executeUpdate();
			
		} catch (SQLException ex) {
			
			System.err.println(ex.getMessage());
			
		} finally {
			
			if (connection != null) {
				
				try {
					
					connection.close();
					
				} catch (SQLException ex) {
					
					System.err.println(ex.getMessage());
					
				}
				
			}
			
		}
		
		return status;
		
	}

}
