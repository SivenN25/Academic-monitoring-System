

package classes;
import classes.Mysqlconnection;
import com.mysql.cj.util.StringUtils;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.BreakableNode;

public class Curriculum {
    private String student_number;
    private String Degree;
    private int Year;
    private int Semester;
    private String []ModulesTaken;
    private int TotalCredits;
    
    public Curriculum(){
         this.ModulesTaken=new String[10];//empty types are denoted by null
    }
    
    public Curriculum(String Degree, String student_number, int Year, int Semester){
        this.Degree=Degree;
        this.student_number=student_number;
        this.Year = Year;
        this.Semester = Semester;
        this.ModulesTaken=new String[10];       //empty types are denoted by null
        this.TotalCredits=0;
                
    }
    
    public String getDegree() {                 //method to get degree type
        return Degree;
    }

    public int getYear() {                      //method to get year type
        return Year;
    }

    public int getSemester() {                  //method to get semester type
        return Semester;
    }

    public String getModulesTaken(int index){   //method to get a module
        return ModulesTaken[index];
    }

    public int getTotalCredits() {              //method to get total credits of curriculum
        return TotalCredits;
    }
    
     public void setDegree(String Degree) {     //method to set degree
        this.Degree = Degree;
    }

    public void setYear(int Year) {             //method to set year
        this.Year = Year;
    }

    public void setSemester(int Semester) {     //method to set semeseter
        this.Semester = Semester;
    }

    public void setModulesTaken(String ModulesTaken, int index) {   //method to add module to curriculum
        this.ModulesTaken[index] = ModulesTaken;
    }
    
