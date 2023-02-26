package ru.hh.todoapp.data.db;

import jakarta.inject.Inject;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;
import ru.hh.todoapp.data.Status;
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
        return transactionHelper.inTransaction(() -> {
            return getSession().createQuery( """
                             SELECT t FROM TodoTask t
                            """, TodoTask.class
            ).getResultList();
        });
    }

    public List<TodoTask> getByStatus(Status status) {
        boolean isCompleted = status == Status.COMPLETED;
        return transactionHelper.inTransaction(() -> {
            return getSession().createQuery( """
                                     SELECT t FROM TodoTask t
                                     WHERE t.completed = :completed
                                    """, TodoTask.class)
                    .setParameter("completed", isCompleted)
                    .getResultList();
        });
    }

    public Optional<TodoTask> getById(Long id) {
        return Optional.ofNullable(get(TodoTask.class, id));
    }

    public void add(TodoTask task) {
        save(task);
    }

    public void update(TodoTask task) {
        update(task);
    }
}
