package com.ilya.todo_list;

import com.ilya.todo_list.controller.TaskController;
import com.ilya.todo_list.dao.TaskRepository;
import com.ilya.todo_list.entity.Task;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TaskTest {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private TaskController taskController;

	@BeforeEach
	public void setUp() {
		taskRepository.deleteAll();
		List<Task> tasks = new ArrayList<>();
		tasks.add(new Task("Task 1", "Description 1", LocalDateTime.now().toString()));
		tasks.add(new Task("Task 2", "Description 2", LocalDateTime.now().toString()));
		tasks.add(new Task("Task 3", "Description 3", LocalDateTime.now().toString()));
		tasks.add(new Task("Task 4", "Description 4", LocalDateTime.now().plusDays(1).toString()));
		tasks.add(new Task("Task 5", "Description 5", LocalDateTime.now().plusDays(1).toString()));
		tasks.add(new Task("Task 6", "Description 6", LocalDateTime.now().plusDays(1).toString()));
		tasks.add(new Task("Task 7", "Description 7", LocalDateTime.now().plusDays(2).toString()));
		tasks.add(new Task("Task 8", "Description 8", LocalDateTime.now().plusDays(2).toString()));
		tasks.add(new Task("Task 9", "Description 9", LocalDateTime.now().plusDays(3).toString()));
		tasks.add(new Task("Task 10","Description 10", LocalDateTime.now().plusDays(5).toString()));
		tasks.add(new Task("Task 11","Description 11", LocalDateTime.now().plusDays(5).toString()));
		tasks.add(new Task("Task 12","Description 12", LocalDateTime.now().plusDays(6).toString()));
		tasks.add(new Task("Task 13","Description 13", LocalDateTime.now().plusDays(7).toString()));
		tasks.add(new Task("Task 14","Description 14", LocalDateTime.now().plusDays(12).toString()));
		tasks.add(new Task("Task 15","Description 15", LocalDateTime.now().plusDays(21).toString()));
		tasks.add(new Task("Task 16","Description 16", LocalDateTime.now().plusDays(30).toString()));
		tasks.add(new Task("Task 17","Description 17", LocalDateTime.now().plusDays(35).toString()));
		tasks.add(new Task("Task 18","Description 18", LocalDateTime.now().plusDays(64).toString()));
		tasks.add(new Task("Task 19","Description 19", LocalDateTime.now().plusDays(100).toString()));
		tasks.add(new Task("Task 20","Description 20", LocalDateTime.now().plusDays(350).toString()));

		for (Task task : tasks) {
			taskController.addTask(task);
		}
	}


	@Test
	public void addTasks() {
		List<Task> tasks = new ArrayList<>(taskController.getAllTasks());
		if (tasks.size() == 20) System.out.println("[OK] --- Test 'addTasks()'");
		else System.out.println("[FAILED] --- Test 'addTasks()'");
	}

	@Test
	public void removeTasks() {
		List<Task> tasks = new ArrayList<>(taskController.getAllTasks());
		for (Task task : tasks) {
			taskController.removeTask(task.getId());
		}
		tasks = new ArrayList<>(taskController.getAllTasks());
		if (tasks.size() == 0) System.out.println("[OK] --- Test 'removeTasks()'");
		else System.out.println("[FAILED] --- Test 'removeTasks()'");
	}

	@Test
	public void editTasks() {
		List<Task> tasks = new ArrayList<>(taskController.getAllTasks());
		String[] nameArray = new String[tasks.size()];
		String[] descriptionArray = new String[tasks.size()];
		boolean[] isDoneArray = new boolean[tasks.size()];
		for (int i = 0; i < tasks.size(); i++) {                    // Копирование полей в массивы, с
			nameArray[i] = tasks.get(i).getName();                  // которыми будут сравниваться
			descriptionArray[i] = tasks.get(i).getDescription();    // измененные задачи.
			isDoneArray[i] = tasks.get(i).isDone();
		}
		// Изменение задач
		for (Task task : tasks) {
			task.setName("new Name");
			task.setDescription("new Description");
			task.setDone(true);
			taskController.addTask(task);
		}
		// Сравнение
		boolean isFailed = false;
		for (int i = 0; i < tasks.size(); i++) {
			if (nameArray[i].equals(taskController.getTask(tasks.get(i).getId()).getName())) isFailed = true;
			if (descriptionArray[i].equals(taskController.getTask(tasks.get(i).getId()).getDescription())) isFailed = true;
			if (isDoneArray[i] == taskController.getTask(tasks.get(i).getId()).isDone()) isFailed = true;
		}
		if (!isFailed && tasks.size() == 20) System.out.println("[OK] --- Test 'editTasks()'");
		else System.out.println("[FAILED] --- Test 'editTasks()'");
	}

	@Test
	public void getTasksForDay() {
		List<Task> tasksForDay = taskController.getTasksForDay();
		if (tasksForDay.size() == 3) System.out.println("[OK] --- Test 'getTasksForDay()'");
		else System.out.println("[FAILED] --- Test 'getTasksForDay()'");
	}

	@Test
	public void getTasksForWeek() {
		List<Task> TasksForWeek = taskController.getTasksForWeek();
		if (TasksForWeek.size() == 13) System.out.println("[OK] --- Test 'getTasksForWeek()'");
		else System.out.println("[FAILED] --- Test 'getTasksForWeek()'");
	}

	@Test
	public void getTasksForMonth() {
		List<Task> TasksForMonth = taskController.getTasksForMonth();
		if (TasksForMonth.size() == 16) System.out.println("[OK] --- Test 'getTasksForMonth()'");
		else System.out.println("[FAILED] --- Test 'getTasksForMonth()'");
	}

}


