package be.xanv.todo.api;

public class TaskDTO {
    private String title;
    private String description;

    private TaskDTO(){}

    private TaskDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static TaskDTO createTaskDTO(String title, String description) {
        return new TaskDTO(title, description);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
