package com.RainbowSea.test;

import com.RainbowSea.DBUtil.DBUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DButilTest {
    @Test
    public void testConnection() {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(connection);
    }
}
