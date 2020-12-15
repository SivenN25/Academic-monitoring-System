


package classes;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author User
 */
public class Mysqlconnection {  

    public Mysqlconnection(){
    }
    public String getspecificdata(String mysqltable, String parameter,String value, String column)throws  Exception{ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="Select * from "+mysqltable+" where "+parameter+"= '"+value+"'"; 
            ResultSet myrs=st.executeQuery(query);
            String return_string="empty";
            while(myrs.next()){
                return_string=myrs.getString(column);
            }
            return return_string;

            }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            return "error";
            }
    }
    
        public String getspecificdata2var(String mysqltable, String parameter,String value, String parameter2, String value2,String column)throws  Exception{ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="Select * from "+mysqltable+" where "+parameter+"= '"+value+"'"; 
            ResultSet myrs=st.executeQuery(query);
            String return_string="empty";
            while(myrs.next()){
                if(myrs.getString(parameter2).equals(value2))
                {
                return_string=myrs.getString(column);
                }
            }
            return return_string;   
            }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            return "error";
            }
    }
        
     public void generatecurriculum(Curriculum student_curriculum,String mysqltable, String parameter,String value, String column, String value2)throws  Exception{ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="Select * from "+mysqltable+" where "+parameter+"= '"+value+"'"; 
            ResultSet myrs=st.executeQuery(query);
            int counter=0;
            while(myrs.next()){
                 if(myrs.getString(column)=="value2"){
                     student_curriculum.setModulesTaken(myrs.getString(column), counter);
                     counter++;
                
            }
                
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            }
        
    }
    public boolean checkuserdb(String mysqltable, String parameter, String lookfor) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement myst=con.createStatement();
            String query="SELECT * from "+mysqltable+" WHERE "+parameter+"= '"+lookfor+"'"; 
            ResultSet myrs=myst.executeQuery(query);
            
            return myrs.next();   
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            return false;
            }
     
    }
    public boolean checkuserdb2var(String mysqltable, String parameter, String lookfor, String parameter2, String lookfor2) throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement myst=con.createStatement();
            String query="Select * from "+mysqltable+" where "+parameter+"= '"+lookfor+"'";
            ResultSet myrs=myst.executeQuery(query);
            boolean did_module=false;
            while(myrs.next()){
                if(myrs.getString(parameter2).equals(lookfor2)){
                    did_module=true;
                    
                    break;
                }
            }
           return did_module;
             
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            return false;
            }
     
    }
    public void addtodb(String mysqltable, String values)throws  Exception{ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="Insert into "+mysqltable+" values("+values+")"; 
            st.executeUpdate(query);
            }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            }
        
    } 
    public void getdataforcomobbox(String mysqltable, String parameter, JComboBox target)throws  Exception{ 
        try{
            target.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            String query="select * from "+mysqltable;
            PreparedStatement myst=con.prepareStatement(query);
            ResultSet rs=myst.executeQuery();
            while(rs.next()){
            String addition=rs.getString(parameter);
            target.addItem(addition);
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            }
    }
    public void getspecificdataforcomobbox(String mysqltable, String parameter, String value, JComboBox target)throws  Exception{ 
        try{
            target.removeAllItems();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="Select * from "+mysqltable+" where "+parameter+"= '"+value+"'";
            ResultSet rs=st.executeQuery(query);
            while(rs.next()){
            String addition=rs.getString(parameter);
            target.addItem(addition);
            }
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            }
    }
    public void setuptable(JTable jtable,String sqltable,String need, String parameter, String value)throws Exception{ // displaying records of the table
        try{
            jtable.removeAll();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            String query="Select "+need+" from "+sqltable+" where "+parameter+"= '"+value+"'";
            PreparedStatement myst=con.prepareStatement(query);
            ResultSet rs=myst.executeQuery();
            jtable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            } 
    }
    public void setup2vartable(JTable jtable,String sqltable,String need, String parameter, String value, String paramerter2, String value2)throws Exception{ // displaying records of the table
        try{
            jtable.removeAll();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            String query="Select "+need+" from "+sqltable+" where "+parameter+" = '"+value+"' and "+paramerter2+" = '"+value2+"'";
            PreparedStatement myst=con.prepareStatement(query);
            ResultSet rs=myst.executeQuery();
            jtable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            } 
    }
    public void setupfulltable(JTable jtable,String sqltable,String need)throws Exception{ // displaying records of the table
        try{
            jtable.removeAll();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            String query="Select "+need+" from "+sqltable;
            PreparedStatement myst=con.prepareStatement(query);
            ResultSet rs=myst.executeQuery();
            jtable.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        } 
    }
    public void deletefromdb(String mysqltable, String parameter,String value)throws Exception{ 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="DELETE from "+mysqltable+" where "+parameter+"= '"+value+"'"; 
            st.executeUpdate(query);
        }
        catch(Exception ex){
            System.out.print(ex.getMessage());
        }
    }
    public void setuparray(String [] modules,String sqltable,String need, String parameter, String value, String paramerter2, String value2)throws Exception{ // displaying records of the table
    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
        String query="Select * from "+sqltable+" where "+parameter+" = '"+value+"' and "+paramerter2+" = '"+value2+"'";
        PreparedStatement myst=con.prepareStatement(query);
        ResultSet rs=myst.executeQuery();
        for(int count=0;rs.next() && count<=9;count++){
            modules[count]=rs.getString(need);
        }
    }
    catch(Exception ex){
        System.out.print(ex.getMessage());
        } 
    }

}

       
    
    

