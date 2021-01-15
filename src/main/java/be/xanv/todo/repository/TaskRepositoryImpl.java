package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return entityManager.createQuery("select t from be.xanv.todo.domain.Task t", Task.class).getResultList();
    }
}
