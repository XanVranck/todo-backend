package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
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
        if (!isADuplicate(taskDTO)) {
            taskRepository.save(taskMapper.map(taskDTO));
        }
    }

    private boolean isADuplicate(TaskDTO taskDTO) {
        return getAllTasks().stream().anyMatch(t -> t.equals(taskDTO));
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> allTasks = taskRepository.getAllTasks();
        return taskMapper.map(allTasks);
    }

    @Override
    public void deleteTask(TaskDTO taskDTO) {
        taskRepository.delete(taskRepository.findByTitleAndDescription(taskDTO.getTitle(), taskDTO.getDescription()));
    }

    @Override
    public void markAsDone(TaskDTO taskDTO) {
        taskRepository.markAsDone(taskRepository.findByTitleAndDescription(taskDTO.getTitle(), taskDTO.getDescription()));
    }

    @Override
    public void markAsUndone(TaskDTO taskDTO) {
        taskRepository.markAsUndone(taskRepository.findByTitleAndDescription(taskDTO.getTitle(), taskDTO.getDescription()));
    }
}
