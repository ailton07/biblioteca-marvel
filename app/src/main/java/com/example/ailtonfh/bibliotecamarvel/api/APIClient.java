package com.example.ailtonfh.bibliotecamarvel.api;

import android.util.Log;
import android.widget.TextView;

import com.example.ailtonfh.bibliotecamarvel.R;
import com.example.ailtonfh.bibliotecamarvel.models.Models;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AiltonFH on 07/06/2016.
 */
public class APIClient {
    final String BASE_URL = "http://gateway.marvel.com:80/";
    private ApiEndpointInterface apiService;
    private Retrofit retrofit;

    private GetHeroesDelegate delegate;

    public ApiEndpointInterface getApiService() {
        return apiService;
    }

    public APIClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public APIClient(GetHeroesDelegate delegate) {
        this.delegate = delegate;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call getCharacters(){

        apiService = retrofit.create(ApiEndpointInterface.class);

        AuthenticationAPI aut = new AuthenticationAPI();
        aut.timeStampReload();
        Call<Models> call = apiService.getCharacters(aut.getTimeStamp(), aut.getPublicKey(), aut.getHash());

        return call;

    }

    public void getHeroes(){

        apiService = retrofit.create(ApiEndpointInterface.class);

        AuthenticationAPI aut = new AuthenticationAPI();
        aut.timeStampReload();

        Call<Models> call = getCharacters();

        call.enqueue(new Callback<Models>() {
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                if (response.isSuccessful()) {

                    delegate.manageHeroes(response);
                }
                else {

                    delegate.manageHeroesError(response);

                }
            }
            @Override
            public void onFailure(Call<Models> call, Throwable t) {
                Log.e("API Error", t.getMessage());

            }

        });

    }

    private void setModels(final TextView view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }



}
