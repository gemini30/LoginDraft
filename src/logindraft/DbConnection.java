/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package logindraft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DbConnection {

    /**
     * @param args the command line arguments
     */
    
    Connection conn;
    
    DbConnection() {
        // MySQL database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project?" + "user=root&password=Jinish@123");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
        public boolean login(String useremail, String password) {
        try {

            PreparedStatement pst = conn.prepareStatement("Select * from user where user_email=? and user_password=?");
            pst.setString(1, useremail);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        
        
     public boolean signup(String username ,String useremail , String password) {
        try {
            PreparedStatement pst = conn.prepareStatement("insert into user(user_name,user_email,user_password)values(?,?,?)");
            pst.setString(1, username);
            pst.setString(2,useremail);
            pst.setString(3, password);
            int rs = pst.executeUpdate();
            if (rs != 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
