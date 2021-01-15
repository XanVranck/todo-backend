package be.xanv.todo.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    private Task(){}

    private Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }

    public static class TaskBuilder {
        private String title;
        private String description;

        public static TaskBuilder createTask() {
            return new TaskBuilder();
        }

        public Task build(){
            return new Task(title, description);
        }

        public TaskBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder withDescription(String description) {
            this.description = description;
            return this;
        }
    }
}
