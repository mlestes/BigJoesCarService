package com.coolcats.bigjoescarservice.util.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.coolcats.bigjoescarservice.mod.Car;

@Database(version = 1, entities = {Car.class})
public abstract class CarDB  extends RoomDatabase {

    private static CarDB database;
    public abstract CarDAO getDAO();

    public static CarDB getDatabase(Context context){

        if(database == null){
            database = Room.databaseBuilder(
                    context,
                    CarDB.class,
                    "car.db"
            ).build();
        }

        return database;

    }

}
