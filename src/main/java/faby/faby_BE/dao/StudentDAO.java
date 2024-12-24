package faby.faby_BE.dao;


import faby.faby_BE.entity.Student;

public interface StudentDAO {

    void save(Student theStudent);

    Student findById(Integer id);

}
