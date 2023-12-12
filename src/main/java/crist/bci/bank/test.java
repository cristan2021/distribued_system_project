package crist.bci.bank;

import java.sql.*;

public class test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "moud";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM test");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");

                System.out.println("ID: " + id + ", Nom: " + nom + ", Prenom: " + prenom);
            }
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
    }

    

}