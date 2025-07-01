package org.rhydo.microecom.services;

import org.rhydo.microecom.models.User;

import java.util.List;

public interface UserService {
    List<User> fetchAllUsers();

    void addUser(User user);

    User fetchUser(Long id);

    boolean updateUser(Long id, User updateduser);
}
