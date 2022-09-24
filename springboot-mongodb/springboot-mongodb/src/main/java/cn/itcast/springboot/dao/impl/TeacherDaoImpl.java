package cn.itcast.springboot.dao.impl;

import cn.itcast.springboot.dao.TeacherDao;
import cn.itcast.springboot.pojo.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class TeacherDaoImpl implements TeacherDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(Teacher t) {
        mongoTemplate.save(t);
    }

    @Override
    public void update(Teacher t) {
        //创建更新条件
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(t.getId());
        query.addCriteria(criteria);

        //创建更新对象
        Update update = new Update();
        update.set("name", t.getName());
        update.set("age", t.getAge());
        update.set("gender", t.getGender());
        update.set("address", t.getAddress());

        //更新所有符合条件的记录
        mongoTemplate.updateMulti(query, update, Teacher.class);
    }

    @Override
    public void deleteById(Long id) {
        //创建删除条件
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        mongoTemplate.remove(query, Teacher.class);
    }

    @Override
    public Teacher findById(Long id) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").is(id);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, Teacher.class);
    }

    @Override
    public List<Teacher> findAll() {
        return mongoTemplate.findAll(Teacher.class);
    }

    @Override
    public List<Teacher> findByName(String name) {
        Query query = new Query();
        //模糊查询，不区分大小写
        Pattern pattern = Pattern.compile("^.*" + name + ".*$", Pattern.CASE_INSENSITIVE);
        Criteria criteria = Criteria.where("name").regex(pattern);
        query.addCriteria(criteria);
        return mongoTemplate.find(query, Teacher.class);
    }
}
