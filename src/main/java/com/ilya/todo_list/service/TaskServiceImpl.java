package com.ilya.todo_list.service;

import com.ilya.todo_list.dao.TaskRepository;
import com.ilya.todo_list.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	private static int getDateTimeDifference(String date) {
		date = date.substring(0, 10);
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			LocalDate targetDate = LocalDate.parse(date, formatter);
			if (currentDate.getYear() - targetDate.getYear() == 0) {
				return targetDate.getDayOfYear() - currentDate.getDayOfYear();
			} else return -1;
		} catch (NullPointerException nullPointerException) {
			System.out.println(nullPointerException);
		}
		System.out.println();
		return -1;
	}

	@Override
	public List<Task> getTasksForDay() {
		List<Task> tasksForDay = taskRepository.findAll();
		tasksForDay = new ArrayList<>(tasksForDay.stream()
				.filter((el) -> getDateTimeDifference(el.getDateOfDeadline()) == 0)
				.sorted((el1, el2) -> Boolean.compare(el1.isDone(), el2.isDone()))  // Сортировка по выполнению
				.toList());
		return tasksForDay;
	}

	@Override
	public List<Task> getTasksForWeek() {
		List<Task> tasksForWeek = getAllTasks();
		tasksForWeek = new ArrayList<>(tasksForWeek.stream()
				.filter((el) -> {
					int difference = getDateTimeDifference(el.getDateOfDeadline());
					return (difference > -1 && difference <= 7);
				})
				.sorted((el1, el2) -> Boolean.compare(el1.isDone(), el2.isDone()))  // Сортировка по выполнению
				.toList());
		return tasksForWeek;
	}

	@Override
	public List<Task> getTasksForMonth() {
		List<Task> tasksForMonth = getAllTasks();
		tasksForMonth = new ArrayList<>(tasksForMonth.stream()
				.filter((el) -> {
					int difference = getDateTimeDifference(el.getDateOfDeadline());
					return (difference > -1 && difference <= 30);
				})
				.sorted((el1, el2) -> Boolean.compare(el1.isDone(), el2.isDone()))  // Сортировка по выполнению
				.toList());
		return tasksForMonth;
	}

	@Override
	public void setTaskDone(int id) {
		Task task = null;
		Optional<Task> taskOptional = taskRepository.findById(id);
		if (taskOptional.isPresent()) {
			task = taskOptional.get();
			task.setDone(true);
			taskRepository.save(task);
		}

	}

	@Override
	public void addTask(Task task) {
		LocalDateTime localDateTime = LocalDateTime.now();
		String currentDateTime = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		task.setDateOfCreation(currentDateTime);
		taskRepository.save(task);
	}

	@Override
	public Task getTask(int id) {
		Task task = null;
		Optional<Task> taskOptional = taskRepository.findById(id);
		if (taskOptional.isPresent()) {
			task = taskOptional.get();
			return task;
		}
		return null;
	}

	@Override
	public void removeTask(int id) {
		taskRepository.deleteById(id);
	}
}
