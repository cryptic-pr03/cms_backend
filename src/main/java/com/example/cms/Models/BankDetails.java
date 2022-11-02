package com.example.cms.Models;

public class BankDetails {

    private int accountNo;
    private int IfscCode;
    private int userId;

    public BankDetails() {

    }


    public BankDetails(int accountNo, int IfscCode, int userId) {
        this.accountNo = accountNo;
        this.IfscCode = IfscCode;
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
                ", userId=" + userId +
                '}';
    }
}
