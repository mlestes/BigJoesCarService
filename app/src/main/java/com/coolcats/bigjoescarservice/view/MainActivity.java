package com.coolcats.bigjoescarservice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

import com.coolcats.bigjoescarservice.R;
import com.coolcats.bigjoescarservice.databinding.ActivityMainBinding;
import com.coolcats.bigjoescarservice.mod.Car;
import com.coolcats.bigjoescarservice.util.CarAdapter;
import com.coolcats.bigjoescarservice.util.MyLog;
import com.coolcats.bigjoescarservice.util.db.CarDB;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CarAdapter.CarDelegate {

    private ActivityMainBinding binding;
    private InputFragment inputFragment;
    private OutputFragment outputFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        inputFragment = (InputFragment) getSupportFragmentManager().findFragmentById(R.id.input_fragment);
        outputFragment = (OutputFragment) getSupportFragmentManager().findFragmentById(R.id.output_fragment);
        readDatabase();

    }

    @Override
    public void insertCar(Car car) {

        MyLog.logger("Inserting" + car.toString());
        new Thread(){
            @Override
            public void run() {
                super.run();
                CarDB.getDatabase(MainActivity.this).getDAO().insertCars(car);
                readDatabase();
            }
        }.start();

    }

    @Override
    public void selectCar(Car car) {
        if(car == null){
            MyLog.logger("Passed nothing!");
            return;
        }
        MyLog.logger("Selecting " + car.toString());
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (car.isAvailable())
                    CarDB.getDatabase(MainActivity.this).getDAO().updateAvailability(car.getId(), false);
                else
                    CarDB.getDatabase(MainActivity.this).getDAO().updateAvailability(car.getId(), true);
                readDatabase();
            }
        }.start();
    }

    @Override
    public void deleteCar(Car car) {

        MyLog.logger("Deleting\n" + car.toString());
        new Thread() {
            @Override
            public void run() {
                super.run();
                CarDB.getDatabase(MainActivity.this).getDAO().deleteCars(car);
                readDatabase();
            }
        }.start();

    }

    private void readDatabase() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                List<Car> carList = CarDB.getDatabase(MainActivity.this).getDAO().getAllCars();
                runOnUiThread(() -> {
                    outputFragment.updateList(carList);
                });

            }
        }.start();
    }
}