package m4final.maslennikov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class GenericWorldDao<T> implements WorldDAO<T> {

    private final Class<T> clazz;

    private SessionFactory sessionFactory;

    public GenericWorldDao(Class<T> clazz, SessionFactory sessionFactory) {
        this.clazz = clazz;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<T> getAll() {
        String hql = "from " + clazz.getName();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hql, clazz)
                    .list();
        }
    }

    @Override
    public List<T> getItems(int offset, int count) {
        String hql = "from " + clazz.getName();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hql, clazz)
                    .setFirstResult(offset)
                    .setMaxResults(count)
                    .list();
        }
    }

    @Override
    public T getById(final int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(clazz, id);
        }
    }

    @Override
    public void create(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void update(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteById(final int entityId) {
        delete(getById(entityId));
    }

    @Override
    public int getTotalCount() {
        String hql = " select count(c) from " + clazz.getName() + " c";
        try (Session session = sessionFactory.openSession()) {
            return Math.toIntExact(
                    session.createQuery(hql, Long.class)
                            .getSingleResult());
        }
    }

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
