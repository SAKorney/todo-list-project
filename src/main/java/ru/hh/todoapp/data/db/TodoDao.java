package ru.hh.todoapp.data.db;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.hh.todoapp.data.db.utils.CommonDao;
import ru.hh.todoapp.data.db.utils.TransactionHelper;

import java.util.List;
import java.util.Optional;

@Component
public class TodoDao extends CommonDao {

    @Inject
    public TodoDao(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        super(sessionFactory, transactionHelper);
    }

    public List<TodoTask> getAll() {
        return transactionHelper.inTransaction(() ->
                getSession().createQuery(
                        "SELECT t FROM TodoTask t ORDER BY t.id",
                        TodoTask.class
                ).getResultList());
    }

    public List<TodoTask> getByStatus(boolean status) {
        return transactionHelper.inTransaction(() ->
                getSession().createQuery("""
                                 SELECT t FROM TodoTask t
                                 WHERE t.completed = :completed
                                 ORDER BY t.id
                                """,
                                TodoTask.class)
                        .setParameter("completed", status)
                        .getResultList());
    }

    public Optional<TodoTask> getById(Long id) {
        return Optional.ofNullable(get(TodoTask.class, id));
    }

    public void add(TodoTask task) {
        saveEntity(task);
    }

    public void update(TodoTask task) {
        updateEntity(task);
    }
}
