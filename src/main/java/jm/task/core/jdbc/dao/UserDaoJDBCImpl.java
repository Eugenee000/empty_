package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS users_table" +
                    "(ID BIGINT NOT NULL AUTO_INCREMENT," +
                    " NAME VARCHAR(45) NOT NULL ," +
                    " LAST_NAME VARCHAR(45) NOT NULL, " +
                    " AGE TINYINT NOT NULL," +
                    "PRIMARY KEY (ID))");
            preparedStatement.executeUpdate();
            System.out.println("Таблица создана.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS users_table");
            System.out.println("Таблица удалена.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users_table" +
                    "(NAME, LAST_NAME, AGE)" +
                    "VALUES (?,?,?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection connection = null;
        User user = new User();
        user.setId(id);
        try {
            connection = Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users_table WHERE ID = ?");
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            System.out.println("Пользователь удален.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users_table");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("ID"));
                user.setName(resultSet.getString("NAME"));
                user.setLastName(resultSet.getString("LAST_NAME"));
                user.setAge((byte) resultSet.getInt("AGE"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE users_table");
            System.out.println("Содеражание таблицы очищенно.");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
