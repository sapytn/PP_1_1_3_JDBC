package jm.task.core.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import jm.task.core.jdbc.model.User;

import java.util.List;
import jm.task.core.jdbc.util.Util;

public class UserDaoJDBCImpl implements UserDao {

  private final Connection connection = Util.getConnection();

  public UserDaoJDBCImpl() {

  }

  public void createUsersTable() {
    String query = "CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT, first_name TINYTEXT, last_name TINYTEXT, age TINYINT UNSIGNED)";
    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();
      statement.execute(query);
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public void dropUsersTable() {
    String query = "DROP TABLE IF EXISTS user";
    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();
      statement.execute(query);
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public void saveUser(String name, String lastName, byte age) {
    String query = "INSERT INTO user (first_name, last_name , age) VALUES (?, ?, ?)";
    try {
      connection.setAutoCommit(false);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setString(1, name);
      preparedStatement.setString(2, lastName);
      preparedStatement.setByte(3, age);
      preparedStatement.executeUpdate();
      connection.commit();
      connection.setAutoCommit(true);
      System.out.println("User с именем – " + name + " добавлен в базу данных");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public void removeUserById(long id) {
    String query = "DELETE FROM user WHERE id = ?";
    try {
      connection.setAutoCommit(false);
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setLong(1, id);
      preparedStatement.executeUpdate();
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }

  public List<User> getAllUsers() {
    List<User> list = new ArrayList<>();
    String query = "SELECT * FROM user";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);

      while (resultSet.next()) {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setAge(resultSet.getByte("age"));
        list.add(user);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return list;
  }

  public void cleanUsersTable() {
    String query = "TRUNCATE TABLE user";
    try {
      connection.setAutoCommit(false);
      Statement statement = connection.createStatement();
      statement.execute(query);
      connection.commit();
      connection.setAutoCommit(true);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
