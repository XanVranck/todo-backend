package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;

public interface TaskRepository {

    void save(Task task);

}
