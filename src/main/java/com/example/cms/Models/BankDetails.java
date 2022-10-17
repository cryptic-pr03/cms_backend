package com.example.cms.Models;

public class BankDetails {

    private int accountNo;
    private int IfscCode;
    private String bankName;
    private String branchName;
    private int userId;

    public BankDetails() {

    }

    public BankDetails(int accountNo, int IfscCode, String bankName, String branchName, int userId) {
        this.accountNo = accountNo;
        this.IfscCode = IfscCode;
        this.bankName = bankName;
        this.branchName = branchName;
        this.userId = userId;
    }

    public int getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(int accountNo) {
        this.accountNo = accountNo;
    }

    public int getIfscCode() {
        return IfscCode;
    }

    public void setIfscCode(int IfscCode) {
        this.IfscCode = IfscCode;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BankDetails{" +
                "accountNo=" + accountNo +
                ", IfscCode=" + IfscCode +
                ", bankName='" + bankName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
