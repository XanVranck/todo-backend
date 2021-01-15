package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;

import java.util.List;

public interface TaskRepository {

    void save(Task task);

    List<Task> getAllTasks();

    void delete(Task task);

    Task findByTitleAndDescription(String title, String description);
}
