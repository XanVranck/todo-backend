package be.xanv.todo.api;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return Objects.equals(title, taskDTO.title) &&
                Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
