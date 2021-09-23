package ru.job4j.store;

import ru.job4j.model.User;

public interface UserStore {
    void saveUser(User user);
    User getUser(String login);
}
