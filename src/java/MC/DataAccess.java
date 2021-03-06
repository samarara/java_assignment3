/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MC;

import java.sql.*;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas AND SANDES
 */
public class DataAccess {
    private Connection conn;
    private boolean connStatus = false; 
    private Statement stm;
    
    public DataAccess(String conURL, String user, String pass){
        try {
            if(this.connect(conURL, user, pass)){
                connStatus = true;
                stm = conn.createStatement();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean connect(String conURL, String user, String pass) throws SQLException{
        try {
            String driverName = "com.mysql.jdbc.Driver";
            try {
                Class.forName(driverName).newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
            conn = DriverManager.getConnection(conURL, user, pass);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccess.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    
    public boolean isConnected(){
        return connStatus;
    }
    
    public ArrayList query(String tblnm){
        ArrayList head = new ArrayList();
        ArrayList rows = new ArrayList();
        ArrayList table = new ArrayList();
        try {
            ResultSet resultSet = stm.executeQuery("SELECT * FROM " + tblnm);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            for (int i = 1; i <= columnCount; i++) {
                    head.add(metaData.getColumnLabel(i));
            }

            while (resultSet.next()) {
                ArrayList row = new ArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(resultSet.getString(i));
                }
                rows.add(row);
            }
        }catch (SQLException e){
            return null;
        }
        table.add(head);
        table.add(rows);
        return table;
    }

    public ArrayList getTables(){
        //returns Arraylist converted set of table names, empty list if fail
        ArrayList rows = new ArrayList();
        try {
            ResultSet result = conn.getMetaData().getTables(null, null, "%", null);
            while (result.next()){
                rows.add(result.getString(3));
            }
        } catch (SQLException ex) {
            return rows;
        }
        return rows;
    }
    
    public ArrayList getBranch(){
        ArrayList table = new ArrayList();
        try {
            ResultSet result = stm.executeQuery("SELECT * FROM branch");
            while (result.next()){
                ArrayList row = new ArrayList();
                row.add(result.getString(1));
                row.add(result.getString(2));
                table.add(row);
            }
        } catch (SQLException ex) {
            return null;
        }
        return table;
    }
    
    /*
    DAO DML    
    */
    
    //inserting data into student table
    
    public boolean insertStudent(String SNUM, String name, String major, String hobby)
    {
        PreparedStatement pst = null;
        String query = "INSERT INTO student VALUES (?, ?, ?, ?);";
         try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, SNUM);
                pst.setString(2, name);
                pst.setString(3, major);
                pst.setString(4, hobby);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //deleting data from the student table
    public boolean deleteStudent(String SNUM, String name, String major, String hobby)
    {
        PreparedStatement pst = null;
        String query = "DELETE FROM student WHERE SNUM=? AND Name=? AND Major=? AND Hobby=?";
         try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, SNUM);
                pst.setString(2, name);
                pst.setString(3, major);
                pst.setString(4, hobby);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //inserting data into frequents table
    public boolean insertFrequents(String student, String fsName, String bnum)
    {
        PreparedStatement pst = null;
        String query = "INSERT INTO frequents VALUES (?, ?, ?);";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, student);
                pst.setString(2, fsName);
                pst.setString(3, bnum);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        
        return true;
    }
    
    //deleting data from the frequents table
    public boolean deleteFrequents(String student, String fsName, String bnum)
    {
        PreparedStatement pst = null;
        String query = "DELETE FROM frequents WHERE Student=? AND Name=? AND BNum=?";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, student);
                pst.setString(2, fsName);
                pst.setString(3, bnum);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //inserting data into foodservice table
    public boolean insertFoodService(String name, String category, String rate)
    {
        PreparedStatement pst = null;
        String query = "INSERT INTO foodservice VALUES (?, ?, ?);";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, category);
                pst.setString(3, rate);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //deleting data from the food service table
    public boolean deleteFoodService(String name, String category, String rate)
    {
        PreparedStatement pst = null;
        String query = "DELETE FROM foodservice WHERE Name=? AND Category=? AND Rate=?";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, category);
                pst.setString(3, rate);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //update data on foodservice
    public boolean updateFoodService(String name, String category, String rate)
    {
        PreparedStatement pst = null;
        String query = "UPDATE foodservice SET Category=?, Rate=? WHERE Name=?";
        try
        {
            if(conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, category);
                pst.setString(2, rate);
                pst.setString(3, name);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (Exception sqle)
        {
            return false;
        }
        return true;
    }
    
    //insert into branch table
    public boolean insertBranch(String name, String bNum, String location, String since)
    {
        PreparedStatement pst = null;
        String query = "INSERT INTO branch VALUES (?, ?, ?, ?);";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, bNum);
                pst.setString(3, location);
                pst.setString(4, since);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //delete from branch table
    public boolean deleteBranch(String name, String bNum, String location, String since)
    {
        PreparedStatement pst = null;
        String query = "DELETE FROM branch WHERE Name=? AND BNum=? AND Location=? AND Since=?";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, bNum);
                pst.setString(3, location);
                pst.setString(4, since);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //isnsert into food
    public boolean insertFood(String name, String cuisine, String type)
    {
        PreparedStatement pst = null;
        String query = "INSERT INTO food VALUES (?, ?, ?);";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, cuisine);
                pst.setString(3, type);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //deleting data from the food  table
     public boolean deleteFood(String name, String cuisine, String type)
    {
        PreparedStatement pst = null;
        String query = "DELETE FROM food WHERE Name=? AND Cuisine=? AND Type=?";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, name);
                pst.setString(2, cuisine);
                pst.setString(3, type);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //update data on food table
    public boolean updateFood(String name, String cuisine, String type)
    {
        PreparedStatement pst = null;
        String query = "UPDATE food SET Cuisine=?, Type=? WHERE Name=?";
        try
        {
            if(conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, cuisine);
                pst.setString(2, type);
                pst.setString(3, name);
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (Exception sqle)
        {
            return false;
        }
        return true;
    }
    
    //insert into likes table
    public boolean insertLikes(String student, String food)
    {
        PreparedStatement pst = null;
        String query = "INSERT INTO likes VALUES (?, ?);";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, student);
                pst.setString(2, food); 
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
    
    //delete into likes table
    public boolean deleteLikes(String student, String food)
    {
        PreparedStatement pst = null;
        String query = "DELETE FROM likes WHERE Student=? AND Food=?";
        try
        {
            if (conn != null)
            {
                pst = conn.prepareStatement(query);
                pst.setString(1, student);
                pst.setString(2, food); 
            }
            if (pst != null)
            {
                pst.executeUpdate();
            }
        }
        catch (SQLException sqle)
        {
            return false;
        }
        return true;
    }
}
