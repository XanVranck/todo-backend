package be.xanv.todo.api;

import be.xanv.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
@RequestMapping(path = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping(path = "/create")
    @ResponseStatus(HttpStatus.OK)
    public void createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
    }

    @PostMapping(path = "/edit")
    @ResponseStatus(HttpStatus.OK)
    public void editTask(@RequestBody TaskDTO taskDTO) {
        taskService.editTask(taskDTO);
    }

    @GetMapping(path = "/tasks")
    public @ResponseBody List<TaskDTO> getAllTasks(){
        return taskService.getAllTasks();
    }

    @DeleteMapping(path = "/delete/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@PathVariable String uuid) {
        taskService.deleteTask(uuid);
    }

    @PostMapping(path = "/mark-as-done/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void markAsDone(@PathVariable String uuid) {
        taskService.markAsDone(uuid);
    }

    @PostMapping(path = "/mark-as-undone/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void markAsUndone(@PathVariable String uuid) {
        taskService.markAsUndone(uuid);
    }
}
