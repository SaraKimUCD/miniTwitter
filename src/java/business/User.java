/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import java.io.Serializable;

/**
 *
 * @javabean for User Entity
 */
public class User implements Serializable {
    private String userID;
    private String fullname;
    private String email;
    private String username;
    private String birthdate;
    private String password;
    private String questionNo;
    private String answer;
    private String profileURL;
    private String coverURL;
    private String lastVist;
    
    
    public User()
    {
        userID = "";
        fullname = "";
        email = "";
        username = "";
        birthdate = "";
        password = "";
        questionNo = "";
        answer = "";
        profileURL = "";
        coverURL = "";
    }
    
    public User(String userID, String fullname, String username, String email, String password, String birthdate, String questionNo, String answer){
        this.userID = userID;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.questionNo = questionNo;
        this.answer = answer;
    }
    
    public User(String fullname, String username, String email, String password, String birthdate, String questionNo, String answer){
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.questionNo = questionNo;
        this.answer = answer;
    }
    
    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("[%s,%s]", this.getFullname(), this.getEmail()));
      return sb.toString();
    }
    
    
    
    public String getFullname(){
        return this.fullname;
    }
    public void setFullname(String fullname){
        this.fullname = fullname;
    }
    
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getBirthdate(){
        return this.birthdate;
    }
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getQuestionNo(){
        return this.questionNo;
    }
    public void setQuestionNo(String questionNo){
        this.questionNo = questionNo;
    }
    
    public String getAnswer(){
        return this.answer;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    
    public void setUserID(String userID){
        this.userID = userID;
    }
    
    public String getUserID(){
        return this.userID;
    }
    
    public void setCoverURL(String url){
        this.coverURL = url;
    }
    
    public void setProfileURL(String url){
        this.profileURL = url;
    }
    
    public String getCoverURL(){
        return this.coverURL;
    }
    
    public String getProfileURL(){
        return this.profileURL;
    }
    
    public void setLastVisit(String lastVisit){
        this.lastVist = lastVisit;
    }
    public String getLastVisit(){
        return this.lastVist;
    }
}
