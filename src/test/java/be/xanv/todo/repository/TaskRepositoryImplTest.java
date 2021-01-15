package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;
import be.xanv.todo.domain.TaskTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TaskRepositoryImplTest {

    @Autowired
    private TaskRepository taskRepository = new TaskRepositoryImpl();

    @Test
    void saveAndGetAllTasks() {
        Task testTask = TaskTestBuilder.createTask().build();

        taskRepository.save(testTask);
        List<Task> actual = taskRepository.getAllTasks();

        Assertions.assertThat(actual).isNotEmpty();
        Assertions.assertThat(actual.get(0)).isEqualTo(testTask);
    }

    @Test
    @Disabled
    void deleteTask() {
        Task testTask = TaskTestBuilder.createTask().build();

        taskRepository.save(testTask);
        List<Task> actual = taskRepository.getAllTasks();

        Assertions.assertThat(actual).hasSize(1);
        Task actualTask = actual.get(0);
        Assertions.assertThat(actualTask).isEqualTo(testTask);

        taskRepository.delete(actualTask);
        Assertions.assertThat(actual).hasSize(0);
    }
}