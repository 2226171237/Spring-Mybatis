package org.example.springmybatis;

import jakarta.annotation.Resource;
import lombok.Setter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {


    @Autowired
    @Setter
    private SqlSessionTemplate sqlSession;

    @Override
    public List<User> getUserList() {
        return sqlSession.getMapper(UserMapper.class).getUserList();
    }

    @Override
    public User getUserById(int id) {
        return sqlSession.getMapper(UserMapper.class).getUserById(id);
    }

    @Override
    public int addUser(User user) {
        return sqlSession.getMapper(UserMapper.class).addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return sqlSession.getMapper(UserMapper.class).updateUser(user);
    }

    @Override
    public int deleteUser(int id) {
        return sqlSession.getMapper(UserMapper.class).deleteUser(id);
    }
}
