package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.api.TaskEditDTO;

import java.util.List;

public interface TaskService {

    void createTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    void deleteTask(String uuid);

    void markAsDone(String uuid);

    void markAsUndone(String uuid);

    void editTask(String uuid, TaskEditDTO taskEditDTO);
}
