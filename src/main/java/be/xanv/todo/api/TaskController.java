package be.xanv.todo.api;

import be.xanv.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createTask(@RequestBody TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
    }

    @GetMapping
    public @ResponseBody List<TaskDTO> getAllTasks(){
        return taskService.getAllTasks();
    }
}
