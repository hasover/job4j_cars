package ru.job4j.store;

import ru.job4j.model.User;

public class HbmUserStore extends HbmStore implements UserStore {

    private HbmUserStore() {}

    public static HbmUserStore instOf() {
        return Lazy.INST;
    }

    private static final class Lazy {
        private static final HbmUserStore INST = new HbmUserStore();
    }

    @Override
    public void saveUser(User user) {
        saveEntity(user);
    }

    @Override
    public User getUser(String login) {
        return this.tx(session -> session.createQuery("select u from User u where u.login =: l", User.class)
        .setParameter("l", login).uniqueResult());
    }
}
