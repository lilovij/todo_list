package com.ilya.todo_list.service;

import com.ilya.todo_list.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskService {

	public List<Task> getAllTasks();

	public List<Task> getTasksForDay();

	public List<Task> getTasksForWeek();

	public List<Task> getTasksForMonth();

	public void setTaskDone(int id);

	public void addTask(Task task);

	public Task getTask(int id);

	public void removeTask(int id);

}
