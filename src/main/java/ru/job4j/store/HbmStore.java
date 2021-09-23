package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;
import java.util.function.Function;

public abstract class HbmStore implements AutoCloseable {
    private  final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private  final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    protected <T> T  tx(Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            throw e;
        } finally {
            session.close();
        }
    }

    public <T> List<T> getEntityList(Class<T> tClass) {
        return tx(session -> session.createQuery("from " + tClass.getName()).list());
    }

    public<T> void saveEntity(T entity) {
        tx(session -> session.save(entity));
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
