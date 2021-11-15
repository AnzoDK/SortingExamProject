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
        public static DBManager INSTANCE = new DBManager();
        String connPath = "jdbc:sqlite:sql.db";
        Connection conn = null;
        public boolean Login(String username, String password) throws NoSuchAlgorithmException
        {
            //TODO
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = /*md.digest(password.getBytes(StandardCharsets.UTF_8));*/ md.digest();
            String hash="";
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) 
            {
                String hex = Integer.toHexString(0xff & bytes[i]);
                if(hex.length() == 1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            hash = hexString.toString();
            String getUsernameSql = "SELECT username, password FROM Users WHERE username='" + username + "'";
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
                    System.out.println("Pass: " + hash);
                    System.out.println("LoadedPass: " + loadedPassword);
                    if(loadedPassword.contentEquals(hash))
                    {
                        //Success
                        Holder.INSTANCE.username = username;
                        Holder.INSTANCE.passhash = loadedPassword;
                        stmt.close();
                        return true;
                    }
                    c++;
                }
                stmt.close();
                return false;
                
            }
            catch(SQLException e)
            {
                System.out.println("SQL Error: " + e.getMessage());
                e.printStackTrace();
                //stmt.close();
            }
            
            
            
            return false;
        }
    }
