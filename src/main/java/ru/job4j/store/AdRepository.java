package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.model.Ad;
import ru.job4j.model.CarBrand;
import java.util.List;
import java.util.function.Function;

public class AdRepository {

    private  final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private  final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private<T> T  tx(Function<Session, T> command) {
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

    public List<Ad> getAdsWithPhoto() {
        return tx(session -> session.createQuery("from Ad where photo is not null ").list());
    }

    public List<Ad> getAdsCreatedToday() {
        return tx(session -> session
                .createQuery("from Ad where DATE(created)= current_date")
                .list());
    }

    public List<Ad> getAdsByCarBrand(CarBrand carBrand) {
        return tx(session -> session
                .createQuery("select distinct a from Ad a " +
                        "join fetch a.carBrand cb " +
                        "where cb = :param")
                .setParameter("param", carBrand)
                .list());
    }
}
