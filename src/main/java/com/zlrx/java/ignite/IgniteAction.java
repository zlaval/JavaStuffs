package com.zlrx.java.ignite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class IgniteAction {

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    static class Person {
        String name;
        Integer age;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    static class Employee {
        Long id;
        String name;
        Boolean isEmployed;
    }

    public static void main(String[] args) {
        Ignite ignite = Ignition.start();
        IgniteCache<String, Person> cache = ignite.getOrCreateCache("test");
        cache.put("joedoe", new Person("JoeDoe", 30));
        cache.put("joedoe2", new Person("JoeDoe2", 30));
        cache.put("joedoe3", new Person("JoeDoe3", 30));

        System.out.println(cache.get("joedoe2"));

        Connection conn = null;
        Statement statement = null;
        try {
            Class.forName("org.apache.ignite.IgniteJdbcThinDriver");
            conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1/");
            statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE Employee (" +
                    " id LONG PRIMARY KEY, name VARCHAR, isEmployed tinyint(1)) " +
                    " WITH \"template=replicated\""
            );
            PreparedStatement sql = conn.prepareStatement(
                    "INSERT INTO Employee (id, name, isEmployed) VALUES (?, ?, ?)");

            sql.setLong(1, 1);
            sql.setString(2, "Bela");
            sql.setBoolean(3, true);
            sql.executeUpdate();
            sql.setLong(1, 2);
            sql.setString(2, "sanyesz");
            sql.setBoolean(3, false);
            sql.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {

                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ignite.close();
        System.out.println("End");
    }

}
