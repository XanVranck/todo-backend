package be.xanv.todo.service;

import be.xanv.todo.api.TaskDTO;
import be.xanv.todo.domain.Task;
import be.xanv.todo.domain.TaskTestBuilder;
import be.xanv.todo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

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
        Task task = Task.TaskBuilder.createTask().withTitle(title).withDescription(description).build();
        TaskDTO taskDTO = TaskDTO.createTaskDTO(title, description);
        when(taskMapper.map(taskDTO)).thenReturn(task);

        taskService.createTask(taskDTO);

        verify(taskRepository).save(task);
    }

    @Test
    void createTask_duplicate_notCreated() {
        String title = "title";
        String description = "description";
        TaskDTO taskDTO = TaskDTO.createTaskDTO(title, description);
        Task task = Task.TaskBuilder.createTask().withTitle(title).withDescription(description).build();
        List<Task> tasks = singletonList(task);
        List<TaskDTO> taskDTOS = singletonList(TaskDTO.createTaskDTO(title, description));
        when(taskRepository.getAllTasks()).thenReturn(tasks);
        when(taskMapper.map(tasks)).thenReturn(taskDTOS);

        taskService.createTask(taskDTO);

        verify(taskRepository, never()).save(task);
    }

    @Test
    void createTask_noDescription_happyPath() {
        String title = "title";
        TaskDTO taskDTO = TaskDTO.createTaskDTO(title, null);
        Task task = Task.TaskBuilder.createTask().withTitle(title).withDescription(null).build();
        when(taskMapper.map(taskDTO)).thenReturn(task);

        taskService.createTask(taskDTO);

        verify(taskRepository).save(task);
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
        when(taskRepository.getAllTasks()).thenReturn(tasks);

        taskService.getAllTasks();

        verify(taskRepository).getAllTasks();
        verify(taskMapper).map(tasks);
    }

    @Test
    void deleteTask_happyPath() {
        Task task = TaskTestBuilder.createTask().build();
        TaskDTO taskDTO = TaskDTO.createTaskDTO(task.getUuid(), "title", "description");

        taskService.deleteTask(taskDTO);

        verify(taskRepository).delete(task.getUuid());
    }

    @Test
    void markAsDone_happyPath() {
        Task task = TaskTestBuilder.createTask().build();
        TaskDTO taskDTO = TaskDTO.createTaskDTO(task.getUuid(), "title", "description");

        taskService.markAsDone(taskDTO);

        verify(taskRepository).markAsDone(task.getUuid());
    }

    @Test
    void markAsUndone_happyPath() {
        Task task = TaskTestBuilder.createTask().build();
        TaskDTO taskDTO = TaskDTO.createTaskDTO(task.getUuid(),"title", "description");

        taskService.markAsUndone(taskDTO);

        verify(taskRepository).markAsUndone(task.getUuid());
    }
}