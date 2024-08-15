package org.example.springmybatis;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;


@Service
public class UserService {

    @Autowired
    @Setter
    private UserMapper userMapper;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void run() {
        System.out.println("ok");
        new Thread(() -> {
            doHandle2();
        }).start();
    }

    private void doHandle() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            for (int i = 0; i < 100; i++) {
                try {
                    String sql = "insert into users (id, name, pwd)\n" +
                            "        values (?, ?, ?)";
                    preparedStatement = connection.prepareStatement(sql);
                    User user = new User(i, "mike" + i, "123#" + i);
                    preparedStatement.setInt(1, user.getId());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getPwd());
                    preparedStatement.executeUpdate();
                    System.out.println(user.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // userMapper.addUser(user);
                sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            release(connection, preparedStatement, null);
        }
    }

    private void doHandle2() {
        try {
            for (int i = 0; i < 100; i++) {
                try {
                    User user = new User(i, "mike" + i, "123#" + i);
                    userMapper.addUser(user);
                    System.out.println(user.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                sleep(10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 释放资源
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    private void sleep(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
