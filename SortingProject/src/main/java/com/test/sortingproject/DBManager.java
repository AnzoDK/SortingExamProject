/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.test.sortingproject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author anzo
 */
public class DBManager
    {
        String connPath = "jdbc:sqlite:sql.db";
        Connection conn = null;
        public boolean Login(String username, String password) throws NoSuchAlgorithmException
        {
            //TODO
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = /*md.digest(password.getBytes(StandardCharsets.UTF_8));*/ md.digest();
            String hash = new String(bytes,StandardCharsets.UTF_8);
            String getUsernameSql = "SELECT (username, password) FROM Users WHERE username='" + username + "'";
            try
            {
                conn = DriverManager.getConnection(connPath);
                Statement stmt = null;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(getUsernameSql);
                int c = 0;
                while (rs.next() && c == 0) 
                {
                    String loadedPassword = rs.getString("password");
                    if(loadedPassword == hash)
                    {
                        //Success
                        Holder.INSTANCE.username = username;
                        Holder.INSTANCE.passhash = loadedPassword;
                        return true;
                    }
                    c++;
                }
                return false;
                
            }
            catch(SQLException e)
            {
                System.out.println("SQL Error: " + e.getMessage());
            }
            
            
            
            return false;
        }
    }
