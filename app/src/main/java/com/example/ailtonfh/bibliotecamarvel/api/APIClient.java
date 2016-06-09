package com.example.ailtonfh.bibliotecamarvel.api;

import android.util.Log;
import android.widget.TextView;

import com.example.ailtonfh.bibliotecamarvel.comicsModels.ComicsModel;
import com.example.ailtonfh.bibliotecamarvel.heroesModels.Models;

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

    private GetHeroesDelegate heroesDelegate;
    private GetComicsDelegate comicsDelegate;

    public ApiEndpointInterface getApiService() {
        return apiService;
    }

    public APIClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Heroes
    public APIClient(GetHeroesDelegate heroesDelegate) {
        this.heroesDelegate = heroesDelegate;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Call getAPICharacters(){

        apiService = retrofit.create(ApiEndpointInterface.class);

        AuthenticationAPI aut = new AuthenticationAPI();
        aut.timeStampReload();
        Call<Models> call = apiService.getCharacters(aut.getTimeStamp(), aut.getPublicKey(), aut.getHash());

        return call;

    }

    public void getHeroes(){

        apiService = retrofit.create(ApiEndpointInterface.class);

        Call<Models> call = getAPICharacters();

        call.enqueue(new Callback<Models>() {
            @Override
            public void onResponse(Call<Models> call, Response<Models> response) {
                if (response.isSuccessful()) {

                    heroesDelegate.manageHeroes(response);
                }
                else {

                    heroesDelegate.manageHeroesError(response);

                }
            }
            @Override
            public void onFailure(Call<Models> call, Throwable t) {
//                Log.e("API Error", t.getMessage());

            }

        });

    }

    private void setModels(final TextView view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

// Comics

    public APIClient(GetComicsDelegate comicsDelegate) {
        this.comicsDelegate = comicsDelegate;

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Call getAPIComics(String charId){

        apiService = retrofit.create(ApiEndpointInterface.class);

        AuthenticationAPI aut = new AuthenticationAPI();
        aut.timeStampReload();
        Call<ComicsModel> call = apiService.getCharacterComics(charId, aut.getTimeStamp(), aut.getPublicKey(), aut.getHash(), "10");

        return call;

    }

    public void getComics(String charId){

        apiService = retrofit.create(ApiEndpointInterface.class);

        Call<ComicsModel> call = getAPIComics(charId);

        call.enqueue(new Callback<ComicsModel>() {
            @Override
            public void onResponse(Call<ComicsModel> call, Response<ComicsModel> response) {

                if (response.isSuccessful()) {

                    comicsDelegate.manageComics(response);
                }
                else {

                    comicsDelegate.manageComicsError(response);

                }
            }

            @Override
            public void onFailure(Call<ComicsModel> call, Throwable t) {
              //  Log.e("API Error", t.getMessage());

            }

        });

    }



}
