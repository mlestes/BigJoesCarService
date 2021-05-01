package com.coolcats.bigjoescarservice.util.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.coolcats.bigjoescarservice.mod.Car;

import java.util.List;

@Dao
public interface CarDAO {

    @Query("SELECT * FROM cars")
    List<Car> getAllCars();

    @Query("SELECT * FROM cars WHERE id = :id")
    Car getCar(int id);

    @Query("UPDATE cars SET available = :availability WHERE id = :id")
    void updateAvailability(int id, boolean availability);

    @Insert
    void insertCars(Car... car);

    @Delete
    void deleteCars(Car... car);
}
