package com.evaluation.www.wunderappg.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.evaluation.www.wunderappg.R;
import com.evaluation.www.wunderappg.model.Placemark;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CustomViewHolder> {
    private List<Placemark> placemarks;

    public CarAdapter(List<Placemark> placemarks) {
        this.placemarks = placemarks;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cars_lists, parent, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        Placemark car = placemarks.get(position);
        holder.carName.setText(car.getName());
        holder.carAddress.setText(car.getAddress());
        holder.carVin.setText(car.getVin());
        holder.carExt.setText(car.getExterior());
        holder.carInterior.setText(car.getInterior());
        holder.carEngType.setText(car.getEngineType());
        holder.carCoord.setText(String.valueOf(car.getCoordinates()));
        holder.carFuel.setText(String.valueOf(car.getFuel()));
    }

    @Override
    public int getItemCount() {
        return placemarks.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView carName, carAddress, carVin, carExt, carInterior,carEngType,carCoord,carFuel;

        public CustomViewHolder(View view) {
            super(view);
            carName = (TextView) view.findViewById(R.id.car_name);
            carAddress = (TextView) view.findViewById(R.id.car_address);
            carVin = (TextView) view.findViewById(R.id.car_vin);
            carExt = (TextView) view.findViewById(R.id.car_fuel);
            carInterior = (TextView) view.findViewById(R.id.car_intr);
            carEngType = (TextView) view.findViewById(R.id.car_eng_type);
            carCoord = (TextView) view.findViewById(R.id.car_coord);
            carFuel = (TextView) view.findViewById(R.id.car_ext);
        }
    }


}
