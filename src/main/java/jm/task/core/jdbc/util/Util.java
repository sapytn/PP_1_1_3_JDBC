package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
  private static final String URL = "jdbc:mysql://127.0.0.1:3306/jdbctest";
  private static  final String USER = "root";
  private static  final String PASSWORD = "Lbhjkmxbr98.";
  

  public static Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  // реализуйте настройку соеденения с БД
}
