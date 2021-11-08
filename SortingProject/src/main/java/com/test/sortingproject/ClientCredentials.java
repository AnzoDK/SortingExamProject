/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.sortingproject;
import javax.sql.*;
/**
 *
 * @author anzo
 */
public class ClientCredentials {
    static class Holder
    {
            public void Setup(String user, String token)
            {
                m_username = user;
                m_sessionToken = token;
            }
            private String m_username;
    
            private String m_sessionToken;
    }
    static class DBManager
    {
        static boolean ValidateToken(String username, String token)
        {
            
        }
    }
    public void Login(String username, String password)
    {
        
    }
    
    private void m_GenerateToken()
    {
    
    }
    

    
}
