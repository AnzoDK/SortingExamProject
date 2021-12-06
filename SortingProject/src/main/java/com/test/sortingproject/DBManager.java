/*
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author anzo
 */
public class DBManager
    {
        public static DBManager INSTANCE = new DBManager();
                
        public enum LoginError{SUCCESS, USERNAME_IN_USE, SQLERROR};
        
        String connPath = "jdbc:sqlite:sql.db";
        Connection conn = null;
        public boolean Login(String username, String password) throws NoSuchAlgorithmException
        {
            
            String hash = Encryptor.EncryptString(password);
            
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
        public LoginError Register(String username, String password)
        {
            String getUsernameSql = "SELECT username FROM Users WHERE username='" + username + "'";
            try{
                conn = DriverManager.getConnection(connPath);
                Statement stmt = null;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(getUsernameSql);
                if(rs.next())
                {
                    return DBManager.LoginError.USERNAME_IN_USE;
                }
                
                String hash = Encryptor.EncryptString(password);
                String registerSQL = "INSERT INTO Users (username, password) VALUES ('" + username + "' , '" + hash + "')";
                stmt.executeUpdate(registerSQL);
                return LoginError.SUCCESS;
                
            }
            catch(SQLException e)
            {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                return LoginError.SQLERROR;
            }
        }
    }
