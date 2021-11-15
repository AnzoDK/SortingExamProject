/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.sortingproject;
import javax.sql.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
/**
 *
 * @author anzo
 */
    public class Holder
    {
            public static Holder INSTANCE = new Holder();
            public String username;
            public String passhash;
    
    }
    
