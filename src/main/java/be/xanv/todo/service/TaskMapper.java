package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.domain.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    List<TaskDTO> map(List<Task> tasks) {
        return tasks.stream()
                .map(task -> TaskDTO.createTaskDTO(task.getUuid(), task.getTitle(), task.getDescription(), task.isDone()))
                .collect(Collectors.toList());
    }

    Task map(TaskDTO taskDTO) {
        return Task.TaskBuilder.createTask()
                .withTitle(taskDTO.getTitle())
                .withDescription(taskDTO.getDescription())
                .withDone(taskDTO.isDone())
                .build();
    }
}
