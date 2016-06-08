package com.example.ailtonfh.bibliotecamarvel.api;

import com.example.ailtonfh.bibliotecamarvel.models.Models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AiltonFH on 07/06/2016.
 */
public interface ApiEndpointInterface {

    @GET("v1/public/characters")
    Call<Models> getCharacters(@Query("ts") String ts, @Query("apikey") String apikey ,@Query("hash") String hash);
}
