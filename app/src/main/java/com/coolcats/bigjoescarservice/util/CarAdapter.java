package com.coolcats.bigjoescarservice.util;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.coolcats.bigjoescarservice.R;
import com.coolcats.bigjoescarservice.databinding.ListItemLayoutBinding;
import com.coolcats.bigjoescarservice.mod.Car;
import com.coolcats.bigjoescarservice.view.InputFragment;

import java.text.DecimalFormat;
import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Car> carList;
    private  CarDelegate carDelegate;

    public interface CarDelegate {
        void insertCar(Car car);
        void selectCar(Car car);
    }

    public CarAdapter(List<Car> carList, CarDelegate carDelegate) {

        this.carList = carList;
        this.carDelegate = carDelegate;

    }

    public void updateCarList(List<Car> carList){
        this.carList = carList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemLayoutBinding binding = ListItemLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );

        return new CarViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        Car car = carList.get(position);
        ConstraintLayout bg = holder.binding.cardBg;
        holder.binding.makeText.setText(car.getMake());
        holder.binding.modelText.setText(car.getModel());
        holder.binding.yearText.setText(car.getYear()+"");
        holder.binding.colorText.setText(car.getColor());
        holder.binding.tagText.setText(car.getTag());
        holder.binding.priceText.setText(new DecimalFormat("#.##").format(car.getPrice())+"");
        bg.setEnabled(car.isAvailable());

    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    class CarViewHolder extends RecyclerView.ViewHolder {

        ListItemLayoutBinding binding;
        public CarViewHolder(ListItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {

                int pos = getAdapterPosition();
                MyLog.logger("Position: " + pos);
                MyLog.logger(carList.get(pos).toString());
                carDelegate.selectCar(carList.get(pos));

            });

        }

    }

}
