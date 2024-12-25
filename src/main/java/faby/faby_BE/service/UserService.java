package faby.faby_BE.service;

import faby.faby_BE.dao.StudentDAO;
import faby.faby_BE.dao.UserDAO;
import faby.faby_BE.entity.Student;
import faby.faby_BE.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
        return user;
    }
}
