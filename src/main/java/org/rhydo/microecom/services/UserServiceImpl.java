package org.rhydo.microecom.services;

import lombok.RequiredArgsConstructor;
import org.rhydo.microecom.repositories.UserRepository;
import org.rhydo.microecom.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User fetchUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateUser(Long id, User updateduser) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateduser.getFirstName());
                    existingUser.setLastName(updateduser.getLastName());
                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);
    }
}
