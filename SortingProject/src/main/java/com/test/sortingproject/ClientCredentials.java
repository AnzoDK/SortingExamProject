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
            public void Setup(String user)
            {
                m_username = user;
            }
            private String m_username;
    
    }
    static class DBManager
    {

        public boolean Login(String username, String password)
        {
            
        }
    }
    

    
}
