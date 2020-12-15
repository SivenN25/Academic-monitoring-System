
package classes;


public class User {
    private String UserID;
    private String Name;
    private String Access_Type;
    
    public User(){
        
    }
    
    public User(String UserID,String Name, String Access_Type)
    {
        this.UserID = UserID;
        this.Name = Name;
        this.Access_Type = Access_Type;
    }

    public String getUserID(){              //get user ID method 
        return UserID;
    }

    public String getName() {               //get name method
        return Name;
    }

    public String getAccess_Type() {        //get access type
        return Access_Type;
    }

    public void setUserID(String UserID) {  //set user ID method
        this.UserID = UserID;
    }

    public void setName(String Name) {      //set  name method
        this.Name = Name;
    }

    public void setAccess_Type(String Access_Type) {    //set access type
        this.Access_Type = Access_Type;
    }
}
