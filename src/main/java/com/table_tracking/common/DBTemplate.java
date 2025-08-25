//package com.table_tracking.common;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//@Configuration
//public class DBTemplate {
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//
//    public String generateSequenceId(String tableName, String prefix) {
//        String sequenceName = tableName + "_seq";
//
//        // Dynamically build SQL
//        String sql = "SELECT nextval('" + sequenceName + "')";
//
//        // Fetch next value from sequence
//        Long nextVal = jdbcTemplate.queryForObject(sql, Long.class);
//
//        // Format ID with prefix and padded number
//        return String.format("%s%06d", prefix, nextVal);
//    }
//
//}
