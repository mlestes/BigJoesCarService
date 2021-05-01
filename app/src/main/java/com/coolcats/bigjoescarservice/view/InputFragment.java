package com.coolcats.bigjoescarservice.view;

import android.content.Context;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.coolcats.bigjoescarservice.databinding.InputFragmentLayoutBinding;
import com.coolcats.bigjoescarservice.mod.Car;
import com.coolcats.bigjoescarservice.util.CarAdapter;
import com.coolcats.bigjoescarservice.util.MyLog;

import java.text.DecimalFormat;

public class InputFragment extends Fragment{

    private InputFragmentLayoutBinding binding;
    private CarAdapter.CarDelegate carDelegate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = InputFragmentLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.tagEditText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        binding.insertButton.setOnClickListener(v -> {

            if(checkInput(binding)) {
                String make = binding.makeEditText.getText().toString().trim();
                String model = binding.modelEditText.getText().toString().trim();
                String color = binding.colorEditText.getText().toString().trim();
                String tag = binding.tagEditText.getText().toString().trim();
                String priceText = binding.priceEditText.getText().toString().trim();
                String yearText = binding.yearEditText.getText().toString().trim();
                int year = Integer.parseInt(yearText);
                double price = Double.parseDouble(priceText);
                new DecimalFormat("#.##").format(price);
                Car car = new Car(make, model, year, color, tag, price, true);
                MyLog.logger("Strings got: " + make + " " + model + " " + color + " " +yearText + " " + tag + " " + priceText);
                MyLog.logger("Created:\n" + car.toString());
                carDelegate.insertCar(car);
                clearText(binding);
            }

        });
    }

    private void clearText(InputFragmentLayoutBinding binding) {
        binding.makeEditText.setText("");
        binding.modelEditText.setText("");
        binding.colorEditText.setText("");
        binding.yearEditText.setText("");
        binding.tagEditText.setText("");
        binding.priceEditText.setText("");
    }

    private boolean checkInput(InputFragmentLayoutBinding binding) {

        String make = binding.makeEditText.getText().toString().trim();
        String model = binding.modelEditText.getText().toString().trim();
        String color = binding.colorEditText.getText().toString().trim();
        String year = binding.yearEditText.getText().toString().trim();
        String tag = binding.tagEditText.getText().toString().trim();
        String price = binding.priceEditText.getText().toString().trim();

        if(make.isEmpty() || model.isEmpty() || color.isEmpty() || year.isEmpty() || tag.isEmpty() || price.isEmpty()) {
            Toast.makeText(getContext(), "All fields must not be empty!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        carDelegate = (CarAdapter.CarDelegate) context;

    }
}
