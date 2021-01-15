package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.domain.Task;
import be.xanv.todo.domain.TaskTestBuilder;
import be.xanv.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskService taskService = new TaskServiceImpl();

    @Test
    void createTask_happyPath() {
        String title = "title";
        String description = "description";
        TaskDTO taskDTO = TaskDTO.createTaskDTO(title, description);
        Task task = Task.TaskBuilder.createTask().withTitle(title).withDescription(description).build();

        taskService.createTask(taskDTO);

        Mockito.verify(taskRepository).save(task);
    }

    @Test
    void createTask_duplicate_notCreated() {
        String title = "title";
        String description = "description";
        TaskDTO taskDTO = TaskDTO.createTaskDTO(title, description);
        Task task = Task.TaskBuilder.createTask().withTitle(title).withDescription(description).build();
        List<Task> tasks = singletonList(task);
        List<TaskDTO> taskDTOS = singletonList(TaskDTO.createTaskDTO(title, description));
        Mockito.when(taskRepository.getAllTasks()).thenReturn(tasks);
        Mockito.when(taskMapper.map(tasks)).thenReturn(taskDTOS);

        taskService.createTask(taskDTO);

        Mockito.verify(taskRepository, Mockito.never()).save(task);
    }

    @Test
    void createTask_noDescription_happyPath() {
        String title = "title";
        TaskDTO taskDTO = TaskDTO.createTaskDTO(title, null);
        Task task = Task.TaskBuilder.createTask().withTitle(title).withDescription(null).build();

        taskService.createTask(taskDTO);

        Mockito.verify(taskRepository).save(task);
    }

    @Test
    void createTask_noTitle_illegalStateException() {
        String description = "description";
        TaskDTO taskDTO = TaskDTO.createTaskDTO(null, description);

        assertThatThrownBy(() -> taskService.createTask(taskDTO))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Title can not be empty.");
    }

    @Test
    void getAllTasks_happyPath() {
        Task task = TaskTestBuilder.createTask().build();
        List<Task> tasks = singletonList(task);
        Mockito.when(taskRepository.getAllTasks()).thenReturn(tasks);

        taskService.getAllTasks();

        Mockito.verify(taskRepository).getAllTasks();
        Mockito.verify(taskMapper).map(tasks);
    }
}