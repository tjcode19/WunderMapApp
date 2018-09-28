package com.evaluation.www.wunderappg.remote;

import com.evaluation.www.wunderappg.model.CarList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("locations.json")
    Call<CarList> getMyJSON();
}
