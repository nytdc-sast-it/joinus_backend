package org.tdsast.joinus.service;

import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.repository.UserRepository;
import org.tdsast.joinus.utils.AuthUtils;

import javax.inject.Inject;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User addUser(String username, String password, Club club) {
        if (isUserExist(username)) {
            throw new IllegalArgumentException("User " + username + " already exist");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(AuthUtils.getEnPassword(username, password));
        user.setClub(club);
        return userRepository.save(user);
    }
}