    public void add_module_credits(int credits){
        this.TotalCredits+=credits;
    }

  
    public boolean Check_prequisites(String pre_reqs){              //method for checking pre-prequisites
        int delimiter_counter=0;
        boolean add_module=true;
            try {
                Mysqlconnection connect=new Mysqlconnection();
                String []pre_reqs_array = pre_reqs.split(",");      //split pre-requisites string into individual strings 
                for(String s:pre_reqs_array){
                System.out.print(s+"\n");
                
                if(connect.getspecificdata2var("modules.`students past`", "`Student Number`", this.student_number,"Module", s, "Rating").equals("p")){  //check if the module pre-requisite was passed
                    System.out.print("pass");
                    add_module=true;
                }
                else{
                    add_module=false;
                    break;
                }
                }
                return add_module;                                   //if all pre-requisites were passed, return true
            } catch (Exception ex) {
                Logger.getLogger(Curriculum.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        
      
 
    public void generatecurriculum()throws  Exception{
        
        int counter=0;
        int delimiter_counter=0;
        int array_counter=0;
        int credits;
        String pre_reqs="";
        String pre_req_rating="";
        String [] pre_reqs_array=new String[5];
        boolean add_module=true;
        int reset_array_count=0;
        try{
            Mysqlconnection connect=new Mysqlconnection();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st=con.createStatement();
            String query="Select * from modules.`students past` where `Student Number`= '"+this.student_number+"' and `Semester taken` = '"+this.Semester+"'"; //get moduels from the past accoriding to semester
            ResultSet myrs=st.executeQuery(query);
            
            while(myrs.next() && this.TotalCredits<72){
                 if(myrs.getString("Rating")=="f"){
                     this.setModulesTaken(myrs.getString("Module"), counter);   //add moudles to the curriculum that the student has failed
                     credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", myrs.getString("Module"), "Credits"));
                     this.add_module_credits(credits);                  //add cresits to curriculum
                     counter++;
                     
                }
                
            }

            //year 1
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st1=con1.createStatement();
            String query1="Select * from modules.`"+this.Degree+"` where Semster = '"+this.Semester+"' and Year = '"+"1"+"'"; //get moduels according to the semester pertaining to the students' degree
            ResultSet myrs1=st1.executeQuery(query1);
            while(myrs1.next() && this.TotalCredits<72){
                String modulecode=myrs1.getString("Module code"); 
                pre_reqs=connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`",modulecode ,"Pre_requisites");
            
                if(pre_reqs.equals("0") && !(connect.checkuserdb2var("modules.`students past`", "`Student Number`", this.student_number, "Module", modulecode))){ //check if the module was done before
                        this.setModulesTaken(modulecode, counter);      //add moudles to the curriculum if it was not done before and has no pre-requisites
                        credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                        this.add_module_credits(credits);
                        counter++; 
                }
                else{
                    add_module=this.Check_prequisites(pre_reqs);        //check if pre-requisites have been met
                        if(add_module){
                            this.setModulesTaken(modulecode, counter); //add moudles to the curriculum if it was not done before, and pre-requisites have been met
                            credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                            this.add_module_credits(credits);
                            counter++;
                        }
                    }
                }
                 //year 2
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st2=con2.createStatement();
            String query2="Select * from modules.`"+this.Degree+"` where Semster = '"+this.Semester+"' and Year = '"+"2"+"'"; //get moduels according to the semester pertaining to the student' degree
            ResultSet myrs2=st2.executeQuery(query2);
            while(myrs2.next() && this.TotalCredits<72){
                String modulecode=myrs2.getString("Module code"); 
                pre_reqs=connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`",modulecode ,"Pre_requisites");
                //System.out.print(modulecode+" "+pre_reqs);
                
                if(pre_reqs.equals("0") && !(connect.checkuserdb2var("modules.`students past`", "`Student Number`", this.student_number, "Module", modulecode))){//check if the module was done before
                        this.setModulesTaken(modulecode, counter); //add moudles to the curriculum
                        credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                        this.add_module_credits(credits);   
                        counter++;  
                }
                else{
                    System.out.print("inelse");
                    System.out.print(pre_reqs);
                    add_module=this.Check_prequisites(pre_reqs);        //check if pre-requisites have been met
                        if(add_module){
                            this.setModulesTaken(modulecode, counter); //add moudles to the curriculum if it was not done before, and pre-requisites have been met
                            credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                            this.add_module_credits(credits);           
                            counter++;
                        }
                    }
                }
            //year 3
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con3=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st3=con3.createStatement();
            String query3="Select * from modules.`"+this.Degree+"` where Semster = '"+this.Semester+"' and Year = '"+"3"+"'"; //get moduels according to the semester pertaining to the student' degree
            ResultSet myrs3=st3.executeQuery(query3);
            while(myrs3.next() && this.TotalCredits<72){
                String modulecode=myrs3.getString("Module code"); 
                pre_reqs=connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`",modulecode ,"Pre_requisites");
                
                if(pre_reqs.equals("0") && !(connect.checkuserdb2var("modules.`students past`", "`Student Number`", this.student_number, "Module", modulecode))){//check if the module was done before
                        this.setModulesTaken(modulecode, counter); //add moudles to the curriculum
                        credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                        this.add_module_credits(credits);
                        counter++; 
                }
                else{
                    add_module=this.Check_prequisites(pre_reqs);      //check if pre-requisites have been met
                        if(add_module){
                            this.setModulesTaken(modulecode, counter);//add moudles to the curriculum if it was not done before, and pre-requisites have been met 
                            credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                            this.add_module_credits(credits);
                            counter++;
                        }
                    }
                }
            //year 4
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con4=DriverManager.getConnection("jdbc:mysql://localhost:3306/modules", "root", "Rocky003!");
            Statement st4=con4.createStatement();
            String query4="Select * from modules.`"+this.Degree+"` where Semster = '"+this.Semester+"' and Year = '"+"4"+"'"; //get moduels according to the semester pertaining to the student' degree
            ResultSet myrs4=st4.executeQuery(query4);
            while(myrs4.next() && this.TotalCredits<72){
                String modulecode=myrs4.getString("Module code"); 
                if(pre_reqs.equals("0") && !(connect.checkuserdb2var("modules.`students past`", "`Student Number`", this.student_number, "Module", modulecode))){//check if the module was done before
                        this.setModulesTaken(modulecode, counter); //add moudles to the curriculum
                        credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                        this.add_module_credits(credits);
                        counter++;

                }
                else{
                    add_module=this.Check_prequisites(pre_reqs);    //check if pre-requisites have been met
                        if(add_module){
                            this.setModulesTaken(modulecode, counter);//add moudles to the curriculum if it was not done before, and pre-requisites have been met 
                            credits=Integer.parseInt(connect.getspecificdata("modules.`"+this.Degree+"`", "`Module code`", modulecode, "Credits"));
                            this.add_module_credits(credits);
                            counter++;
                        }
                    }
                }
            }
        catch(Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
            }
        
    }

}
