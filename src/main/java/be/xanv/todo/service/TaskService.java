package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;

import java.util.List;

public interface TaskService {

    void createTask(TaskDTO taskDTO);

    List<TaskDTO> getAllTasks();

    void deleteTask(TaskDTO taskDTO);

    void markAsDone(TaskDTO taskDTO);

    void markAsUndone(TaskDTO taskDTO);
}
