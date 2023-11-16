package com.example.test123;
import java.nio.channels.ConnectionPendingException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaPostgreSql {

    public static void writeToDatabase(String username, String userEmail, String userPassword){
        String url = "jdbc:postgresql://localhost:5432/tododb";
        String user = ""; // here we put the username of the database
        String password = ""; // here we put the password to the database

        String name = username;
        String Email = userEmail;
        String pass = userPassword;




        String query1 = "INSERT into worker(name, \"Email\", password) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url,user,password);
             PreparedStatement pst = con.prepareStatement(query1)){
            pst.setString(1,name);
            pst.setString(2,Email);
            pst.setString(3,pass);
            pst.executeUpdate();
            System.out.println("Successfully Created");


        }catch (SQLException e){
            Logger lgr = Logger.getLogger(JavaPostgreSql.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);

        }
    }

    public static boolean getFromDatabase(String username,String password){

        String url = "jdbc:postgresql://localhost:5432/userInformation";
        String user = ""; // here we put the username of the database
        String pass = ""; // here we put the password to the database

        String name = username;
        String userPassword = password;

        String query = "INSERT into worker(name, \"Email\", password) VALUES(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url,user,pass);
             Statement stat = con.createStatement();

             ResultSet resultSet = stat.executeQuery("SELECT * FORM userInformation")){
            String getName = "name";
            String userPass = "password";
            while (resultSet.next()){
                if (resultSet.getString(getName).equals(name) && resultSet.getString(userPass).equals(userPassword)){
                    return true;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }
}
