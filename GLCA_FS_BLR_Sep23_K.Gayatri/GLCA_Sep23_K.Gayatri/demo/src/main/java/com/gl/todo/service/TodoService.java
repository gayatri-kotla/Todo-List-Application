package com.gl.todo.service;

import java.util.List;

import com.gl.todo.dto.TodoDto;

public interface TodoService {
	
TodoDto createTodo(TodoDto todo);
TodoDto getTodoById(Long id);
List<TodoDto> getAllTodos();
void deleteTodo(Long id);
TodoDto updateTodo(Long id,TodoDto todo);
TodoDto completeTodo(Long id);
TodoDto incompleteTodo(Long id);
}
