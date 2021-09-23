package ru.job4j.store;

import ru.job4j.model.Ad;
import ru.job4j.model.User;

import java.util.List;

public interface AdStore {
    void addAd(String desc, int brand, int body, String photo, User user);
    void removeAd(int id);
    List<Ad> getAllAds();
}
