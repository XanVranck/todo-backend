package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.api.TaskEditDTO;
import be.xanv.todo.domain.Task;
import be.xanv.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public void createTask(TaskDTO taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalStateException("Title can not be empty.");
        }
        taskRepository.save(taskMapper.map(taskDTO));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> allTasks = taskRepository.getAllTasks();
        return taskMapper.map(allTasks);
    }

    @Override
    public void deleteTask(String uuid) {
        taskRepository.delete(uuid);
    }

    @Override
    public void markAsDone(String uuid) {
        taskRepository.markAsDone(uuid);
    }

    @Override
    public void markAsUndone(String uuid) {
        taskRepository.markAsUndone(uuid);
    }

    @Override
    public void editTask(String uuid, TaskEditDTO taskEditDTO) {
        taskRepository.edit(uuid, taskEditDTO);
    }
}
