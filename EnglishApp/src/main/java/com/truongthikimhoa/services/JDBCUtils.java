/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.truongthikimhoa.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class JDBCUtils {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Connection createConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/englishdb", "root", "10102002");
    }
}
