package be.xanv.todo.api;

import java.util.Objects;

public class TaskDTO {
    private String uuid;
    private String title;
    private String description;

    private TaskDTO(){}

    private TaskDTO(String uuid, String title, String description) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
    }

    public static TaskDTO createTaskDTO(String uuid, String title, String description) {
        return new TaskDTO(uuid, title, description);
    }

    public static TaskDTO createTaskDTO(String title, String description) {
        return new TaskDTO(null, title, description);
    }


    public String getUuid() {
        return uuid;
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
        return Objects.equals(uuid, taskDTO.uuid) &&
                Objects.equals(title, taskDTO.title) &&
                Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, description);
    }
}
