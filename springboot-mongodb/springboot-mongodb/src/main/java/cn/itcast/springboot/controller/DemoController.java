package cn.itcast.springboot.controller;


import cn.itcast.springboot.dao.TeacherDao;
import cn.itcast.springboot.dao.UserDao;
import cn.itcast.springboot.pojo.Teacher;
import cn.itcast.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: hekuangsheng
 * @Date: 2018/11/16
 */
@RestController
@RequestMapping("/mongo")
public class DemoController {

    @Autowired
    UserDao userDao;
    @Autowired
    TeacherDao teacherDao;

    @Transactional
    @RequestMapping("/add")
    public String add(String name){
        try{
            User user = new User(133L, "学生", 12, "男", "北京");
            userDao.save(user);

            Teacher teacher = new Teacher(133L, "学生", 12, "男", "北京");
            teacherDao.save(teacher);

            if (1 == 1) {
                throw new RuntimeException("异常啦");
            }
        }
        catch (Exception ex){
            throw ex;
        }
        return "success";
    }

    @RequestMapping("get")
    public User get(Long id){
        return userDao.findById(id);
    }
}