package be.xanv.todo.api;

import java.time.LocalDate;
import java.util.Objects;

public class TaskDTO {
    private String uuid;
    private String title;
    private String description;
    private boolean done;
    private LocalDate executionDate;

    private TaskDTO(){}

    private TaskDTO(String uuid, String title, String description, boolean done, LocalDate executionDate) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.done = done;
        this.executionDate = executionDate;
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

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return done == taskDTO.done &&
                Objects.equals(uuid, taskDTO.uuid) &&
                Objects.equals(title, taskDTO.title) &&
                Objects.equals(description, taskDTO.description) &&
                Objects.equals(executionDate, taskDTO.executionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, title, description, done, executionDate);
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", executionDate=" + executionDate +
                '}';
    }

    public static class TaskDTOBuilder{
        private String uuid;
        private String title;
        private String description;
        private boolean done;
        private LocalDate executionDate;

        public static TaskDTOBuilder createTaskDTO(){
            return new TaskDTOBuilder();
        }

        public TaskDTO build() {
            return new TaskDTO(uuid, title, description, done, executionDate);
        }

        public TaskDTOBuilder withUuid(String uuid) {
            this.uuid = uuid;
            return this;
        }

        public TaskDTOBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskDTOBuilder withDone(boolean done) {
            this.done = done;
            return this;
        }

        public TaskDTOBuilder withExecutionDate(LocalDate executionDate) {
            this.executionDate = executionDate;
            return this;
        }
    }
}
