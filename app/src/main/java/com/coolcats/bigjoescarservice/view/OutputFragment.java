package com.coolcats.bigjoescarservice.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.SnapHelper;

import com.coolcats.bigjoescarservice.databinding.OutputFragmentLayoutBinding;
import com.coolcats.bigjoescarservice.mod.Car;
import com.coolcats.bigjoescarservice.util.CarAdapter;

import java.util.LinkedList;
import java.util.List;

public class OutputFragment extends Fragment {

    private OutputFragmentLayoutBinding binding;
    private CarAdapter carAdapter;
    private CarAdapter.CarDelegate carDelegate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = OutputFragmentLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        carAdapter = new CarAdapter(new LinkedList<Car>(), carDelegate);
        binding.outputRecycleView.setAdapter(carAdapter);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(binding.outputRecycleView);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        carDelegate = (CarAdapter.CarDelegate) context;
    }

    public void updateList(List<Car> list){
        carAdapter.updateCarList(list);
    }
}
