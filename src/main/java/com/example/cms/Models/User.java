package com.example.cms.Models;

import java.sql.Date;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int contact_no;
    private String gender;
    private Date DOB;

    public User(){

    }

    public User(int userId, String firstName, String lastName, String email, String password, int contact_no,
                String gender, Date DOB){
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contact_no = contact_no;
        this.gender = gender;
        this.DOB = DOB;
    }

    public int getUserID(){
        return userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }

    public String getUserFirstName(){
        return firstName;
    }

    public void setUserFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getUserLastName(){
        return lastName;
    }

    public void setUserLastName(String lastName){
        this.lastName = lastName;
    }

    public String getUserEmail(){
        return email;
    }

    public void setUserEmail(String email){
        this.email = email;
    }

    public String getUserPassword(){
        return password;
    }

    public void setUserPassword(String password){
        this.password = password;
    }

    public int getUserContactNo(){
        return contact_no;
    }

    public void setUserContactNo(int contact_no){
        this.contact_no = contact_no;
    }

    public String getUserGender(){
        return gender;
    }

    public void setUserGender(String gender){
        this.gender = gender;
    }

    public Date getUserDOB(){
        return DOB;
    }

    public void setUserDOB(Date DOB){
        this.DOB = DOB;
    }
}
