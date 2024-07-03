package com.example.proiect_android_ionanamaria;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

    @Entity(tableName = "cards")
    public class Card implements Serializable {


        @PrimaryKey(autoGenerate = true)
        long id;
        String bankName;
        long cardNum;
        Integer cardCVV;
        Integer expYr;
        Integer expMth;
        Integer balance;
        String currency;



        public Card(String bankName, Long cardNum, Integer cardCVV, Integer expYr, Integer expMth, Integer balance, String currency) {
            this.bankName = bankName;
            this.cardNum = cardNum;
            this.cardCVV = cardCVV;
            this.expYr = expYr;
            this.expMth = expMth;
            this.balance=balance;
            this.currency=currency;
        }
       @Ignore

        public Card() {

        }
        public long getId() {
            return id;
        }
        public void setId(long id) {
            this.id = id;
        }

        public String getBankName() {
            return bankName;
        }
        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public long getCardNum() {return cardNum;}
        public void setCardNum(Long cardNum) {this.cardNum = cardNum;}

        public Integer getCardCVV() {
            return cardCVV;
        }
        public void setCardCVV(Integer cardCVV) {
            this.cardCVV = cardCVV;
        }

        public Integer getExpYr() {
            return expYr;
        }
        public void setExpYr(Integer expYr) {
            this.expYr = expYr;
        }

        public Integer getExpMth() {
            return expMth;
        }
        public void setExpMth(Integer expMth) {
            this.expMth = expMth;
        }

        public Integer getBalance(){return balance;}
        public void setBalance(Integer balance){this.balance=balance;}

        public String getCurrency(){return currency;}
        public void setCurrency(String currency){this.currency=currency;}

//            @Override
//            public String toString() {
//
//                return "id{" +
//                        "bankName='" + password + '\'' +
//                        ", cardNum=" + birthDate +
//                        ", cardCVV'" + bankName + '\'' +
//                        ", expYr=" + value +
//                        ", expMth=" + value +'\'' +
//                        ", expMth=" + value +
//                        ", rank='" + rank + '\'' +
//                        '}';
//
        }






