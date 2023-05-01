package com.ilya.todo_list.dao;

import com.ilya.todo_list.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {

}
