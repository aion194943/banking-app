package com.example.proiect_android_ionanamaria;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;


public class User  implements Serializable {
    String username;
    String rank; //student, professor, admin


    public User() {

    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }



    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }



    public User(String username,  String rank) {
        this.username = username;
        this.rank=rank;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", rank='" + rank + '\'' +
                '}';
    }
}




