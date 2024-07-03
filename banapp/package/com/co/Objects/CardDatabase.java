package com.example.proiect_android_ionanamaria;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class}, version=1)
public abstract class CardDatabase extends RoomDatabase {
    public abstract CardDao getCardDao();

    private static CardDatabase instance;

    public static synchronized CardDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, CardDatabase.class, "CardDB.db").fallbackToDestructiveMigration().build();
        }

        return instance;
    }


}