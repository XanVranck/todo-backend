package be.xanv.todo.api;

import java.util.Objects;

public class TaskDTO {
    private String uuid;
    private String title;
    private String description;
    private boolean done;

    private TaskDTO(){}

    private TaskDTO(String uuid, String title, String description, boolean done) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.done = done;
    }

    public static TaskDTO createTaskDTO(String uuid, String title, String description, boolean done) {
        return new TaskDTO(uuid, title, description, done);
    }

    public static TaskDTO createTaskDTO(String title, String description, boolean done) {
        return new TaskDTO(null, title, description, done);
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

    public boolean isDone() {
        return done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return done == taskDTO.done &&
                Objects.equals(uuid, taskDTO.uuid) &&
                Objects.equals(title, taskDTO.title) &&
                Objects.equals(description, taskDTO.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, description, done);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }
}
