package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;
import be.xanv.todo.domain.TaskTestBuilder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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
        taskRepository.delete(testTask.getUuid());
    }

    @Test
    void deleteTask() {
        Task testTask = TaskTestBuilder.createTask().build();

        taskRepository.save(testTask);
        List<Task> actualBeforeDelete = taskRepository.getAllTasks();
        Task actualTask = actualBeforeDelete.get(0);

        taskRepository.delete(actualTask.getUuid());
        Assertions.assertThat(taskRepository.getAllTasks()).hasSize(0);
    }

    @Test
    void markAsDone() {
        Task testTask = TaskTestBuilder.createTask().build();

        taskRepository.save(testTask);
        List<Task> beforeMarkAsDone = taskRepository.getAllTasks();
        Assertions.assertThat(beforeMarkAsDone).hasSize(1);
        Assertions.assertThat(beforeMarkAsDone.get(0).isDone()).isFalse();

        taskRepository.markAsDone(testTask.getUuid());
        List<Task> afterMarkAsDone = taskRepository.getAllTasks();
        Assertions.assertThat(afterMarkAsDone).hasSize(1);
        Assertions.assertThat(afterMarkAsDone.get(0).isDone()).isTrue();
    }
}