package com.fashionstore.util;

import java.sql.Connection;

public class TestDBConnection {

    public static void main(String[] args) {

        Connection conn = DBConnection.getConnection();

        if (conn != null) {
            System.out.println("✅ DB Connected Successfully!");
        } else {
            System.out.println("❌ DB Connection Failed!");
        }
    }
}