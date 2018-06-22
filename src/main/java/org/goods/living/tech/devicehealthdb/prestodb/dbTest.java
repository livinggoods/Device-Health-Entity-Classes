/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.goods.living.tech.devicehealthdb.prestodb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *
 * @author ernestmurimi
 */
public class dbTest {
    
    public static void main(String[] args) {
    Connection conn = null;  
    String url = "jdbc:presto://127.0.0.1:8092/postgresql/public";
    try {    
      System.out.println("Connecting to database...");    
      conn = DriverManager.getConnection(url, "test", null);
         
      
      String query = "select * from users";
      Statement stmt = null;
    try {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            
            //id  | chv_id |   phone    |    android_id    | update_interval |         created_at       
            //|         updated_at         | version_code |  
            //version_name  |   username    | password |      recorded_at       | disable_sync
            String chv_id = rs.getString(4);
            String phone = rs.getString(5);
            String android_id = rs.getString(6);
            System.out.println(chv_id + "\t" + phone +
                               "\t" + android_id );
        }
    } catch (SQLException e ) {
        e.printStackTrace();
    } finally {
        if (stmt != null) { stmt.close(); }
    }
    
    } catch (Exception e) {    
      e.printStackTrace();    
    } finally {    
      if (conn != null) {    
        try {     
          conn.close();    
        } catch (SQLException e) {    
          // ignore    
          System.out.print(e.getMessage());
        }    
      }    
    }            
  } 
    
  

}
