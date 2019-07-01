package com.test.loadtest;

import java.sql.ResultSet;
import java.util.List;

import org.apache.catalina.valves.JDBCAccessLogValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class Service {
   @Autowired
   JdbcTemplate jdbcTemplate;
   
   
   public List<Players>  getData(){
	     String queryString = "select * from players";
	return  jdbcTemplate.query(queryString,(rs,rowNum)-> new Players(rs.getInt(1), rs.getString(2),
	    		rs.getString(3)));
   }


public void addData(String id, String name, String email) {
	String querySting ="insert into Players values('"+id+"','"+name+"','"+email+"')";
	jdbcTemplate.execute(querySting);
}
}
