package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.domain.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static be.xanv.todo.api.TaskDTO.TaskDTOBuilder.createTaskDTO;

@Component
class TaskMapper {

    List<TaskDTO> map(List<Task> tasks) {
        return tasks.stream()
                .map(task -> createTaskDTO()
                        .withUuid(task.getUuid())
                        .withTitle(task.getTitle())
                        .withDescription(task.getDescription())
                        .withDone(task.isDone())
                        .withExecutionDate(task.getExecutionDate())
                        .build())
                .collect(Collectors.toList());
    }

    Task map(TaskDTO taskDTO) {
        return Task.TaskBuilder.createTask()
                .withTitle(taskDTO.getTitle())
                .withDescription(taskDTO.getDescription())
                .withDone(taskDTO.isDone())
                .withExecutionDate(taskDTO.getExecutionDate())
                .build();
    }
}
