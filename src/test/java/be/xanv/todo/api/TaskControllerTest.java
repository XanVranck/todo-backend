package be.xanv.todo.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static be.xanv.todo.api.TaskDTO.TaskDTOBuilder.createTaskDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TaskControllerTest {

    @LocalServerPort
    private int port;

    private String BASE_URL;
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        BASE_URL = "http://localhost:" + port + "/task";
    }

    @Test
    void createTask() {
        ResponseEntity<HttpStatus> httpStatusResponseEntity = this.restTemplate.postForEntity(BASE_URL + "/create", createTaskDTO().withTitle("title").withDescription("description").build(), HttpStatus.class);
        assertThat(httpStatusResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void editTask() {
        this.restTemplate.postForEntity(BASE_URL + "/create", createTaskDTO().withTitle("title").withDescription("description").build(), HttpStatus.class);
        ResponseEntity<TaskDTO[]> response = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);
        this.restTemplate.postForEntity(BASE_URL + "/edit/" + response.getBody()[0].getUuid(), TaskEditDTO.createTaskEditDTO("edited title", "edited description"), HttpStatus.class);
        ResponseEntity<TaskDTO[]> actual = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);

        TaskDTO taskDTO = actual.getBody()[0];
        assertThat(taskDTO.getTitle()).isEqualTo("edited title");
        assertThat(taskDTO.getDescription()).isEqualTo("edited description");
    }

    @Test
    void getAllTasks() {
        this.restTemplate.postForEntity(BASE_URL + "/create", createTaskDTO().withTitle("title").withDescription("description").build(), HttpStatus.class);
        ResponseEntity<TaskDTO[]> response =
                restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    void deleteTask() {
        this.restTemplate.postForEntity(BASE_URL + "/create", createTaskDTO().withTitle("title").withDescription("description").build(), HttpStatus.class);
        ResponseEntity<TaskDTO[]> response = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);
        String uuid = response.getBody()[0].getUuid();
        this.restTemplate.delete(BASE_URL + "/delete/" + uuid);
        assertThat(restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class).getBody()).hasSize(0);
    }

    @Test
    void markTaskAsUndone() {
        this.restTemplate.postForEntity(BASE_URL + "/create", createTaskDTO().withTitle("title").withDone(true).withDescription("description").build(), HttpStatus.class);
        ResponseEntity<TaskDTO[]> response = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);
        String uuid = response.getBody()[0].getUuid();
        this.restTemplate.postForEntity(BASE_URL + "/mark-as-undone/" + uuid, null, HttpStatus.class);
        ResponseEntity<TaskDTO[]> responseAfterMarkAsDone = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);

        assertFalse(responseAfterMarkAsDone.getBody()[0].isDone());
    }

    @Test
    void markTaskAsDone() {
        this.restTemplate.postForEntity(BASE_URL + "/create", createTaskDTO().withTitle("title").withDescription("description").build(), HttpStatus.class);
        ResponseEntity<TaskDTO[]> response = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);
        String uuid = response.getBody()[0].getUuid();
        this.restTemplate.postForEntity(BASE_URL + "/mark-as-done/" + uuid, null, HttpStatus.class);
        ResponseEntity<TaskDTO[]> responseAfterMarkAsDone = restTemplate.getForEntity(BASE_URL + "/tasks", TaskDTO[].class);

        assertTrue(responseAfterMarkAsDone.getBody()[0].isDone());
    }

}