package com.ilya.todo_list.controller;

import com.ilya.todo_list.entity.Task;
import com.ilya.todo_list.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/tasks")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}

	@GetMapping("/tasks/{id}")
	public Task getTask(@PathVariable int id) {
		return taskService.getTask(id);
	}

	@GetMapping("/tasks/today")
	public List<Task> getTasksForDay() {
		return taskService.getTasksForDay();
	}

	@GetMapping("/tasks/week")
	public List<Task> getTasksForWeek() {
		return taskService.getTasksForWeek();
	}

	@GetMapping("/tasks/month")
	public List<Task> getTasksForMonth() {
		return taskService.getTasksForMonth();
	}

	@PostMapping("/tasks")
	public Task addTask(@RequestBody Task task) {
		taskService.addTask(task);
		return task;
	}

	@PutMapping("/tasks/{id}")
	public String removeTask(@PathVariable int id) {
		taskService.removeTask(id);
		return "The task with ID = " + id + " was successfully deleted";
	}

	@PutMapping("/tasks/setDone/{id}")
	public String setTaskDone(@PathVariable int id) {
		taskService.setTaskDone(id);
		return "The task with ID = " + id + " set as done";
	}

}
