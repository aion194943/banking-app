package com.example.proiect_android_ionanamaria;
import java.io.Serializable;
import java.util.Date;
public class Transaction implements Serializable {

    private String beneficiary;
    private Date transactionDate;
    private float initialValue;
    private String operation; //addition, substraction
    private float finalValue;

    public Transaction(){}

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(float initialValue) {
        this.initialValue = initialValue;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public float getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(float finalValue) {
        this.finalValue = finalValue;
    }


    public Transaction(String beneficiary, Date transactionDate, float initialValue, String operation, float finalValue) {
        this.beneficiary = beneficiary;
        this.transactionDate = transactionDate;
        this.initialValue = initialValue;
        this.operation = operation;
        this.finalValue = finalValue;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "beneficiary='" + beneficiary + '\'' +
                ", transactionDate=" + transactionDate +
                ", initialValue=" + initialValue +
                ", operation='" + operation + '\'' +
                ", finalValue=" + finalValue +
                '}';
    }
}
