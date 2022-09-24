package cn.itcast.springboot.dao;

import cn.itcast.springboot.pojo.Teacher;

import java.util.List;

public interface TeacherDao {
    void save(Teacher t);

    void update(Teacher t);

    void deleteById(Long id);

    Teacher findById(Long id);

    List<Teacher> findAll();

    List<Teacher> findByName(String name);
}
