package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.domain.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static be.xanv.todo.api.TaskDTO.TaskDTOBuilder.createTaskDTO;
import static be.xanv.todo.domain.TaskTestBuilder.createTask;
import static org.assertj.core.api.Assertions.assertThat;

class TaskMapperTest {

    private TaskMapper mapper = new TaskMapper();

    @Test
    void mapTasksToTaskDTOs() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(createTask().build());
        tasks.add(createTask().withTitle("dishes").withDescription("No wait, i have a dishwasher").build());

        List<TaskDTO> actual = mapper.map(tasks);

        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getTitle()).isEqualTo("Title");
        assertThat(actual.get(0).getDescription()).isEqualTo("Description");
        assertThat(actual.get(1).getTitle()).isEqualTo("dishes");
        assertThat(actual.get(1).getDescription()).isEqualTo("No wait, i have a dishwasher");
    }

    @Test
    void mapTaskDTOToTask() {
        Task actual = mapper.map(createTaskDTO().withTitle("clean").withDescription("clean the house").build());

        assertThat(actual.getTitle()).isEqualTo("clean");
        assertThat(actual.getDescription()).isEqualTo("clean the house");
    }
}