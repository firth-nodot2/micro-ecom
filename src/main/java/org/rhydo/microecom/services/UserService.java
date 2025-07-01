package org.rhydo.microecom.services;

import org.rhydo.microecom.dtos.UserRequest;
import org.rhydo.microecom.dtos.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> fetchAllUsers();

    void addUser(UserRequest userRequest);

    UserResponse fetchUser(Long id);

    boolean updateUser(Long id, UserRequest updateduser);
}
