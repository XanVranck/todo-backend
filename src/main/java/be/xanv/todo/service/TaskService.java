package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.domain.Task;
import be.xanv.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(TaskDTO taskDTO) {
        if (taskDTO.getTitle() == null || taskDTO.getTitle().isEmpty()) {
            throw new IllegalStateException("Title can not be empty.");
        }
        taskRepository.save(Task.TaskBuilder.createTask()
                .withTitle(taskDTO.getTitle())
                .withDescription(taskDTO.getDescription())
                .build());
    }
}
