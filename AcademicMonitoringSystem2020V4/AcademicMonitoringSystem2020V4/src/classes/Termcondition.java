/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author User
 */
public class Termcondition {
    public Termcondition(){
    }
    public String generateconditon(int yearofStudy, int credits, String currentRiskStatus){
        switch (yearofStudy){
        case(1):{ //1st year
            if(credits>=144){
                currentRiskStatus = "Green";
            }   
            else if(credits>=72 && credits<144){
                currentRiskStatus = "Orange";
            }   
            else {
                currentRiskStatus = "Red";
            }
            return currentRiskStatus;
        }

        case(2):{ //2nd year
            if(credits>=216){
                currentRiskStatus = "Green";
            }   
            else if(credits>=144 && credits<216){
                currentRiskStatus = "Orange";
            }   
            else {
                currentRiskStatus = "Red";
            }
            return currentRiskStatus;
        }
         
         
        case(3):{//3rd year
            if(credits>=360){
                currentRiskStatus = "Green";
            }
            else if(credits>=288 && credits<360){
                currentRiskStatus = "Orange";
             }
            else{
            currentRiskStatus = "Red";
            }
            return currentRiskStatus;
        }
        
        case (4):{//4th year
            if(credits>=504){
                currentRiskStatus = "Green";
            }
            else if(credits>=432 && credits<504){
                currentRiskStatus = "Orange";
            }
            else{
                currentRiskStatus = "Red";
            }
            return currentRiskStatus;
        }
        }
        return currentRiskStatus;
    }
          
            
}
              
            


