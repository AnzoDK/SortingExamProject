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
import java.util.ArrayList;
import java.time.format.*;
import java.time.*;

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
                stmt.close();
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
        public ArrayList<Mail> RetreiveMails(String username)
        {
            ArrayList<Mail> tmp = new ArrayList<Mail>();
            String sql = "SELECT * FROM Mails WHERE `to`='" + username + "';";
            try {
                conn = DriverManager.getConnection(connPath);
                Statement stmt = null;
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                
                while(rs.next())
                {
                    tmp.add(new Mail(rs.getString("title"), rs.getString("from"),rs.getString("to"),rs.getString("sendTime"), rs.getString("message")));
                }
                stmt.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return tmp;

        }
        public boolean HasUser(String username)
        {
            String sql = "SELECT `username` FROM Users WHERE `username`='" + username + "';";
            Statement stmt = null;
            try {  
                conn = DriverManager.getConnection(connPath);
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if(rs.next())
                {
                    stmt.close();
                    return true;
                }
                stmt.close();
                return false;
            } catch (SQLException ex) {
                try {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                    stmt.close();
                    return false;
                } catch (SQLException ex1) {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex1);
                    return false;
                }
            }
        }
        public boolean AddMail(Mail m)
        {
            DateTimeFormatter t = DateTimeFormatter.ofPattern("yyyy/MM/dd:HH:mm:ss");
            String sql = "INSERT INTO Mails(`title`,`from`, `to`, `sendTime`, `message`) VALUES ('" + m.title + "' , '" + m.from + "' , '" + m.to + "' , '" + m.sendTime.format(t) + "' , '" + m.message + "');";
            Statement stmt = null;
            try {
                conn = DriverManager.getConnection(connPath);
                
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
                try {
                    stmt.close();
                } catch (SQLException ex1) {
                    Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex1);
                }
                return false;
            }
        }
    }
