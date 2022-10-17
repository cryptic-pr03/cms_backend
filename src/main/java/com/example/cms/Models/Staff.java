package com.example.cms.Models;

import java.sql.Date;
import java.sql.Time;

public class Staff {

    private int staffId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String contactNo;
    private String gender;
    private Date DOB;
    private int role;
    private int groupNumber;
    private int salary;
    private int venueId;
    private Date joiningDate;
    private Date leavingDate;
    private String accountNo;
    private String IFSCCode;
    private String bankName;
    private String branchName;


    public Staff() {

    }

    public Staff(int staffId, String firstName, String lastName, String email, String password, String contactNo, String gender, Date DOB, int role, int groupNumber, int salary, int venueId, Date joiningDate, Date leavingDate, String accountNo, String IFSCCode, String bankName, String branchName) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.gender = gender;
        this.DOB = DOB;
        this.role = role;
        this.groupNumber = groupNumber;
        this.salary = salary;
        this.venueId = venueId;
        this.joiningDate = joiningDate;
        this.leavingDate = leavingDate;
        this.accountNo = accountNo;
        this.IFSCCode = IFSCCode;
        this.bankName = bankName;
        this.branchName = branchName;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", gender='" + gender + '\'' +
                ", DOB=" + DOB +
                ", role=" + role +
                ", groupNumber=" + groupNumber +
                ", salary=" + salary +
                ", venueId=" + venueId +
                ", joiningDate=" + joiningDate +
                ", leavingDate=" + leavingDate +
                ", accountNo='" + accountNo + '\'' +
                ", IFSCCode='" + IFSCCode + '\'' +
                ", bankName='" + bankName + '\'' +
                ", branchName='" + branchName + '\'' +
                '}';
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Date getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}
