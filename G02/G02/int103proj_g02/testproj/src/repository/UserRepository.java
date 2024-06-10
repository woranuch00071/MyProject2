package repository;

import domain.User;

public interface UserRepository {
    void addUser(User user);

    User getUser(String username);
}
