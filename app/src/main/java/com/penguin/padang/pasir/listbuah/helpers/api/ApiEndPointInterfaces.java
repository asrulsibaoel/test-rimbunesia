package com.penguin.padang.pasir.listbuah.helpers.api;

import com.penguin.padang.pasir.listbuah.models.Fruit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by jowy on 8/11/16.
 */
public interface ApiEndPointInterfaces {

    @GET("test-rimbunesia")
    Call<List<Fruit>> getEmployee();
}
