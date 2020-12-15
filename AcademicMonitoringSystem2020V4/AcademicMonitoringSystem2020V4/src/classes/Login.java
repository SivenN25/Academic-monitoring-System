
package classes;
public class Login {
    public String UserID;
    public String Password;
    public Login(){
        
    }
    public Login(String UserID,String Password)
    {
        this.UserID = UserID;
        this.Password = Password;
    }

    public String getUserID() {                 //get User ID method
        return UserID;
    }

    public String getPassword() {               //get password method
        return Password;
    }

    public void setUserID(String UserID) {      //set user ID method
        this.UserID = UserID;
    }

    public void setPassword(String Password) {  //set passsword method
        this.Password = Password;
    }
}
