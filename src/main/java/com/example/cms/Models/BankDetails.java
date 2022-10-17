package com.example.cms.Models;

public class BankDetails {

    private int accountNo;
    private int IFSCCode;
    private String bankName;
    private String branchName;
    private int userId;

    public BankDetails() {

    }

    public BankDetails(int accountNo, int IFSCCode, String bankName, String branchName, int userId) {
        this.accountNo = accountNo;
        this.IFSCCode = IFSCCode;
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

    public int getIFSCCode() {
        return IFSCCode;
    }

    public void setIFSCCode(int IFSCCode) {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
