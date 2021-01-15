package be.xanv.todo.domain;

public class TaskTestBuilder {
    private String title = "Title";
    private String description = "Description";

    public static TaskTestBuilder createTask() {
        return new TaskTestBuilder();
    }

    public Task build() {
        return Task.TaskBuilder.createTask().withTitle(title)
                .withDescription(description)
                .build();
    }

    public TaskTestBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TaskTestBuilder withDescription(String description) {
        this.description = description;
        return this;
    }
}