package ru.job4j.store;

import ru.job4j.model.*;

import java.util.List;

public class HbmAdStore extends HbmStore implements AdStore {

    private HbmAdStore() {}

    public static HbmAdStore instOf() {
        return Lazy.INST;
    }

    private static final class Lazy {
        private static final HbmAdStore INST = new HbmAdStore();
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

    @Override
    public void addAd(String desc, int brand, int body, String photo, User user) {
        tx(session -> {
            CarBrand carBrand = session.get(CarBrand.class, brand);
            CarBody carBody = session.get(CarBody.class, body);
            Ad ad = new Ad(desc, carBrand, carBody, photo, user);
            session.save(ad);
            return null;
        });
    }

    @Override
    public void removeAd(int id) {
        tx(session -> {
            Ad ad = session.get(Ad.class, id);
            ad.setSold(true);
            return null;
        });
    }
    @Override
    public List<Ad> getAllAds() {
        return tx(session -> session.createQuery("select distinct a from Ad a " +
                " left join fetch a.carBrand " +
                " left join fetch a.carBody " +
                " join fetch a.user order by a.created desc").list());
    }
}
