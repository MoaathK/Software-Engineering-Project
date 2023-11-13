package com.example.test123;
import java.nio.channels.ConnectionPendingException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;

public class JavaPostgreSql {

    public static void writeToDatabase(String username, String userEmail, String userPassword){
        String url = "jdbc:postgresql://localhost:5432/tododb";
        String user = "postgres";
        String password = "m=0552564107";

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
}
