package be.xanv.todo.repository;

import be.xanv.todo.api.TaskEditDTO;
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
    public void delete(String uuid) {
        entityManager.createQuery("delete from be.xanv.todo.domain.Task t " +
                "where t.uuid = :uuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }

    @Override
    public void markAsDone(String uuid) {
        entityManager.createQuery("update be.xanv.todo.domain.Task t " +
                "set t.done = 1 " +
                "where t.uuid = :uuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }

    @Override
    public void markAsUndone(String uuid) {
        entityManager.createQuery("update be.xanv.todo.domain.Task t " +
                "set t.done = 0 " +
                "where t.uuid = :uuid")
                .setParameter("uuid", uuid)
                .executeUpdate();
    }

    @Override
    public void edit(String uuid, TaskEditDTO taskEditDTO) {
        entityManager.createQuery("update be.xanv.todo.domain.Task t " +
                "set t.title = :title, " +
                "t.description = :description " +
                "where t.uuid = :uuid")
                .setParameter("title", taskEditDTO.getTitle())
                .setParameter("description", taskEditDTO.getDescription())
                .setParameter("uuid", uuid)
                .executeUpdate();
    }
}
