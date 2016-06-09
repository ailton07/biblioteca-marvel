package com.example.ailtonfh.bibliotecamarvel.api;

import com.example.ailtonfh.bibliotecamarvel.heroesModels.Models;
import com.example.ailtonfh.bibliotecamarvel.comicsModels.ComicsModel;


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

    @GET("v1/public/characters/{characterId}/comics")
    Call<ComicsModel> getCharacterComics(@Path("characterId") String characterId, @Query("ts") String ts, @Query("apikey") String apikey ,@Query("hash") String hash, @Query("limit") String limite);
}
