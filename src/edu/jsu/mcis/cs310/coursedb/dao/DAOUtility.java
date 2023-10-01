package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.clifton.json_simple.*;


public class DAOUtility {
    
    public static final int TERMID_FA23 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {
                
                while(rs.next()) {
                    
                    JsonObject line = new JsonObject();
                    
                    ResultSetMetaData header = rs.getMetaData();
                    int columnNumber = header.getColumnCount();
               
 
                for (int i = 0; i < columnNumber; i++){
                    
                    String column = header.getColumnName(i + 1);
                    String value = rs.getString(column);
                    
                    line.put(column, value);
                    
                }
                
                records.add(line);
                

            }
            }
            
        }
        catch (SQLException e) {
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
