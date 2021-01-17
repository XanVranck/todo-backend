package be.xanv.todo.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "TASK")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "UUID", updatable = false, nullable = false)
    private String uuid;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "DONE")
    private boolean done;

    private Task(){}

    private Task(String title, String description, boolean done) {
        this.uuid = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.done = done;
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
        Task task = (Task) o;
        return done == task.done &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, done);
    }

    @Override
    public String toString() {
        return "Task{" +
                "uuid='" + uuid + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                '}';
    }

    public static class TaskBuilder {
        private String title;
        private String description;
        private boolean done;

        public static TaskBuilder createTask() {
            return new TaskBuilder();
        }

        public Task build(){
            return new Task(title, description, done);
        }

        public TaskBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public TaskBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder withDone(boolean done) {
            this.done = done;
            return this;
        }
    }
}
