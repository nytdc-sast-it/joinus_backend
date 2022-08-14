package com.sastit.joinus.service;

import com.sastit.joinus.model.entity.Club;
import com.sastit.joinus.model.entity.Department;
import com.sastit.joinus.model.entity.User;
import com.sastit.joinus.repository.UserRepository;
import com.sastit.joinus.utils.AuthUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Cacheable(value = "user", key = "{#root.methodName, #username}")
    public boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public User addUser(String username, String password, Club club, Department department, Boolean admin) {
        if (isUserExist(username)) {
            throw new IllegalArgumentException("User " + username + " already exist");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(AuthUtils.getEnPassword(username, password));
        user.setClub(club);
        user.setDepartment(department);
        user.setIsAdmin(admin);
        user.setPwdNeedModified(true);
        return userRepository.save(user);
    }

    public void removeUser(Long userId) {
        User user = getUserById(userId);
        if (user == null) {
            throw new IllegalArgumentException("User " + userId + " not exist");
        }
        if (Boolean.TRUE.equals(user.getIsAdmin())) {
            throw new IllegalArgumentException("Admin user " + userId + " cannot be removed");
        }
        userRepository.deleteById(userId);
    }
}
