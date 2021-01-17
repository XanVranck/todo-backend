package be.xanv.todo.api;

import java.time.LocalDate;
import java.util.Objects;

public class TaskEditDTO {
    private String title;
    private String description;
    private LocalDate executionDate;

    private TaskEditDTO(){}

    private TaskEditDTO(String title, String description, LocalDate executionDate) {
        this.title = title;
        this.description = description;
        this.executionDate = executionDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getExecutionDate() {
        return executionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEditDTO that = (TaskEditDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(executionDate, that.executionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, executionDate);
    }

    @Override
    public String toString() {
        return "TaskEditDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", executionDate=" + executionDate +
                '}';
    }

    public static class TaskEditDTOBuilder{
        private String title;
        private String description;
        private LocalDate executionDate;

        public static TaskEditDTOBuilder createTaskEditDTO(){
            return new TaskEditDTOBuilder();
        }

        public TaskEditDTO build() {
            return new TaskEditDTO(title, description, executionDate);
        }

        public TaskEditDTOBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskEditDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskEditDTOBuilder withExecutionDate(LocalDate executionDate) {
            this.executionDate = executionDate;
            return this;
        }
    }
}
