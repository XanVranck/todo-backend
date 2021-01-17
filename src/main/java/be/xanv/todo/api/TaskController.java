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

    @GetMapping(path = "/tasks")
    public @ResponseBody List<TaskDTO> getAllTasks(){
        return taskService.getAllTasks();
    }

    @DeleteMapping(path = "/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTask(@RequestBody TaskDTO taskDTO) {
        taskService.deleteTask(taskDTO);
    }

    @PostMapping(path = "/mark-as-done")
    @ResponseStatus(HttpStatus.OK)
    public void markAsDone(@RequestBody TaskDTO taskDTO) {
        taskService.markAsDone(taskDTO);
    }

    @PostMapping(path = "/mark-as-undone")
    @ResponseStatus(HttpStatus.OK)
    public void markAsUndone(@RequestBody TaskDTO taskDTO) {
        taskService.markAsUndone(taskDTO);
    }
}
