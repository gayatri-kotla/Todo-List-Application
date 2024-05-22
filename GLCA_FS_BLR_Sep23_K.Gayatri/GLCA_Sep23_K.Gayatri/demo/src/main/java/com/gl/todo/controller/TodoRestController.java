package com.gl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.todo.dto.TodoDto;
import com.gl.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin("*")
public class TodoRestController {
	@Autowired
	TodoService todoService;

	@PostMapping
	public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto dto) {
		return new ResponseEntity<>(todoService.createTodo(dto), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TodoDto> getTodoById(@PathVariable Long id) {
		return new ResponseEntity<TodoDto>(todoService.getTodoById(id), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		return new ResponseEntity<List<TodoDto>>(todoService.getAllTodos(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto dto) {		
		return new ResponseEntity<TodoDto>(todoService.updateTodo(id, dto), HttpStatus.OK);
	}
	
	@PatchMapping("/{id}/complete")
	public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
		return new ResponseEntity<TodoDto>(todoService.completeTodo(id), HttpStatus.OK);
	}
	
	@PatchMapping("/{id}/incomplete")
	public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id) {
		return new ResponseEntity<TodoDto>(todoService.incompleteTodo(id), HttpStatus.OK);
	}
}
