package be.xanv.todo.domain;

import javax.persistence.*;

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

        public TaskBuilder getDescription(String description) {
            this.description = description;
            return this;
        }
    }
}
