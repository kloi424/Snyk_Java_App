package com.example;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:sqlite::memory:";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT, password TEXT)");
            stmt.execute("INSERT INTO users (name, password) VALUES ('admin', 'admin123')");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter username: ");
            String inputName = scanner.nextLine();

            // ❌ Vulnerable to SQL Injection
            String sql = "SELECT * FROM users WHERE name = '" + inputName + "'";

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("User found: " + rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
