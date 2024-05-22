package com.gl.todo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.todo.dto.TodoDto;
import com.gl.todo.entity.Todo;
import com.gl.todo.exception.ResourceNotFoundException;
import com.gl.todo.repository.TodoRepository;
import com.gl.todo.service.TodoService;

@Service
public class TodoServiceImpl implements TodoService {
	@Autowired
	TodoRepository todorepo;
	@Autowired
	ModelMapper mapper;

	@Override
	public TodoDto createTodo(TodoDto todoDto) {
		Todo todo = mapper.map(todoDto,Todo.class);
		Todo savedTodo = todorepo.save(todo);
		TodoDto newDto = mapper.map(savedTodo, TodoDto.class);
		return newDto;
	}

	@Override
	public TodoDto getTodoById(Long id) {
		Optional<Todo> result = todorepo.findById(id);
		TodoDto dto=null;
		if(result.isPresent()) {
			Todo todo = result.get();
			 dto = mapper.map(todo, TodoDto.class);		
		}
		else {
			throw new ResourceNotFoundException("todo with"+" "+id+" "+"doesn't exist");
		}
		
		return dto;
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todolist = todorepo.findAll();
		
		return todolist.stream().map(item->mapper.map(item,TodoDto.class)).toList();
	}

	@Override
	public void deleteTodo(Long id) {
		Optional<Todo> result = todorepo.findById(id);
		if(result.isPresent()) {
			todorepo.delete(result.get());
		}
		else {
			throw new ResourceNotFoundException("todo with"+" "+id+" "+"doesn't exist");
		}

	}

	@Override
	public TodoDto updateTodo(Long id, TodoDto todoDto) {
		Todo todo = todorepo.findById(id).orElseThrow(()->new ResourceNotFoundException("todo with"+" "+id+" "+"doesn't exist"));
		Todo newTodo = mapper.map(todoDto,Todo.class);
		todo.setTitle(newTodo.getTitle());
		todo.setDescription(newTodo.getDescription());
		todo.setCompleted(newTodo.isCompleted());
		Todo savedTodo=todorepo.save(todo);
		return mapper.map(savedTodo, TodoDto.class);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = todorepo.findById(id).orElseThrow(()->new ResourceNotFoundException("todo with"+" "+id+" "+"doesn't exist"));
		todo.setCompleted(true);
		Todo savedTodo = todorepo.save(todo);
		return mapper.map(savedTodo, TodoDto.class);
	}

	@Override
	public TodoDto incompleteTodo(Long id) {
		Todo todo = todorepo.findById(id).orElseThrow(()->new ResourceNotFoundException("todo with"+" "+id+" "+"doesn't exist"));
		todo.setCompleted(false);
		Todo savedTodo = todorepo.save(todo);
		return mapper.map(savedTodo, TodoDto.class);
	}

}
