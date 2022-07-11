package org.tdsast.joinus.service;

import org.springframework.stereotype.Service;
import org.tdsast.joinus.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
