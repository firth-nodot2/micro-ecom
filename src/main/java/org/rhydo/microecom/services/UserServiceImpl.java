package org.rhydo.microecom.services;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.rhydo.microecom.dtos.UserRequest;
import org.rhydo.microecom.dtos.UserResponse;
import org.rhydo.microecom.models.Address;
import org.rhydo.microecom.repositories.UserRepository;
import org.rhydo.microecom.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .toList();
    }

    @Override
    public void addUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        userRepository.save(user);
    }

    @Override
    public UserResponse fetchUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public boolean updateUser(Long id, UserRequest updateduserRequest) {
        User updateduser = modelMapper.map(updateduserRequest, User.class);

        return userRepository.findById(id)
                .map(existingUser -> {
                    existingUser.setFirstName(updateduser.getFirstName());
                    existingUser.setLastName(updateduser.getLastName());
                    existingUser.setEmail(updateduser.getEmail());
                    existingUser.setPhone(updateduser.getPhone());

                    if (updateduserRequest.getAddress() != null) {
                        Address existingAddress = existingUser.getAddress();

                        if (existingAddress == null) {
                            existingAddress = new Address();
                            existingUser.setAddress(existingAddress);
                        }

                        existingAddress.setStreet(updateduserRequest.getAddress().getStreet());
                        existingAddress.setCity(updateduserRequest.getAddress().getCity());
                        existingAddress.setState(updateduserRequest.getAddress().getState());
                        existingAddress.setCountry(updateduserRequest.getAddress().getCountry());
                        existingAddress.setZipcode(updateduserRequest.getAddress().getZipcode());
                    }

                    userRepository.save(existingUser);
                    return true;
                })
                .orElse(false);
    }
}
