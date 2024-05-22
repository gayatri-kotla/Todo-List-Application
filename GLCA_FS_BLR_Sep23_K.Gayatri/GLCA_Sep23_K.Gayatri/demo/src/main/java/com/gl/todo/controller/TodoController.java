package com.gl.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gl.todo.dto.TodoDto;
import com.gl.todo.service.TodoService;

@Controller
public class TodoController{
	@Autowired
	TodoService todoService;
	@RequestMapping("/home")
	public ModelAndView listOfTodos() {
		ModelAndView mandv = new ModelAndView();
		List<TodoDto> allTodos = todoService.getAllTodos();
		mandv.addObject("todos",allTodos);
		mandv.setViewName("Home");
		System.out.println("in controller");
		return mandv;
	}
	@RequestMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Long id) {
		todoService.deleteTodo(id);
		return "redirect:/home";
	}
	
	@RequestMapping("/complete/{id}")
	public String completeTodo(@PathVariable Long id) {
		todoService.completeTodo(id);
		return "redirect:/home";
	}
	
	@RequestMapping("/incomplete/{id}")
	public String incompleteTodo(@PathVariable Long id) {
		todoService.incompleteTodo(id);
		return "redirect:/home";
	}
	
	
}