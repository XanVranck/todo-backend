package be.xanv.todo.repository;

import be.xanv.todo.domain.Task;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryImpl implements TaskRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Task task) {
        entityManager.persist(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return entityManager.createQuery("select t from be.xanv.todo.domain.Task t", Task.class).getResultList();
    }

    @Override
    public void delete(Task task) {
        entityManager.remove(task);
    }

    @Override
    public Task findByTitleAndDescription(String title, String description) {
        return entityManager.createQuery("select t from be.xanv.todo.domain.Task t " +
                "where t.title = :title" +
                " and t.description = :description", Task.class)
                .setParameter("title", title)
                .setParameter("description", description)
                .getSingleResult();
    }

    @Override
    public void markAsDone(Task task) {
        entityManager.createQuery("update be.xanv.todo.domain.Task t " +
                "set t.done = 1 " +
                "where t.id = :id")
                .setParameter("id", task.getId())
                .executeUpdate();
    }

    @Override
    public void markAsUndone(Task task) {
        entityManager.createQuery("update be.xanv.todo.domain.Task t " +
                "set t.done = 0 " +
                "where t.id = :id")
                .setParameter("id", task.getId())
                .executeUpdate();
    }
}
