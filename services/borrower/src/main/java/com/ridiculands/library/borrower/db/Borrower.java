package com.ridiculands.library.borrower.db;

public class Borrower {
    private int id;

    private String borrowerCardNumber;

    private String userName;
    private String borrowerFullName;

    // TODO ISRAELW change to enum
    private String borrowerType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBorrowerCardNumber() {
        return borrowerCardNumber;
    }

    public void setBorrowerCardNumber(String borrowerCardNumber) {
        this.borrowerCardNumber = borrowerCardNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBorrowerFullName() {
        return borrowerFullName;
    }

    public void setBorrowerFullName(String borrowerFullName) {
        this.borrowerFullName = borrowerFullName;
    }

    public String getBorrowerType() {
        return borrowerType;
    }

    public void setBorrowerType(String borrowerType) {
        this.borrowerType = borrowerType;
    }
}
