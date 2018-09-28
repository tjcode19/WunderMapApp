package com.evaluation.www.wunderappg;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.evaluation.www.wunderappg.adapter.CarAdapter;
import com.evaluation.www.wunderappg.model.CarList;
import com.evaluation.www.wunderappg.model.Placemark;
import com.evaluation.www.wunderappg.remote.ApiServices;
import com.evaluation.www.wunderappg.remote.RecyclerItemListener;
import com.evaluation.www.wunderappg.remote.RetrofitClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Placemark> carList;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView, rV;
    private CarAdapter eAdapter;
    private double coordLat, coordLong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(eLayoutManager);

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        recyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(),
                recyclerView, new RecyclerItemListener.RecyclerTouchListener() {
            @Override
            public void onClickItem(View v, int position) {
                //Toast.makeText(getApplicationContext(), "Clicked: " +
               //         carList.get(position).getCoordinates(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                String carName = carList.get(position).getName();
                String carAddr = carList.get(position).getAddress();
                List<Double> carCoord = carList.get(position).getCoordinates();
                coordLat = carCoord.get(0);
                coordLong = carCoord.get(1);

                intent.putExtra("car_name", carName);
                intent.putExtra("car_addr", carAddr);
                intent.putExtra("car_coordinate_lat", coordLat);
                intent.putExtra("car_coordinate_long", coordLong);
                startActivity(intent);
                finish();
            }

            @Override
            public void onLongClickItem(View v, int position) {
                Toast.makeText(getApplicationContext(), "Long Pressed: " +
                        carList.get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        }));

        getCarData(); //call method to Populate car data
    }

    private void getCarData(){
        //Creating an object of our api interface
        ApiServices api = RetrofitClient.getApiService();

        //Calling JSON
        Call<CarList> call = api.getMyJSON();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<CarList>() {
            @Override
            public void onResponse(Call<CarList> call, Response<CarList> response) {
                //Dismiss Dialog
                pDialog.dismiss();

                if (response.isSuccessful()) {
                    //Got Successfully
                    carList = response.body().getPlacemarks();
                    eAdapter = new CarAdapter(carList);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);
                    eAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<CarList> call, Throwable t) {
                Log.e("log_tag", t.getMessage());
                pDialog.dismiss();
            }
        });

    }
}
