package org.tdsast.joinus.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.tdsast.joinus.model.entity.Club;
import org.tdsast.joinus.model.entity.Department;
import org.tdsast.joinus.model.entity.User;
import org.tdsast.joinus.repository.UserRepository;
import org.tdsast.joinus.utils.AuthUtils;

@Service
public class UserService {
    private final UserRepository userRepository;

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

    public User addUser(String username, String password, Club club, Department department) {
        if (isUserExist(username)) {
            throw new IllegalArgumentException("User " + username + " already exist");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(AuthUtils.getEnPsssword(username, password));
        user.setClub(club);
        user.setDepartment(department);
        return userRepository.save(user);
    }
}
