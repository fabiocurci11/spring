package faby.faby_BE.dao;

import faby.faby_BE.entity.Student;
import faby.faby_BE.entity.User;

public interface UserDAO {

    void save(User user);

    User findById(Integer id);

}
