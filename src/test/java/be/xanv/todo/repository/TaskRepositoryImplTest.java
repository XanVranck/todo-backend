package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;
import be.xanv.todo.domain.TaskTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TaskRepositoryImplTest {

    @Autowired
    private TaskRepository taskRepository = new TaskRepositoryImpl();

    @Test
    void save() {
        Task testTask = TaskTestBuilder.createTask().build();

        taskRepository.save(testTask);
    }

    @Test
    void getAllTasks() {
        List<Task> actual = taskRepository.getAllTasks();

        Assertions.assertThat(actual).isNotEmpty();
    }
}