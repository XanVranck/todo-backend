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
        taskRepository.save(Task.TaskBuilder.createTask()
                .withTitle(taskDTO.getTitle())
                .withDescription(taskDTO.getDescription())
                .build());
    }

    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> allTasks = taskRepository.getAllTasks();
        return taskMapper.map(allTasks);
    }
}
