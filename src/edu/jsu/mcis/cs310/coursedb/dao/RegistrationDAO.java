package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class RegistrationDAO {
    
    public static final String QUERY_CREATE = "INSERT INTO registration(studentid, termid, crn) VALUES(?, ?, ?)";
    public static final String QUERY_DROP = "DELET FROM registration WHERE studentid = ? AND termid = And crn = ?";
    public static final String QUERY_WITHDRAW = "DELET FROM registration WHERE studentid = ? AND termid = ?";
    public static final String QUERY_LIST = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_CREATE);
                
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3,crn);
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) {e.printStackTrace();}
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) {e.printStackTrace();} }
            if (ps != null) { try { ps.close(); } catch (Exception e) {e.printStackTrace();} }
            
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_DROP);
                
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                int updateCount = ps.executeUpdate();
                
                if(updateCount > 0){
                    result = true;
                }
                
            }
            
        }
        
        catch (Exception e) {e.printStackTrace();}
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) {e.printStackTrace();} }
            
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_WITHDRAW);
                
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                int updateCount = ps.executeUpdate();
                
                if (updateCount > 0){
                result = true;
            }
                
            }
            
        }
        
        catch (Exception e) {e.printStackTrace();}
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) {e.printStackTrace();} }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                ps = conn.prepareStatement(QUERY_LIST);
                
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                boolean hasresults = ps.execute();
                
                if(hasresults){
                    
                    rs = ps.getResultSet();
                    result = DAOUtility.getResultSetAsJson(rs);
                }

            }
            
        }
       
        
        catch (Exception e) {e.printStackTrace();}
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) {e.printStackTrace();} }
            if (ps != null) { try { ps.close(); } catch (Exception e) {e.printStackTrace();} }
            
        }
        
        return result;
        
    }
    
}
