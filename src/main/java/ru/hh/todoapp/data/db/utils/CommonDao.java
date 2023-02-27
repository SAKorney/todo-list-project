package ru.hh.todoapp.data.db.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

public class CommonDao {
    protected final SessionFactory sessionFactory;
    protected final TransactionHelper transactionHelper;

    public CommonDao(SessionFactory sessionFactory, TransactionHelper transactionHelper) {
        this.sessionFactory = sessionFactory;
        this.transactionHelper = transactionHelper;
    }

    public void saveEntity(Object object) {
        if (object == null)  return;

        transactionHelper.inTransaction(() -> {
            getSession().save(object);
        });
    }

    public void updateEntity(Object object) {
        if (object == null) return;
        transactionHelper.inTransaction(() -> {
            getSession().update(object);
        });
    }

    public <T> T get(Class<T> clazz, Serializable id) {
        return transactionHelper.inTransaction(() -> getSession().get(clazz, id));
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
