package com.ilya.todo_list.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "isdone")
	private boolean isDone;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "created")
	private String dateOfCreation;
	@Column(name = "deadline")
	private String dateOfDeadline;

	public Task() {}

	public Task(String name, String description, String dateOfDeadline) {
		this.name = name;
		this.description = description;
		this.dateOfDeadline = dateOfDeadline;
	}

	@Override
	public String toString() {
		return "Task{" +
				"id=" + id +
				", isDone=" + isDone +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", dateOfCreation=" + dateOfCreation +
				", dateOfDeadline=" + dateOfDeadline +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Task task = (Task) o;

		if (getId() != task.getId()) return false;
		if (isDone() != task.isDone()) return false;
		if (!getName().equals(task.getName())) return false;
		if (!getDescription().equals(task.getDescription())) return false;
		if (!getDateOfCreation().equals(task.getDateOfCreation())) return false;
		return getDateOfDeadline().equals(task.getDateOfDeadline());
	}

	@Override
	public int hashCode() {
		int result = getId();
		result = 31 * result + (isDone() ? 1 : 0);
		result = 31 * result + getName().hashCode();
		result = 31 * result + getDescription().hashCode();
		result = 31 * result + getDateOfCreation().hashCode();
		result = 31 * result + getDateOfDeadline().hashCode();
		return result;
	}
//	public static int compare(boolean x, boolean y) {
//		return (x == y) ? 0 : (x ? -1 : 1);
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean done) {
		isDone = done;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getDateOfDeadline() {
		return dateOfDeadline;
	}

	public void setDateOfDeadline(String dateOfDeadline) {
		this.dateOfDeadline = dateOfDeadline;
	}
}
