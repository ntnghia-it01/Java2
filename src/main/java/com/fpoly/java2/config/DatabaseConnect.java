/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fpoly.java2.config;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author trongnghia
 */
public class DatabaseConnect {
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "123456A@";
    private static final String DB_NAME = "java2";

//  Đây là hàm kết nối database
//  Nếu kết nối thành công => Trả về 1 connection để tương tác vào db
//  Nếu kết nối thất bại => Trả về null
    public static Connection connection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;database=" + DB_NAME + ";encrypt=true;trustServerCertificate=true";
            Connection con = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
