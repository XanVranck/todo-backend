package be.xanv.todo.api;

import java.util.Objects;

public class TaskEditDTO {
    private String title;
    private String description;

    private TaskEditDTO(){}

    private TaskEditDTO(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static TaskEditDTO createTaskEditDTO(String title, String description) {
        return new TaskEditDTO(title, description);
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
        TaskEditDTO taskDTO = (TaskEditDTO) o;
        return Objects.equals(title, taskDTO.title) &&
                Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
