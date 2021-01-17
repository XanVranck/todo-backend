package be.xanv.todo.repository;

import be.xanv.todo.api.TaskEditDTO;
import be.xanv.todo.domain.Task;

import java.util.List;

public interface TaskRepository {

    void save(Task task);

    List<Task> getAllTasks();

    void delete(String uuid);

    void markAsDone(String uuid);

    void markAsUndone(String uuid);

    void edit(String uuid, TaskEditDTO taskEditDTO);
}
